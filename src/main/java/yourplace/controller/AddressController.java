package yourplace.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AddressController {

    @GetMapping("/address")
    public String address(){
        System.out.println("카카오 테스트");

        return "address";
    }
}
