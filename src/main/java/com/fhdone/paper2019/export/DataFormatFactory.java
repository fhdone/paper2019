package com.fhdone.paper2019.export;

import java.util.Map;

public interface DataFormatFactory<T> {

	public final static String TITLT = "TITLE";
	public final static String LARGE = "LARGE";
	public final static String NORMAL = "NORMAL";
	
	public  Map<String, T> getCellForamtMap() throws Exception;
	
}
