package data.model.app;


import java.io.Serializable;

public class ConfigRecord implements Serializable {
    private Integer maxSize;
    private String format;
    private String channel;
    private Integer frequence;
    private Integer maxTime;

    public ConfigRecord() {
        maxSize=1024;
        channel="Mono";
        frequence=44100;
        format=".mp3";
        maxTime=3600;
        bitNumber=32;
    }

    public ConfigRecord(Integer maxSize, String format, String channel, Integer frequence, Integer maxTime, Integer bitNumber) {
        this.maxSize = maxSize;
        this.format = format;
        this.channel = channel;
        this.frequence = frequence;
        this.maxTime = maxTime;
        this.bitNumber = bitNumber;
    }

    private Integer bitNumber;

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getFrequence() {
        return frequence;
    }

    public void setFrequence(Integer frequence) {
        this.frequence = frequence;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getBitNumber() {
        return bitNumber;
    }

    public void setBitNumber(Integer bitNumber) {
        this.bitNumber = bitNumber;
    }
}
