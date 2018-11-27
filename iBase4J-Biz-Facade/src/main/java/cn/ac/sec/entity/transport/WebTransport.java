package cn.ac.sec.entity.transport;

import java.io.Serializable;

public class WebTransport<T>  implements Serializable{
    private T data;
    private Pager pager;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
