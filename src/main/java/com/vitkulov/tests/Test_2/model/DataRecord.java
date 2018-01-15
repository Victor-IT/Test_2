package com.vitkulov.tests.Test_2.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DataRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long uplink;
    private Long downlink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public DataRecord() {
    }

    public DataRecord(Long uplink, Long downlink, User user) {
        this.uplink = uplink;
        this.downlink = downlink;
        this.user = user;
    }

    public Long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRecord that = (DataRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
    }
}
