package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;

	public static String TESTDATA_SHEET_PATH = "/Users/udayabhaskar/Documents/eclipseWorkspace/SauceDemo_POM_SDET/src/main/java/testdata/SauceDemoTestData.xlsx";

	
	public static Object[][] getTestData(String sheetName){
		
		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);//"UserCredentials"
			
			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			int lastRow = sheet.getLastRowNum();
			
			for(int i = 0 ; i < lastRow ; i++) {
				for(int j = 0; j < sheet.getRow(0).getLastCellNum() ; j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			fis.close();
			return data;
		}catch(Exception e) {
			
		}
		return null;
	}
	
	public static void writeDataToExcel(String sheetName,String testMethodName) {
		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
			
			int lastRow = sheet.getLastRowNum();
			
			for(int i = 1 ; i <= lastRow ; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.createCell(i+1);
				
				cell.setCellValue(testMethodName);
				
			}
			FileOutputStream fos = new FileOutputStream(TESTDATA_SHEET_PATH);
			book.write(fos);
			fos.close();
		}catch(Exception e) {
			
		}
	}

}
