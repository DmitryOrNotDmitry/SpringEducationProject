package ny.dmitrium.app;

import ny.dmitrium.app.data.HerbRepository;
import ny.dmitrium.app.entity.Herb;
import ny.dmitrium.app.entity.HerbOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final HerbRepository herbRepository;

    public WebController(HerbRepository herbRepository) {
        this.herbRepository = herbRepository;
    }

    @GetMapping("/")
    public String loadIndex(Model model) {
        HerbOrder order = new HerbOrder();
        model.addAttribute("order", order);

        Iterable<Herb> herbs = herbRepository.findAll();
        model.addAttribute("herbs", herbs);

        return "index";
    }

}
