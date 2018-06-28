package io.zensoft.share.aspect.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by temirlan on 6/28/18.
 * this class listens for the execution of PostListener interface methods
 * if execution occurs it logs with corresponding actions and descriptions
 *
 * @see io.zensoft.share.listener.PostListener
 */
@Aspect
@Component
public class PostListenerLoggingAdvice {


    @Autowired
    @Qualifier("rabbitMqMessageListenerLogger")
    private Logger logger;

    /**
     * logs before message handled by controllers(listeners)
     * logs :
     * queue name where message came
     * body of a message
     * message handler class detail(class name,method name etc.)
     *
     * @param joinPoint object to extract information about message handler class
     */
    @Before("io.zensoft.share.aspect.PostListenerPointCut.onPublishMethodCalled()")
    public void logBeforePublishMessageHandled(JoinPoint joinPoint) {
        String queueNameWhereMessageCame = getQueueNameAnnotatedWithRabbitListener(joinPoint);
        logger.info("Receiving message to queue : " + queueNameWhereMessageCame);
        logger.info("Message body : " + joinPoint.getArgs()[0]);
        logger.info("Message handler class signature :" + joinPoint.getSignature().toString());
    }

    /**
     * logs after message handled
     * logs :
     * queue name where message came
     * body of a message
     * message handler class detail(class name,method name etc.)
     *
     * @param joinPoint object to extract information about message handler class
     */
    @After("io.zensoft.share.aspect.PostListenerPointCut.onPublishMethodCalled()")
    public void logAfterPublishMessageHandled(JoinPoint joinPoint) {
        String queueNameWhereMessageCame = getQueueNameAnnotatedWithRabbitListener(joinPoint);
        logger.info("Queue '" + queueNameWhereMessageCame + "' with body '" + joinPoint.getArgs()[0] + "' handled");
        logger.info("Message handler class signature :" + joinPoint.getSignature().toString());
    }

    /**
     * @param joinPoint object to get method signature
     * @return returns the first queue name that method listens
     * method should be annotated with RabbitListener
     * @see RabbitListener
     */
    public String getQueueNameAnnotatedWithRabbitListener(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RabbitListener rabbitListener = method.getAnnotation(RabbitListener.class);
        if (rabbitListener == null) {
            return "No rabbit listener annotation present on method " + method.getName();
        }
        return rabbitListener.queues()[0];
    }

}
