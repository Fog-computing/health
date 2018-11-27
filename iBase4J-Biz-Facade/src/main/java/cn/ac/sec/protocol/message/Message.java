package cn.ac.sec.protocol.message;

import java.util.Date;
import java.util.List;

public abstract class Message {
    protected String version;
    protected String identifier;
    protected Date messageTime;
    protected Long IMEI;
    protected int type;
    protected List<String> params;

    @Override
    public String toString() {
        return "Message{" +
                "version='" + version + '\'' +
                ", identifier='" + identifier + '\'' +
                ", messageTime=" + messageTime +
                ", IMEI='" + IMEI + '\'' +
                ", type='" + type + '\'' +
                ", params=" + params +
                '}';
    }

    protected Message(String identifier, Date messageTime, String IMEI, String type, List<String> params){
        this.version="V1.0.0";
    }
    protected Message(){

    }
    protected Message(String version, String identifier, Date messageTime, Long IMEI, int type, List<String> params) {
        this.version = version;
        this.identifier = identifier;
        this.messageTime = messageTime;
        this.IMEI = IMEI;
        this.type = type;
        this.params = params;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public Long getIMEI() {
        return IMEI;
    }

    public void setIMEI(Long IMEI) {
        this.IMEI = IMEI;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
