package com.fhdone.paper2019.export;

import java.io.OutputStream;
import java.util.List;

/**
 * 类TableExport.java的实现描述：导出表格
 */
public interface TableExport {
	
	public void defineTitle(Object[] row);
	public void defineTitle(Object[] row, DataFormat[] dataFormats);

	public void addRow(Object[] row);
	public void addRow(Object[] row, DataFormat[] dataFormats);
	public void addRows(List<Object[]> rows);
	public void addRows(List<Object[]> rows, DataFormat[] dataFormats);
	
	public void addRow(String sheetName, Object[] row);
	public void addRow(String sheetName, Object[] row, DataFormat[] dataFormats);
	
	public void setSheetName(String sheetName);
	public String getSheetName();
	public void export(OutputStream output);
}