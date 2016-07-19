import org.apache.commons.lang3.math.NumberUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Zeynin on 2016-07-17.
 */
public class Employee
{
    private String employeeId = "";
    private List<StockInvestment> stockInvestments = null;
    private double employeeCashGain = 0.00;

    public Employee( String[] record )
    {
        super();
        if( record[StockInvestment.Fields.EMPLOYEE.getIndex()].isEmpty() ) return;

        this.stockInvestments = new ArrayList<StockInvestment>();
        StockInvestment stockInvestment = new StockInvestment( record );
        this.stockInvestments.add( stockInvestment );
        this.employeeId = record[StockInvestment.Fields.EMPLOYEE.getIndex()];
        this.employeeCashGain = 0.00;
    }

    public String getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId( String employeeId )
    {
        this.employeeId = employeeId;
    }

    public List<StockInvestment> getStockInvestments()
    {
        return stockInvestments;
    }

    public void addStock( String[] stock )
    {
        StockInvestment stockInvestment = new StockInvestment( stock );
        this.stockInvestments.add( stockInvestment );
    }

    public double calculateEmployeeCashGain( String[] marketData ) throws Exception
    {
        // Convert array of strings into marketDate and marketPrice
        SimpleDateFormat dateFormat = new SimpleDateFormat( "YYYYMMDD" );
        Date marketDate = new Date();
        double marketPrice = 0.0;

        marketDate = dateFormat.parse( marketData[0] );
        marketPrice = NumberUtils.toDouble( marketData[1], 0.0 );

        for( StockInvestment investment : stockInvestments )
        {
            employeeCashGain += investment.calculateCashGain( marketDate, marketPrice );
        }

        return employeeCashGain;
    }

    @Override
    public String toString()
    {
        DecimalFormat df2 = new DecimalFormat( "0.00" );
        df2.setRoundingMode( RoundingMode.HALF_UP );

        String s = employeeId + "," + df2.format( employeeCashGain );

        return s;
    }
}
