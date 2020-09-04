package mn.transactions.issues;

import io.micronaut.spring.tx.annotation.Transactional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;

@Singleton
@RequiredArgsConstructor
public class PersonManager {

    private final PersonRepository personRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doSomePersonStuff(Person p) {
        personRepository.save(p);
        throw new RuntimeException("mohahaha");
    }

    @Transactional(readOnly = true)
    public Person getPersons(long id) {
        return personRepository.findById(id).orElse(null);
    }

}
