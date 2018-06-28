package io.zensoft.share.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by temirlan on 6/28/18.
 * <p>
 * this class contains pointcuts for PostListener's methods  (see below)
 *
 * @see io.zensoft.share.listener.PostListener
 * <p>
 * any Aop advices can use this pointcuts
 */
@Aspect
public class PostListenerPointCut {

    @Pointcut("execution(* io.zensoft.share.listener.PostListener.*(*))")
    public void onAnyMethodCalled() {
    }

    @Pointcut("execution(* io.zensoft.share.listener.PostListener.publish(*))")
    public void onPublishMethodCalled() {
    }

}



