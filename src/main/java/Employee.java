/**
 * Created by Zeynin on 2016-07-17.
 */
public class Employee
{
    public String employeeId = "";
    public StockInvestment[] stockInvestments = null;

    public Employee( String[] record )
    {
        if( record[1].isEmpty() ) return;

        StockInvestment stock = new StockInvestment( record );
        stockInvestments = new StockInvestment[]{ stock };

        this.employeeId = record[1];
        this.stockInvestments = stockInvestments;
    }
}
