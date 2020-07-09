package mn.transactions.issues;

import io.micronaut.transaction.annotation.ReadOnly;
import java.util.List;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class PersonManager {

    private final PersonRepository personRepository;

    @Transactional
    public void doSomePersonStuff(Person p) {
        personRepository.save(p);
        throw new RuntimeException("mohahaha");
    }

    @ReadOnly
    public List<Person> getPersons(String name) {
        return personRepository.getByName(name);
    }

}
