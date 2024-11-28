package wizehub.TestComponents;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import wizehub.pageobjects.LandingPage;

public class BaseTest {

	// properties class can read the global properties and decide at runtime on
	// which browser to run tests
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		// read properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\GlobalData.properties");
		prop.load(fis); // accepts input stream object and fileinputstream will be used to create that
						// object and convert the file
		String browserName = System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			
			ChromeOptions options=new ChromeOptions();
			
			   if (browserName.contains("headless")) {	
			        options.addArguments("--headless");
			        options.addArguments("--disable-gpu");  // Disable GPU, commonly needed in headless mode.
			        options.addArguments("--no-sandbox");     // This makes sure that Chrome runs without sandboxing (needed in some environments).
			        options.addArguments("--disable-dev-shm-usage");  // Prevents issues related to limited shared memory.
			      //  options.addArguments("--window-size=1920x1080");  // Define a specific window size, often required for headless mode.
			    }
			
			driver = new ChromeDriver(options);
			driver.manage().window().fullscreen();
			

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//resources//" + testCaseName + ".png");

		FileUtils.copyFile(source, destination);

		return System.getProperty("user.dir") + "//resources//" + testCaseName + ".png"; // returning the path of saved
																							// screenshot

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		// reading json to string
		String jsonData = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		// String content to hashmap using jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
		// created hashmpaps with two indexes (two hashmaps) and stored in a list as
		// {0},{1}
	}

}
