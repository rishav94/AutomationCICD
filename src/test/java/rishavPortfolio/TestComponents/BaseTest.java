package rishavPortfolio.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rishavPortfolio.Data.DataReader;
import rishavPortfolio.PageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initlizeDriver() throws IOException, URISyntaxException {
		// properties class

		Properties property = new Properties();
		FileInputStream fil = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//java//rishavPortfolio//Resources//GlobalData.properties");
		property.load(fil);
		String browserName = System.getProperty("Browser") != null ? System.getProperty("Browser")
				: property.getProperty("Browser");

		String hubLink = System.getProperty("HubLink") != null ? System.getProperty("HubLink")
				: property.getProperty("HubLink");
		DesiredCapabilities caps = new DesiredCapabilities();

		if (browserName.contains("chrome")) {
			if (System.getProperty("HubLink") != null) {
				caps.setBrowserName("chrome");
				//run headless test on Grid node
				if (browserName.contains("headless")) {
					caps.setCapability("headless", true);
				}
				driver = new RemoteWebDriver(new URI(hubLink).toURL(), caps);
			} else {
				ChromeOptions options = new ChromeOptions();
				if (browserName.contains("headless")) {
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900)); // fullscreen
			}

		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			// check if hublink is available else run remotely
			if (System.getProperty("HubLink") != null) {
				caps.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URI(hubLink).toURL(), caps);
			} else {
				System.getProperty("webdriver.gecko.driver", "H://eclipse/geckodriver.exe");
				driver = new FirefoxDriver();
			}

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return driver;
	}

	// DataReader
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		// String to Hashmap Jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	// take Screenshot
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException, URISyntaxException {
		driver = initlizeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closebrowser() {
		driver.quit();
	}

}
