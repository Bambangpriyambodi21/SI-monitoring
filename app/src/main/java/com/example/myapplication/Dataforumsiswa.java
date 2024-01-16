package com.example.myapplication;

public class Dataforumsiswa {
    private String idf,namaf,jamf,chatf,tglf;
    public Dataforumsiswa() {

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

    public Dataforumsiswa(String idf, String namaf, String jamf, String chatf){
        this.idf=idf;
        this.namaf=namaf;
        this.jamf=jamf;
        this.chatf=chatf;


    }

}

