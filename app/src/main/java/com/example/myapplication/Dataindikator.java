package com.example.myapplication;


public class Dataindikator {
    private String id,ketindi;
    public Dataindikator() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKetindi() {
        return ketindi;
    }

    public void setKetindi(String ketindi) {
        this.ketindi = ketindi;
    }

    public Dataindikator(String id, String ketindi){
        this.id=id;
        this.ketindi=ketindi;

    }

}
