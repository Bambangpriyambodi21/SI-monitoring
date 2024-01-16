package com.example.myapplication;


public class Datasiswa {
    private String ids, nis, namas, kelas, telps, pembimbing, industri;
    public Datasiswa() {

    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNamas() {
        return namas;
    }

    public void setNamas(String namas) {
        this.namas = namas;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTelps() {
        return telps;
    }

    public void setTelps(String telps) {
        this.telps = telps;
    }

    public String getPembimbing() {
        return pembimbing;
    }

    public void setPembimbing(String pembimbing) {
        this.pembimbing = pembimbing;
    }

    public String getIndustri() {
        return industri;
    }

    public void setIndustri(String industri) {
        this.industri = industri;
    }

    public Datasiswa(String ids, String nis, String namas, String kelas, String telps, String pembimbing, String industri){
        this.ids=ids;
        this.nis=nis;
        this.namas=namas;
        this.kelas=kelas;
        this.telps=telps;
        this.pembimbing=pembimbing;
        this.industri=industri;
    }




}

