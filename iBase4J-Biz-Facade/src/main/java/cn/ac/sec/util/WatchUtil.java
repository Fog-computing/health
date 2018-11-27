package cn.ac.sec.util;

import cn.ac.sec.entity.BizDataWatch;
import cn.ac.sec.entity.messure.BloodOxygen;
import cn.ac.sec.entity.messure.BloodPressure;
import cn.ac.sec.entity.messure.BloodSugar;
import cn.ac.sec.entity.messure.HeartRateBase;

import java.util.List;

public class WatchUtil {

    public static BloodPressure getBloodPresureFromWatchData(BizDataWatch dataWatch){
        List<String> paramList;
        BloodPressure bloodPressure = new BloodPressure ();
        paramList=JSONUtil.readListValue (dataWatch.getData (),String.class);
        bloodPressure.setHighPressure (Integer.parseInt (paramList.get (1)));
        bloodPressure.setLowPressure (Integer.parseInt (paramList.get (2)));
        bloodPressure.setHeartRate (Integer.parseInt (paramList.get (3)));
        bloodPressure.setMeasureDate (dataWatch.getDatetimeMeasure ());
        return bloodPressure;
    }

    public static BloodSugar getBloodSugerFromWatchData(BizDataWatch dataWatch){
        List<String> paramList;
        paramList=JSONUtil.readListValue (dataWatch.getData (),String.class);
        BloodSugar bloodSugar=new BloodSugar ();
        return bloodSugar;
    }

    public static HeartRateBase getHeartRateBaseFromWatchData(BizDataWatch dataWatch){
        List<String> paramList;
        paramList=JSONUtil.readListValue (dataWatch.getData (),String.class);
        HeartRateBase heartRateBase=new HeartRateBase ();

        return heartRateBase;
    }}
