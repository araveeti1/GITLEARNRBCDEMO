package com.walmart.ecom.ui.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelHandler {
	

	
	public ExcelHandler(){
	}
	
	public static  Properties p;
	public static  Properties p1;

	public LinkedList<Properties> list;
	
	public static Properties aTestData = new Properties();
        
   public LinkedList<Properties> readProperties(String aMethod,String aFile) throws IOException{
        FileInputStream in  = new FileInputStream(new File(aFile));
        Workbook wb = new XSSFWorkbook(in);
        Sheet sh =  wb.getSheetAt(0);
        System.out.println(sh);
        
         list = new LinkedList<Properties>();
        
        int onOfPhysicalRows = sh.getPhysicalNumberOfRows();
        
        for (int i=sh.getFirstRowNum();i<=sh.getLastRowNum();i++){
            
            Row currentRow = sh.getRow(i);
            if(currentRow == null){
                continue;
            }
            
            Cell tcCell = currentRow.getCell(0);
            String name = tcCell.getStringCellValue();
            if(!name.equalsIgnoreCase(aMethod)){
                continue;
            }
        
            int noOfColumns = currentRow.getPhysicalNumberOfCells();
            
             p = new Properties();
            for(int j=currentRow.getFirstCellNum();j<=currentRow.getLastCellNum();j++ ){
            	
            	
                
                Cell cell = currentRow.getCell(j);
                if(cell == null){
                    continue;
                }
                
                
                String value = cell.getStringCellValue();
                
            	System.out.println(value);
            	
                int index = value.indexOf("=");
                if(index != -1){
                String Key = value.substring(0,index);
                String valueForKey = value.substring(index+1);
                p.setProperty(Key, valueForKey);
            }
            }
            
            
        list.add(p); 
        aTestData = p;
    }
        
      ///  System.out.println(p.getProperty("age"));
        
        System.out.println(list.size());
        
    return list;    
    }
    	
	
    
	
}
