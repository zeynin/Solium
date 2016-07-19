import org.testng.annotations.Test;

/**
 * Created by Zeynin on 2016-07-18.
 */
public class EmployeesTest
{
    @Test(dataProvider = "ValidRows", dataProviderClass = InputTest.class)
    public void whenEmployeesIsAssignedThenNoException( String[][] Input ) throws Exception
    {
        Employees employees = new Employees( Input );

        employees.outputEmployees();
    }
}
