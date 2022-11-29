package com.wegotrip.models.pojo;


public class LoginResponsePojoModel {

    private String name, job, id, createdAt, updatedAt;



    public void setName(String name) {
        this.name = name;
    }


    public void setJob(String job) {
        this.job = job;
    }


    public String getName() {
        return name;
    }


    public String getJob() {
        return job;
    }


    public String getId() {
        return id;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }


}
