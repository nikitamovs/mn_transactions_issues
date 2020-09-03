package mn.transactions.issues;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Controller("/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonManager manager;

    @Post
    public void doSomeShit() {
        manager.doSomePersonStuff(Person.builder()
                .name("Per")
                .build());
    }

    @Get
    public List<Person> getPersons() {
        return manager.getPersons("Per");
    }

}
