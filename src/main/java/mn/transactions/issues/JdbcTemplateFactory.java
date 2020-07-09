package mn.transactions.issues;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Factory
public class JdbcTemplateFactory {

    @Bean
    @Singleton
    @Inject
    public JdbcTemplate jdbcTemplate( DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Singleton
    @Inject
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
