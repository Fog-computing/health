package cn.ac.sec.util;

import org.ibase4j.core.util.HttpUtil;

import java.util.Map;

public class HTTPUtil {
    public static void main(String args[]) {
        String a = HttpUtil.httpClientPost ("http://localhost:8090/app/device/data?startTime=1511610745791&endTime=1513650745791&userId=1&type=PM10");
        System.out.println (JSONUtil.readValue (a, Map.class));
    }
}



