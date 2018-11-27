package cn.ac.sec.util;

import cn.ac.sec.entity.watch.Location;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.util.HttpUtil;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPSUtil {
    private static double EARTH_RADIUS = 6371.0;
    private static double pi = 3.14159265358979324;
    private static double a = 6378245.0;
    private static double ee = 0.00669342162296594323;
    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    private static final String gdKey = "a588087019ac7e5cc6ce4ffa34fdd61a";
    private static final String WIFI = "1";
    private static final String LBS = "0";
    private static final String APID = "d31fa244-d3ea-4fcd-86a5-ec00d1ecda63";
    private static final String KEY = "af12db15-97cd-49d5-9605-c49f437f6b10";
    private static final String params = "mcount$6$460$0$255#11d6|204a|24|57#11d6|e169|40|37#11d6|7f8b|519|32#11d6|2048|33|32#11d6|967a|529|26#10de|21|31|26";

    public static double getDistance(double longitude1, double longitude2,
                                     double latitude1, double latitude2) {
        longitude1 = ConvertDegreesToRadians (longitude1);
        longitude2 = ConvertDegreesToRadians (longitude2);
        latitude1 = ConvertDegreesToRadians (latitude1);
        latitude2 = ConvertDegreesToRadians (latitude2);

        double vLon = Math.abs (longitude1 - longitude2);
        double vLat = Math.abs (latitude1 - latitude2);

        double h = haverSin (vLat) + Math.cos (latitude1) *
                Math.cos (latitude2) * haverSin (vLon);

        double distance = 2 * EARTH_RADIUS * Math.asin (Math.sqrt (h));
        return distance * 1000;
    }

    private static double haverSin(double theta) {
        double v = Math.sin (theta / 2);
        return v * v;
    }

    private static double ConvertDegreesToRadians(double degree) {
        return degree * Math.PI / 180;
    }

    private static double ConvertRadiansToDegrees(double radian) {
        return radian * 180.0 / Math.PI;
    }

//    public static void main(String args[]) {
//        System.out.println (getBaiduPosition (new Point (114.546322D, 38.039763D)));
//
//    }

    public static Map<String, Object> getPosition(String params) {
        List<String> paramList = getStationParamsRaw (params);
//        System.out.println (paramList);
        List<String> stationList = getStationList (paramList.get (paramList.size () - 1));
//        for (int i=1;i<stationList.size ();i++){
//            System.out.println (getStationPramas (stationList.get (i)));
//        }
        List<String> stationParams = getStationPramas (stationList.get (1));
//        System.out.println (stationParams);
        Map<String, Object> postContent = new HashMap<> ();
        String sigOri;
        if (paramList.get (3).equals ("3")) {
            postContent.put ("ap", APID);
            postContent.put ("mcc", paramList.get (2));
            postContent.put ("sid", paramList.get (3));
            postContent.put ("nid", Integer.parseInt (stationParams.get (0), 16));
            postContent.put ("bid", Integer.parseInt (stationParams.get (1), 16));
            sigOri = "ap=" + APID + "&bid=" + postContent.get ("bid") + "&mmc=" + postContent.get ("mmc") + "&nid=" + postContent.get ("nid") + "&sid=" + postContent.get ("sid")
                    + KEY;
        } else {
            postContent.put ("ap", APID);
            postContent.put ("mcc", paramList.get (2));
            postContent.put ("mnc", paramList.get (3));
            postContent.put ("lac", Integer.parseInt (stationParams.get (0), 16));
            postContent.put ("ci", Integer.parseInt (stationParams.get (1), 16));
            sigOri = "ap=" + APID + "&ci=" + postContent.get ("ci") + "&lac=" + postContent.get ("lac") + "&mcc=" + postContent.get ("mcc") + "&mnc=" + postContent.get ("mnc")
                    + KEY;
        }
        String md5 = null;
        try {
//            System.out.println (sigOri);
            md5 = DigestUtils.md5Hex (sigOri);
//            System.out.println (md5);
//            System.out.println (md5.length ());
        } catch (Exception e) {
            e.printStackTrace ();
        }
        postContent.put ("sig", md5.toUpperCase ());
        System.out.println (JSONUtil.toJSon (postContent));
        String result = HttpUtil.post ("http://d.xzhealth.cn/lbs/v1/cell", JSONUtil.toJSon (postContent));
        System.out.println (result);
        return JSONUtil.readValue (result, Map.class);
    }

    private static List<String> getStationParamsRaw(String params) {
        return Arrays.asList (params.split ("\\$"));
    }

    private static List<String> getStationList(String params) {
        return Arrays.asList (params.split ("#"));
    }

    private static List<String> getStationPramas(String params) {
        return Arrays.asList (params.split ("\\|"));
    }


    public static Point getBaiduPosition(Point point) {
        System.out.println (point);
        String requestURL = "http://api.map.baidu.com/geoconv/v1/?coords=" + point.getLongitude () + "," + point.getLatitude () + "&from=1&to=5&ak=Ae3tfplb5qONgk6RLUUuGlGD9NwbwYxw";
        JSONObject jsonObject = HttpRequestUtils.httpGet (requestURL);
        JSONArray result = (JSONArray) jsonObject.get ("result");
        JSONObject resultObj = (JSONObject) result.get (0);
        return new Point (resultObj.getDoubleValue ("x"), resultObj.getDoubleValue ("y"));
    }


    public static Point getWGSPosition(Point point) {
        System.out.println (point);
        String requestURL = "http://api.map.baidu.com/geoconv/v1/?coords=" + point.getLongitude () + "," + point.getLatitude () + "&from=5&to=3&ak=Ae3tfplb5qONgk6RLUUuGlGD9NwbwYxw";
        JSONObject jsonObject = HttpRequestUtils.httpGet (requestURL);
        JSONArray result = (JSONArray) jsonObject.get ("result");
        JSONObject resultObj = (JSONObject) result.get (0);
        Point gcjPoint = new Point (resultObj.getDoubleValue ("x"), resultObj.getDoubleValue ("y"));
        Point wgsPoint = gcj02towgs84 (gcjPoint);
        System.out.println (wgsPoint);
        return wgsPoint;
    }

    public static Point getGDPosition(Location location) {
        String resultString = "";
        if (location.getType () == 3 || location.getType () == 5) {
            StringBuilder requestURL = new StringBuilder ("http://apilocate.amap.com/position?key=" + gdKey + "&accesstype=" + WIFI + "&imei=" + location.getDeviceId ()
                    + "&macs=");
            for (String mac : location.getMacs ()) {
                requestURL.append (URLEncoder.encode (mac + '|'));
            }
            resultString = HttpClientUtil.doGet (requestURL.toString ());
            System.out.println (resultString);

        } else {
            String requestURL = "http://apilocate.amap.com/position?key=" + gdKey + "&accesstype=" + LBS + "&imei=" + location.getDeviceId ()
                    + "&cdma=0&network=EDGE&bts=";
            String bts = location.getMcc () + ',' + location.getMnc () + ',';
            String lbs = getLBSList (location.getNearbts ().get (0));
            bts += lbs;
            System.out.println (bts);
            resultString = HttpClientUtil.doGet (requestURL + URLEncoder.encode (bts));
            System.out.println (resultString);
        }
        JSONObject jsonObject = JSON.parseObject (resultString);
        Point point = new Point ();
        JSONObject result = jsonObject.getJSONObject ("result");
        if(location.getType ()==5&&result.getIntValue ("type")==0){
            String requestURL = "http://apilocate.amap.com/position?key=" + gdKey + "&accesstype=" + LBS + "&imei=" + location.getDeviceId ()
                    + "&cdma=0&network=EDGE&bts=";
            String bts = location.getMcc () + ',' + location.getMnc () + ',';
            String lbs = getLBSList (location.getNearbts ().get (0));
            bts += lbs;
            System.out.println (bts);
            resultString = HttpClientUtil.doGet (requestURL + URLEncoder.encode (bts));
            System.out.println (resultString);
            jsonObject = JSON.parseObject (resultString);
            result = jsonObject.getJSONObject ("result");
        }
        String locationString = result.getString ("location");
        String[] locationArray = locationString.split (",");
        point.setLongitude (Double.parseDouble (locationArray[0]));
        point.setLatitude (Double.parseDouble (locationArray[1]));
        return point;
    }

    public static Point getBaduPositionFromGD(Point point) {
        System.out.println (point);
        String requestURL = "http://api.map.baidu.com/geoconv/v1/?coords=" + point.getLongitude () + "," + point.getLatitude () + "&from=3&to=5&ak=Ae3tfplb5qONgk6RLUUuGlGD9NwbwYxw";
        JSONObject jsonObject = HttpRequestUtils.httpGet (requestURL);
        JSONArray result = (JSONArray) jsonObject.get ("result");
        JSONObject resultObj = (JSONObject) result.get (0);
        return new Point (resultObj.getDoubleValue ("x"), resultObj.getDoubleValue ("y"));
    }

    private static double transformLatitude(double longitude, double latitude) {
        double ret = -100.0 + 2.0 * latitude + 3.0 * longitude + 0.2 * longitude * longitude + 0.1 * latitude * longitude + 0.2 * Math.sqrt (Math.abs (latitude));
        ret += (20.0 * Math.sin (6.0 * latitude * pi) + 20.0 * Math.sin (2.0 * latitude * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin (longitude * pi) + 40.0 * Math.sin (longitude / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin (longitude / 12.0 * pi) + 320 * Math.sin (longitude * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLongitude(double lat, double lon) {
        double ret = 300.0 + lat + 2.0 * lon + 0.1 * lat * lat + 0.1 * lat * lon + 0.1 * Math.sqrt (Math.abs (lat));
        ret += (20.0 * Math.sin (6.0 * lat * pi) + 20.0 * Math.sin (2.0 * lat * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin (lat * pi) + 40.0 * Math.sin (lat / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin (lat / 12.0 * pi) + 300.0 * Math.sin (lat / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static Point wgs2bd(double lat, double lon) {
        Point wgs2gcjR = wgs2gcj (lat, lon);
        Point gcj2bdR = gcj2bd (wgs2gcjR);
        return gcj2bdR;
    }

    private static Point wgs2gcj(double lat, double lon) {
        double dLat = transformLatitude (lon - 105, lat - 35);
        double dLon = transformLongitude (lon - 105, lat - 35);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin (radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt (magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos (radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        Point point = new Point ();
        point.setLatitude (mgLat);
        point.setLongitude (mgLon);
        return point;
    }

    private static Point gcj2bd(Point point) {
        double x = point.getLongitude (), y = point.getLatitude ();
        double z = Math.sqrt (x * x + y * y) + 0.00002 * Math.sin (y * x_pi);
        double theta = Math.atan2 (y, x) + 0.000003 * Math.cos (x * x_pi);
        double bd_lon = z * Math.cos (theta) + 0.0065;
        double bd_lat = z * Math.sin (theta) + 0.006;
        Point bdPoint = new Point ();
        bdPoint.setLongitude (bd_lon);
        bdPoint.setLatitude (bd_lat);
        return bdPoint;
    }

    private static Point gcj02towgs84(Point point) {
            Double dlat = transformLatitude (point.getLongitude () - 105.0, point.getLatitude () - 35.0);
            Double dlng = transformLongitude (point.getLongitude () - 105.0, point.getLatitude () - 35.0);
            Double radlat = point.getLatitude () / 180.0 * pi;
            Double magic = Math.sin(radlat);
            magic = 1 - ee * magic * magic;
            Double sqrtmagic = Math.sqrt(magic);
            dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * pi);
            dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * pi);
            Double wgsLat = point.getLatitude () + dlat;
            Double wgsLng = point.getLongitude () + dlng;
            return new Point (point.getLongitude ()*2-wgsLng,point.getLatitude ()*2-wgsLat);
    }

    /* 11d6|204a|0|65443 */
    private static String getLBSList(String string) {
        List<String> result = Arrays.asList (string.split ("\\|"));
        String resultString = Integer.parseInt (result.get (0), 16) + "," +
                Integer.parseInt (result.get (1), 16) + "," + result.get (3);
        System.out.println (resultString);
        return resultString;
    }

    public static void main(String args[]) {
        getLBSList ("11d6|204a|0|65443");
    }
}


