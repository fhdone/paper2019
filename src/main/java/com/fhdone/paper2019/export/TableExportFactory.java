package com.fhdone.paper2019.export;


import com.fhdone.paper2019.export.poi.PoiExcelTableXlsExport;
import com.fhdone.paper2019.export.poi.PoiExcelTableXlsxExport;

/**
 * 类TableExportFactory.java的实现描述：导出类的工厂类 
 */
public abstract class TableExportFactory {

    public static TableExport createPoiExcelTableExport(){
        return new PoiExcelTableXlsExport();
    }

    public static TableExport createPoiExcelTableExport(String extName){
        if("xlsx".equalsIgnoreCase(extName)){
            return new PoiExcelTableXlsxExport();
        }else  if("xls".equalsIgnoreCase(extName)){
            return new PoiExcelTableXlsExport();
        }
        return new PoiExcelTableXlsxExport();
    }
}
