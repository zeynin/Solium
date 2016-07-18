import org.apache.commons.lang3.math.NumberUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zeynin on 2016-07-16.
 */
public class StockInvestment
{
    public String employeeId;
    public String grantDate;
    public int shares;
    public double grantPrice;

    public StockInvestment( String[] record )
    {
        // Constructor. Initialize multiplier here, as well as fields
        // What happens if a string is empty? Enter default values
        employeeId = "";
        SimpleDateFormat date = new SimpleDateFormat( "YYYYMMdd" );
        grantDate = date.format( new Date() );
        shares = 0;
        grantPrice = 0.0;

        String field = record[0].toUpperCase();
        if( field.equals( "VEST" ) )
        {
            if( record[1].isEmpty() ) return;
            if( record[2].isEmpty() ) return;

            int employeeShares = NumberUtils.toInt( record[3], 0 );
            double price = NumberUtils.toDouble( record[4], 0.0 );

            if( employeeShares < 0 ) return;
            if( price < 0 ) return;

            employeeId = record[1];
            grantDate = record[2];
            shares = employeeShares;
            grantPrice = price;
        }
    }

    public double calculateCashGain( Date marketDate, double marketPrice ) throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "YYYYMMDD" );

        // Verify shares were granted before the market date
        if( dateFormat.parse( grantDate ).compareTo( marketDate ) > 0 ) return 0.0;

        double holdings = shares * ( marketPrice - grantPrice );
        return holdings;
    }
}
