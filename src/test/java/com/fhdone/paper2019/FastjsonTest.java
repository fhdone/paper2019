package com.fhdone.paper2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.fhdone.paper2019.model.Student;

public class FastjsonTest {

	@Test
	public void test01(){
		Map<String, Object> mapSrc = new HashMap<String, Object>();
		mapSrc.put("key1", "One");
		mapSrc.put("key2", "Two");

		String mapJson = JSON.toJSONString(mapSrc);
		System.out.println(mapJson);
		Map<String, Object> mapDst = JSON.parseObject(mapJson, new TypeReference<Map<String, Object>>(){});
		for(String dataKey : mapDst.keySet()) { 
			System.out.println(dataKey +"|" +mapDst.get(dataKey) ); 
		} 

//		mapDst = JSON.parseObject(mapJson, new TypeReference<Map<String, Object>>(){}, Feature.DisableCircularReferenceDetect);
//		for(String dataKey : mapDst.keySet()) {
//			System.out.println(dataKey +"|" +mapDst.get(dataKey) ); 
//		} 

	}
	
	
	@Test
	public void test02() {
		List<Student> sListSrc = genData(100);
		String stuJson = JSON.toJSONString(sListSrc);
		
		List<Student> sListDst =  JSON.parseObject(stuJson, new TypeReference<List<Student>>(){});
		//		for(Student s:sListDst) {
		//			System.out.println(s.getId()+"|"+s.getStuName()+"|"+s.getStuAdd());
		//		}
		

		JSONArray j = JSONArray.parseArray(JSON.toJSONString(sListSrc));
		for(int i=0; i<j.size(); i++){
			System.out.println(j.get(i));
		}
		
	}


	public List<Student> genData(int count) {
		List<Student> sListSrc = new ArrayList<>();
		for(int i=0;i<count;i++) {
			Student s = new Student();
			s.setId(Long.valueOf(i));
			s.setStuName(getRandomString(10));
			s.setStuAdd(getRandomString(20));
			sListSrc.add(s);
		}
		return sListSrc;
	}


	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();     
		StringBuffer sb = new StringBuffer();     
		for (int i = 0; i < length; i++) {     
			int number = random.nextInt(base.length());     
			sb.append(base.charAt(number));     
		}
		return sb.toString();
	} 


}
