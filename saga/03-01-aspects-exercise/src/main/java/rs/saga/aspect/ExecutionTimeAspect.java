package rs.saga.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-09
 */
@Component
@Aspect
public class ExecutionTimeAspect {

    @Around("execution(* rs.saga.*..*(..))")
    public Object executionTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        System.out.println("Method " + joinPoint.getSignature().getName() + " took " + (System.currentTimeMillis() - start));
        return proceed;
    }
}
