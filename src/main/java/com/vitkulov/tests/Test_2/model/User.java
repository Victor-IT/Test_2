package com.vitkulov.tests.Test_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private String name;

    private String email;

    private Long uplink;

    private Long downlink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUplink() {
        return uplink;
    }

    public void setUplink(Long uplink) {
        this.uplink = uplink;
    }

    public Long getDownlink() {
        return downlink;
    }

    public void setDownlink(Long downlink) {
        this.downlink = downlink;
    }
}
