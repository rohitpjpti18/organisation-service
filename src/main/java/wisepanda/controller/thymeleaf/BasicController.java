package wisepanda.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

    @RequestMapping("/index")
    public String indexpage(){
        return "homepage/index";
    }
}
