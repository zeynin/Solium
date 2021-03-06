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

        employees.calculateTotalGain( new InputTest().ValidMarketData() );
        System.out.println( employees );
    }

    @Test(dataProvider = "ValidRows", dataProviderClass = InputTest.class)
    public void whenEmployeesIsAssigned2ThenNoException( String[][] Input ) throws Exception
    {
        Employees employees = new Employees( Input );

        employees.calculateTotalGain( new InputTest().ValidMarketData2() );
        System.out.println( employees );
    }

    @Test(dataProvider = "ValidRows", dataProviderClass = InputTest.class)
    public void whenEmployeesIsAssigned3ThenNoException( String[][] Input ) throws Exception
    {
        Employees employees = new Employees( Input );

        employees.calculateTotalGain( new InputTest().ValidMarketData2() );
        System.out.println( employees );
    }

}
