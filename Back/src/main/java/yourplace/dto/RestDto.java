package yourplace.dto;

import yourplace.domain.Rest;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class RestDto {
    private int restId;
    private String restName;
    private String restAddr;
    private String restHour;
    private String restNumber;
    private double restTotalRate;

    @Builder
    public RestDto(Rest rest) {
        super();
        this.restId = rest.getRestId();
        this.restName = rest.getRestName();
        this.restAddr = rest.getRestAddr();
        this.restHour = rest.getRestHour();
        this.restNumber = rest.getRestNumber();
        this.restTotalRate = rest.getRestTotalRate();
    }



}
