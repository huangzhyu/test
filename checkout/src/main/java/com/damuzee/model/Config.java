package com.damuzee.model;

import org.springframework.beans.factory.annotation.Value;

public class Config {
    @Value("#{propertyConfig['self.ratio']}")
    private String selfRatio;
    @Value("#{propertyConfig['firstLevel.ratio']}")
    private String firstLevelRatio;
    @Value("#{propertyConfig['secondLevel.ratio']}")
    private String secondLevelRatio;

    public String getSelfRatio() {
        return selfRatio;
    }

    public String getFirstLevelRatio() {
        return firstLevelRatio;
    }

    public String getSecondLevelRatio() {
        return secondLevelRatio;
    }

}
