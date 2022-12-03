package com.walmart.ecom.ui.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//sachn
		//tendulkar
		//test
		//123
		
//write
		//test
		//learn
		//eeee


        FileInputStream in  = new FileInputStream(new File("/Users/venkat/Documents/WalmarteCommerceDemo/walmarteCom/TestData/sample/TestData_Items.xlsx"));
        Workbook wb = new XSSFWorkbook(in);
        Sheet sh =  wb.getSheetAt(0);
        System.out.println(sh);
        
        String textToInsert = "TEXTISREADYTOINSERT";
        
        
        int onOfPhysicalRows = sh.getPhysicalNumberOfRows();
        
        for (int i=sh.getFirstRowNum();i<=sh.getLastRowNum();i++){
            
            Row currentRow = sh.getRow(i);
            if(currentRow == null){
                continue;
            }
            
            Cell tcCell = currentRow.getCell(0);
            String name = tcCell.getStringCellValue();
            if(!name.equalsIgnoreCase("sample")){
                continue;
            }
        
            int noOfColumns = currentRow.getPhysicalNumberOfCells();
            for(int j=currentRow.getFirstCellNum();j<=currentRow.getLastCellNum();j++ ){
            	
            	
            	
            	
                
                Cell cell = currentRow.getCell(j);
                if(cell == null){
                    continue;
                }
                
                
                String value = cell.getStringCellValue();
                
                
                System.out.println(value); 

                
                
                currentRow.createCell(1).setCellValue("NGIKHN8");
                
                
                
            }  
           // currentRow.createCell(2).setCellValue("Annamayya");

            in.close();
            
            FileOutputStream outputStream = new FileOutputStream("/Users/venkat/Documents/WalmarteCommerceDemo/walmarteCom/TestData/sample/TestData_Items.xlsx");
            wb.write(outputStream);
            wb.close();
            outputStream.close();

        }
	}

}
