package ny.dmitrium.app.data;

import ny.dmitrium.app.entity.HerbOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HerbOrderRepository extends JpaRepository<HerbOrder, Integer> {
    List<HerbOrder> findByConsumerId(int consumerId);
}
