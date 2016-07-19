import org.testng.annotations.Test;

/**
 * Created by Zeynin on 2016-07-18.
 */
public class EmployeesTest
{
    @Test(dataProvider = "ValidInput", dataProviderClass = InputTest.class)
    public void whenEmployeesIsAssignedThenNoException( String[][] Input ) throws Exception
    {
        Employees employee = new Employees();
    }
}
