package com.fhdone.paper2019.lombok;

import com.fhdone.paper2019.BaseTest;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


//http://www.cnblogs.com/huey/p/4693484.html
@RequiredArgsConstructor
public class LombokDemo extends BaseTest {

	@Getter
	private final String finalField;
	@NonNull
	@Getter @Setter
	private String nonNullField;
	@Getter @Setter
	private String commonField;

	public static void main(String[] args) {
		LombokDemo foo = new LombokDemo("FINAL FIELD", "NON NULL FIELD");        
		System.out.println(foo.getFinalField());      // [OUTPUT]: FINAL FIELD
		System.out.println(foo.getNonNullField());    // [OUTPUT]: NON NULL FIELD
		System.out.println(foo.getCommonField()); 
	}
	

}
