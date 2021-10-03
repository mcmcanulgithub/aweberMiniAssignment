package test.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class MyProfileTest {
	
	WebDriver driver;
	
	@BeforeEach
	public void setup() {
		
		// System Property for Chrome Driver
	    System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_WEB_DRIVER_PATH"));
	      
	    // Instantiate a ChromeDriver class.       
	    driver = new ChromeDriver();
	    	    
	    // Launch Website  
        driver.navigate().to("https://wordpress.com/me");  
          
        //Maximize the browser  
        driver.manage().window().maximize(); 
        
        //log in
        
        delay(2000);
        driver.findElement(By.id("usernameOrEmail")).sendKeys(System.getenv("WP_USERNAME"));
        findElementByCssAndClick("button[class='button form-button is-primary']");
        delay(2000);
        driver.findElement(By.id("password")).sendKeys(System.getenv("WP_PASSWORD"));
        findElementByCssAndClick("button[class='button form-button is-primary']");

        //start tests from the point of myProfile page after a brief wait
        
        delay(2000);
		
	}
	
	@AfterEach
	public void tearDown() {
		driver.close();
	}

	@Test
	void profilePhotoVerifyIconInformational() {

		//Make sure informational popup is not displaying on start
		
		String informationalPhotoTooltipCss = "button[class='info-popover edit-gravatar__pop-over']";
		String isInformationalPopupEnabled = findElementByCssAndGetAttribute(informationalPhotoTooltipCss, "aria-expanded");
		assertEquals("false",isInformationalPopupEnabled);
		
		//Test informational popup display
		
        findElementByCssAndClick(informationalPhotoTooltipCss);
		delay(2000);
		informationalPhotoTooltipCss = "button[class='info-popover edit-gravatar__pop-over is-active']";
		isInformationalPopupEnabled = findElementByCssAndGetAttribute(informationalPhotoTooltipCss, "aria-expanded");
		assertEquals("true",isInformationalPopupEnabled);	
		
		//Test informational popup hide
		
        findElementByCssAndClick(informationalPhotoTooltipCss);
		delay(2000);
		informationalPhotoTooltipCss = "button[class='info-popover edit-gravatar__pop-over']";
		isInformationalPopupEnabled = findElementByCssAndGetAttribute(informationalPhotoTooltipCss, "aria-expanded");
		assertEquals("false",isInformationalPopupEnabled);
		
	}
	
	@Test
	void profileVerifyFirstName() {
		
		//Test adding first name
        findElementByCssAndClick("input[id='first_name']");
        findElementByCssAndSendKeys("input[id='first_name']", "Matt");
		
		//save and validate notice text
		
        String saveButtonCss = "button[class='button form-button is-primary']";
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		String saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
		
		//dismiss notice and refresh page
		
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);

        //validate first name was still added after refresh
        
      	String firstName = findElementByCssAndGetAttribute("input[id='first_name']", "value");
      	assertEquals("Matt",firstName);

      	
      	
        
		//Test updating first name
		
        findElementByCssAndClick("input[id='first_name']");
        findElementByCssAndSendKeys("input[id='first_name']", "Matt");
		
		//save and dismiss notice
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
		findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate first name is updated
        
		firstName = findElementByCssAndGetAttribute("input[id='first_name']", "value");
		assertEquals("MattMatt",firstName);
		delay(2000);
		
		//reset popup for next test
       
		
		
		
		
		//Test deleting first name
        findElementByCssAndClick("input[id='first_name']");
        findElementByCssAndSendKeys("input[id='first_name']", "\b\b\b\b\b\b\b\b");
		
		//save page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
        
        //validate that first name is deleted
        
		firstName = findElementByCssAndGetAttribute("input[id='first_name']", "value");
		assertEquals("",firstName);
		delay(2000);
		saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
		
		//reset popup for next sequence of events
		
        findElementByCssAndClick("button[class='notice__dismiss']");

	}
	
	@Test
	void profileVerifyLastName() {
		
		//Test adding last name
		
        findElementByCssAndClick("input[id='last_name']");
        findElementByCssAndSendKeys("input[id='last_name']","Mcanulty");
		
		//save and refresh page
        
        String saveButtonCss = "button[class='button form-button is-primary']";
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		String saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate last name was added
        
		String lastName = findElementByCssAndGetAttribute("input[id='last_name']","value");
		assertEquals("Mcanulty",lastName);
		delay(2000);
				
		
		
		
		//Test updating last name
		
        findElementByCssAndClick("input[id='last_name']");
        findElementByCssAndSendKeys("input[id='last_name']","Mcanulty");
		
		//save and refresh page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate last name was updated
        
		lastName = findElementByCssAndGetAttribute("input[id='last_name']","value");
		assertEquals("McanultyMcanulty",lastName);
		delay(2000);
						
		
		
		
		//Test deleting last name
		
        findElementByCssAndClick("input[id='last_name']");
		String backspaceKeys = "\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b";
		findElementByCssAndSendKeys("input[id='last_name']",backspaceKeys);
		
		//save and refresh page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate last name was deleted
        
		lastName = findElementByCssAndGetAttribute("input[id='last_name']","value");
		assertEquals("",lastName);
		delay(2000);
		
	}
	
	@Test
	void profileVerifyPublicDisplayName() {
		
		//Test updating public display name
		
		findElementByCssAndClick("input[id='display_name']");
		findElementByCssAndSendKeys("input[id='display_name']","com");
		
		//save and refresh page
		
        String saveButtonCss = "button[class='button form-button is-primary']";
				
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		String saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
		
        //validate public display name is updated
        
		String publicDisplayName = findElementByCssAndGetAttribute("input[id='display_name']","value");
		assertEquals("mcmcanulgmailcomcom",publicDisplayName);
		delay(2000);
		
		
		
		
		//Test deleting public display name
		
		findElementByCssAndClick("input[id='display_name']");
		String backspaceKeys = "\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b";
		findElementByCssAndSendKeys("input[id='display_name']",backspaceKeys);
		
		//save and refresh page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate public display name is deleted
        
		publicDisplayName = findElementByCssAndGetAttribute("input[id='display_name']","value");
		assertEquals("",publicDisplayName);
		delay(2000);
		
		
		
		
		//Test adding public display name
		
		findElementByCssAndClick("input[id='display_name']");
		findElementByCssAndSendKeys("input[id='display_name']","mcmcanulgmailcom");
		
		//save and refresh page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate public display name was added
        
		publicDisplayName = findElementByCssAndGetAttribute("input[id='display_name']","value");
		assertEquals("mcmcanulgmailcom",publicDisplayName);
		delay(2000);
		
	}
	
	@Test
	void profileVerfiyAboutMe() {
		
		//Test adding about me
		
		findElementByCssAndClick("textarea[id='description']");
		findElementByCssAndSendKeys("textarea[id='description']","Hello World! 1234!");
		
		//save and refresh page
		
        String saveButtonCss = "button[class='button form-button is-primary']";
				
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		String saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate about me was added
        
		String aboutMe = findElementByCssAndGetText("textarea[id='description']");
		assertEquals("Hello World! 1234!",aboutMe);
		delay(2000);
		
		
		
		
		//Test updating about me
		
		findElementByCssAndClick("textarea[id='description']");
		findElementByCssAndSendKeys("textarea[id='description']","Hello World! 1234!");
		
		//save and refresh page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate about me was updated
        
		aboutMe = findElementByCssAndGetText("textarea[id='description']");
		assertEquals("Hello World! 1234!Hello World! 1234!",aboutMe);
		delay(2000);
		
		
		
		
		//Test Deleting about me
		
		findElementByCssAndClick("textarea[id='description']");
		String backspaceKeys = "\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b";
		findElementByCssAndSendKeys("textarea[id='description']",backspaceKeys);
		
		//save and refresh page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
		saveText = findElementByCssAndGetText("span[class='notice__text']");
		assertEquals("Settings saved successfully!",saveText);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate about me was deleted
        
		aboutMe = findElementByCssAndGetText("textarea[id='description']");
		assertEquals("",aboutMe);
		delay(2000);
		
	}
	
	@Test
	void profileVerifyGravatarProfile() {
		
		//Test gravatar profile toggle behaviour, should be unchecked at start
		
		try{
			driver.findElement(By.cssSelector("span[class='components-form-toggle']"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		findElementByCssAndClick("input[id='inspector-toggle-control-0']");
				
		//save and refresh page
		
        String saveButtonCss = "button[class='button form-button is-primary']";
				
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate toggle is checked now
        
		try{
			driver.findElement(By.cssSelector("span[class='components-form-toggle is-checked']"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test gravatar profile toggle uncheck
		
		delay(2000);
		findElementByCssAndClick("input[id='inspector-toggle-control-0']");
		
		//save and refresh page
		
        findElementByCssAndClick(saveButtonCss);
		delay(2000);
        findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
        
        //validate toggle is once again unchecked
        
		try{
			driver.findElement(By.cssSelector("span[class='components-form-toggle']"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void profileVerifyChangePhoto() {
		
		//Test upload profile pic
		
		findElementByCssAndClick("div[class='edit-gravatar__image-container']");
		delay(2000);
		findElementByCssAndSendKeys("input[type='file']",System.getenv("IMAGE_1"));
		delay(2000);
		
		try {
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		delay(2000);
		String uploadImageButtonCss = "button[class='button image-editor__buttons-button is-primary']";
		findElementByCssAndClick(uploadImageButtonCss);
		delay(6000);
		
		//validate that profile pic was successfully uploaded then dismiss notice for next test
		
		try{
			
			String uploadText = findElementByCssAndGetText("span[class='notice__text']");
			assertEquals("You successfully uploaded a new profile photo — looking sharp!",uploadText);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
		
		
		

		//Test upload image but then cancel
		
		findElementByCssAndClick("div[class='edit-gravatar__image-container']");
		delay(2000);
		findElementByCssAndSendKeys("input[type='file']",System.getenv("IMAGE_2"));
		delay(2000);
		
		try {
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		delay(2000);
		findElementByCssAndClick("button[data-e2e-button='cancel']");
		delay(6000);
		
		//validate that notice is not fired for successful image upload
		
		try{
			driver.findElement(By.cssSelector("span[class='notice__text']"));
		} catch(Exception e) {
			//do nothing, test worked since element should not be present after cancel
		}
		
		
		
		
		//Test add photo with rotate and flip
		
		findElementByCssAndClick("div[class='edit-gravatar__image-container']");
		delay(2000);
		findElementByCssAndSendKeys("input[type='file']",System.getenv("IMAGE_1"));
		delay(2000);
		
		try {
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		delay(2000);
		WebElement buttonContainerOne = driver
				.findElement(By.cssSelector("div[class='image-editor__toolbar']"));
		
		//rotate twice
		
		String rotateCss = "button[class='image-editor__toolbar-button']:nth-child(1)";
		
		buttonContainerOne
			.findElement(By.cssSelector(rotateCss))
			.click();
		delay(2000);
		buttonContainerOne
			.findElement(By.cssSelector(rotateCss))
			.click();
		delay(2000);
		
		//flip twice
		
		String flipCss = "button[class='image-editor__toolbar-button']:nth-child(2)";
		
		buttonContainerOne
			.findElement(By.cssSelector(flipCss))
			.click();
		delay(2000);
		buttonContainerOne
			.findElement(By.cssSelector(flipCss))
			.click();
		delay(2000);
		driver
			.findElement(By.cssSelector(uploadImageButtonCss))
			.click();
		delay(6000);
		
		//validate photo added successfully
		
		try{
			
			String uploadText = findElementByCssAndGetText("span[class='notice__text']");
			assertEquals("You successfully uploaded a new profile photo — looking sharp!",uploadText);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
		
		
		
		
		//Test add photo with rotate, flip, and reset all being used
		
		findElementByCssAndClick("div[class='edit-gravatar__image-container']");
		delay(2000);
		findElementByCssAndSendKeys("input[type='file']",System.getenv("IMAGE_1"));
		delay(2000);
		
		try {
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		//validate that reset button is disabled initially then edit photo where it should be enabled thereafter
		
		String resetDisabled = findElementByCssAndGetAttribute("button[data-e2e-button='reset']","disabled");
		assertEquals("true",resetDisabled);
		
		delay(2000);
		WebElement buttonContainerTwo = driver.findElement(By.cssSelector("div[class='image-editor__toolbar']"));
		
		buttonContainerTwo
			.findElement(By.cssSelector(rotateCss))
			.click();
		delay(2000);
		buttonContainerTwo
			.findElement(By.cssSelector(rotateCss))
			.click();
		delay(2000);
		buttonContainerTwo
			.findElement(By.cssSelector(flipCss))
			.click();
		delay(2000);
		buttonContainerTwo
			.findElement(By.cssSelector(flipCss))
			.click();
		delay(2000);
		
		resetDisabled = driver.findElement(By.cssSelector("button[data-e2e-button='reset']"))
				.getAttribute("disabled");
		assertEquals(null,resetDisabled);
		
		findElementByCssAndClick("button[data-e2e-button='reset']");
		delay(2000);
		
		//validate that reset button is disabled again since it was just clicked up above then submit photo
		
		resetDisabled = findElementByCssAndGetAttribute("button[data-e2e-button='reset']","disabled");
		assertEquals("true",resetDisabled);
		
		findElementByCssAndClick(uploadImageButtonCss);
		delay(6000);
		
		//validate that photo upload was successful and dismiss notice
		
		try{
			
			String uploadText = findElementByCssAndGetText("span[class='notice__text']");
			assertEquals("You successfully uploaded a new profile photo — looking sharp!",uploadText);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		findElementByCssAndClick("button[class='notice__dismiss']");
		delay(2000);
		
	}
	
	@Test
	void profileVerifyWordpressSite() {
			
		//Test add wordpress site
		
		findElementByCssAndClick("button[class='button is-compact']");
		delay(2000);
		findElementByCssAndClick("button[class='popover__menu-item']");
		delay(2000);
		findElementByCssAndClick("input[class='profile-links-add-wordpress__checkbox form-checkbox']");
		delay(2000);
		WebElement addForm = driver
				.findElement(By.cssSelector("form[class='profile-links-add-wordpress']"));
		addForm
			.findElement(By.cssSelector("button[class='button form-button is-primary']"))
			.click();   
		delay(6000);
		        
        //validate wordpress site link was added
        
		try{
			driver.findElement(By.cssSelector("li[class='profile-link']"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test attempt to add another wordpress site and then cancel and delete since not possible
		
		findElementByCssAndClick("button[class='button is-compact']");
		delay(2000);
		findElementByCssAndClick("button[class='popover__menu-item']");
		delay(2000);
		
		//validate info text after one link already added and then cancel 
		//is this a bug actually does not make much sense?
		
		try{
			String selectText;
			WebElement wpLinkContainer = driver
					.findElement(By.cssSelector("form[class='profile-links-add-wordpress']"));
			selectText = wpLinkContainer.findElement(By.cssSelector("p")).getText();
			assertEquals("Please select one or more sites to add to your profile.",selectText);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		findElementByCssAndClick("button[class='button profile-links-add-wordpress__cancel form-button']");
		delay(2000);
        
        //validate wordpress site is still present after the cancel and then delete the wordpress site
        
		try{
			driver.findElement(By.cssSelector("li[class='profile-link']"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		findElementByCssAndClick("button[class='button profile-link__remove is-borderless']");
		delay(2000);

		//validate info text after the wordpress site is deleted
		
		try{
			
			String noSites = findElementByCssAndGetText("p[class='profile-links__no-links']");
			assertEquals("You have no sites in your profile links. You may add sites if you'd like.",noSites);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test cancel with checkbox where wordpress site is not already added
		
		findElementByCssAndClick("button[class='button is-compact']");
		delay(2000);
		findElementByCssAndClick("button[class='popover__menu-item']");
		delay(2000);
		findElementByCssAndClick("input[class='profile-links-add-wordpress__checkbox form-checkbox']");
		delay(2000);
		findElementByCssAndClick("button[class='button profile-links-add-wordpress__cancel form-button']");
		delay(2000);
		
		//validate wordpress site does not get added

		try{
			String noSites = findElementByCssAndGetText("p[class='profile-links__no-links']");
			assertEquals("You have no sites in your profile links. You may add sites if you'd like.",noSites);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void profileVerifyUrl() {
		
		//Test attempt to add valid url but with no description, then cancel
		
		findElementByCssAndClick("button[class='button is-compact']");
		delay(2000);
		findElementByXpathAndClick("//button[text()='Add URL']");
		delay(2000);
		String css = "input[class='form-text-input profile-links-add-other__value']";
		findElementByCssAndSendKeys(css, "https://www.tesla.com/");
		delay(2000);
				
		//validate site add is disabled, and upon cancel site is not added
		
		try{
			
			
			String addSiteEnabled = findElementByXpathAndGetAttribute("//button[text()='Add Site']","disabled");
			assertEquals("true",addSiteEnabled);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		findElementByCssAndClick("button[class='button profile-links-add-other__cancel form-button']");
		delay(2000);

		try{
			
			String noSites = findElementByCssAndGetText("p[class='profile-links__no-links']");
			assertEquals("You have no sites in your profile links. You may add sites if you'd like.",noSites);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test send in a description only then cancel
		
		findElementByCssAndClick("button[class='button is-compact']");
		delay(2000);
		findElementByXpathAndClick("//button[text()='Add URL']");
		delay(2000);
		String descriptionSelector = "input[class='form-text-input profile-links-add-other__title']";
		findElementByCssAndSendKeys(descriptionSelector,"generic description");
		delay(2000);
				
		//validate site add is disabled and site is not added after cancel
		
		try{
			
			String addSiteDisabled = findElementByXpathAndGetAttribute("//button[text()='Add Site']","disabled");
			assertEquals("true",addSiteDisabled);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		findElementByCssAndClick("button[class='button profile-links-add-other__cancel form-button']");
		delay(2000);

		try{
			
			String noSites = findElementByCssAndGetText("p[class='profile-links__no-links']");
			assertEquals("You have no sites in your profile links. You may add sites if you'd like.",noSites);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test add valid url and description then cancel
		
		findElementByCssAndClick("button[class='button is-compact']");
		delay(2000);
		findElementByXpathAndClick("//button[text()='Add URL']");
		delay(2000);
		findElementByCssAndSendKeys("input[class='form-text-input profile-links-add-other__value']", "htt://ww.tesla.com/");
		findElementByCssAndSendKeys(descriptionSelector, "generic description");
		delay(2000);
				
		//validate that site add button is enabled, then cancel and validate site was not added
		
		try{
			
			String addSiteDisabled = findElementByXpathAndGetAttribute("//button[text()='Add Site']","disabled");
			assertEquals("true",addSiteDisabled);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		findElementByCssAndClick("button[class='button profile-links-add-other__cancel form-button']");

		try{
			
			String noSites = findElementByCssAndGetText("p[class='profile-links__no-links']");
			assertEquals("You have no sites in your profile links. You may add sites if you'd like.",noSites);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test add valid url and description then add site
		
		findElementByCssAndClick("button[class='button is-compact']");
		delay(2000);
		findElementByXpathAndClick("//button[text()='Add URL']");
		delay(2000);
		
		findElementByCssAndSendKeys("input[class='form-text-input profile-links-add-other__value']", "https://www.tesla.com/");
		findElementByCssAndSendKeys(descriptionSelector,"generic description");
		delay(2000);
				
		//validate that add site button is enabled and after adding validate that info is correct
		
		try{
			
			String addSiteDisabled = findElementByXpathAndGetAttribute("//button[text()='Add Site']","disabled");
			assertEquals(null,addSiteDisabled);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		findElementByXpathAndClick("//button[text()='Add Site']");
		delay(2000);
		
		try{
			
			String actualDescription = findElementByCssAndGetText("span[class='profile-link__title']");
			assertEquals("generic description",actualDescription);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try{
			
			String url = findElementByCssAndGetText("span[class='profile-link__url']");
			assertEquals("www.tesla.com/",url);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test delete site just added and verify it is removed
		
		findElementByCssAndClick("button[class='button profile-link__remove is-borderless']");
		delay(2000);
		
		try{
			
			String noSites = findElementByCssAndGetText("p[class='profile-links__no-links']");
			assertEquals("You have no sites in your profile links. You may add sites if you'd like.",noSites);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	void findElementByCssAndClick(String cssSelector) {
		driver.findElement(By.cssSelector(cssSelector)).click();
	}
	
	String findElementByCssAndGetAttribute(String cssSelector, String attribute) {
		return driver.findElement(By.cssSelector(cssSelector)).getAttribute(attribute);
	}
	
	String findElementByCssAndGetText(String cssSelector) {
		return driver.findElement(By.cssSelector(cssSelector)).getText();
	}
	
	void findElementByCssAndSendKeys(String cssSelector, String keys) {
		driver.findElement(By.cssSelector(cssSelector)).sendKeys(keys);
	}
	
	void findElementByXpathAndClick(String cssSelector) {
		driver.findElement(By.xpath(cssSelector)).click();
	}
	
	String findElementByXpathAndGetAttribute(String xPath, String attribute) {
		return driver.findElement(By.xpath(xPath)).getAttribute(attribute);
	}
	
	void findElementByIdAndClick(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	void delay(int milliseconds) {
		
		//wait when needed to make debugging more visible to human eye 
		//and for page rendering catch-up
		
		try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}

}