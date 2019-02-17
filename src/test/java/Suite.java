import com.automationpractice.ui.HomePageTest;
import com.automationpractice.ui.RegistrationTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
//@SelectPackages("com.automationpractice.ui")
//@SelectClasses(HomePageTest.class)
@SelectClasses({HomePageTest.class, RegistrationTest.class})
public class Suite {
}
