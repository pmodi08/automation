package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.LoginPage;
import utilities.XLUtils;
import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass{

    @Test(dataProvider ="LoginData")
    public void loginDDT(String user,String pwd) throws InterruptedException {

        driver.get(baseURL);
        LoginPage lp=new LoginPage(driver);
        lp.setUserName(user);
        lp.setPassword(pwd);
        lp.clickSubmit();
        Thread.sleep(3000);

        if (isAlertPresent()==true){
            driver.switchTo().alert().accept();//close alert
            driver.switchTo().defaultContent();// move to default login page
            Assert.assertTrue(false);

        }
        else{
            Assert.assertTrue(true);
            lp.clickLogout();
            Thread.sleep(3000);
            driver.switchTo().alert().accept(); //close the logout alert
            driver.switchTo().defaultContent();// move to default login page

        }

    }

    public boolean isAlertPresent(){
        try {
            driver.switchTo().alert();
            return true;
        }
        catch (Exception e){
            return false;
        }

    }


    @DataProvider(name="LoginData")
    String[][] getData() throws IOException {
        String path=System.getProperty("user.dir")+"/src/test/java/testData/LoginData.xlsx";
        int rownum= XLUtils.getRowCount(path,"Sheet1");
        int colcount=XLUtils.getCellCount(path,"Sheet1",1);

        String logindata[][]=new String[rownum][colcount];

        for (int i = 1; i <=rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);

            }

        }
        return logindata;

    }

}
