package yourplace.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name="rest_subcategory")
    private String restSubcategory;

    @Column(name="rest_img")
    private String restImg;




}
