package com.yourplace.rest.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="rest_info")
public class Rest{
    @Id
    @Column(name="rest_id")
    private int restId;

    @Column(nullable = false, name="rest_name")
    private String restName;

    @Column(name="rest_addr")
    private String restAddr;

    @Column(name="rest_hour")
    private String restHour;

    @Column(name="rest_number")
    private String restNumber;

    @Column(name="rest_total_rate")
    private double restTotalRate;

    @Builder
    public Rest(int restId, String restName, String restAddr, String restHour, String restNumber, double restTotalRate){
        this.restId = restId;
        this.restName = restName;
        this.restAddr = restAddr;
        this.restHour = restHour;
        this.restNumber = restNumber;
        this.restTotalRate = restTotalRate;

    }



}
