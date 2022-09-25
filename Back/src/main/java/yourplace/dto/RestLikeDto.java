package yourplace.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yourplace.domain.CafeLike;
import yourplace.domain.RestLike;

@Getter
@Setter
@NoArgsConstructor
public class RestLikeDto {
    private Long id;
    private int cafe_id;
    private Long code;

    @Builder
    public RestLikeDto(RestLike restLike) {
        super();
        this.id = restLike.getId();
        this.cafe_id = restLike.getRest().getRestId();
        this.code = restLike.getUser().getCode();
    }

}
