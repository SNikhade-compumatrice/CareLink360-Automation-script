package com.carelink360Package;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

   public class StartUp1 {
		public static WebDriver driver;
		public static FileInputStream objfile=null,excelfile=null, propertyfile=null;
		public static FileOutputStream excelfile1=null;
		public static Properties obj=null;
		public static  Actions action;
		public static WebDriverWait wait; 
		public static Workbook wb;
		public static Sheet SignUpmanualorg=null,Login=null,AddUser=null,AddNewrecipient=null, Recipient=null, AddContacts=null, EditContacts=null;
		public static String RFNameregex = "[a-zA-Z]+\\.?";
		public static String RLNameregex = "[a-zA-Z]+\\.?";
		public static String Rphregex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		public static String Reidregex = "^[_A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+(\\.[A-Za-z]{2,}|com|net)$";
		public static String Rcityregex = "[a-zA-Z]+\\.?";
	    public static String Rzipregex = "\\d{5}(-\\d{4})?";
	    
	    public static void start(String url) throws IOException, InvalidFormatException, InterruptedException
	    {
	    	
	    	System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe" );
	        driver= new ChromeDriver();
	        Thread.sleep(3000);
	    	//driver= new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(700, TimeUnit.SECONDS);
	    	driver.manage().window().maximize();
	    	driver.get(url);
	    	wait = new WebDriverWait(driver, 300);
	 	    action=new Actions(driver); 
	 	    obj = new Properties();   
	 	    objfile = new FileInputStream(".\\ConfigFileforObjects\\Locators.properties");
	 	    obj.load(objfile); 
	 	    excelfile=new FileInputStream(".\\TestData\\Automation1.xlsx");
	    //  excelfile=new FileInputStream("E:\\selenium\\Carelink360\\Automation.xlsx");
	 	    wb=WorkbookFactory.create(excelfile);
	        Login= wb.getSheet("Login");
	        AddUser=wb.getSheet("AddUser");
	        AddNewrecipient=wb.getSheet("AddNewRecipient");
	        Recipient=wb.getSheet("EditRecipient");
	        AddContacts=wb.getSheet("AddContacts");
	        EditContacts=wb.getSheet("EditContacts");
	    }
	   
	    public void quit()
	    {
	   	 driver.quit();
	    }
	    
	    //**********To check login*************************
	    public static void login() throws InterruptedException
	    {
	    	
	    	WebElement Username=driver.findElement(By.xpath(obj.getProperty("Admin_EmailAddress")));
	    	
	 	   WebElement AdPass=driver.findElement(By.xpath(obj.getProperty("Admin_Password")));
	 	   int rowct=Login.getLastRowNum();
	 	   int j=0;
	 	   Username.sendKeys(Login.getRow(rowct).getCell(j).getStringCellValue());	   
	 	   AdPass.sendKeys(Login.getRow(rowct).getCell(j+1).getStringCellValue());
	 	   Thread.sleep(3000);
	 	   WebElement SignIn=driver.findElement(By.xpath(obj.getProperty("SignInButton")));
	 	   SignIn.click();
	 	   Thread.sleep(4000);
	 	   try
	 	   {
	 		   String org=Login.getRow(rowct).getCell(j+2).getStringCellValue();
	 		   
	 				  
	 		 driver.findElement(By.xpath("//html/body/div[4]/div/div/div[2]/ul/li/label/input[@value='"+org+"']")).click();
	 		 Thread.sleep(4000);
	 		 driver.findElement(By.xpath(obj.getProperty("OkButton"))).click();
	 		 }
	 		 catch(Throwable t)
	 		 {
	 		 System.out.println("organization is empty");
	 		 }
	 	     Thread.sleep(10000);
	    }
	    
	    static String cellvalue;
		public static String excelvalidation(Cell cell) throws InvalidFormatException, IOException
		  {
		  
		 switch (cell.getCellType())
	     {

	                 case Cell.CELL_TYPE_FORMULA:
	                          
	                	         cellvalue = cell.getCellFormula();
	                	        break;
	                 case Cell.CELL_TYPE_NUMERIC:
	                            int x=(int)cell.getNumericCellValue();
	                            cellvalue=Integer.toString(x);
	                	        
	                            break;
	                 case Cell.CELL_TYPE_STRING:
	                             cellvalue=cell.getStringCellValue();
	                              
		              case Cell.CELL_TYPE_BLANK:
	                           
	                             cellvalue = cell.getStringCellValue();
	                             break;   

	                 default:
	     }
		 return cellvalue; 
		  } 
	 
		  
	  }

		


