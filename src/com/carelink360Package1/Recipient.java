package com.carelink360Package1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.carelink360Package.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
public class Recipient extends StartUp1
{
	private static String RfnVal, RlnVal, RPhnoVal, RemailVal, RaddressVal, RcityVal, RstateVal, RzipVal, RrecIdVal;
	private static WebElement Rfn ,Rln,Rphno, Remail,Raddress,Rcity, Rstate,Rzip,Rid, RSave, RCancel,BeconList_left,BeconList_right ,RightMove_Button, Latitude, Longitude;
    private static WebElement Becon_dropdown, BeconList_SelectId, Move_Forword, Move_Backword, LatitudeLongitude_Link,ErrMsg;
    private static Select sel;
	private static String becon;
	private static WebElement ERfn, ERln, ERPhno, ERemail;
	private static String ERfnVal, ERlnVal, ERPhnoVal;
	private static String Cfn_Val, Cln_Val, CPhno_Val, Cemail_Val,ECfn_Val, ECln_Val, ECPhno_Val, ECemail_Val ;
	private static WebElement Cfn,Cln,CPhno,Cemail, AddContact_Btn, CSave, EditContact_Btn, ECfn, ECln, ECPhno, ECemail, ECSave;
	private static WebElement SearchById;
	@BeforeTest
	public void beforeTest() throws InvalidFormatException, IOException, InterruptedException 
	{
		start("https://cm-dev5.cloudapp.net:8443/#/");
		login();
		Thread.sleep(5000);
		  
	}
	
