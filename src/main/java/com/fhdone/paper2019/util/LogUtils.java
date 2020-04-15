package com.fhdone.paper2019.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.K;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LogUtils {

    private static Logger logger = LogManager.getLogger(LogUtils.class);

	public static void logip() {
		HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		logger.info(request.getRemoteAddr());
        Set<K> set = Collections.newSetFromMap(new ConcurrentHashMap<K, Boolean>());
	}
	
	
}
