import org.testng.annotations.Test;

/**
 * Created by Zeynin on 2016-07-17.
 */
public class EmployeeTest
{
    @Test(dataProvider = "ValidInput", dataProviderClass = InputTest.class)
    public void whenEmployeeIsAssignedThenNoException( String[] Input ) throws Exception
    {
        Employee employee = new Employee( Input );
    }
}
