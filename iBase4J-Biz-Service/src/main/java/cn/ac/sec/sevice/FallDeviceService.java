package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizFallDeviceMapper;
import cn.ac.sec.entity.BizFallDevice;
import cn.ac.sec.entity.BizFallDeviceExample;
import cn.ac.sec.entity.BizMessageAlarm;
import cn.ac.sec.entity.BizUserRelationPhoneNum;
import cn.ac.sec.server.NettyTCPServer;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FallDeviceService {
    @Autowired
    private BizFallDeviceMapper deviceMapper;

    @Autowired
    private BizUserService userService;

    @Autowired
    private UserRelationPhoneNumService phoneNumService;

    @Autowired
    private AlarmService alarmService;

    public int updateLatestLogin(BizFallDevice fallDevice){
        BizFallDeviceExample example = new BizFallDeviceExample ();
        example.createCriteria ().andImeiEqualTo (fallDevice.getImei ());
        return deviceMapper.updateByExampleSelective (fallDevice,example);
    }

    public int addFallAlarm(BizMessageAlarm alarm){
        return alarmService.addAlarm(alarm);
    }

    public int addFallDevice(BizFallDevice bizFallDevice){
        return deviceMapper.insertSelective (bizFallDevice);
    }

    public int updateUserFallDevice(BizFallDevice bizFallDevice) {
        return deviceMapper.updateByPrimaryKeySelective (bizFallDevice);
    }
    public BizFallDevice getFallDeviceByUser(Long userId){
        BizFallDeviceExample example= new BizFallDeviceExample ();
        example.createCriteria ().andUserEqualTo (userId);
        List<BizFallDevice> deviceList = deviceMapper.selectByExample (example);
        if(deviceList!=null&&deviceList.size ()!=0)return deviceList.get (0);
        else return null;
    }

    public BizFallDevice getDeviceByIMEI(String imei){
        BizFallDeviceExample example =new BizFallDeviceExample ();
        example.createCriteria ().andImeiEqualTo (imei);
        List<BizFallDevice> deviceList = deviceMapper.selectByExample (example);
        if(deviceList!=null&&deviceList.size ()!=0)return deviceList.get (0);
        else return null;
    }

    public int setRelationPhoneNum(String imei){
        BizFallDevice device=getDeviceByIMEI (imei);
        if(device.getUpdateStatus ().equals ((byte)1)){
            List<BizUserRelationPhoneNum> phoneNumList= phoneNumService.getPhoneNumListByUserId (device.getUser ());
            StringBuilder builder=new StringBuilder ();
            builder.append ("+CONFIG:");
            builder.append (imei+",");
            builder.append (device.getBindPhoneNum ()+",");
            builder.append ("00,");
            int count=0;
            for (BizUserRelationPhoneNum phoneNum:phoneNumList){
                count+=1;
                builder.append (phoneNum.getPhoneNum ()+",");
                if(count==4)break;
            }
            for (int i=count;i<4;i++){
                builder.append ("00000000000,");
            }
            builder.append ("00:00,00:00,00:00,05\r\n");
            System.out.println (builder.toString ());
            Channel channel = NettyTCPServer.getClient_map ().get (Long.parseLong (imei));
            channel.write (builder.toString ());
        }
        return 0;
    }

    public int setBindPhoneNum(String imei) {
        BizFallDevice device = getDeviceByIMEI (imei);
        if (device.getUpdateStatus ().equals ((byte) 1)) return 0;
        setRelationPhoneNum (imei);
        String stringBuilder = "+COMMAND:KBD," +
                device.getBindPhoneNum () +
                "\r\n";
        Channel channel = NettyTCPServer.getClient_map ().get (Long.parseLong (imei));
        channel.write (stringBuilder);
        BizFallDevice deviceUpdate = new BizFallDevice ();
        deviceUpdate.setId (device.getId ());
        deviceUpdate.setUpdateStatus ((byte) 1);
        updateUserFallDevice (deviceUpdate);
        return 0;
    }


}
