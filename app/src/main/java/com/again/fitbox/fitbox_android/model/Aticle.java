package com.again.fitbox.fitbox_android.model;

/**
 * Created by jeong on 2016. 11. 13..
 */

public class Aticle {

    private int aticleNo;
    private String subject;
    private String description;
    private String auther;


    public Aticle(int aticleNo, String subject, String description, String auther) {
        this.aticleNo = aticleNo;
        this.subject = subject;
        this.description = description;
        this.auther = auther;
    }

    public int getAticleNo() {
        return aticleNo;
    }

    public void setAticleNo(int aticleNo) {
        this.aticleNo = aticleNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }
}
