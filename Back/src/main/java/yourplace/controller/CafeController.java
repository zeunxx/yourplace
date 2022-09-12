package yourplace.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import yourplace.domain.Cafe;
import yourplace.domain.CafeLike;
import yourplace.domain.MemberAdapter;
import yourplace.domain.User;
import yourplace.dto.CafeDto;
import yourplace.service.CafeLikeService;
import yourplace.service.CafeService;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;
    private final CafeLikeService cafeLikeService;

    
    @GetMapping("/cafe/search")
    public String cafeSearch(Model model,
                             @PageableDefault(page = 0, size = 10, sort = "cafeId", direction = Sort.Direction.DESC) Pageable pageable,
                             String searchKeyword) {

        Page<Cafe> list = null;

        if (searchKeyword == null) {
            list = cafeService.cafeList(pageable);
        } else {
            list = cafeService.search(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "cafe/cafeSearch";

    }

    @GetMapping("/cafe/cafeList")
    public String cafeList(Model model, @PageableDefault(page = 0, size = 10, sort = "cafeId", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Cafe> cafes = cafeService.cafeList(pageable);

        int nowPage = cafes.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, cafes.getTotalPages());

        model.addAttribute("cafes", cafes);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "cafe/cafeList";
    }

    @GetMapping("/cafe/view")
    public String cafeView(Model model, Integer id) {
        model.addAttribute("cafe", cafeService.cafeView(id));
        return "cafe/cafeView";
    }

//    @GetMapping("test")
//    public String test(Model model) {
//        return "test";
//    }

    /**
    @PostMapping("/cafe/like/{cafeId}")
    public ResponseEntity<String> addLike(@PathVariable int cafeId, @AuthenticationPrincipal MemberAdapter memberAdapter) {
        boolean result = false;

        if(memberAdapter!=null) {
            result = cafeLikeService.addLike(cafeId,memberAdapter.getUser());
        }
        return result ?
                new ResponseEntity<>(HttpStatus.OK)
                    :new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/
    
}

