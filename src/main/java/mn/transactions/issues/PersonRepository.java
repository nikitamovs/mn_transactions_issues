package mn.transactions.issues;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Singleton
@AllArgsConstructor
@NoArgsConstructor
public class PersonRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public void save(Person p) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int inserted = jdbcTemplate.update(
                "insert into person(name) values(:name)",
                new MapSqlParameterSource(new HashMap<>() {{
                    put("name", p.getName());
                }}),
                keyHolder
        );

        if (inserted == 0) {
            throw new RuntimeException("wtf?");
        }

        p.setId(((Number) keyHolder.getKeyList().get(0).get("ID")).longValue());
        p.setVersion(((Timestamp) keyHolder.getKeyList().get(0).get("VERSION")).toInstant());
    }

    public Optional<Person> findById(long id) {
        List<Person> persons = jdbcTemplate.query(
                "select id, name, version from person where id = :id",
                new HashMap<>() {{
                    put("id", id);
                }},
                (rs, rowNum) ->
                        Person.builder()
                                .id(rs.getLong("id"))
                                .name(rs.getString("name"))
                                .version(rs.getTimestamp("version").toInstant())
                                .build()
        );
        if (persons.size() != 1) {
            return Optional.empty();
        } else {
            return Optional.of(persons.get(0));
        }
    }

}
