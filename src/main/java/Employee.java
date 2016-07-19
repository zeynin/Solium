/**
 * Created by Zeynin on 2016-07-17.
 */
public class Employee
{
    private String employeeId = "";
    private StockInvestment[] stockInvestments = null;

    public Employee( String[] record )
    {
        if( record[StockInvestment.Fields.EMPLOYEE.getIndex()].isEmpty() ) return;

        this.stockInvestments = new StockInvestment[]{ new StockInvestment( record ) };
        this.employeeId = record[StockInvestment.Fields.EMPLOYEE.getIndex()];
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId( String employeeId )
    {
        this.employeeId = employeeId;
    }

    public StockInvestment[] getStockInvestments()
    {
        return stockInvestments;
    }

    public void addStock( String[] stock )
    {
        int i = stockInvestments.length;
        this.stockInvestments[i] = new StockInvestment( stock );
    }
}
