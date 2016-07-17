/**
 * Created by @author Zeynin on 2016-07-15.
 */

import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.AssertJUnit.assertEquals;

/*
using ByteArrayOutputStream and System.setXXX is simple:

private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

@Before
public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
}

@After
public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
}
sample test cases:

@Test
public void out() {
    System.out.print("hello");
    assertEquals("hello", outContent.toString());
}

@Test
public void err() {
    System.err.print("hello again");
    assertEquals("hello again", errContent.toString());
}
I used this code to test the command line option (asserting that -version outputs the version string, etc etc)
 */

public class StockInvestmentTest
{
    @Test(dataProvider = "ValidInput", dataProviderClass = InputTest.class)
    public static void whenCorrectInputThenNoExceptionIsThrown( String[] input ) throws Exception
    {
        StockInvestment stockInvestment = new StockInvestment( input );
    }

    @Test
    public static void whenEmptyGrantPriceThenNoExceptionIsThrown() throws Exception
    {
        String[] input1 = { "VEST", "001B", "20120101", "1000", "" };
        StockInvestment stockInvestment = new StockInvestment( input1 );
    }

    @Test
    public static void whenEmptyGrantDateThenNoExceptionIsThrown() throws Exception
    {
        String[] input1 = { "VEST", "001B", "", "1000", "0.45" };
        StockInvestment stockInvestment = new StockInvestment( input1 );
    }

    @Test
    public static void whenEmptyEmployeeThenNoExceptionIsThrown() throws Exception
    {
        String[] input1 = { "VEST", "", "20120101", "1000", "0.45" };
        StockInvestment stockInvestment = new StockInvestment( input1 );
    }

    @Test
    public static void whenEmptyFieldThenNoExceptionIsThrown() throws Exception
    {
        String[] input1 = { "", "003B", "20120101", "1000", "0.45" };
        StockInvestment stockInvestment = new StockInvestment( input1 );
    }

    @Test(dataProvider = "ValidInput003B", dataProviderClass = InputTest.class)
    public static void whenMarketDateIsEqualToGrantDateThenOutputIs500( String[] input ) throws Exception
    {
        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMdd" );
        Date marketDate = dateFormatter.parse( "20130101" );
        double marketPrice = 1.00;

        assertEquals( 500.0, stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @Test(dataProvider = "ValidInput003B", dataProviderClass = InputTest.class)
    public static void whenMarketDateIsAfterGrantDateThenOutputIs500( String[] input ) throws Exception
    {
        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMdd" );
        Date marketDate = dateFormatter.parse( "20140101" );
        double marketPrice = 1.00;

        assertEquals( 500.0, stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @Test(dataProvider = "ValidInput003B", dataProviderClass = InputTest.class)
    public static void whenMarketDateIsBeforeGrantDateThenOutputIs0( String[] input ) throws Exception
    {
        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMdd" );
        Date marketDate = dateFormatter.parse( "20120101" );
        double marketPrice = 1.00;

        assertEquals( 0.0, stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @Test(expectedExceptions = ParseException.class)
    public static void whenGrantDateIsMisformattedThenExceptionThrown() throws Exception
    {
        String[] input = { "VEST", "003B", "2013/01/01", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMdd" );
        Date marketDate = dateFormatter.parse( "20120101" );
        double marketPrice = 1.00;

        System.out.println( stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @Test
    public static void whenVestIsLowercaseThenOutputIs500() throws Exception
    {
        String[] input = { "vest", "003B", "20130101", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMdd" );
        Date marketDate = dateFormatter.parse( "20140101" );
        double marketPrice = 1.00;

        assertEquals( 500.0, stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @Test
    public static void whenFirstFieldDoesNotExistThenOutputIs0() throws Exception
    {
        String[] input = { "vestr", "003B", "20130101", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMdd" );
        Date marketDate = dateFormatter.parse( "20140101" );
        double marketPrice = 1.00;

        assertEquals( 0.0, stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }

    @Test
    public static void whenDefaultsUsedThenOutputIs0() throws Exception
    {
        String[] input = { "vest", "003B", "", "1000", "0.50" };

        StockInvestment stockInvestment = new StockInvestment( input );

        SimpleDateFormat dateFormatter = new SimpleDateFormat( "YYYYMMdd" );
        Date marketDate = dateFormatter.parse( "20170101" );
        double marketPrice = 1.00;

        assertEquals( 0.0, stockInvestment.calculateCashGain( marketDate, marketPrice ) );
    }
}
