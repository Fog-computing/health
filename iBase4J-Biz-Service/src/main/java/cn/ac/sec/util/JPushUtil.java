package cn.ac.sec.util;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPushUtil {
    protected static final Logger LOG = LoggerFactory.getLogger(JPushUtil.class);

    private static final String APP_KEY= "83e95cccaf9ce5b160062355";
    private static final String MASTER_SECRET= "979e1cd89aab8b6b1d1dc2ad";

    public static void main(String args[])
    {
        testDefaultClient ();
        testCustomPushClient ();
        testCustomClient ();
//        testSendPush ();
    }

//    public static void testSendPush() {
//        ClientConfig clientConfig = ClientConfig.getInstance();
//        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
//        // Here you can use NativeHttpClient or NettyHttpClient or ApacheHttpClient.
//        // Call setHttpClient to set httpClient,
//        // If you don't invoke this method, default httpClient will use NativeHttpClient.
////        ApacheHttpClient httpClient = new ApacheHttpClient(authCode, null, clientConfig);
////        jpushClient.getPushClient().setHttpClient(httpClient);
////        final PushPayload payload = PushExample.buildPushObject_android_and_ios();
////        // For push, all you need do is to build PushPayload object.
////        PushPayload payload = buildPushObject_all_alias_alert();
//        try {
//            PushResult result = jpushClient.sendPush(payload);
//            LOG.info("Got result - " + result);
//            System.out.println(result);
//            // 如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
//            // If uses NettyHttpClient, call close when finished sending request, otherwise process will not exit.
//            // jpushClient.close();
//        } catch (APIConnectionException e) {
//            LOG.error("Connection error. Should retry later. ", e);
//            LOG.error("Sendno: " + payload.getSendno());
//
//        } catch (APIRequestException e) {
//            LOG.error("Error response from JPush server. Should review and fix it. ", e);
//            LOG.info("HTTP Status: " + e.getStatus());
//            LOG.info("Error Code: " + e.getErrorCode());
//            LOG.info("Error Message: " + e.getErrorMessage());
//            LOG.info("Msg ID: " + e.getMsgId());
//            LOG.error("Sendno: " + payload.getSendno());
//        }
//    }

    public static void testDefaultClient() {
        JPushClient client = new JPushClient(MASTER_SECRET, APP_KEY);

        //	JPushClient client1 = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
    }

    public static void testCustomClient() {
        ClientConfig config = ClientConfig.getInstance();
        config.setMaxRetryTimes(5);
        config.setConnectionTimeout(10 * 1000);	// 10 seconds
        config.setSSLVersion("TLSv1.1");		// JPush server supports SSLv3, TLSv1, TLSv1.1, TLSv1.2

        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);
    }

    public static void testCustomPushClient() {
        ClientConfig config = ClientConfig.getInstance();

        config.setApnsProduction(false); 	// development env
        config.setTimeToLive(60 * 60 * 24); // one day

        //	config.setGlobalPushSetting(false, 60 * 60 * 24); // development env, one day

        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config); 	// JPush client
        //	PushClient pushClient = new PushClient(masterSecret, appKey, null, config); 	// push client only
    }


}
