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
        employeeId = "empty";
        SimpleDateFormat date = new SimpleDateFormat( "YYYYMMdd" );
        grantDate = date.format( new Date() );
        shares = 0;
        grantPrice = 0.0;

        String field = record[0].toUpperCase();
        if( field.equals( "VEST" ) )
        {
            if( record[1].isEmpty() ) return;
            employeeId = record[1];

            if( record[2].isEmpty() ) return;
            grantDate = record[2];
            shares = NumberUtils.toInt( record[3], 0 );
            grantPrice = NumberUtils.toDouble( record[4], 0.0 );
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
