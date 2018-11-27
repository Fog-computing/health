package cn.ac.sec.entity.watch;

import java.io.Serializable;

public class BaseResp implements Serializable {

    public int rCode = 200;

    /**
     * @return the rCode
     */
    public int getrCode() {
        return rCode;
    }

    /**
     * @param rCode the rCode to set
     */
    public void setrCode(int rCode) {
        this.rCode = rCode;
    }


}
