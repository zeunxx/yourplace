package com.yourplace.rest.service;

import com.yourplace.rest.domain.entity.Rest;
import com.yourplace.rest.domain.repository.RestRepository;
import com.yourplace.rest.dto.RestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RestService {
    private final RestRepository restRepository;
    /*
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4; // 한 페이지에 존재하는 게시글 수
*/

    @Transactional
    public List<RestDto> getRestlist(){
       // Page<Rest> page = restRepository.findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "restName")));

        List<Rest> rests= restRepository.findAll();
        List<RestDto> restDtoList = new ArrayList<>();

        for(Rest rest : rests){
            RestDto restDto = RestDto.builder()
                    .restId(rest.getRestId())
                    .restName(rest.getRestName())
                    .restNumber(rest.getRestNumber())
                    .restAddr(rest.getRestAddr())
                    .restHour(rest.getRestHour())
                    .restTotalRate(rest.getRestTotalRate())
                    .build();

            restDtoList.add(restDto);
        }
        return restDtoList;
    }

    @Transactional
    public List<RestDto> searchPosts(String keyword){
        List<Rest> rests = restRepository.findByRestNameContaining(keyword);
        List<RestDto> restDtoList = new ArrayList<>();

        if(rests.isEmpty()) return restDtoList;

        for(Rest rest : rests){
            restDtoList.add(this.convertEntityToDto(rest));
        }
        return restDtoList;
    }

    private RestDto convertEntityToDto(Rest rest){
        return RestDto.builder()
                .restId(rest.getRestId())
                .restName(rest.getRestName())
                .restAddr(rest.getRestAddr())
                .restHour(rest.getRestHour())
                .restNumber(rest.getRestNumber())
                .restTotalRate(rest.getRestTotalRate())
                .build();

    }

    @Transactional
    public RestDto getPost(int restId){
        Optional<Rest> restWrapper = restRepository.findById(restId);
        Rest rest = restWrapper.get();

        RestDto restDto = RestDto.builder()
                .restId(rest.getRestId())
                .restName(rest.getRestName())
                .restNumber(rest.getRestNumber())
                .restAddr(rest.getRestAddr())
                .restHour(rest.getRestHour())
                .restTotalRate(rest.getRestTotalRate())
                .build();

        return restDto;

    }

    /*
    @Transactional
    public Long getRestCount() {
        return restRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        Double postsTotalCount = Double.valueOf(this.getRestCount());

        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ?curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum<=3)?1:curPageNum-2;

        for(int val=curPageNum,idx=0;val<=blockLastPageNum; val++, idx++){
            pageList[idx] = val;
        }

        return pageList;
    }
*/


}
