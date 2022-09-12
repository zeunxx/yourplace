package yourplace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import yourplace.domain.MemberAdapter;
import yourplace.domain.Rest;
import yourplace.dto.RestDto;
import yourplace.service.RestLikeService;
import yourplace.service.RestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestController {
    private final RestService restService;
    private final RestLikeService restLikeService;


    @GetMapping("/rest/search")
    public String restSearch(Model model,
                             @PageableDefault(page = 0, size = 10) @SortDefault.SortDefaults(
                                     {@SortDefault(sort = "restSubcategory", direction = Sort.Direction.DESC),
                                             @SortDefault(sort = "restName", direction = Sort.Direction.DESC)}
                             )  Pageable pageable, String searchKeyword) {

        Page<Rest> list = null;

        if (searchKeyword == null) {
            list = restService.restList(pageable);
        } else {
            list = restService.search(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "rest/restSearch";

    }

    @GetMapping("/rest/restList")
    public String restList(Model model, @PageableDefault(page = 0, size = 10, sort = "restId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Rest> rests = restService.restList(pageable);

        int nowPage = rests.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, rests.getTotalPages());

        model.addAttribute("rests", rests);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "rest/restList";
    }

    @GetMapping("/rest/view")
    public String restView(Model model, Integer id) {
        model.addAttribute("rest", restService.restView(id));
        return "rest/restView";
    }

//    @GetMapping("test")
//    public String test(Model model) {
//        return "test";
//    }

    /**
    @PostMapping("/rest/like/{restId}")
    public ResponseEntity<String> addLike(@PathVariable int restId, @AuthenticationPrincipal MemberAdapter memberAdapter) {
        boolean result = false;

        if(memberAdapter!=null) {
            result = restLikeService.addLike(memberAdapter.getUser(),restId);
        }
        return result ?
                new ResponseEntity<>(HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }**/
}