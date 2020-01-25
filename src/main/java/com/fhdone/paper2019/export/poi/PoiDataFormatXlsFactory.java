package com.fhdone.paper2019.export.poi;


import com.fhdone.paper2019.export.DataFormatFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成单元格格式
 */
public class PoiDataFormatXlsFactory implements DataFormatFactory<HSSFCellStyle> {

	private  HSSFWorkbook wb;
	private  HashMap<String,HSSFCellStyle> cellForamtMap;
	private  HSSFCellStyle titleFormat;
	private  HSSFCellStyle normalFormat;
	private  HSSFCellStyle largeFormat;

	// poi 字体只能new一个
	private HSSFFont titleFont;
	private HSSFFont normalFont;
	private HSSFFont largeFont;

	private HSSFCellStyle titleCellStyle;
	private HSSFCellStyle normalCellStyle;
	private HSSFCellStyle largeCellStyle;

	public PoiDataFormatXlsFactory(HSSFWorkbook wb) {
		super();
		this.wb = wb;
		this.titleFont = wb.createFont();
		this.normalFont = wb.createFont();
		this.largeFont = wb.createFont();
	}

	@Override
	public Map<String, HSSFCellStyle> getCellForamtMap() throws Exception {
		if(cellForamtMap==null) {
			cellForamtMap = new HashMap<>();
			cellForamtMap.put(DataFormatFactory.NORMAL, NormalFormat() );
			cellForamtMap.put(DataFormatFactory.LARGE, LargeFormat() );
			cellForamtMap.put(DataFormatFactory.TITLT, TitleFormat() );
		}
		return cellForamtMap;
	}

	private HSSFCellStyle TitleFormat( )  {
		if(titleFormat==null) {
			titleFont.setFontHeightInPoints((short)10);
			titleFont.setFontName("Arial");
			titleFont.setBold(true);
			titleCellStyle = wb.createCellStyle();   
			titleCellStyle.setFont(titleFont);
			titleCellStyle.setAlignment(HorizontalAlignment.CENTER);  
			titleFormat = titleCellStyle;			
		}
		return titleFormat;
	}

	private HSSFCellStyle NormalFormat( )  {
		if(normalFormat==null) {
			normalFont.setFontHeightInPoints((short)10);
			normalFont.setFontName("Arial");
			normalFont.setBold(true);
			normalCellStyle = wb.createCellStyle();   
			normalCellStyle.setFont(titleFont);
			normalCellStyle.setAlignment(HorizontalAlignment.CENTER);  
			normalFormat = normalCellStyle;
		}
		return normalFormat;
	}

	private HSSFCellStyle LargeFormat( ) {
		if(largeFormat==null) {
			largeFont.setFontHeightInPoints((short)20);
			largeFont.setFontName("Arial");
			largeFont.setBold(true);
			largeCellStyle = wb.createCellStyle(); 
			largeCellStyle.setFont(largeFont);
			largeCellStyle.setAlignment(HorizontalAlignment.CENTER);  
			largeFormat = largeCellStyle;
		}
		return largeFormat;
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}



}
