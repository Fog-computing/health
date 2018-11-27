package cn.ac.sec.entity.watch;


import java.io.Serializable;

public class FileExtra implements Serializable {

    /**
     * 文件类型
     */
    public int type;

    /**
     * 文件大小
     */
    public int size;

    /**
     * 文件id
     */
    public int id;

    /**
     * 文件对应的文件服务器ip
     */
    public String ip;

    /**
     * 文件对应的文件服务器端口
     */
    public int port;

    /**
     * 语音时长
     */
    public int duration;

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
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

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
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FileExtra [type=" + type + ", size=" + size + ", id=" + id
                + ", ip=" + ip + ", port=" + port + ", duration=" + duration
                + "]";
    }


}
