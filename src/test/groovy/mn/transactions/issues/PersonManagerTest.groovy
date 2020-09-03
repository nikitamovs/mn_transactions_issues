package mn.transactions.issues

import groovy.io.FileType
import groovy.sql.Sql
import io.micronaut.test.annotation.MicronautTest
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import javax.inject.Inject
import javax.sql.DataSource
import java.sql.Connection

@MicronautTest
class PersonManagerTest extends Specification {

    @AutoCleanup
    static DataSource datasource

    @Shared
    Sql sql = new Sql(getConnection())

    @Subject
    @Inject
    PersonManager personManager

    def 'cleanup'() {
        sql.executeUpdate('DROP ALL OBJECTS')
    }

    def 'test transaction management'() {
        given:
            def person = Person.builder().name("Peer Gynt").build()
        when:
            personManager.doSomePersonStuff(person)
        then:
            thrown(RuntimeException)
        when:
            def persons = personManager.getPersons(person.getName())
        then:
            persons.size() == 0
    }

    Connection getConnection() {
        lazyloadDatasource().getConnection()
    }

    def lazyloadDatasource() {
        if (datasource == null) {
            datasource = buildDatasource()
        }
        return datasource
    }

    def buildDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource()
        dataSource.setDriverClassName('org.h2.Driver')
        dataSource.setUrl('jdbc:h2:mem:test')
        dataSource.setUsername('sa')
        dataSource.setPassword('');

        return dataSource;
    }

}
