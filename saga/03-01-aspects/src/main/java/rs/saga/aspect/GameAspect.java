package rs.saga.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-09
 */
@Component
@Aspect
public class GameAspect {

    @Before("execution(* rs.saga.*..play*(..))")
    public void playGameAspect(JoinPoint jp) {
        System.out.println(jp.getSignature().getName() + " method advised");
        System.out.println("Game started");
    }
}
