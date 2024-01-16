package com.example.myapplication;

public class Dataforum {
    private String idf,namaf,jamf,chatf,tglf;
    public Dataforum() {

    }

    public String getTglf() {
        return tglf;
    }

    public void setTglf(String tglf) {
        this.tglf = tglf;
    }

    public String getIdf() {
        return idf;
    }

    public void setIdf(String idf) {
        this.idf = idf;
    }

    public String getNamaf() {
        return namaf;
    }

    public void setNamaf(String namaf) {
        this.namaf = namaf;
    }

    public String getJamf() {
        return jamf;
    }

    public void setJamf(String jamf) {
        this.jamf = jamf;
    }

    public String getChatf() {
        return chatf;
    }

    public void setChatf(String chatf) {
        this.chatf = chatf;
    }

    public Dataforum(String idf, String namaf, String jamf, String chatf, String tglf){
        this.idf=idf;
        this.namaf=namaf;
        this.jamf=jamf;
        this.chatf=chatf;
        this.tglf=tglf;


    }

}

