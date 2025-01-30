package e_Com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	//public static final int IMPLICIT_WAIT_TIME = 5;
	public static final int IMPLICIT_WAIT_TIME = 5;

	public static String emailtimestamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "simj" + timestamp + "@gmail.com";

	}
	
	public static Object[] [] getTestDataFromExcel(String sheetName) {
		
		XSSFWorkbook workbook=null;
		
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\e_Com\\testData\\E-CommerseWeb.xlsx");
		
		try {
		FileInputStream fisExcel = new FileInputStream(excelFile);	
		workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet= workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i = 0; i<rows; i++) {
			XSSFRow row =sheet.getRow(i+1);
			
			for(int j = 0; j<cols; j++) {
				XSSFCell cell= row.getCell(j);
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
					 
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}
	
	public static String captureScreenShot(WebDriver driver,String testName) {
		File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destPath= System.getProperty("user.dir")+ "\\Screenshots\\"+testName+".png";
		
		try {
			FileHandler.copy(source, new File(destPath));
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		return destPath;
	}

}
