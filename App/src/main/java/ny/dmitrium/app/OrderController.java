package ny.dmitrium.app;

import ny.dmitrium.app.data.HerbOrderRepository;
import ny.dmitrium.app.data.HerbRepository;
import ny.dmitrium.app.entity.Herb;
import ny.dmitrium.app.entity.HerbOrder;
import ny.dmitrium.app.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private final HerbOrderRepository herbOrderRepository;
    private final HerbRepository herbRepository;

    public OrderController(HerbOrderRepository herbOrderRepository, HerbRepository herbRepository) {
        this.herbOrderRepository = herbOrderRepository;
        this.herbRepository = herbRepository;
    }

    @PostMapping(value = "/submitOrder")
    public String formValidate(@Valid @ModelAttribute("order") HerbOrder order, Errors errors, Model model,
                   @AuthenticationPrincipal User userDetails) {
        if (userDetails != null) {
            if (!isEnough(order)) {
                errors.rejectValue("quantity", "errorCode", "Недостаточно товара на складе");
            }

            if (errors.hasErrors()) {
                Iterable<Herb> herbs = herbRepository.findAll();
                model.addAttribute("herbs", herbs);

                return "index";
            }

            List<Herb> boughtHerbs = herbRepository.findByName(order.getHerb());

            if (boughtHerbs.size() == 1) {
                Herb boughtHerb = boughtHerbs.getFirst();
                boughtHerb.reduce(order.getQuantity());
                herbRepository.save(boughtHerb);

                order.setConsumerId(userDetails.getId());
                HerbOrder savedOrder = herbOrderRepository.save(order);
                System.out.println("Processing order [id = " + savedOrder.getId() + "]: " + savedOrder);
            }
        }

        return "redirect:/";
    }

    private boolean isEnough(HerbOrder order){
        List<Herb> herbs = herbRepository.findByName(order.getHerb());
        int stock = 0;
        if (herbs.size() == 1) {
            stock = herbs.getFirst().getRemains();
        }

        return stock >= order.getQuantity();
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<HerbOrder> orders = herbOrderRepository.findAll();
        model.addAttribute("orders", orders);
        return "ordersTable";
    }

    @GetMapping("/orders/{id}")
    public String getOrder(@PathVariable("id") Integer orderId, Model model, @AuthenticationPrincipal User user) {
        boolean isAdmin = "ROLE_ADMIN".equals(user.getRole());

        Optional<HerbOrder> order = herbOrderRepository.findById(orderId);

        if (isAdmin) {
            return getOrderForAdmin(order, model, user);
        } else {
            return getOrderForUser(order, model, user);
        }
    }

    private String getOrderForAdmin(Optional<HerbOrder> order, Model model, User user) {
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "orderInfo";
        }

        return "redirect:/orders";
    }

    private String getOrderForUser(Optional<HerbOrder> order, Model model, User user) {
        if (order.isPresent() && order.get().getConsumerId() == user.getId()) {
            model.addAttribute("order", order.get());
            return "orderInfo";
        }

        return "redirect:/my_orders";
    }

    @GetMapping("/my_orders")
    public String myOrders(Model model, @AuthenticationPrincipal User user) {
        List<HerbOrder> orders = herbOrderRepository.findByConsumerId(user.getId());
        model.addAttribute("orders", orders);
        return "ordersTable";
    }

}
