package ny.dmitrium.app.data;

import ny.dmitrium.app.entity.Herb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "herbs", path = "herbs")
public interface HerbRepository extends CrudRepository<Herb, Integer> {
    List<Herb> findByName(String name);
}
