package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.config.DataSourceConfig;
import rs.saga.domain.ShoppingCart;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
@Commit
public class ShoppingCartRepositoryIT {

    @Autowired
    private ShoppingCartRepository shoppingCartRepo;

    @Test
    public void save() throws Exception {
        // ...
        ShoppingCart savedCart = null;
        ShoppingCart cart = shoppingCartRepo.getOne(savedCart.getId());
        assertEquals(cart.getName(), "name");
        assertEquals(cart.getItems().size(), 1);

    }

    @Configuration
    @Import(DataSourceConfig.class)
    @EnableJpaRepositories
    static class TestConfig {

    }
}