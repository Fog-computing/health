package org.ibase4j.model;
import java.io.Serializable;
/**
 * Created by pjliang on 2018/10/18.
 */
public final class ChartData implements Serializable {

    private static final long serialVersionUID = 6934114681511957222L;

    Integer x;
    Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}