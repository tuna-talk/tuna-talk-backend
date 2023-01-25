package shop.iamhyunjun.tunatalk.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RequiredArgsConstructor
@RestController
public class HjTestController {

    @GetMapping("/")
    public String success() {


        return "성공";
    }

}
