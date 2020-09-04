package mn.transactions.issues;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Controller("/")
@RequiredArgsConstructor
public class PersonController {

    private final PersonManager manager;

    @Post
    public void doSomeShit() {
        manager.doSomePersonStuff(Person.builder().id(200L).name("Per Heimly").version(Instant.now()).build());
    }

    @Get
    public Person getPersons() {
        return manager.getPersons(200L);
    }

}
