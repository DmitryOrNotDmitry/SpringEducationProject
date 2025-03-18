package ny.dmitrium.app.api;

import ny.dmitrium.app.data.HerbOrderRepository;
import ny.dmitrium.app.entity.HerbOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/orders")
public class RestOrdersController {

    private final HerbOrderRepository herbOrderRepository;

    @Autowired
    public RestOrdersController(HerbOrderRepository herbOrderRepository) {
        this.herbOrderRepository = herbOrderRepository;
    }

    @GetMapping
    public List<HerbOrder> getAllOrdersHerbs() {
        return herbOrderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<HerbOrder>> getOrderById(@PathVariable Integer id) {
        Optional<HerbOrder> order = herbOrderRepository.findById(id);

        if (order.isPresent()) {
            return ResponseEntity.ok().body(EntityModel.of(
                    order.get(),
                    buildOrderLinks(order.get())
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EntityModel<HerbOrder>> createOrder(@RequestBody HerbOrder newOrder) {
        HerbOrder createdOrder = herbOrderRepository.save(newOrder);

        EntityModel<HerbOrder> resource = EntityModel.of(
                createdOrder,
                buildOrderLinks(createdOrder)
        );

        return ResponseEntity
                .created(linkTo(methodOn(RestOrdersController.class).getOrderById(createdOrder.getId())).toUri())
                .body(resource);
    }

    @PutMapping("/{id}")
    public EntityModel<HerbOrder> updateOrder(@PathVariable Integer id, @RequestBody HerbOrder order) {
        order.setId(id);
        herbOrderRepository.save(order);

        return EntityModel.of(
                order,
                buildOrderLinks(order)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        herbOrderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private List<Link> buildOrderLinks(HerbOrder order) {
        return List.of(
                linkTo(methodOn(RestOrdersController.class).getOrderById(order.getId())).withSelfRel(),
                linkTo(methodOn(RestOrdersController.class).deleteOrder(order.getId())).withRel("delete").withType("DELETE"),
                linkTo(methodOn(RestOrdersController.class).updateOrder(order.getId(), null)).withRel("update").withType("PUT"),
                linkTo(methodOn(RestOrdersController.class).createOrder(null)).withRel("create").withType("POST"),
                linkTo(methodOn(RestOrdersController.class).getAllOrdersHerbs()).withRel("all-orders")
        );
    }
}