	//****************To verify that Add New Recipient page is displayed*********************
    @Test(priority=0)
	public void AddRecipientPage_Displayed() throws InvalidFormatException, IOException, InterruptedException 
	{
		Thread.sleep(5000);
		driver.findElement(By.xpath(obj.getProperty("AdminTab"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(obj.getProperty("AddNewRecipient"))).click();
		Thread.sleep(5000);
		String title1=driver.getTitle();
		String ActualTitle="Add Recipients";
		boolean res6=title1.contentEquals(ActualTitle);
		Assert.assertTrue(res6, "Add New Recipient page is not displayed");
		 
	}
  //****************To verify placeholder on Add New Recipient*********************
    @Test(priority=1)
	public void AddRecipient_Placeholder() throws InvalidFormatException, IOException, InterruptedException 
	{
    	Rfn=driver.findElement(By.xpath(obj.getProperty("FirstName")));
		Rln=driver.findElement(By.xpath(obj.getProperty("LastName"))); 
		Rphno=driver.findElement(By.xpath(obj.getProperty("PhoneNo")));
		Remail= driver.findElement(By.xpath(obj.getProperty("Email")));
		Raddress=driver.findElement(By.xpath(obj.getProperty("Addr")));
		Rcity=driver.findElement(By.xpath(obj.getProperty("Cty")));
		Rstate=driver.findElement(By.xpath(obj.getProperty("State")));
		Rzip=driver.findElement(By.xpath(obj.getProperty("Zip")));
		LatitudeLongitude_Link=driver.findElement(By.xpath(obj.getProperty("LatitudeLongitude")));
		Latitude=driver.findElement(By.xpath(obj.getProperty("Latitude")));
		Longitude=driver.findElement(By.xpath(obj.getProperty("Longitude")));
		BeconList_left=driver.findElement(By.xpath(obj.getProperty("BeconList_Left")));
		BeconList_right=driver.findElement(By.xpath(obj.getProperty("BeconList_Right")));
		Move_Forword=driver.findElement(By.xpath(obj.getProperty("MoveForward")));
		BeconList_SelectId=driver.findElement(By.xpath(obj.getProperty("SelectBeconId")));
		RightMove_Button=driver.findElement(By.xpath(obj.getProperty("MoveForward")));
		Becon_dropdown=driver.findElement(By.xpath(obj.getProperty("BeconDropDown")));
		Rid=driver.findElement(By.xpath(obj.getProperty("RecipientId")));
		Assert.assertEquals(Rfn.getAttribute("placeholder"), "First Name");
		Assert.assertEquals(Rln.getAttribute("placeholder"), "Last Name");
		Assert.assertEquals(Rphno.getAttribute("placeholder"), "Phone No");
		Assert.assertEquals(Remail.getAttribute("placeholder"), "Email Address");
		Assert.assertEquals(Raddress.getAttribute("placeholder"), "Address");
		Assert.assertEquals(Rcity.getAttribute("placeholder"), "City");
		Assert.assertEquals(Rzip.getAttribute("placeholder"), "ZIP Code");
		Assert.assertEquals(Latitude.getAttribute("placeholder"), "Latitude");
		Assert.assertEquals(Longitude.getAttribute("placeholder"), "Longitude");
		Assert.assertEquals(Rid.getAttribute("placeholder"), "Recipient ID");
	}
  
	 
	//**********************To check validation on Add New Recipient page******************
	@Test(priority=2)
	public void AddRecipient_ValidEvents() throws InvalidFormatException, IOException, InterruptedException 
	{
		String lc_Rfncellvalue="", lc_Rlncellvalue="",  lc_Rphnocellvalue="", lc_Remailcellvalue="";
		String lc_Raddresscellvalue="", lc_Rcitycellvalue="", lc_Rstatecellvalue="", lc_Rzipcellvalue="";
		String lc_Rbeaconcellvalue="",lc_Ridcellvalue="";
		    
		
		RSave=  driver.findElement(By.xpath(obj.getProperty("RSave")));
		RCancel=driver.findElement(By.xpath(obj.getProperty("RCancel")));

		//************ Get valid and invalid data from excel from excel sheet************* 
		int addrecrct=AddNewrecipient.getLastRowNum();
		for (int i=1; i<=addrecrct; i++)
		{  
		   	int j=0;
		    int count=0;
		    
		    try
		    {
		    	lc_Rfncellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j)); 
		    	Rfn.sendKeys(lc_Rfncellvalue); 
		    	
					if(lc_Rfncellvalue.matches(RFNameregex)==false)
					{
						((JavascriptExecutor)driver).executeScript("alert('Invalid Recipient First Name');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
					else
					{
						Rfn.clear();
						Rfn.sendKeys(lc_Rfncellvalue);
						RfnVal=lc_Rfncellvalue;
		    		}
		    }//end of try
		    
		    catch(NullPointerException ex)
		    {
		    	Rfn.sendKeys("a");
			    Rfn.clear();
			    ((JavascriptExecutor)driver).executeScript("alert('Recipient First Name is Empty');");
			    Thread.sleep(3000);
			    driver.switchTo().alert().accept();
			    count=count+1;
		    }
		    
		    try
		    {
		    	lc_Rlncellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+1));
		    	Rln.sendKeys(lc_Rlncellvalue);
					if(lc_Rlncellvalue.matches(RLNameregex)==false)
					{
						((JavascriptExecutor)driver).executeScript("alert('Invalid User Last Name');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
					else
					{
						Rln.clear();
						Rln.sendKeys(lc_Rlncellvalue);
						RlnVal=lc_Rlncellvalue;
					}
		    }
		    
		    catch(NullPointerException ex)
		    {
		    	Rln.sendKeys("a");
			    Rln.clear();
			    ((JavascriptExecutor)driver).executeScript("alert('User Last Name is Empty');");
			    Thread.sleep(3000);
			    driver.switchTo().alert().accept();
			    count=count+1;
		    }
		
		    try
		    {
		    	lc_Rphnocellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+2));//To get Phone number from excel sheet
		    	System.out.println(lc_Rphnocellvalue);
		    	Rphno.sendKeys(lc_Rphnocellvalue);
					if(lc_Rphnocellvalue.matches(Rphregex)==false)
					{
						((JavascriptExecutor)driver).executeScript("alert('Invalid Phone Number');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
					else
					{
						Rphno.clear();
						Rphno.sendKeys(lc_Rphnocellvalue);
						RPhnoVal=lc_Rphnocellvalue;
					}
		    }
		    catch(NullPointerException ex)
		    {
		    	Rphno.sendKeys("a");
			    Rphno.clear();
			    ((JavascriptExecutor)driver).executeScript("alert('Phone Number is empty');");
			    Thread.sleep(3000);
			    driver.switchTo().alert().accept();
			    count=count+1;
		    }
		    
		    	lc_Remailcellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+3));
		    	if(lc_Remailcellvalue.equals(""))
		    	{
					Remail.sendKeys("a");
				    Remail.clear();
				    ((JavascriptExecutor)driver).executeScript("alert('Email Address is empty');");
				    Thread.sleep(3000);
				    driver.switchTo().alert().accept();
				    count=count+1;
		    	}
		        else
		    	{
					Remail.sendKeys(lc_Remailcellvalue);
						if(lc_Remailcellvalue.matches(Reidregex)==false)
						{
							((JavascriptExecutor)driver).executeScript("alert('Invalid Email Address');");
							Thread.sleep(3000);
							driver.switchTo().alert().accept();
							count=count+1;
						}
						else
						{
							Remail.clear();
							Remail.sendKeys(lc_Remailcellvalue);
							RemailVal=lc_Remailcellvalue;
						}
		    	}
		    	
				lc_Raddresscellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+4));
				Raddress.sendKeys(lc_Raddresscellvalue);
			    	if(lc_Raddresscellvalue.equals(""))
			    	{
			    		Raddress.sendKeys("a");
					    Raddress.clear();
					    ((JavascriptExecutor)driver).executeScript("alert('User Address Empty');");
					    Thread.sleep(3000);
					    driver.switchTo().alert().accept();
					    count=count+1;
			    	}
			    	else
			    	{
			    		Raddress.clear();
			    		Raddress.sendKeys(lc_Raddresscellvalue);
			    		RaddressVal=lc_Raddresscellvalue;
			    	} 
				try
				{
					lc_Rcitycellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+5));
					Rcity.sendKeys(lc_Rcitycellvalue);
			   
					if(lc_Rcitycellvalue.matches(Rcityregex)==false)
			    	{
			    		((JavascriptExecutor)driver).executeScript("alert('Invalid City');");
						Thread.sleep(3000);
					    driver.switchTo().alert().accept();
					    count=count+1;
			    	}
			    	else
			    	{
			    		Rcity.clear();
			    		Rcity.sendKeys(lc_Rcitycellvalue);
			    		RcityVal=lc_Rcitycellvalue;
			    	}
				}		
				catch(NullPointerException ex)
				{
					Rcity.sendKeys("a");
					Rcity.clear();
					((JavascriptExecutor)driver).executeScript("alert('User City Empty');");
					Thread.sleep(3000);
					driver.switchTo().alert().accept();
					count=count+1;
				}
			   
				try
				{
				    lc_Rstatecellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+6));
				    Select slt2=new Select(Rstate);
					List<WebElement> states=slt2.getOptions();
					int e3count=0;
					    for(WebElement e3:states)
					    {
							if(e3.getText().equals(lc_Rstatecellvalue))
							{
					    	   e3count=1;
							}
							else
							{
							}
					    }
					    if(e3count==1)
					    {
					    	Rstate.click();
					    	Thread.sleep(2000);
					    	slt2.selectByVisibleText(lc_Rstatecellvalue);
					    	RstateVal=lc_Rstatecellvalue;
				    		System.out.println(RstateVal);
					    }
					    else
					    {
					    	((JavascriptExecutor)driver).executeScript("alert('State is invalid');");
						    Thread.sleep(3000);
						    driver.switchTo().alert().accept();
						    count=count+1;	 
					    }
				}
				catch(NullPointerException ex)
				{
				    	 
				    ((JavascriptExecutor)driver).executeScript("alert('State is Empty');");
				    Thread.sleep(3000);
				    driver.switchTo().alert().accept();
				    count=count+1;
				} 
			    	  
				try
				{
				   	lc_Rzipcellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+7));
				   	Rzip.sendKeys(lc_Rzipcellvalue);
				   	if(lc_Rzipcellvalue.matches(Rzipregex)==false)
				   	{
				 		((JavascriptExecutor)driver).executeScript("alert('Invalid ZIP Code');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
					else
					{
					    Rzip.clear();
					    Rzip.sendKeys(lc_Rzipcellvalue);
					    RzipVal=lc_Rzipcellvalue;    		
					}
				}
				catch(NullPointerException ex)
				{
					    Rzip.sendKeys("a");
						Rzip.clear();
						((JavascriptExecutor)driver).executeScript("alert('ZIP Code is empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
				} 
						   
				try
				{
					    lc_Ridcellvalue=excelvalidation(AddNewrecipient.getRow(i).getCell(j+8));
					    Rid.sendKeys(lc_Ridcellvalue);
					    RrecIdVal=lc_Ridcellvalue;  	
							
				}
				catch(NullPointerException ex)
				{
					    Rid.sendKeys("a");
						Rid.clear();
						((JavascriptExecutor)driver).executeScript("alert('Recipient ID is Empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
				}
					 
		Row row = AddNewrecipient.getRow(i);
		Cell invalidcell=row.createCell(j+9);
		if(count!=0)
		{
				invalidcell.setCellValue("invalid");
				RCancel.click();
			try
			{
				excelfile1= new FileOutputStream(".//TestData/Automation1.xlsx");
			    wb.write(excelfile1);
			    excelfile1.close();
			}
			catch(Throwable t )
			{
				System.out.println("File not found");
			}	 			
		}//end of if 
		else
		{
			invalidcell.setCellValue("valid");
			   
				try
				{
		 			excelfile1= new FileOutputStream(".//TestData/Automation1.xlsx");
			        wb.write(excelfile1);
			        excelfile1.close();
				}
				catch(Throwable t )
				{
				  System.out.println("File not found");
				}		  
			    LatitudeLongitude_Link.click();
			    Thread.sleep(4000);
			    String RLatVal=Latitude.getAttribute("value");
			    String RLongVal=Longitude.getAttribute("value");
			    System.out.println("Latitude is: "+RLatVal+"Longitude is: "+RLongVal);
			    BeconList_SelectId.click();
				RightMove_Button.click();
			    becon=driver.findElement(By.xpath(obj.getProperty("BeconList_Right"))).getText();
				System.out.println(becon);
				Thread.sleep(4000);
				sel=new Select(Becon_dropdown);
				sel.selectByVisibleText(becon);
				Thread.sleep(5000);
		        RSave.click();
				String Msg1=driver.findElement(By.xpath(obj.getProperty("RSuccessMsg"))).getText();
				System.out.println(Msg1);
				String ActualMsg1="Recipient Added";
				boolean res1=Msg1.matches(ActualMsg1);
      
			Assert.assertTrue(res1, Msg1);
			Thread.sleep(5000);
		}  //end of else	
		
		}//end of for loop  
		    	     
	}//end of AddRecipientPage_Displayed() method

	
	  
	//********************** Check for the duplicate Recipient Id and Email Address********
	@Test(priority=3)
	public void RecipientIdExist() throws InterruptedException
	{
	        Thread.sleep(8000);
		    Rfn.sendKeys(RfnVal);
	        Rln.sendKeys(RlnVal);
	        Rphno.sendKeys(RPhnoVal);
	        Remail.sendKeys(RemailVal);
	        Raddress.sendKeys(RaddressVal);
	        Rcity.sendKeys(RcityVal);
	        Rstate.sendKeys(RstateVal);
	        Rzip.sendKeys(RzipVal);
	        LatitudeLongitude_Link.click();
	        Thread.sleep(4000);
	        BeconList_left.click();
	        RightMove_Button.click();
	        Thread.sleep(1000);
	        Rid.sendKeys(RrecIdVal);
	        String becon1=BeconList_right.getText();
	        System.out.println(becon1);
	        sel.selectByVisibleText(becon1);
	        Thread.sleep(4000);
	        driver.findElement(By.xpath(obj.getProperty("RSave"))).click();
	        Thread.sleep(2000);
	        ErrMsg=driver.findElement(By.xpath(obj.getProperty("RErrorMsg1")));
	        String Msg2=driver.findElement(By.xpath(obj.getProperty("RErrorMsg1"))).getText();
	        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj.getProperty("ErrorMsg3"))));
	        System.out.println(Msg2);
	        String ActualMsg2="Recipient already exists.";
	        boolean res2=Msg2.matches(ActualMsg2);
	        Assert.assertTrue(res2,"Not added");
	}
	  
	
	//*****************To check Cancel button on Add New Recipient page working or not**************** 
	@Test(priority=4)
	public void CancelBtn() throws InterruptedException
	{
	        Thread.sleep(4000);
	        driver.findElement(By.xpath(obj.getProperty("RCancel"))).click();
	        Thread.sleep(4000);
	        Assert.assertTrue(Rfn.getText().equals(""),"First Name field is not Empty");
	        Assert.assertTrue(Rln.getText().equals(""),"Last Name field is not Empty");
	        Assert.assertTrue(Rphno.getText().equals(""),"Phone Number field is not Empty");
	        Assert.assertTrue(Remail.getText().equals(""),"Email field is not Empty");
	        Assert.assertTrue(Raddress.getText().equals(""),"Address field is not Empty");
	        Assert.assertTrue(Rcity.getText().equals(""),"City field is not Empty");
	        // Assert.assertTrue(Rstate.getText().equals(""),"State field is not Empty");
	        Assert.assertTrue(Rzip.getText().equals(""),"Zip field is not Empty");
	        Assert.assertTrue(Latitude.getText().equals(""),"Latitude field is not Empty");
	        Assert.assertTrue(Longitude.getText().equals(""),"Longitude field is not Empty");
	        Assert.assertTrue(BeconList_right.getText().equals(""),"BeconList field is not Empty");
	        Assert.assertTrue(Rid.getText().equals(""),"Recipient Id field is not Empty");
	        Assert.assertTrue(ErrMsg.isDisplayed()==true,"Message is not erased");
	                
    }
	
	//************Find Recipient page is displayed or not when clicking on Find tab, select Recipient option****
	@Test(priority=5)
		public void FindRecipientPage_Displayed() throws InterruptedException
		{
		    Thread.sleep(4000);      
			driver.findElement(By.xpath(obj.getProperty("Find_Tab"))).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(obj.getProperty("Find_RecipientTab"))).click();
			Thread.sleep(14000);
			String title2=driver.getTitle();
		    String ActualTitle2="Search Recipients";
		    boolean res7=title2.contentEquals(ActualTitle2);
		    Assert.assertTrue(res7, "Find Recipient page is not displayed");
	  
		}
	
	//***********Click on "Click here" link present on Find Recipient page, check it is redirecting to Add New Recipient page************  
	@Test(priority=6)
		public void Clickhere_Link() throws InterruptedException
		{
			Thread.sleep(15000);
			driver.findElement(By.xpath(obj.getProperty("ClickHereToAddRecipient"))).click();
			Thread.sleep(2000);
			String title1=driver.getTitle();
			String ActualTitle="Add Recipients";
			boolean res6=title1.contentEquals(ActualTitle);
			Assert.assertTrue(res6, "Add New Recipient page is not displayed");
		}
	
	//*****Again clicked on Find tab, select Recipient option, Recipient Search page displayed or not********
	@Test(priority=7)
		public void FindRecipientPage() throws InterruptedException
		{
			Thread.sleep(4000);      
			driver.findElement(By.xpath(obj.getProperty("Find_Tab"))).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(obj.getProperty("Find_RecipientTab"))).click();
			Thread.sleep(14000);
			String title2=driver.getTitle();
			String ActualTitle2="Search Recipients";
			boolean res7=title2.contentEquals(ActualTitle2);
			Assert.assertTrue(res7, "Find Recipient page is not displayed");
		}
	
	// ************Search for the Recipient, check Recipient Details page is displayed or not************
	 @Test(priority=8)
		public void FindRecipient() throws InterruptedException
		{
				Thread.sleep(4000);
	            SearchById=driver.findElement(By.xpath(obj.getProperty("Find_Recipient"))); 
	            Thread.sleep(15000);
	            SearchById.sendKeys(RfnVal);
	            action=new Actions(driver);
	            action.sendKeys(SearchById, Keys.BACK_SPACE).perform();
	            driver.findElement(By.xpath(obj.getProperty("Select_Recipient"))).click();
	            Thread.sleep(10000);
	            String title3=driver.getTitle();
	      		String ActualTitle3="Recipient Details";
	      		boolean res8=title3.contentEquals(ActualTitle3);
	      		Assert.assertTrue(res8, "Recipient Details page is not displayed");
	            
	    }
	
	//*********Check for the Recipient's Personnel Details with Details using while Adding recipient*****
	@Test(priority=9)
		public static void EditRecipientCheck_Details() throws InterruptedException 
		{
				ERfn=driver.findElement(By.xpath(obj.getProperty("First_Name")));
				ERln=driver.findElement(By.xpath(obj.getProperty("Last_Name")));
				ERPhno=driver.findElement(By.xpath(obj.getProperty("Phone_Number")));
				ERemail=driver.findElement(By.xpath(obj.getProperty("Email_Address")));
				String ERfn1=ERfn.getAttribute("value");
				String ERln1=ERln.getAttribute("value");
				String ERPhno1=ERPhno.getAttribute("value");
				String ERemail1= ERemail.getAttribute("value");
				System.out.println("Personnel panel Email matching result= "+ERemail1);
				Assert.assertTrue(RfnVal.equals(ERfn1), "First Name is not matching");
				Assert.assertTrue(RlnVal.equals(ERln1), "Last Name is not matching");
				Assert.assertTrue(RPhnoVal.equals(ERPhno1), "Phone Number is not matching");
				Assert.assertTrue(RemailVal.equals(ERemail1), "Email Address is not matching");
	   
		}

	
	//*******To Check for the Text Boxes in the Personnel details panel is Disabled before clicking on Edit button*****
	@Test(priority=10)
		public static void EditRecipientPanel() throws InterruptedException
		{
				Thread.sleep(4000);
				driver.findElement(By.xpath(obj.getProperty("EditButton"))).click();
				Assert.assertTrue(ERfn.isEnabled(),"Disabled");
				Assert.assertTrue(ERln.isEnabled(),"Disabled");
				Assert.assertTrue(ERPhno.isEnabled(),"Disabled");
				Assert.assertFalse(ERemail.isEnabled(),"Enabled");
		}

	
	//*********To Check for the text boxes is getting enabled after clicking on Edit button**********
	@Test(priority=11)
		public static void EditRecipient_ValidEvent() throws InterruptedException, InvalidFormatException, IOException
		{
			Thread.sleep(5000);
			String lc_ERfncellvalue="", lc_ERlncellvalue="",  lc_ERphnocellvalue="", lc_ERemailcellvalue="";
 	    
			int editrecrct=Recipient.getLastRowNum();
				for (int i=1; i<=editrecrct; i++)
				{  
					int j=0;
					int count=0;
					try
					{
						lc_ERfncellvalue=excelvalidation(Recipient.getRow(i).getCell(j));
						ERfn.clear();
						ERfn.sendKeys(lc_ERfncellvalue);
							if(lc_ERfncellvalue.matches(RFNameregex)==false)
							{
								((JavascriptExecutor)driver).executeScript("alert('Invalid Recipient First Name');");
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								count=count+1;
							}
							else
							{
								ERfn.clear();
								ERfn.sendKeys(lc_ERfncellvalue);
								ERfnVal=lc_ERfncellvalue;
							}
					}
					catch(NullPointerException ex)
					{
						ERfn.sendKeys("a");
						ERfn.clear();
						((JavascriptExecutor)driver).executeScript("alert('Recipient First Name is Empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
	    
					try
					{
						lc_ERlncellvalue=excelvalidation(Recipient.getRow(i).getCell(j+1));
						ERln.clear();
						ERln.sendKeys(lc_ERlncellvalue);
							if(lc_ERlncellvalue.matches(RLNameregex)==false)
							{
								((JavascriptExecutor)driver).executeScript("alert('Invalid Recipient Last Name');");
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								count=count+1;
							}
							else
							{
								ERln.clear();
								ERln.sendKeys(lc_ERlncellvalue);
								ERlnVal=lc_ERlncellvalue;
							}
					}
					catch(NullPointerException ex)
					{
						ERln.sendKeys("a");
						ERln.clear();
						((JavascriptExecutor)driver).executeScript("alert('Recipient Last Name is Empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
	
					try
					{
						lc_ERphnocellvalue=excelvalidation(Recipient.getRow(i).getCell(j+2));
						ERPhno.clear();
						ERPhno.sendKeys(lc_ERphnocellvalue);
							if(lc_ERphnocellvalue.matches(Rphregex)==false)
							{
								((JavascriptExecutor)driver).executeScript("alert('Invalid Phone Number');");
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								count=count+1;
							}
							else
							{
								ERPhno.clear();
								ERPhno.sendKeys(lc_ERphnocellvalue);
								ERPhnoVal=lc_ERphnocellvalue;
								System.out.println(ERPhnoVal);
							}
					}
					catch(NullPointerException ex)
					{
						ERPhno.sendKeys("a");
						ERPhno.clear();
						((JavascriptExecutor)driver).executeScript("alert('Phone Number is empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
					Row row =Recipient.getRow(i);
					Cell invalidcell=row.createCell(j+3);
					if(count!=0)
					{
						invalidcell.setCellValue("invalid");
						  
						try
						{
							excelfile1= new FileOutputStream(".\\TestData\\Automation1.xlsx");
							wb.write(excelfile1);
							excelfile1.close();
						}
						catch(Throwable t )
						{
							System.out.println("File not found");
						}	 			
						  
					}//end of if
					else
					{
						invalidcell.setCellValue("valid");
			   
						try
						{
							excelfile1= new FileOutputStream(".//TestData/Automation1.xlsx");
							wb.write(excelfile1);
							excelfile1.close();
						}
						catch(Throwable t )
						{
							System.out.println("File not found");
						}	
			   
						Thread.sleep(4000);
						driver.findElement(By.xpath(obj.getProperty("SaveChangesButton1"))).click();// click on SaveChanges button if it found valid data
					}//end of else 
					
				}//end of for loop
				
		}

	
	//***********After clicking on SaveChanges button in Edit Recipient Details panel, All the fields getting disabled or not and data is updated or not***********
	@Test(priority=12)
		public static void RecipientDetails_Update() throws InterruptedException, InvalidFormatException, IOException
		{
			Thread.sleep(4000);
			Assert.assertFalse(ERfn.isEnabled(),"Enabled");
			Assert.assertFalse(ERln.isEnabled(),"Enabled");
			Assert.assertFalse(ERPhno.isEnabled(),"Enabled");
			Assert.assertFalse(ERemail.isEnabled(),"Enabled"); 
			String UERfn=ERfn.getAttribute("value");
			String UERln=ERln.getAttribute("value");
			String UERPhno=ERPhno.getAttribute("value");
			String UERemail= ERemail.getAttribute("value");
			Assert.assertTrue(ERfnVal.equals(UERfn), "First Name is not matching");
			Assert.assertTrue(ERlnVal.equals(UERln), "Last Name is not matching");
			Assert.assertTrue(ERPhnoVal.equals(UERPhno), "Phone Number is not matching");    
	    
		}

	
	//*************To check that Add Recipient Contact pop-up is displayed or not*************
	@Test(priority=13)
		public static void AddContactPopUp_Displayed() throws InterruptedException
		{
			Thread.sleep(10000);
			AddContact_Btn=driver.findElement(By.xpath(obj.getProperty("AddButton1")));
			AddContact_Btn.click();
			Thread.sleep(1000);
			String Msg10=driver.findElement(By.xpath(obj.getProperty("PopUp_Heading"))).getText();
			String ActualHeading="Add Recipient Contacts";
			boolean res10=Msg10.contentEquals(ActualHeading);
			Assert.assertTrue(res10, "pop-up is not displayed");
			
		}

	//**************To check Add Recipient Contact for valid and invalid data****************
	@Test(priority=14)
		public static void AddContacts_Validations() throws InterruptedException, InvalidFormatException, IOException
		{
			Thread.sleep(6000);	
			Cfn= driver.findElement(By.xpath(obj.getProperty("Add_FName")));
			Cln= driver.findElement(By.xpath(obj.getProperty("Add_LName")));
			CPhno= driver.findElement(By.xpath(obj.getProperty("Add_PNo")));
			Cemail= driver.findElement(By.xpath(obj.getProperty("Add_EmailId")));
			String lc_Cfncellvalue="", lc_Clncellvalue="",  lc_Cphnocellvalue="", lc_Cemailcellvalue="";
	  	    
		    int editrecrct=AddContacts.getLastRowNum();
				for (int i=1; i<=editrecrct; i++)
				{  
					int j=0;
					int count=0;
		    
					try
					{
						lc_Cfncellvalue=excelvalidation(AddContacts.getRow(i).getCell(j));
						Cfn.sendKeys(lc_Cfncellvalue);
							if(lc_Cfncellvalue.matches(RFNameregex)==false)
							{
								((JavascriptExecutor)driver).executeScript("alert('Invalid Contact's First Name');");
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								count=count+1;
							}
							else
							{
								Cfn.clear();
								Cfn.sendKeys(lc_Cfncellvalue);
								Cfn_Val=lc_Cfncellvalue;
							}
					}
					catch(NullPointerException ex)
					{
						Cfn.sendKeys("a");
						Cfn.clear();
						((JavascriptExecutor)driver).executeScript("alert('Contact's First Name is Empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
		    
					try
					{
						lc_Clncellvalue=excelvalidation(AddContacts.getRow(i).getCell(j+1));
						Cln.sendKeys(lc_Clncellvalue);
							if(lc_Clncellvalue.matches(RLNameregex)==false)
							{
								((JavascriptExecutor)driver).executeScript("alert('Invalid Contacts Last Name');");
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								count=count+1;
							}
							else
							{
								Cln.clear();
								Cln.sendKeys(lc_Clncellvalue);
								Cln_Val=lc_Clncellvalue;
							}
					}
					catch(NullPointerException ex)
					{
						Cln.sendKeys("a");
						Cln.clear();
						((JavascriptExecutor)driver).executeScript("alert('Contact's Last Name is Empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
		
					try
					{
						lc_Cphnocellvalue=excelvalidation(AddContacts.getRow(i).getCell(j+2));
						System.out.println(lc_Cphnocellvalue);
						CPhno.sendKeys(lc_Cphnocellvalue);
							if(lc_Cphnocellvalue.matches(Rphregex)==false)
							{
								((JavascriptExecutor)driver).executeScript("alert('Invalid Phone Number');");
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								count=count+1;
							}
							else
							{
								CPhno.clear();
								CPhno.sendKeys(lc_Cphnocellvalue);
								CPhno_Val=lc_Cphnocellvalue;
							}
					}
					catch(NullPointerException ex)
					{
						CPhno.sendKeys("a");
						CPhno.clear();
						((JavascriptExecutor)driver).executeScript("alert('Phone Number is empty');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
		    lc_Cemailcellvalue=excelvalidation(AddContacts.getRow(i).getCell(j+3));
	    	
				if(lc_Cemailcellvalue.equals(""))
				{
					Cemail.sendKeys("a");
					Cemail.clear();
					((JavascriptExecutor)driver).executeScript("alert('Email Address is empty');");
					Thread.sleep(3000);
					driver.switchTo().alert().accept();
					count=count+1;
				}
				else
				{
					Cemail.sendKeys(lc_Cemailcellvalue);
					if(lc_Cemailcellvalue.matches(Reidregex)==false)
					{
						((JavascriptExecutor)driver).executeScript("alert('Invalid Email Address');");
						Thread.sleep(3000);
						driver.switchTo().alert().accept();
						count=count+1;
					}
					else
					{
						Cemail.clear();
						Cemail.sendKeys(lc_Cemailcellvalue);
						Cemail_Val=lc_Cemailcellvalue;
					}
				}
		    
				Row row = AddContacts.getRow(i);
				Cell invalidcell=row.createCell(j+4);
					if(count!=0)
					{
						invalidcell.setCellValue("invalid");
						Cfn.clear();
						Cln.clear();
						CPhno.clear();
						Cemail.clear();
							   
							try
							{
								excelfile1= new FileOutputStream(".\\TestData\\Automation1.xlsx");
								wb.write(excelfile1);
								excelfile1.close();
							}
							catch(Throwable t )
							{
								System.out.println("File not found");
							}	 			
							  
					}//end of if
					else
					{
						invalidcell.setCellValue("valid");
				   
							try
							{
								excelfile1= new FileOutputStream(".\\TestData\\Automation1.xlsx");
								wb.write(excelfile1);
								excelfile1.close();
							}
							catch(Throwable t )
							{
								System.out.println("File not found");
							}	
				   
							Thread.sleep(4000);
							CSave=driver.findElement(By.xpath(obj.getProperty("CSave")));
							CSave.click();
							Thread.sleep(3000);
					}//end of else 
					
				}//end of for loop
				
		}
	
	//***************To Check for contact already exist***************
	 @Test(priority=15)
	  public static void ContactAlready_Exist() throws InterruptedException
	   {
		   Thread.sleep(4000);
		   AddContact_Btn.click();
		   Thread.sleep(2000);
		   Cfn.sendKeys(Cfn_Val);
		   Cln.sendKeys(Cln_Val);
		   CPhno.sendKeys(CPhno_Val);
		   Cemail.sendKeys(Cemail_Val);
		   CSave.click();
		   Thread.sleep(4000);
		   String Msg11=driver.findElement(By.xpath(obj.getProperty("ContactExistMsg"))).getText();
	       String ActualMsg11="Contact already exists.";
	       boolean res11=Msg11.contentEquals(ActualMsg11);
	       Assert.assertTrue(res11, "Allow to add duplicate contacts");
		
	   }	
	 
	
	//**********To Add one more contact for the recipient 
	
	//**************To check that Edit Recipient contact pop-up displayed or not***************
	@Test(priority=15)
		public static void EditContactPopup_Displayed() throws InterruptedException
		{
		    Thread.sleep(2000);
		    EditContact_Btn=driver.findElement(By.xpath(obj.getProperty("ECBtn")));
		    EditContact_Btn.click();
		    Thread.sleep(2000);
	        String Msg10=driver.findElement(By.xpath(obj.getProperty("ECPopUp_Heading"))).getText();
	        String ActualHeading="Add Recipient Contacts";
	        boolean res10=Msg10.contentEquals(ActualHeading);
	        Assert.assertTrue(res10, "pop-up is not displayed");
		}
	 
	@Test(priority=16)
	public static void EditContactDetails() throws InterruptedException
	{
		Thread.sleep(4000);
		ECfn= driver.findElement(By.xpath(obj.getProperty("Edit_FirstName")));
		ECln= driver.findElement(By.xpath(obj.getProperty("Edit_LastName")));
		ECPhno= driver.findElement(By.xpath(obj.getProperty("Edit_PhoneNo")));
		ECemail= driver.findElement(By.xpath(obj.getProperty("Edit_Email")));
		String ECfn1=ERfn.getAttribute("value");
		String ECln1=ERln.getAttribute("value");
		String ECPhno1=ERPhno.getAttribute("value");
		String ECemail1= ERemail.getAttribute("value");
		Assert.assertTrue(Cfn_Val.equals(ECfn1), "First Name is not showing as we have added");
		Assert.assertTrue(Cln_Val.equals(ECln1), "Last Name is not showing as we have added");
		Assert.assertTrue(CPhno_Val.equals(ECPhno1), "Phone Number is not showing as we have added");
		Assert.assertTrue(Cemail_Val.equals(ECemail1), "Email is not showing as we have added");
	}
	
	//***************To Check Edit recipient details for valid and invalid data**************
	@Test(priority=17)
		public static void EditContacts_ValidEvent() throws InterruptedException, InvalidFormatException, IOException
		{
			String lc_ECfncellvalue="", lc_EClncellvalue="",  lc_ECphnocellvalue="", lc_ECemailcellvalue="";
			Thread.sleep(5000);
			
	 	    
	 	    int editcontctrct=EditContacts.getLastRowNum();
				for (int i=1; i<=editcontctrct; i++)
				{  
					int j=0;
					int count=0;
	 		     
						try
						{
							lc_ECfncellvalue=excelvalidation(EditContacts.getRow(i).getCell(j));
							ECfn.clear();
							ECfn.sendKeys(lc_ECfncellvalue);
								if(lc_ECfncellvalue.matches(RFNameregex)==false)
								{
									((JavascriptExecutor)driver).executeScript("alert('Invalid contact's First Name');");
									Thread.sleep(3000);
									driver.switchTo().alert().accept();
									count=count+1;
								}
								else
								{
									ECfn.clear();
									ECfn.sendKeys(lc_ECfncellvalue);
									ECfn_Val=lc_ECfncellvalue;
								}
						}
						catch(NullPointerException ex)
						{
							ECfn.sendKeys("a");
							ECfn.clear();
							((JavascriptExecutor)driver).executeScript("alert('Contact's First Name is Empty');");
							Thread.sleep(3000);
							driver.switchTo().alert().accept();
							count=count+1;
						}
	 	    
						try
						{
							lc_EClncellvalue=excelvalidation(EditContacts.getRow(i).getCell(j+1));
							ECln.clear();
							ECln.sendKeys(lc_EClncellvalue);
								if(lc_EClncellvalue.matches(RLNameregex)==false)
								{
									((JavascriptExecutor)driver).executeScript("alert('Invalid Contact's Last Name');");
									Thread.sleep(3000);
									driver.switchTo().alert().accept();
									count=count+1;
								}
								else
								{
									ECln.clear();
									ECln.sendKeys(lc_EClncellvalue);
									ECln_Val=lc_EClncellvalue;
								}
						}
						catch(NullPointerException ex)
						{
							ECln.sendKeys("a");
							ECln.clear();
							((JavascriptExecutor)driver).executeScript("alert('User Last Name is Empty');");
							Thread.sleep(3000);
							driver.switchTo().alert().accept();
							count=count+1;
						}
	 	
						try
						{
							lc_ECphnocellvalue=excelvalidation(EditContacts.getRow(i).getCell(j+2));
							ECPhno.clear();
							ECPhno.sendKeys(lc_ECphnocellvalue);
								if(lc_ECphnocellvalue.matches(Rphregex)==false)
								{
									((JavascriptExecutor)driver).executeScript("alert('Invalid Phone Number');");
									Thread.sleep(3000);
									driver.switchTo().alert().accept();
									count=count+1;
								}
								else
								{
									ECPhno.clear();
									ECPhno.sendKeys(lc_ECphnocellvalue);
									ECPhno_Val=lc_ECphnocellvalue;
								}
						}
						catch(NullPointerException ex)
						{
							ECPhno.sendKeys("a");
							ECPhno.clear();
							((JavascriptExecutor)driver).executeScript("alert('Phone Number is empty');");
							Thread.sleep(3000);
							driver.switchTo().alert().accept();
							count=count+1;
						}
						lc_ECemailcellvalue=excelvalidation(EditContacts.getRow(i).getCell(j+3));
						ECemail.clear();
						if(lc_ECemailcellvalue.equals(""))
						{
							ECemail.sendKeys("a");
							ECemail.clear();
							((JavascriptExecutor)driver).executeScript("alert('Email Address is empty');");
							Thread.sleep(3000);
							driver.switchTo().alert().accept();
							count=count+1;
						}
						else
						{
							ECemail.sendKeys(lc_ECemailcellvalue);
							if(lc_ECemailcellvalue.matches(Reidregex)==false)
							{
								((JavascriptExecutor)driver).executeScript("alert('Invalid Email Address');");
								Thread.sleep(3000);
								driver.switchTo().alert().accept();
								count=count+1;
							}
							else
							{
								ECemail.clear();
								ECemail.sendKeys(lc_ECemailcellvalue);
								ECemail_Val=lc_ECemailcellvalue;
								System.out.println(Cemail_Val);
							}
						}
	 	    
						Row row = EditContacts.getRow(i);
						Cell invalidcell=row.createCell(j+4);
						if(count!=0)
						{
							invalidcell.setCellValue("invalid");
							ECfn.clear();
							ECln.clear();
							ECPhno.clear();
							ECemail.clear();
	 						   
								try
								{
									excelfile1= new FileOutputStream(".\\TestData\\Automation1.xlsx");
									wb.write(excelfile1);
									excelfile1.close();
								}
								catch(Throwable t )
								{
									System.out.println("File not found");
								}	 			
	 		
						}//end of if 
						else
						{
							invalidcell.setCellValue("valid");
	 			   
								try
								{
									excelfile1= new FileOutputStream(".\\TestData\\Automation1.xlsx");
									wb.write(excelfile1);
									excelfile1.close();
								}
								catch(Throwable t )
								{
									System.out.println("File not found");
								}	
	 			   
							Thread.sleep(2000);
							ECSave=driver.findElement(By.xpath(obj.getProperty("ECSave")));
							ECSave.click();
							Thread.sleep(2000);
						}//end of else 
						
				}//end of for loop
				
		}	 //end of EditContacts_ValidEvent() method
	
	
	//***Click on SendMessage button on Recipient Details page and Check Send Message page displayed or not******	 
	      @Test(priority=18)
		  public static void SendMessagePage_Displayed()
		   {
				driver.findElement(By.xpath(obj.getProperty("SendMsgBtnOnDetails"))).click();
				String Title18=driver.getTitle();
			    String ActualTitle18="Send Message";
			    boolean res18=Title18.contentEquals(ActualTitle18);
			    Assert.assertTrue(res18, "Send Message page is not displayed");	
		   }
		
	//****To check Send Message functionality and Cancel button on Send Message************
		@Test(priority=19)
		public static void SendMessage() throws InterruptedException
		  {
				WebElement MsgTxtArea=driver.findElement(By.xpath(obj.getProperty("MessageTextArea")));
				MsgTxtArea.sendKeys("Testing carelink360");
				driver.findElement(By.xpath(obj.getProperty("MSendButton"))).click();
				String Msg12=driver.findElement(By.xpath(obj.getProperty("SuccessMessage"))).getText();
			    String ActualMsg12="Message sent successfully";
			    boolean res12=Msg12.contentEquals(ActualMsg12);
			    Assert.assertTrue(res12, "Message is not sent");
				MsgTxtArea.sendKeys("Testing carelink360");
				driver.findElement(By.xpath(obj.getProperty("MCancelBtn"))).click();
				String Msgtext=MsgTxtArea.getText();
				boolean res13=Msgtext.equals("");
				Assert.assertTrue(res13, "Message text area is not ceared");
				String Msg13=driver.findElement(By.xpath(obj.getProperty("SuccessMessage"))).getText();
				boolean res14=Msg13.equals("");
				Assert.assertTrue(res14, "Success Message is not cleared");
				
			}

		 //**********To check Logout is working or not***************
		 @Test(priority=20)
		public static void LogOut() throws InterruptedException
		{
			   Thread.sleep(4000);
			   driver.findElement(By.xpath(obj.getProperty("Logout"))).click();
			   Thread.sleep(4000);
			   String Title21=driver.getTitle();
			   System.out.println(Title21);
			   String ActualTitle21="Sign Up";
			   boolean res21=Title21.contentEquals("ActualTitle21");
			   Assert.assertTrue(res21, "Logout functionality is not working");
	    
		 }
		 
	
	//  @AfterTest
			public void afterTest() 
			{
				quit();
			}

}


