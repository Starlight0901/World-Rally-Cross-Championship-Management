package com.example.programming_cw_final;

public class VctTableDetails {
    private String vctName;
    private Integer vctAge;
    private String vctTeam;
    private String vctCar;
    private Integer vctScores;

    public VctTableDetails(String vctName, Integer vctAge, String vctTeam, String vctCar, Integer vctScores) {
        this.vctName = vctName;
        this.vctAge = vctAge;
        this.vctTeam = vctTeam;
        this.vctCar = vctCar;
        this.vctScores = vctScores;
    }

    public String getVctName() {

        return vctName;
    }

    public Integer getVctAge() {

        return vctAge;
    }

    public String getVctTeam() {

        return vctTeam;
    }

    public String getVctCar() {

        return vctCar;
    }

    public Integer getVctScores() {

        return vctScores;
    }
}
