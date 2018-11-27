package cn.ac.sec.sevice;


import cn.ac.sec.dao.BizDeviceDataMapper;
import cn.ac.sec.entity.BizDeviceData;
import cn.ac.sec.entity.BizDeviceDataExample;
import cn.ac.sec.entity.BizMessageAlarm;
import cn.ac.sec.entity.messure.*;
import cn.ac.sec.entity.transport.DataTransport;
import cn.ac.sec.util.CommonUtil;
import cn.ac.sec.util.JSONUtil;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class DeviceDataService {
    private static final String DATA_TABLE_PREFIX = "biz_data_";
    @Autowired
    DeviceService deviceService;

    @Autowired
    BizDeviceDataMapper dataMapper;

    @Autowired
    BizUserService userService;


    @Autowired
    AlarmService alarmService;

    public void insertMessageData(BizDeviceData data) {
        dataMapper.insert (data);
    }

    public <T extends Base> void insertDataFromTransport(DataTransport<T> transport) {
        List<T> dataList = transport.getData ();
        List<BizDeviceData> saveList = new ArrayList<> ();
        BizDeviceData data = new BizDeviceData ();
        String tableName = DATA_TABLE_PREFIX + deviceService.getTypeIdByTypeCode (transport.getType ());
        data.setTableName (tableName);
        data.setUserId (transport.getUserId ());

        for (T aDataList : dataList) {
            data.setDatetimeMeasure (aDataList.getMeasureDate ());
            data.setDatetimeUpdate (transport.getUpdateTime ());
            data.setData (JSONUtil.toJSon (aDataList));
            saveList.add (data);
            insertMessageData (data);
            //心电异常报警
            if (aDataList instanceof HeartRate) {
                HeartRate heartRate = (HeartRate) aDataList;
                if ((heartRate.getAnalysis () != null && (heartRate.getAnalysis ().size () != 1 || heartRate.getAnalysis ().get (0) != 0))
                        || (heartRate.getAnalysisAndroid () != null && (!heartRate.getAnalysisAndroid ().get (0).equals ("未见异常") || heartRate.getAnalysisAndroid ().size () != 1)))
                    alarmService.addAlarm (new BizMessageAlarm (data.getUserId (), (byte) 7,
                            aDataList.getMeasureDate (), transport.getLatitude (), transport.getLongitude ()));
            }
            //心率异常报警
            if (aDataList instanceof HeartRateBase) {
                HeartRateBase heartRate = (HeartRateBase) aDataList;
                if (heartRate.getHeartRate () > HeartRateBase.HIGH
                        || heartRate.getHeartRate () < HeartRateBase.LOW)
                    alarmService.addAlarm (new BizMessageAlarm (data.getUserId (), (byte) 6,
                            aDataList.getMeasureDate (), transport.getLatitude (), transport.getLongitude ()));
            }
            //血氧异常报警
            if (aDataList instanceof BloodOxygen) {
                BloodOxygen bloodOxygen = (BloodOxygen) aDataList;
                if (bloodOxygen.getOxygen () < BloodOxygen.OXYGEN_L)
                    alarmService.addAlarm (new BizMessageAlarm (data.getUserId (), (byte) 9,
                            aDataList.getMeasureDate (), transport.getLatitude (), transport.getLongitude ()));
            }
            //血糖异常报警
            if (aDataList instanceof BloodSugar) {
                BloodSugar bloodSugar = (BloodSugar) aDataList;
                if ((bloodSugar.getBloodSugarValue () > BloodSugar.BS_H && bloodSugar.getBloodSugarValue () < BloodSugar.DIABETES)
                        || bloodSugar.getBloodSugarValue () < BloodSugar.BS_L)
                    alarmService.addAlarm (new BizMessageAlarm (data.getUserId (), (byte) 8,
                            aDataList.getMeasureDate (), transport.getLatitude (), transport.getLongitude ()));
                else if (bloodSugar.getBloodSugarValue () > BloodSugar.DIABETES)
                    alarmService.addAlarm (new BizMessageAlarm (data.getUserId (), (byte) 13,
                            aDataList.getMeasureDate (), transport.getLatitude (), transport.getLongitude ()));
            }
            //血压异常报警
            if (aDataList instanceof BloodPressure) {
                BloodPressure bloodPressure = (BloodPressure) aDataList;
                if (bloodPressure.getHighPressure () > BloodPressure.HIGH_H
                        || bloodPressure.getHighPressure () < BloodPressure.HIGH_L
                        || bloodPressure.getLowPressure () > BloodPressure.LOW_H
                        || bloodPressure.getLowPressure () < BloodPressure.LOW_L)
                    alarmService.addAlarm (new BizMessageAlarm (data.getUserId (), (byte) 11,
                            aDataList.getMeasureDate (), transport.getLatitude (), transport.getLongitude ()));
            }
            //肥胖警告
            if (aDataList instanceof BodyFat) {
                Boolean gender = userService.getUserById (data.getUserId ()).getGender ();
                BodyFat bodyFat = (BodyFat) aDataList;
                if ((gender && bodyFat.getBodyFat () > BodyFat.FEMALEHIGH) || (!gender && bodyFat.getBodyFat () > BodyFat.MALEHIGH))
                    alarmService.addAlarm (new BizMessageAlarm (data.getUserId (), (byte) 12,
                            aDataList.getMeasureDate (), transport.getLatitude (), transport.getLongitude ()));
            }

        }
    }

    public Base getLatestDataByUser(Long userId, String typeCode) {
        Integer typeId = deviceService.getTypeIdByTypeCode (typeCode);
        String tableName = DATA_TABLE_PREFIX + typeId;
        String dataString = dataMapper.selectLatestByUser (userId, tableName);
        if (dataString == null || dataString.equals ("")) throw new BusinessException ("没有数据");
        Base base = getBaseFromString (dataString, typeId);
        if (base instanceof HeartRate) {
            HeartRate hr = (HeartRate) base;
            hr.setData (null);
        }
        return base;
    }


    private Base getBaseFromString(String dataString, Integer typeId) {
        switch (typeId) {
            case 1:
            case 13:
                return JSONUtil.readValue (dataString, BloodPressure.class);
            case 2:
            case 11:
                return JSONUtil.readValue (dataString, BloodSugar.class);
            case 9:
                HeartRate heartRate = JSONUtil.readValue (dataString, HeartRate.class);
                getHRStringList (heartRate);
                return heartRate;
            case 10:
                return JSONUtil.readValue (dataString, BloodOxygen.class);
            case 12:
                return JSONUtil.readValue (dataString, Urinalysis.class);
            case 14:
                return JSONUtil.readValue (dataString, BodyFat.class);
            case 15:
                return JSONUtil.readValue (dataString, HeartRateBase.class);
        }
        return null;
    }

    public Base getDataById(Long id, String typeCode) {
        Integer typeId = deviceService.getTypeIdByTypeCode (typeCode);
        String tableName = DATA_TABLE_PREFIX + typeId;
        String dataString = dataMapper.selectByPrimaryKey (id, tableName);
        return getBaseFromString (dataString, typeId);
    }

    public Base getDataByTime(Long userId, Date time, String typeCode) {
        Integer typeId = deviceService.getTypeIdByTypeCode (typeCode);
        String tableName = DATA_TABLE_PREFIX + typeId;
        String dataString = dataMapper.selectByTime (userId, time, tableName);
        if (dataString == null || dataString.equals ("")) throw new BusinessException ("没有数据");
        return getBaseFromString (dataString, typeId);
    }

    public List<HeartRate> getHeartRate(Long userId, Date startTime, Date endTime) {
        String tableName = DATA_TABLE_PREFIX + 9;
        BizDeviceDataExample deviceDataExample = new BizDeviceDataExample ();
        deviceDataExample.setTableName (tableName);
        deviceDataExample.setOrderByClause ("datetime_measure desc");
        deviceDataExample.createCriteria ()
                .andDatetimeMeasureBetween (startTime, CommonUtil.getEndDay (endTime))
                .andUserIdEqualTo (userId);
        List<BizDeviceData> dataList = dataMapper.selectByExample (deviceDataExample);
        if (dataList.size () == 0) return null;
        List<HeartRate> resultList = new ArrayList<> ();
        for (BizDeviceData data : dataList) {
            HeartRate heartRate = JSONUtil.readValue (data.getData (), HeartRate.class);
            heartRate.setData (null);
            resultList.add (heartRate);
        }
        return resultList;
    }

    public List<Base> getDataByUser(Long userId, Date startTime, Date endTime, String typeCode) {
        Integer typeId = deviceService.getTypeIdByTypeCode (typeCode);
        String tableName = DATA_TABLE_PREFIX + typeId;
        BizDeviceDataExample deviceDataExample = new BizDeviceDataExample ();
        deviceDataExample.setTableName (tableName);
        deviceDataExample.setOrderByClause ("datetime_measure desc");
        deviceDataExample.createCriteria ()
                .andDatetimeMeasureBetween (startTime, CommonUtil.getEndDay (endTime))
                .andUserIdEqualTo (userId);
        List<BizDeviceData> dataList = dataMapper.selectByExample (deviceDataExample);
        if (dataList == null || dataList.size () == 0) throw new BusinessException ("没有数据");
        List<Base> resultList = new ArrayList<> ();
        for (BizDeviceData data : dataList) {
            String detailString = data.getData ();
            switch (typeId) {
                case 1:
                case 13:
                    resultList.add (JSONUtil.readValue (detailString, BloodPressure.class));
                    break;
                case 2:
                case 11:
                    resultList.add (JSONUtil.readValue (detailString, BloodSugar.class));
                    break;
                case 9:
                    HeartRate heartRate = JSONUtil.readValue (detailString, HeartRate.class);
                    heartRate.setData (null);
                    getHRStringList (heartRate);
                    resultList.add (heartRate);
                    break;
                case 10:
                    resultList.add (JSONUtil.readValue (detailString, BloodOxygen.class));
                    break;
                case 12:
                    resultList.add (JSONUtil.readValue (detailString, Urinalysis.class));
                    break;
                case 14:
                    resultList.add (JSONUtil.readValue (detailString, BodyFat.class));
                    break;
                case 15:
                    resultList.add (JSONUtil.readValue (detailString, HeartRateBase.class));
                    break;
            }
        }
        return resultList;
    }

    private void getHRStringList(HeartRate heartRate) {
        if (heartRate.getAnalysisAndroid () == null) {
            List<String> androidList = new ArrayList<> ();
            for (Integer integer : heartRate.getAnalysis ()) {
                switch (integer) {
                    case 0:
                        androidList.add ("未见异常");
                        break;
                    case 1:
                        androidList.add ("漏搏");
                        break;
                    case 2:
                        androidList.add ("偶发室早");
                        break;
                    case 3:
                        androidList.add ("室早三联律");
                        break;
                    case 4:
                        androidList.add ("室早二联律");
                        break;
                    case 5:
                        androidList.add ("成对室早");
                        break;
                    case 6:
                        androidList.add ("室早三连发");
                        break;
                    case 7:
                        androidList.add ("室早四连发");
                        break;
                    case 8:
                        androidList.add ("室早RonT");
                        break;
                    case 9:
                        androidList.add ("心动过缓");
                        break;
                    case 10:
                        androidList.add ("心动过速");
                        break;
                    case 11:
                        androidList.add ("心律不齐");
                        break;
                    case 12:
                        androidList.add ("ST抬高");
                        break;
                    case 13:
                        androidList.add ("ST压低");
                        break;
                }
            }
            heartRate.setAnalysisAndroid (androidList);
        }

    }


    public void getSleepTime() {

    }
}
