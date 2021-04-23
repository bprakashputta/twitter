package tech.codingclub.helix.entity;

public class TimeApi {
    private String timeNow;
    private Long epochTime;

    public TimeApi(){

    }

    public TimeApi(String timeNow, Long epochTime) {
        this.timeNow = timeNow;
        this.epochTime = epochTime;
    }
}
