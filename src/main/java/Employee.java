import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeynin on 2016-07-17.
 */
public class Employee
{
    private String employeeId = "";
    private List<StockInvestment> stockInvestments = null;

    public Employee( String[] record )
    {
        if( record[StockInvestment.Fields.EMPLOYEE.getIndex()].isEmpty() ) return;

        this.stockInvestments = new ArrayList<StockInvestment>();
        StockInvestment stockInvestment = new StockInvestment( record );
        this.stockInvestments.add( stockInvestment );
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

    //public StockInvestment[] getStockInvestments()
    //{
    //    return stockInvestments;
    //}

    public void addStock( String[] stock )
    {
        StockInvestment stockInvestment = new StockInvestment( stock );
        this.stockInvestments.add( stockInvestment );
    }
}
