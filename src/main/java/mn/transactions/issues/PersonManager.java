package mn.transactions.issues;


import io.micronaut.transaction.annotation.ReadOnly;
import java.util.List;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class PersonManager {

    private final PersonRepository personRepository;

    @Transactional(TxType.REQUIRED)
    public void doSomePersonStuff(Person p) {
        personRepository.save(p);
        throw new RuntimeException("mohahaha");
    }

    @ReadOnly
    public Person getPersons(long id) {
        return  personRepository.findById(id).orElse(null);
    }

}
