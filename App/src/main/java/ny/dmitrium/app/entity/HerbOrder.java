package ny.dmitrium.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "herb_order")
public class HerbOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int consumerId;

    @NotBlank(message = "Укажите своё имя")
    private String consumerName;

    @NotBlank(message = "Укажите свой email")
    @Email(message = "Неверно указан email адрес")
    private String consumerEmail;

    @NotBlank(message = "Укажите свой город")
    private String city;

    @NotBlank(message = "Выберите лечебную траву")
    private String herb;

    @Positive(message = "Количество должно быть положительным")
    private int quantity;

    @Future(message = "Дата доставки должна быть в будущем")
    private LocalDate deliveryDate;

}
