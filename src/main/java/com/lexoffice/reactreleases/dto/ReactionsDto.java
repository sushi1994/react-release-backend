package com.lexoffice.reactreleases.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReactionsDto {
    private String url;
    private int total_count;
    private int plusOne;
    private int minusOne;
    private int laugh;
    private int hooray;
    private int confused;
    private int heart;
    private int rocket;
    private int eyes;

    @JsonProperty("+1")
    public void setPlusOne(int plusOne) {
        this.plusOne = plusOne;
    }

    @JsonProperty("plusOne")
    public int getPlusOne() {
        return plusOne;
    }

    @JsonProperty("-1")
    public void setMinusOne(int plusOne) {
        this.plusOne = plusOne;
    }

    @JsonProperty("minusOne")
    public int getMinusOne() {
        return plusOne;
    }
}
