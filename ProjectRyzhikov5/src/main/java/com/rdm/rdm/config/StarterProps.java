package com.rdm.rdm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties
public class StarterProps {

    private List<String> catNames;
    private List<String> dogNames;
    private List<String> wolfNames;
    private List<String> sharkNames;

    public List<String> getCatNames() {
        return catNames;
    }

    public void setCatNames(List<String> catNames) {
        this.catNames = catNames;
    }


    public List<String> getDogNames() {
        return dogNames;
    }

    public void setDogNames(List<String> dogNames) {
        this.dogNames = dogNames;
    }

    public List<String> getWolfNames() {
        return wolfNames;
    }

    public void setWolfNames(List<String> wolfNames) {
        this.wolfNames = wolfNames;
    }

    public List<String> getSharkNames() {
        return sharkNames;
    }

    public void setSharkNames(List<String> sharkNames) {
        this.sharkNames = sharkNames;
    }
}
