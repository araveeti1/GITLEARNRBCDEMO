package com.walmart.ecom.ui.core;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

//import au.com.bytecode.opencsv.CSVReader;

public class ReadCsvFiles {

 public static void main(String[] args) throws Exception {
	 //losing
	 //lost
	 //lost1
	 //lost2
    
 // This will load csv file 
 CSVReader reader = new CSVReader(new FileReader("/Users/venkat/Downloads/SampleCSVFile_53000kb.csv"));

 // this will load content into list
  List<String[]> li=reader.readAll();
  System.out.println("Total rows which we have is "+li.size());
            
 // create Iterator reference
  Iterator<String[]>i1= li.iterator();
    
 // Iterate all values 
 while(i1.hasNext()){
     
 String[] str=i1.next();
   
 System.out.print(" Values are ");

 for(int i=0;i<str.length;i++)
{

   System.out.print(" "+str[i]);
   
   if(str[i].contains("Global Commerce")) {
	   
	 System.out.println("PASS");  
	   
   }
  // else {
	   
	//   System.out.println("FAIL");
   //}

}
   System.out.println("   ");
     
    
}

}

}
