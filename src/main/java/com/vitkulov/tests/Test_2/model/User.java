package com.vitkulov.tests.Test_2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DataRecord> records = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataRecord> getRecords() {
        return records;
    }

    public void addDataRecord(DataRecord dataRecord) {
        records.add(dataRecord);
        dataRecord.setUser(this);
    }

    public void removeDataRecord(DataRecord dataRecord) {
        records.remove(dataRecord);
        dataRecord.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
