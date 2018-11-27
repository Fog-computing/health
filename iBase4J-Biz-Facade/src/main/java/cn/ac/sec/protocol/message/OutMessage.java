package cn.ac.sec.protocol.message;

import java.util.Date;

public class OutMessage extends Message {

    public OutMessage(Long imei){
        this.IMEI=imei;
        this.identifier = imei.toString ().substring (0, 1);
        this.messageTime=new Date ();
        this.version="V1.0.0";
    }
    public OutMessage(Message inMessage){
        this.identifier=inMessage.getIdentifier ();
        this.IMEI=inMessage.getIMEI ();
        this.messageTime=new Date ();
        this.type=inMessage.getType ();
        this.version=inMessage.getVersion ();
    }
    //绑定 无用
    public static final int BIND= 0;
    //实时心率检测
    public static final int HRDETECT = 5;
    //静音时间见设置 暂时没有用到
    public static final int SILENCE= 9;
    //实时定位检测
    public static final int POSITIONDETECT=13;
    //定位分段定时设置
    public static final int POSITIONSCHEDULE=29;
    //心率报警设置
    public static final int HRRATE=39;
    //SOS号码详情设置
    public static final int SOSNUMDETAIL=69;
    //快速拨号号码设置
    public static final int QUICKNUM=70;
    //SOS号码设置 失效
    public static final int SOSNUM=71;
    //睡眠检测设置 存在问题
    public static final int SLEEPSET=75;
    //个人参数设置
    public static final int INFOSET=77;
    //记步分段定时设置
    public static final int STEPSET=78;
    //久坐提醒开关
    public static final int SITSET=87;
    //心率定时检测
    public static final int HRSCHEDULE=8;
    //设置提醒
    public static final int REMIND=83;
    //删除提醒
    public static final int DE_REMIND=92;
}
