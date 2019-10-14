package com.fhdone.paper2019.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class LogUtils {

	private static Logger logger = LoggerFactory.getLogger(LogUtils.class); 


	public static void logip() {
		HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		logger.info(request.getRemoteAddr());
	}
	
	
}
