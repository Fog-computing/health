package cn.ac.sec.entity.watch;

public class Todo {
    public static final int TYPE_ONCE = 0;
    public static final int TYPE_EVERY_DAY = 1;
    /**
     * 提醒id,用于添加删除指定的id
     */
    public int id;

    /**
     * 提醒类型0单次、1每天
     */
    public int type;

    /**
     * 提醒标题
     */
    public String title;

    /**
     * 提醒时间
     */
    public String settime;

    /**
     * 提醒语音url
     */
    public String url;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the settime
     */
    public String getSettime() {
        return settime;
    }

    /**
     * @param settime the settime to set
     */
    public void setSettime(String settime) {
        this.settime = settime;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }


}
