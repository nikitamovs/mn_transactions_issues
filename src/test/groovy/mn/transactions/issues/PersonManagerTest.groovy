package mn.transactions.issues

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Subject

import javax.inject.Inject
import java.time.Instant

@MicronautTest
class PersonManagerTest extends Specification {

    @Inject
    PersonManager personManager

    @Inject
    @Client("/")
    HttpClient client

    def 'cleanup'() {
        //entitymanager.createNativeQuery('DROP ALL OBJECTS').executeUpdate()
    }

    def 'test transaction management'() {
        given:
            def person = Person.builder().name("Peer Gynt").build()
//        when:
//            client.toBlocking().exchange(HttpRequest.POST("/", "test"))
//        then:
//            thrown(RuntimeException)
//        when:
//            def fetched = client.toBlocking().retrieve("/", Person)
//        then:
//            thrown(RuntimeException)
//            fetched == null
        when:
            personManager.doSomePersonStuff(person)
        then:
            person.id != null
            thrown(RuntimeException)
        when:
            def fetched = personManager.getPersons(person.id)
        then:
            fetched == null
    }

}
