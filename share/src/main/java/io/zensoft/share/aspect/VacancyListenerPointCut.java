package io.zensoft.share.aspect;

import io.zensoft.share.listener.VacancyListener;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by temirlan on 6/28/18.
 * <p>
 * this class contains pointcuts for VacancyListener's methods  (see below)
 *
 * @see VacancyListener
 * <p>
 * any Aop advices can use this pointcuts
 */
@Aspect
public class VacancyListenerPointCut {

    @Pointcut("execution(* io.zensoft.share.listener.VacancyListener.*(*))")
    public void onAnyMethodCalled() {
    }

    @Pointcut("execution(* io.zensoft.share.listener.VacancyListener.publish(*))")
    public void onPublishMethodCalled() {
    }

}



