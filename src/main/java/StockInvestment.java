import org.apache.commons.lang3.math.NumberUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zeynin on 2016-07-16.
 */
public class StockInvestment
{
    public enum Fields
    {
        DIRECTIVE ( 0 ),
        EMPLOYEE ( 1 ),
        VEST_DATE ( 2 ),
        UNITS_VESTING ( 3 ),
        GRANT_PRICE ( 4 );

        private final int index;

        Fields( int index )
        {
            this.index = index;
        }

        public int getIndex() { return index; }
    }

    private String directive = "";
    private String grantDate = "";
    private int shares = 0;
    private double grantPrice = 0.0;
    private double totalCashGain = 0.0;

    public StockInvestment( String[] record )
    {
        super();
        // Constructor. Initialize multiplier here, as well as fields
        // What happens if a string is empty? Enter default values
        SimpleDateFormat date = new SimpleDateFormat( "YYYYMMdd" );
        grantDate = date.format( new Date() );
        shares = 0;
        grantPrice = 0.0;
        directive = record[Fields.DIRECTIVE.getIndex()].toUpperCase();

        if( directive.equals( "VEST" ) )
        {
            if( record[Fields.VEST_DATE.getIndex()].isEmpty() ) return;

            // Employee shares can't really be negative when vested (neither can price)
            // However, the requirements only ask for negative values to be filtered
            // on the output. Remove this:
            //int employeeShares = NumberUtils.toInt( record[Fields.UNITS_VESTING.getIndex()], 0 );
            //double price = NumberUtils.toDouble( record[Fields.GRANT_PRICE.getIndex()], 0.0 );

            //if( employeeShares < 0 ) return;
            //if( price < 0 ) return;

            grantDate = record[Fields.VEST_DATE.getIndex()];
            shares = NumberUtils.toInt( record[Fields.UNITS_VESTING.getIndex()], 0 );
            grantPrice = NumberUtils.toDouble( record[Fields.GRANT_PRICE.getIndex()], 0.0 );

        }
    }

    public double calculateCashGain( Date marketDate, double marketPrice ) throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "YYYYMMDD" );

        // Verify shares were granted before the market date
        if( dateFormat.parse( grantDate ).compareTo( marketDate ) > 0 ) return 0.0;

        totalCashGain = shares * ( marketPrice - grantPrice );
        return totalCashGain;
    }

    @Override
    public String toString()
    {
        DecimalFormat df2 = new DecimalFormat("0.00");
        df2.setRoundingMode( RoundingMode.HALF_UP );

        return df2.format( totalCashGain );
    }
}
