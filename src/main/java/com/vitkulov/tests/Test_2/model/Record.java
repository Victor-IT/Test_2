package com.vitkulov.tests.Test_2.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;
    private Long uplink;
    private Long downlink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Record() {
    }

    public Record(Long uplink, Long downlink, User user) {
        this.date = LocalDate.now();
        this.uplink = uplink;
        this.downlink = downlink;
        this.user = user;
    }

    public Record(LocalDate date, Long uplink, Long downlink, User user) {
        this.date = date;
        this.uplink = uplink;
        this.downlink = downlink;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
        Record that = (Record) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, user);
    }
}
