package cn.ac.sec.entity.messure;

import java.io.Serializable;
import java.util.Date;

public class Base implements Serializable {
    private Date measureDate;
    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }
}
