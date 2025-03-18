package ny.dmitrium.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "herb")
public class Herb {

    public Herb() {}

    @Id
    private Integer id;

    private String name;
    private String features;
    private String usage;

    private String imagePath;

    private int remains;

    public void reduce(int delta) {
        remains -= delta;
    }
}
