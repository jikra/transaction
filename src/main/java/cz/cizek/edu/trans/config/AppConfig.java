package cz.cizek.edu.trans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author jiricizek <jiri.cizek@cleverlance.com>
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "cz.cizek.edu.trans.service")
public class AppConfig {

    @Bean("transactionManager")
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {

        PlatformTransactionManager manager =
                new DataSourceTransactionManager(dataSource);

        return manager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embd = embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("embd")
                .addScript("db/sql/init.sql")
                .build();

        return embd;
    }
}
