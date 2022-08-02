package toy1.yourplace.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
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

    @Builder
    public Cafe(int cafeId, String cafeName, String cafeAddr, String cafeHour, String cafeNumber, double cafeTotalRate) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.cafeAddr = cafeAddr;
        this.cafeHour = cafeHour;
        this.cafeNumber = cafeNumber;
        this.cafeTotalRate = cafeTotalRate;
    }
}




