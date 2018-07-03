package io.zensoft.share.aspect.log;

import io.zensoft.share.listener.VacancyListener;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by temirlan on 6/28/18.
 * this class listens for the execution of VacancyListener interface methods
 * if execution occurs it logs with corresponding actions and descriptions
 *
 * @see VacancyListener
 */
@Aspect
@Component
public class VacancyListenerLoggingAdvice {

    private final Logger logger;

    @Autowired
    public VacancyListenerLoggingAdvice(final Logger rabbitMqMessageListenerLogger) {
        this.logger = rabbitMqMessageListenerLogger;
    }

    /**
     * logs before message handled by controllers(listeners)
     * logs :
     * queue name where message came
     * body of a message
     * message handler class detail(class name,method name etc.)
     *
     * @param joinPoint object to extract information about message handler class
     */
    @Before("io.zensoft.share.aspect.VacancyListenerPointCut.onAnyMethodCalled()")
    public void logBeforeAnyMessageHandled(JoinPoint joinPoint) {
        String requestReceiverQueue = getPostListenerQueueName(joinPoint);
        logger.info("Receiving message to queue : " + requestReceiverQueue);
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
    @After("io.zensoft.share.aspect.VacancyListenerPointCut.onAnyMethodCalled()")
    public void logAfterAnyMessageHandled(JoinPoint joinPoint) {
        String requestReceiverQueue = getPostListenerQueueName(joinPoint);
        logger.info("Queue '" + requestReceiverQueue + "' with body '" + joinPoint.getArgs()[0] + "' handled");
        logger.info("Message handler class signature :" + joinPoint.getSignature().toString());
    }

    /**
     * @param joinPoint object to get method signature
     * @return returns the first queue name that method listens
     * method should be annotated with RabbitListener
     * @see RabbitListener
     */
    public String getPostListenerQueueName(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RabbitListener rabbitListener = method.getAnnotation(RabbitListener.class);
        if (rabbitListener == null) {
            return "No rabbit listener annotation present on method " + method.getName();
        }
        return rabbitListener.queues()[0];
    }

}
