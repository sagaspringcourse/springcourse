package rs.saga.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-09
 */
@EnableJpaRepositories(basePackages = "rs.saga.dao")
public class SpringDataConfig extends JPAConfig {
}
