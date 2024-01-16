package com.example.myapplication;

public class Datapenempatan {
    private String idp,kodei,kodep,namap,siswa1,siswa2,siswa3,siswa4,siswa5,siswa6;
    public Datapenempatan() {

    }

    public String getSiswa1() {
        return siswa1;
    }

    public void setSiswa1(String siswa1) {
        this.siswa1 = siswa1;
    }

    public String getSiswa2() {
        return siswa2;
    }

    public void setSiswa2(String siswa2) {
        this.siswa2 = siswa2;
    }

    public String getSiswa3() {
        return siswa3;
    }

    public void setSiswa3(String siswa3) {
        this.siswa3 = siswa3;
    }

    public String getSiswa4() {
        return siswa4;
    }

    public void setSiswa4(String siswa4) {
        this.siswa4 = siswa4;
    }

    public String getSiswa5() {
        return siswa5;
    }

    public void setSiswa5(String siswa5) {
        this.siswa5 = siswa5;
    }

    public String getSiswa6() {
        return siswa6;
    }

    public void setSiswa6(String siswa6) {
        this.siswa6 = siswa6;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }



    public String getNamap() {
        return namap;
    }

    public void setNamap(String namap) {
        this.namap = namap;
    }

    public String getKodep() {
        return kodep;
    }

    public void setKodep(String kodep) {
        this.kodep = kodep;
    }

    public String getKodei() {
        return kodei;
    }

    public void setKodei(String kodei) {
        this.kodei = kodei;
    }

    public Datapenempatan(String idp, String kodep, String kodei, String namap, String siswa1, String siswa2, String siswa3, String siswa4, String siswa5, String siswa6){
        this.idp=idp;
        this.kodep=kodep;
        this.kodei=kodei;
        this.namap=namap;
        this.siswa1=siswa1;
        this.siswa2=siswa2;
        this.siswa3=siswa3;
        this.siswa4=siswa4;
        this.siswa5=siswa5;
        this.siswa6=siswa6;

    }
}


