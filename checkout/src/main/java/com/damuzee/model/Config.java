package com.damuzee.model;

import org.springframework.beans.factory.annotation.Value;

public class Config {
    @Value("#{propertyConfig['self.ratio']}")
    private int selfRatio;
    
    @Value("#{propertyConfig['superior.ratio']}")
    private int superiorRatio;
    
    @Value("#{propertyConfig['finalSuperior.ratio']}")
    private int finalSuperiorRatio;
    
    @Value("#{propertyConfig['bonus.ratio']}")
    private int bonus;
    
    @Value("#{propertyConfig['conversion.ratio']}")
    private int conversion;

    public int getSelfRatio() {
        return selfRatio;
    }

    public void setSelfRatio(int selfRatio) {
        this.selfRatio = selfRatio;
    }


    public int getSuperiorRatio() {
        return superiorRatio;
    }

    public void setSuperiorRatio(int superiorRatio) {
        this.superiorRatio = superiorRatio;
    }

    public int getFinalSuperiorRatio() {
        return finalSuperiorRatio;
    }

    public void setFinalSuperiorRatio(int finalSuperiorRatio) {
        this.finalSuperiorRatio = finalSuperiorRatio;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getConversion() {
        return conversion;
    }

    public void setConversion(int conversion) {
        this.conversion = conversion;
    }
    
    

}
