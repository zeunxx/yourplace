package com.yourplace.rest.controller;

import com.yourplace.rest.domain.entity.Rest;
import com.yourplace.rest.domain.repository.RestRepository;
import com.yourplace.rest.dto.RestDto;
import com.yourplace.rest.service.RestService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
@AllArgsConstructor
public class RestController {
    private final RestService restService;

    @GetMapping("/")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum){
        List<RestDto> restList = restService.getRestlist();
        //Integer[] pageList= restService.getPageList(pageNum);

        model.addAttribute("restList", restList);
       // model.addAttribute("pageList", pageList);
        return "rest/list";
    }

    @GetMapping("/rest/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<RestDto> restDtoList = restService.searchPosts(keyword);

        model.addAttribute("restList", restDtoList);

        return "rest/list";
    }

    @GetMapping("/detail/{restId}")
    public String detail(@PathVariable("restId") int restId, Model model){
        RestDto restDto = restService.getPost(restId);

        model.addAttribute("restDto", restDto);
        return "rest/detail";
    }



}
