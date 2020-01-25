package com.fhdone.paper2019.export.poi;


import com.fhdone.paper2019.export.DataFormatFactory;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成单元格格式
 */
public class PoiDataFormatXlsxFactory implements DataFormatFactory<XSSFCellStyle> {

	private XSSFWorkbook wb;
	private  HashMap<String,XSSFCellStyle> cellForamtMap;
	private  XSSFCellStyle titleFormat;
	private  XSSFCellStyle normalFormat;
	private  XSSFCellStyle largeFormat;

	// poi 字体只能new一个
	private XSSFFont titleFont;
	private XSSFFont normalFont;
	private XSSFFont largeFont;

	private XSSFCellStyle titleCellStyle;
	private XSSFCellStyle normalCellStyle;
	private XSSFCellStyle largeCellStyle;

	public PoiDataFormatXlsxFactory(XSSFWorkbook wb) {
		super();
		this.wb = wb;
		this.titleFont = wb.createFont();
		this.normalFont = wb.createFont();
		this.largeFont = wb.createFont();
	}

	@Override
	public Map<String, XSSFCellStyle> getCellForamtMap() throws Exception {
		if(cellForamtMap==null) {
			cellForamtMap = new HashMap<>();
			cellForamtMap.put(DataFormatFactory.NORMAL, NormalFormat() );
			cellForamtMap.put(DataFormatFactory.LARGE, LargeFormat() );
			cellForamtMap.put(DataFormatFactory.TITLT, TitleFormat() );
		}
		return cellForamtMap;
	}

	private XSSFCellStyle TitleFormat( )  {
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

	private XSSFCellStyle NormalFormat( )  {
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

	private XSSFCellStyle LargeFormat( ) {
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

	public XSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(XSSFWorkbook wb) {
		this.wb = wb;
	}



}
