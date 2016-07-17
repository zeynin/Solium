/**
 * Created by @author Zeynin on 2016-07-15.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class StockInvestmentTest
{
    @org.testng.annotations.Test
    public static void whenCorrectInputThenNoExceptionIsThrown() throws Exception
    {
        String[] input = { "VEST", "001B", "20120101", "1000", "0.45" };
        StockInvestment stockInvestment = new StockInvestment( input );
    }
/* handles exception gracefully
    @org.testng.annotations.Test(expectedExceptions = NumberFormatException.class)
    public static void whenEmptyInputThenExceptionIsThrown() throws NumberFormatException
    {
        String[] input1 = { "VEST", "001B", "20120101", "1000", "" };
        StockInvestment stockInvestment = new StockInvestment( input1 );
    }
*/
    @org.testng.annotations.Test(expectedExceptions = ParseException.class)
    public static void whenEmptyInputThenThrowsException() throws Exception
    {
        String[] input1 = { "VEST", "001B", "", "1000", "0.45" };
        StockInvestment stockInvestment = new StockInvestment( input1 );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMDD" );
        Date marketDate = dateFormatter.parse( "20130101" );
        double marketPrice = 1.00;

        System.out.println( stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @org.testng.annotations.Test
    public static void whenMarketDateIsEqualToGrantDateThenOutputIs500() throws Exception
    {
        String[] input = { "VEST", "003B", "20130101", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMDD" );
        Date marketDate = dateFormatter.parse( "20130101" );
        double marketPrice = 1.00;

        System.out.println( stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @org.testng.annotations.Test
    public static void whenMarketDateIsAfterGrantDateThenOutputIs500() throws Exception
    {
        String[] input = { "VEST", "003B", "20130101", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMDD" );
        Date marketDate = dateFormatter.parse( "20140101" );
        double marketPrice = 1.00;

        System.out.println( stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @org.testng.annotations.Test
    public static void whenMarketDateIsBeforeGrantDateThenOutputIs0() throws Exception
    {
        String[] input = { "VEST", "003B", "20130101", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMDD" );
        Date marketDate = dateFormatter.parse( "20120101" );
        double marketPrice = 1.00;

        System.out.println( stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @org.testng.annotations.Test(expectedExceptions = ParseException.class)
    public static void whenGrantDateIsMisformattedThenExceptionThrown() throws Exception
    {
        String[] input = { "VEST", "003B", "2013/01/01", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMDD" );
        Date marketDate = dateFormatter.parse( "20120101" );
        double marketPrice = 1.00;

        System.out.println( stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @org.testng.annotations.Test
    public static void whenVestIsLowercaseThenOutputIs500() throws Exception
    {
        String[] input = { "vest", "003B", "20130101", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMDD" );
        Date marketDate = dateFormatter.parse( "20140101" );
        double marketPrice = 1.00;

        System.out.println( stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }
}
