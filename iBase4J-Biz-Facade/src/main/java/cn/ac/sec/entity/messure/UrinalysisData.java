package cn.ac.sec.entity.messure;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UrinalysisData {
    @JsonIgnore
    private static final String[] uroData = {"3.3umol/l",
            "33umol/l",
            "66umol/l",
            "131umol/l"};
    @JsonIgnore
    private static final String[] bldData = {"-",
            "10/ul",
            "25/ul",
            "50/ul",
            "250/ul"};
    @JsonIgnore
    private static final String[] bilData = {
            "0umol/l",
            "17umol/l",
            "50umol/l",
            "100umol/l"
    };
    @JsonIgnore
    private static final String[] ketData = {
            "0mmol/l",
            "1.5mmol/l",
            "4.0mmol/l",
            "8.0mmol/l"
    };
    @JsonIgnore
    private static final String[] leuData = {
            "-",
            "15cells/ul",
            "70cells/ul",
            "125cells/ul",
            "500cells/ul"
    };
    @JsonIgnore
    private static final String[] gluData = {
            "0mmol/l",
            "2.8mmol/l",
            "5.5mmol/l",
            "14mmol/l",
            "28mmol/l",
            "55mmol/l"
    };
    @JsonIgnore
    private static final String[] proData = {
            "0g/l",
            "0.15g/l",
            "0.3g/l",
            "1g/l",
            "3g/l"
    };
    @JsonIgnore
    private static final String[] phData = {
            "5",
            "6",
            "7",
            "8",
            "9"
    };
    @JsonIgnore
    private static final String[] nitData = {
            "-",
            "18umol/l"
    };
    @JsonIgnore
    private static final String[] sgData = {
            "1.005",
            "1.010",
            "1.015",
            "1.020",
            "1.025",
            "1.030"
    };
    @JsonIgnore
    private static final String[] vcData = {
            "0mmol/l",
            "0.6mmol/l",
            "1.4mmol/l",
            "2.8mmol/l",
            "5.6mmol/l",
    };
    private String uro;
    private String bld;
    private String glu;
    private String bil;
    private String ket;
    private String pro;
    private String ph;
    private String nit;
    private String leu;
    private String sg;
    private String vc;

    public static UrinalysisData toData(Urinalysis urinalysis) {
        UrinalysisData data = new UrinalysisData ();
        data.setBil (UrinalysisData.bilData[urinalysis.getBil ()]);
        data.setKet (UrinalysisData.ketData[urinalysis.getKet ()]);
        data.setGlu (UrinalysisData.gluData[urinalysis.getGlu ()]);
        data.setLeu (UrinalysisData.leuData[urinalysis.getLeu ()]);
        data.setNit (UrinalysisData.nitData[urinalysis.getNit ()]);
        data.setPh (UrinalysisData.phData[urinalysis.getPh ()]);
        data.setPro (UrinalysisData.proData[urinalysis.getPro ()]);
        data.setSg (UrinalysisData.sgData[urinalysis.getSg ()]);
//        data.setUro (UrinalysisData[urinalysis.getGlu ()]);
        data.setVc (UrinalysisData.vcData[urinalysis.getVc ()]);

        return data;
    }

    public String getUro() {
        return uro;
    }

    public void setUro(String uro) {
        this.uro = uro;
    }

    public String getBld() {
        return bld;
    }

    public void setBld(String bld) {
        this.bld = bld;
    }

    public String getGlu() {
        return glu;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }

    public String getBil() {
        return bil;
    }

    public void setBil(String bil) {
        this.bil = bil;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getLeu() {
        return leu;
    }

    public void setLeu(String leu) {
        this.leu = leu;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }


}
