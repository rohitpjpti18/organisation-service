package wisepanda.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppConfigController {
    
    @RequestMapping("/cache")
    public String cachePage() {
        return "/app/home";
    }
}
