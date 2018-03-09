package rs.saga.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-09
 */
public class ExecutionTimeAspect {

    public Object executionTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        System.out.println("Method " + joinPoint.getSignature().getName() + " took " + (System.currentTimeMillis() - start));
        return proceed;
    }
}
