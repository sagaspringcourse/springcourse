package rs.saga.businessobject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-03
 */
@Component
public class GameProcessing implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("processing game");
    }
}
