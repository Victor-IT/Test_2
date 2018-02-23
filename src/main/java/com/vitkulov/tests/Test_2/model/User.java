package com.vitkulov.tests.Test_2.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Setter(AccessLevel.NONE)
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Record> recordList = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public void addRecord(Record record) {
        recordList.add(record);
        record.setUser(this);
    }

    public void removeRecord(Record record) {
        recordList.remove(record);
        record.setUser(null);
    }
}
