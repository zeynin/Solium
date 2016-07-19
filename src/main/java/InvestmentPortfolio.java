/**
 * Created by Zeynin on 2016-07-17.
 */
public class InvestmentPortfolio
{
    public void main( String[][] Input ) throws Exception
    {
        // Get the input
        // Sort each row by Employee
        // Enter each row into Employees
        // Output findings
        Employees employees = new Employees( Input );

        employees.calculateTotalGain( new HandleInput().getMarketData() );
        System.out.println( employees );
    }
}
