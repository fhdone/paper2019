package com.fhdone.paper2019.lombok;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import lombok.Cleanup;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


//http://www.cnblogs.com/huey/p/4693484.html
//http://www.cnblogs.com/holten/p/5729226.html
@NoArgsConstructor
@Data
public class LombokDemo2 {


	@Getter
	private String finalField;
	@NonNull
	@Getter @Setter
	private String nonNullField;
	@Getter @Setter
	private String commonField;

	public void printString(@NonNull String str) {
		System.out.println(str);
	}


	@Test
	public void test() {
		LombokDemo2 foo = new LombokDemo2();        
		System.out.println(foo.getFinalField());      // [OUTPUT]: FINAL FIELD
		System.out.println(foo.getNonNullField());    // [OUTPUT]: NON NULL FIELD
		System.out.println(foo.getCommonField()); 

		foo.finalField = "finalField";
		foo.nonNullField = "nonNullField";
		foo.commonField = "commonField";
		System.out.println(foo.toString());

		foo.printString("null");
		//foo.printString(null);    //exception
	}


	@Test
	public void test2() throws Exception {
		@Cleanup InputStream in = new FileInputStream("/Users/fhdone/Documents/code/workspace/java2017/read.me");
		@Cleanup OutputStream out = new FileOutputStream("/Users/fhdone/Documents/code/workspace/java2017/read.me2");
		byte[] b = new byte[10000];
		while (true) {
			int r = in.read(b);
			if (r == -1) break;
			out.write(b, 0, r);
		}
	}

}
