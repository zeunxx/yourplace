package toy1.yourplace.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name="cafe_info")
public class Cafe {

    @Id
    @Column(name = "cafe_id")
    private int cafeId;
    @Column(nullable = false, name = "cafe_name")
    private String cafeName;
    @Column(name = "cafe_addr")
    private String cafeAddr;
    @Column(name = "cafe_hour")
    private String cafeHour;
    @Column(name = "cafe_number")
    private String cafeNumber;
    @Column(name = "cafe_total_rate")
    private double cafeTotalRate;
}




