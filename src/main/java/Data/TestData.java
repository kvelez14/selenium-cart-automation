package Data;

import Pages.HomePage;
import org.testng.annotations.DataProvider;
import utils.CSVUtils;

import java.util.List;

public class TestData {

    @DataProvider(name = "loginData")
    public Object[][] loginCredentials() {
        return new Object[][]{
                {"kevin.velez1560@gmail.com", "Tracer@44"}
        };
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] incorrectCredentials() {
        return new Object[][]{
                {"kevin1560@gmail.com", "pass1"},
                {"Kevin1570@gmail.com", "pass2"},
                {"kevinvelez.com", "1234"}
        };
    }

    @DataProvider(name = "loginDataFile")
    public Object[][] getLoginData() {
        List<String[]> users = CSVUtils.readUsers();
        return users.toArray(new Object[0][]);
    }

}
