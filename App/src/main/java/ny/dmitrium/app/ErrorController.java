package ny.dmitrium.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/unavailable_url")
    public String accessDenied() {
        return "unavailable_url";
    }

}
