package cn.ac.sec.entity.transport;


import java.util.List;

public class BaseTransport {
    private String userId;
    private List<String> params;
    public String getUserId() {
        return userId;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public BaseTransport(String userId) {
        this.userId = userId;
    }

    public BaseTransport(){

    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
