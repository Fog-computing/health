package cn.ac.sec.protocol.message;

public class InMessage extends Message {
    //登录对时
    public static final int LOGIN = 1;
    //客户端心跳包
    public static final int HEARTBEAT=2;
    //实时心率监测
//    public static int HR="T5";
    //心率数据
    public static final int HR=28;
    //步数数据
    public static final int STEPS=30;
    //血压数据
    public static final int BP=45;
    //GPS定位
    public static final int GPS=53;
    //血糖数据
    public static final int BS=56;
    //血压带时间
    public static final int BSWITHTIME = 63;
    //血糖带时间
    public static final int BPWITHTIME = 62;
    //手表低电报警
    public static final int LOWPOWER=82;
    //多基站定位
    public static final int MULTIBS=86;
    //久坐提醒通知
    public static final int SITELONG=88;
    //SOS警报
    public static final int SOS=89;
    //睡眠质量
    public static final int SLEEPINFO=90;
    //睡眠质量详细
    public static final int SLEPPINFOMORE=91;
    //多种定位信息
    public static final int MULTIPOSITION = 94;
    //历史消息
    public static final int HISTORY = 10;
    //低电报警
    public InMessage(){

    }
}
