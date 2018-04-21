package com.vitkulov.tests.Test_2.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@EqualsAndHashCode(exclude = {"uplink", "downlink"})
public class Record {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private Long uplink;
    private Long downlink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Record() {
    }

    public Record(Long uplink, Long downlink, User user) {
        this.date = LocalDateTime.now();
        this.uplink = uplink;
        this.downlink = downlink;
        this.user = user;
    }

    public Record(LocalDateTime date, Long uplink, Long downlink, User user) {
        this.date = date;
        this.uplink = uplink;
        this.downlink = downlink;
        this.user = user;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm:ss");
        return date.format(formatter);
    }
}
