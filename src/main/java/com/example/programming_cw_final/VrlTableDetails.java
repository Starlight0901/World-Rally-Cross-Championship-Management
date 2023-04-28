package com.example.programming_cw_final;

public class VrlTableDetails {

    private Integer vrlDate;
    private String vrlLocation;
    private String vrlName;
    private Integer vrlPosition;
    private Integer vrlScores;

    public VrlTableDetails(Integer vrlDate, String vrlLocation, String vrlName, Integer vrlPosition, Integer vrlScores) {
        this.vrlDate = vrlDate;
        this.vrlLocation = vrlLocation;
        this.vrlName = vrlName;
        this.vrlPosition = vrlPosition;
        this.vrlScores = vrlScores;
    }

    public Integer getVrlDate() {
        return vrlDate;
    }

    public String getVrlLocation(){
        return vrlLocation;
    }
    public String getVrlName() {
        return vrlName;
    }

    public Integer getVrlPosition() {
        return vrlPosition;
    }

    public Integer getVrlScores() {
        return vrlScores;
    }
}
