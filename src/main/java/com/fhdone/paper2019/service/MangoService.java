package com.fhdone.paper2019.service;

import java.util.List;
import java.util.Map;

public interface MangoService{

    public List<Map<String,Object>> queryKey(String key, String value);

    public List<Map<String,Object>> queryKeyByRegex(String key, String regexValue);

    public List<Map<String,Object>> queryKeyGtValue(String key, double value);

}
