package com.wegotrip.models.pojo;

public class LoginBodyPojoModel {
    //  "{ \"name\": \"morpheus\", \"password\": \"leader\" }";

    private String name;

    private String job;


    public String getName() {

        return name;
    }


    public String getJob() {

        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }
}