package com.fhdone.paper2019.export.poi;

import com.fhdone.paper2019.export.DataFormat;
import com.fhdone.paper2019.export.DataFormatFactory;
import com.fhdone.paper2019.export.TableExport;
import org.apache.poi.hssf.usermodel.*;

import java.io.OutputStream;
import java.util.*;
import java.util.Map.Entry;

public class PoiExcelTableXlsExport implements TableExport {

	private PoiDataFormatXlsFactory dataformatFactory;
	private Map<String,List<ExcelRow>> exlObjectMap  = new LinkedHashMap<String,List<ExcelRow>>();
	private String sheetName = "未定义";
    private ExcelRow titleRow;

	@Override
    public void defineTitle(Object[] row) {
		ExcelRow er = new ExcelRow(row,null);
		this.titleRow = er;
	}

    @Override
	public void defineTitle(Object[] row,DataFormat[] dataFormats) {
		ExcelRow er = new ExcelRow(row,dataFormats);
		this.titleRow = er;
	}

    @Override
	public void addRow(Object[] row) {
		this.addRow(sheetName, row);
	}

    @Override
	public void addRow(Object[] row, DataFormat[] dataFormats) {
		this.addRow(sheetName, row, dataFormats);
	}

    @Override
	public void addRows(List<Object[]> rows) {
		for(Object[] oArrar:rows ){
			this.addRow(sheetName, oArrar);
		}		
	}

    @Override
	public void addRows(List<Object[]> rows, DataFormat[] dataFormats) {
		for(Object[] oArrar:rows ){
			this.addRow(sheetName, oArrar, dataFormats);
		}		
	}

    @Override
	public void addRow(String sheetName, Object[] row) {
		ExcelRow er = new ExcelRow(row,null);
		putRowToMap(sheetName, er);
	}

    @Override
	public void addRow(String sheetName, Object[] row, DataFormat[] dataFormats) {
		ExcelRow er = new ExcelRow(row,dataFormats);
		putRowToMap(sheetName, er);
	}

	private void putRowToMap(String sheetName, ExcelRow er) {
		if(exlObjectMap.containsKey(sheetName)) {
			exlObjectMap.get(sheetName).add(er);
		}
		else {
			List<ExcelRow> exlObject = new ArrayList<ExcelRow>();
			exlObject.add(er);
			exlObjectMap.put(sheetName, exlObject);
		}
	}

    @Override
	public void export(OutputStream output) {
		HSSFWorkbook workbook;
		HSSFSheet sheet;
		try {
			workbook = new HSSFWorkbook();
			int rowNum = 0;
			for (Entry<String, List<ExcelRow>> entry : exlObjectMap.entrySet()) {
				String key = entry.getKey();
				sheet = workbook.createSheet(key);
				rowNum = 0;
				//add title row
				if(titleRow!=null) {
					this.addRowToExl(this.titleRow, workbook, sheet,rowNum++);	
				}
				List<ExcelRow> listExcelRow = entry.getValue();
				for(ExcelRow e : listExcelRow){
					this.addRowToExl(e,workbook, sheet,rowNum++);	
				}
			}

			workbook.write(output);
			workbook.close();
			output.close();

		}catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}

    @Override
	public String getSheetName() {
		return sheetName;	
	}

    @Override
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;	
	}

	private void addRowToExl(ExcelRow er , HSSFWorkbook wb , HSSFSheet sheet , int rowNum ) throws Exception {
		if(dataformatFactory==null) {
			dataformatFactory = new PoiDataFormatXlsFactory(wb);
		}
		String wcfStr = null;
		if(er.getDf()!=null) {
			for(DataFormat df : er.getDf()) {
				wcfStr = df.getFormat();
			}
		}
		HSSFCellStyle hcs = dataformatFactory.getCellForamtMap().get(wcfStr);
		if(hcs==null) {
			hcs = dataformatFactory.getCellForamtMap().get(DataFormatFactory.NORMAL);
		}

		Object[] objArray = er.getRowData();
		int cellIndex = 0;
		HSSFRow row = sheet.createRow(rowNum);
		for(Object obj:objArray){
			if( obj != null){
				if(String.class.isAssignableFrom(obj.getClass())){
					//字符串
					HSSFCell cell = row.createCell(cellIndex++);
					cell.setCellValue(obj.toString());   
					cell.setCellStyle(hcs);
				}else if(Long.class.isAssignableFrom(obj.getClass())
						||Integer.class.isAssignableFrom(obj.getClass())){
					//整形
					HSSFCell cell = row.createCell(cellIndex++);  
					cell.setCellValue( Double.valueOf(obj.toString()) );
					cell.setCellStyle(hcs);
				}else if(Number.class.isAssignableFrom(obj.getClass())){
					//浮点型
					HSSFCell cell = row.createCell(cellIndex++);  
					cell.setCellValue( Double.valueOf(obj.toString()) );
					hcs.setDataFormat(HSSFDataFormat.getBuiltinFormat("#0.00"));
					cell.setCellStyle(hcs);
				}else if(Date.class.isAssignableFrom(obj.getClass())){
					//日期型
					HSSFCell cell = row.createCell(cellIndex++);  
					cell.setCellValue((Date)obj); 
					hcs.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
					cell.setCellStyle(hcs);
				}else{
					row.createCell(cellIndex++).setCellValue(obj.toString()); 
				}
			}
		}
	}


	private class  ExcelRow{
		private Object[] rowData;
		private DataFormat[] df;

		public Object[] getRowData() {
			return rowData;
		}

		public void setRowData(Object[] rowData) {
			this.rowData = rowData;
		}

		public DataFormat[] getDf() {
			return df;
		}

		public void setDf(DataFormat[] df) {
			this.df = df;
		}

		public ExcelRow(Object[] rowData,DataFormat[] df){
			this.rowData = rowData;
			this.df = df;
		}
	}

}
