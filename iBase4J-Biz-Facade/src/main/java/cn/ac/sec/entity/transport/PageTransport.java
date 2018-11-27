package cn.ac.sec.entity.transport;

import java.io.Serializable;

public class PageTransport implements Serializable{
    private String keyword;
    private Integer sEcho;
    private Integer pageSize;
    private Integer pageNum;
    private Integer pageIndex;

    @Override
    public String toString() {
        return "PageTransport{" +
                "keyword='" + keyword + '\'' +
                ", sEcho=" + sEcho +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", pageIndex=" + pageIndex +
                '}';
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
