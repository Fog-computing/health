package cn.ac.sec.sevice;


import cn.ac.sec.dao.BizDeviceMapper;
import cn.ac.sec.dao.BizDeviceTypeMapper;
import cn.ac.sec.entity.BizDevice;
import cn.ac.sec.entity.BizDeviceExample;
import cn.ac.sec.entity.BizDeviceType;
import cn.ac.sec.entity.BizDeviceTypeExample;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.util.DateUtil;
import org.ibase4j.model.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class DeviceService extends BaseService {
    private static final String DATA_TABLE_PREFIX = "biz_data_";
    @Autowired
    private BizDeviceMapper deviceMapper;
    @Autowired
    private BizDeviceTypeMapper typeMapper;

    @Autowired
    private WatchService watchService;

    /**
     * device type start
     **/

    public List<BizDeviceType> queryDeviceTypeList() {
        return typeMapper.selectByExample(new BizDeviceTypeExample());
    }

    public int getTypeIdByTypeCode(String typecode) {
        BizDeviceTypeExample example = new BizDeviceTypeExample();
        example.createCriteria().andTypeCodeEqualTo(typecode);
        return typeMapper.selectByExample(example).get(0).getId();
    }

    public BizDeviceType queryDeviceTypeById(Byte id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    public int insertDeviceType(BizDeviceType deviceType) {
        int result = typeMapper.insertSelective(deviceType);
        String tableName = "" + deviceType.getId();
        System.out.println(deviceType.getId());
        deviceMapper.createNewDataTable(tableName);
        return result;
    }

    public int updateDeviceType(BizDeviceType deviceType) {
        return typeMapper.updateByPrimaryKeySelective(deviceType);
    }


    public int deleteDeviceType(Byte id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    /**
     * device type end
     **/

    public List<BizDevice> getDeviceList() {
        return deviceMapper.selectByExample(new BizDeviceExample());
    }

    public List<Map<String, Object>> getDeviceListByUser(Long userId) {
        BizDeviceExample example = new BizDeviceExample();
        example.createCriteria().andUserEqualTo(userId);
        List<Map<String, Object>> list = deviceMapper.deviceByUserId(userId);
        return list;
    }

    public int insertDevice(BizDevice device) {
        if (device.getDeviceType().startsWith("watch")) {
            device.setImei(watchService.getWatchByUser(device.getUser()).getImei());
            String deviceCode = String.valueOf(Math.toIntExact(device.getImei() % 10000000));
            device.setDeviceCode(deviceCode);
        }
        return deviceMapper.insertSelective(device);
    }

    public List<BizDevice> getDeviceListByType(String type) {
        BizDeviceExample example = new BizDeviceExample();
        example.createCriteria().andDeviceTypeEqualTo(type);
        return deviceMapper.selectByExample(example);
    }

    public Page<Map<String, Object>> getDeviceList(PageTransport transport) {
        Page<Map<String, Object>> pager;
        pager = getPage(transport);
        PageHelper.startPage(pager.getCurrent(), pager.getSize());
        List<Map<String, Object>> devices = deviceMapper.deviceWithUserAndType("%" + transport.getKeyword() + "%");
        pager.setRecords(devices);
        pager.setTotal(devices.size());
        pager.setTotal((int) ((com.github.pagehelper.Page) devices).getTotal());
        return pager;
    }

    public List<BizDevice> getDeviceListOri(Long userId) {
        BizDeviceExample example = new BizDeviceExample();
        example.createCriteria().andUserEqualTo(userId);
        return deviceMapper.selectByExample(example);
    }

    public int deleteDevice(BizDevice device) {
        return deviceMapper.deleteByPrimaryKey(device.getId());
    }

    public int deleteDeviceById(Long id) {
        return deviceMapper.deleteByPrimaryKey(id);
    }

    public BizDevice getDeviceById(Long id) {
        return deviceMapper.selectByPrimaryKey(id);
    }

    public int updateDevice(BizDevice device) {
        return deviceMapper.updateByPrimaryKeySelective(device);
    }

    /**
     * 按月方式统计设备数量
     */
    public List<ChartData> getRegisterCountByMonth(List<String> list) {
        return deviceMapper.getRegisterCountByMonth(list.get(0), list.get(1));
    }

    /**
     * 按周方式统计设备数量
     * 星期由周一开始
     */
    public List<ChartData> getRegisterCountByWeek(List<String> list) {
        return deviceMapper.getRegisterCountByWeek(list.get(0), list.get(1));
    }

    /**
     * 按日方式统计设备数量
     */
    public List<ChartData> getRegisterCountByDay(List<String> list) {
        return deviceMapper.getRegisterCountByDay(list.get(0), list.get(1), list.get(2));
    }

    /**
     * 按月方式统计设备使用次数
     */
    public List<ChartData> getUseDeviceCountByMonth(List<String> list) {
        if (list.get(1).equals("watch_bp")) {
            return deviceMapper.getUseWatch_bpCountByMonth(list.get(0));
        } else if (list.get(1).equals("watch_bs")) {
            return deviceMapper.getUseWatch_bsCountByMonth(list.get(0));
        } else if (list.get(1).equals("PM10")) {
            return deviceMapper.getUsePM10CountByMonth(list.get(0));
        } else if (list.get(1).equals("SpO208")) {
            return deviceMapper.getUseSpO208CountByMonth(list.get(0));
        } else if (list.get(1).equals("BG01")) {
            return deviceMapper.getUseBG01CountByMonth(list.get(0));
        } else if (list.get(1).equals("BC01")) {
            return deviceMapper.getUseBC01CountByMonth(list.get(0));
        } else if (list.get(1).equals("NIBP04")) {
            return deviceMapper.getUseNIBP04CountByMonth(list.get(0));
        } else if (list.get(1).equals("SWAN")) {
            return deviceMapper.getUseSWANCountByMonth(list.get(0));
        } else if (list.get(1).equals("watch_hr")) {
            return deviceMapper.getUseWatch_hrCountByMonth(list.get(0));
        } else if (list.get(1).equals("lay_down")) {
            return deviceMapper.getUseLay_downCountByMonth(list.get(0));
        } else {
            return null;
        }

    }

    /**
     * 按月方式统计设备使用次数
     */
    public List<ChartData> getUseDeviceCountByWeek(List<String> list) {
        if (list.get(1).equals("watch_bp")) {
            return deviceMapper.getUseWatch_bpCountByWeek(list.get(0));
        } else if (list.get(1).equals("watch_bs")) {
            return deviceMapper.getUseWatch_bsCountByWeek(list.get(0));
        } else if (list.get(1).equals("PM10")) {
            return deviceMapper.getUsePM10CountByWeek(list.get(0));
        } else if (list.get(1).equals("SpO208")) {
            return deviceMapper.getUseSpO208CountByWeek(list.get(0));
        } else if (list.get(1).equals("BG01")) {
            return deviceMapper.getUseBG01CountByWeek(list.get(0));
        } else if (list.get(1).equals("BC01")) {
            return deviceMapper.getUseBC01CountByWeek(list.get(0));
        } else if (list.get(1).equals("NIBP04")) {
            return deviceMapper.getUseNIBP04CountByWeek(list.get(0));
        } else if (list.get(1).equals("SWAN")) {
            return deviceMapper.getUseSWANCountByWeek(list.get(0));
        } else if (list.get(1).equals("watch_hr")) {
            return deviceMapper.getUseWatch_hrCountByWeek(list.get(0));
        } else if (list.get(1).equals("lay_down")) {
            return deviceMapper.getUseLay_downCountByWeek(list.get(0));
        } else {
            return null;
        }
    }

    /**
     * 按月方式统计设备使用次数
     */
    public List<ChartData> getUseDeviceCountByDay(List<String> list) {
        if (list.get(2).equals("watch_bp")) {
            return deviceMapper.getUseWatch_bpCountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("watch_bs")) {
            return deviceMapper.getUseWatch_bsCountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("PM10")) {
            return deviceMapper.getUsePM10CountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("SpO208")) {
            return deviceMapper.getUseSpO208CountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("BG01")) {
            return deviceMapper.getUseBG01CountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("BC01")) {
            return deviceMapper.getUseBC01CountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("NIBP04")) {
            return deviceMapper.getUseNIBP04CountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("SWAN")) {
            return deviceMapper.getUseSWANCountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("watch_hr")) {
            return deviceMapper.getUseWatch_hrCountByDay(list.get(0),list.get(1));
        } else if (list.get(2).equals("lay_down")) {
            return deviceMapper.getUseLay_downCountByDay(list.get(0),list.get(1));
        } else {
            return null;
        }
    }

    /**
     * 查询每月所有设备使用的总次数
     */
    public List<Integer> getAllUseDeviceCountByMonth(String time){
        List<Integer> list =new ArrayList<>();
        for (int i=0;i<12;i++){
            int data = deviceMapper.getAllWatch_bpDataByMonth(time,i+1)+
                       deviceMapper.getAllWatch_bsDataByMonth(time,i+1)+
                       deviceMapper.getAllPM10DataByMonth(time,i+1)+
                       deviceMapper.getAllSpO208DataByMonth(time,i+1)+
                       deviceMapper.getAllBG01DataByMonth(time,i+1)+
                       deviceMapper.getAllBC01DataByMonth(time,i+1)+
                       deviceMapper.getAllNIBP04DataByMonth(time,i+1)+
                       deviceMapper.getAllSWANDataByMonth(time,i+1)+
                       deviceMapper.getAllWatch_hrDataByMonth(time,i+1)+
                       deviceMapper.getAllLay_downDataByMonth(time,i+1);
            list.add(data);
        }

        return  list;
    }

    /**
     * 查询每周所有设备使用的总次数
     */
    public List<Integer> getAllUseDeviceCountByWeek(String time){
        List<Integer> list =new ArrayList<>();
        for (int i=0;i<53;i++){
            int data = deviceMapper.getAllWatch_bpDataByWeek(time,i+1)+
                    deviceMapper.getAllWatch_bsDataByWeek(time,i+1)+
                    deviceMapper.getAllPM10DataByWeek(time,i+1)+
                    deviceMapper.getAllSpO208DataByWeek(time,i+1)+
                    deviceMapper.getAllBG01DataByWeek(time,i+1)+
                    deviceMapper.getAllBC01DataByWeek(time,i+1)+
                    deviceMapper.getAllNIBP04DataByWeek(time,i+1)+
                    deviceMapper.getAllSWANDataByWeek(time,i+1)+
                    deviceMapper.getAllWatch_hrDataByWeek(time,i+1)+
                    deviceMapper.getAllLay_downDataByWeek(time,i+1);
            list.add(data);
        }

        return  list;
    }

    /**
     * 查询每日所有设备使用的总次数
     */
    public List<Integer> getAllUseDeviceCountByDay(List<String> list){
        int year = Integer.parseInt(list.get(0));
        int month = Integer.parseInt(list.get(1));
        List<Integer> device =new ArrayList<>();
        for (int i = 0; i< DateUtil.getDaysByYearMonth(year, month); i++){
            int data = deviceMapper.getAllWatch_bpDataByDay(year,month,i+1)+
                       deviceMapper.getAllWatch_bsDataByDay(year,month,i+1)+
                       deviceMapper.getAllPM10DataByDay(year,month,i+1)+
                       deviceMapper.getAllSpO208DataByDay(year,month,i+1)+
                       deviceMapper.getAllBG01DataByDay(year,month,i+1)+
                       deviceMapper.getAllBC01DataByDay(year,month,i+1)+
                       deviceMapper.getAllNIBP04DataByDay(year,month,i+1)+
                       deviceMapper.getAllSWANDataByDay(year,month,i+1)+
                       deviceMapper.getAllWatch_hrDataByDay(year,month,i+1)+
                       deviceMapper.getAllLay_downDataByDay(year,month,i+1);
            device.add(data);
        }

        return  device;
    }
}


