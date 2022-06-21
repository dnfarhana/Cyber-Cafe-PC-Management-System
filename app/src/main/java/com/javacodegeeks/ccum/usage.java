package com.javacodegeeks.ccum;

public class usage {
    String matrix,pcid,pcname,dateset;
    double totamount;
    double tottime;

    public usage(){}

    public usage(String matrix, String pcid, String pcname, String dateset, double totamount, double tottime) {
        this.matrix = matrix;
        this.pcid = pcid;
        this.pcname = pcname;
        this.dateset = dateset;
        this.totamount = totamount;
        this.tottime = tottime;
    }

    public String getMatrix() {
        return matrix;
    }

    public void setMatrix(String matrix) {
        this.matrix = matrix;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getPcname() {
        return pcname;
    }

    public void setPcname(String pcname) {
        this.pcname = pcname;
    }

    public String getDateset() {
        return dateset;
    }

    public void setDateset(String dateset) {
        this.dateset = dateset;
    }

    public double getTotamount() {
        return totamount;
    }

    public void setTotamount(double totamount) {
        this.totamount = totamount;
    }

    public double getTottime() {
        return tottime;
    }

    public void setTottime(double tottime) {
        this.tottime = tottime;
    }

    @Override
    public String toString() {
        return "usage{" +
                "matrix='" + matrix + '\'' +
                ", pcid='" + pcid + '\'' +
                ", pcname='" + pcname + '\'' +
                ", dateset='" + dateset + '\'' +
                ", totamount=" + totamount +
                ", tottime=" + tottime +
                '}';
    }
}
