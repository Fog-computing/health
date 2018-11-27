package cn.ac.sec.entity.messure;

import java.util.List;

public class HeartRate extends HeartRateBase {
    private List<String> data;
    private List<Integer> analysis;
    private Integer timeLen;
    private List<String> analysisAndroid;

    public List<String> getAnalysisAndroid() {
        return analysisAndroid;
    }

    public void setAnalysisAndroid(List<String> analysisAndroid) {
        this.analysisAndroid = analysisAndroid;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<Integer> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(List<Integer> analysis) {
        this.analysis = analysis;
    }

    public Integer getTimeLen() {
        return timeLen;
    }

    public void setTimeLen(Integer timeLen) {
        this.timeLen = timeLen;
    }
}
