package Tests;

import Utils.WebDiverConfig;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class BasicTest {

    public WebDriver driver = WebDiverConfig.setFirefoxPofile();

    public boolean skipAlet() {
        Alert alert = driver.switchTo().alert();
        try {
            alert.dismiss();
            return true;
        } catch (RuntimeException expected){
            expected.getStackTrace();
            return false;
        }
    }

    @DataProvider(name="empLogin")
    public static Object[][] loginData() {
        Object[][] arrayObject = getExcelData("src\\test\\java\\utils\\login_pass.xls","Sheet1");
        return arrayObject;
    }

    public static String[][] getExcelData(String fileName, String sheetName) {
        String[][] arrayExcelData = null;
        try {
            FileInputStream fs = new FileInputStream(fileName);
            Workbook wb = Workbook.getWorkbook(fs);
            Sheet sh = wb.getSheet(sheetName);

            int totalNoOfCols = sh.getColumns();
            int totalNoOfRows = sh.getRows();

            arrayExcelData = new String[totalNoOfRows][totalNoOfCols];

            for (int i= 0 ; i < totalNoOfRows; i++) {

                for (int j=0; j < totalNoOfCols; j++) {
                    arrayExcelData[i-0][j] = sh.getCell(j, i).getContents();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return arrayExcelData;
    }

}
