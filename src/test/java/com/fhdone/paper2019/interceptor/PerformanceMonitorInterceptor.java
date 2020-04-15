package com.fhdone.paper2019.interceptor;

import com.fhdone.paper2019.controller.StudentController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class PerformanceMonitorInterceptor {

    private Logger logger = LogManager.getLogger(PerformanceMonitorInterceptor.class);

	private boolean isEnabled = true;

    @Pointcut("execution(* com.fhdone.paper2019.service.*.*(..)) ||" +
            " execution(* com.fhdone.paper2019.util.*.*(..))  )")
    private void pointcut() {

    }

    @Around("pointcut()")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		if (isEnabled) {
			long startTime = System.currentTimeMillis();
			Object ret = joinPoint.proceed();

			long endTime = System.currentTimeMillis();
			long spanTime = endTime - startTime;
			if (logger.isDebugEnabled() && spanTime <= 20) {
				logger.debug(getLoggerInfo(spanTime, joinPoint ,false));
			} else if (logger.isInfoEnabled() && spanTime > 20 && spanTime <= 50) {
				logger.info(getLoggerInfo(spanTime, joinPoint ,false));
			} else if (spanTime > 50 && spanTime <= 100) {
				logger.warn(getLoggerInfo(spanTime, joinPoint ,false));
			} else if(spanTime > 100){
				logger.error(getLoggerInfo(spanTime, joinPoint ,true));
			}
			return ret;
		} else {
			return joinPoint.proceed();
		}

	}

	private String getLoggerInfo(long spanTime, ProceedingJoinPoint joinPoint, boolean isError) {
		StringBuffer sb = new StringBuffer();
		sb.append(joinPoint.getSignature().getName());
		sb.append('.').append(joinPoint.getSignature().getName()).append("() ");
		sb.append(" ").append(", span time = ").append(spanTime).append("ms  ");
		if(isError){
			if(joinPoint.getArgs() != null){
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					sb.append("arg"+i+":").append(joinPoint.getArgs()[i]).append("    ");
				}
			}
		}
		return sb.toString();
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
