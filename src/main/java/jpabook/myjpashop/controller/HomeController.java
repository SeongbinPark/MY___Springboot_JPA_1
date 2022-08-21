package jpabook.myjpashop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j//Logger log= LoggerFactory.getLogger(getClass()); 같은 코드. log. 쓸수있음.

public class HomeController {


    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";//이렇게 return하면 resources/templates/home.html
    }


}
