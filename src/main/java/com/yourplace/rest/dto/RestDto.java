package com.yourplace.rest.dto;

import com.yourplace.rest.domain.entity.Rest;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RestDto {
    private int restId;
    private String restName;
    private String restAddr;
    private String restHour;
    private String restNumber;
    private double restTotalRate;

    public Rest toEntity(){
        Rest rest = Rest.builder()
                .restId(restId)
                .restName(restName)
                .restAddr(restAddr)
                .restHour(restHour)
                .restNumber(restNumber)
                .restTotalRate(restTotalRate)
                .build();
        return rest;
    }

    @Builder
    public RestDto(int restId, String restName, String restAddr, String restHour, String restNumber, double restTotalRate){
        this.restId = restId;
        this.restName = restName;
        this.restAddr = restAddr;
        this.restHour = restHour;
        this.restNumber = restNumber;
        this.restTotalRate = restTotalRate;

    }



}