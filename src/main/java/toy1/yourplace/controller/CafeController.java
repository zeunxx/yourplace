package toy1.yourplace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toy1.yourplace.domain.Cafe;
import toy1.yourplace.service.CafeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;


    @GetMapping("/")
    public String cafe() {return "home";}

    @GetMapping("/cafe/search")
    public String cafeSearch(Model model,
                             @PageableDefault(page = 0, size = 10, sort = "cafeId", direction = Sort.Direction.DESC) Pageable pageable,
                             String searchKeyword) {

        Page<Cafe> list = null;

        if(searchKeyword == null) {
            list = cafeService.cafeList(pageable);
        }else {
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

    @GetMapping("/cafe/list")
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

}
