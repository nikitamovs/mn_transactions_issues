package mn.transactions.issues;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByNameLike(String name);
}
