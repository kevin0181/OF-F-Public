package of_f.of_f_spring.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/v1/of_f/admin")
public class TestController {

    @PostMapping("/test")
    public String test() {
        return "test";
    }

}
