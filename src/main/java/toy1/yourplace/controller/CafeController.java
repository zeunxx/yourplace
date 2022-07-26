package toy1.yourplace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toy1.yourplace.domain.Cafe;
import toy1.yourplace.dto.CafeDto;
import toy1.yourplace.service.CafeService;

import java.util.List;

@Controller
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/search")
    public String search(String keyword, Model model, @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Cafe> searchList = cafeService.search(keyword,pageable);

        model.addAttribute("searchList", searchList);

        return "cafes-search";
    }
}
