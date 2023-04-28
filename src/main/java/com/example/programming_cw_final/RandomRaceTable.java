package com.example.programming_cw_final;

public class RandomRaceTable {
    private String srrName;
    private Integer srrPosition;
    private Integer srrPoints;

    public RandomRaceTable(Integer srrPosition, String srrName, Integer srrPoints) {
        this.srrPosition = srrPosition;
        this.srrName = srrName;
        this.srrPoints = srrPoints;
    }

    public String getSrrName() {

        return srrName;
    }

    public Integer getSrrPosition() {

        return srrPosition;
    }

    public Integer getSrrPoints() {

        return srrPoints;
    }
}
