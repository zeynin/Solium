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

    @Test
    public static void whenEmptyInputThenNoException() throws Exception
    {
        InputTest inputTest = new InputTest();

        for( int i = 0; i < inputTest.inputLength; i++ )
        {
            // Inputs each field as "".
            Employee employee = new Employee( inputTest.InvalidInput( i ) );
        }
    }
}
