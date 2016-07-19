import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Zeynin on 2016-07-17.
 */
public class InputTest
{
    private InputStream stdin = null;
    private FileInputStream stream = null;
    private static String[] input = {"VEST", "001B", "20120101", "1000", "0.45"};
    public final int inputLength = input.length;
    private static String[][] rows = {
            {"VEST","001B","20120101","1000","0.45"},
            {"VEST","002B","20120101","1500","0.45"},
            {"VEST","002B","20130101","1000","0.50"},
            {"VEST","001B","20130101","1500","0.50"},
            {"VEST","003B","20130101","1000","0.50"}
            };
    private static String[] marketData = { "20140101" , "1.00" };
    private static String[] marketData2 = { "20120615", "1.00" };

    @DataProvider( name = "ValidInput" )
    public static Object[][] ValidInput()
    {
        return new Object[][] { { input }, };
    }

    @DataProvider( name = "ValidRows" )
    public static Object[][] ValidRows() { return new Object[][] { { rows } }; }
    //{
    //    HandleInput handleInput = new HandleInput();
    //    return new Object[][] { { handleInput.getInput() } };
    //}

    //@DataProvider( name = "ValidMarketData" )
    public String[] ValidMarketData()
    {
        return marketData;
    }

    public String[] ValidMarketData2()
    {
        return marketData2;
    }

    public String[] InvalidInput( int index )
    {
        String[] invalidInput = input.clone();
        invalidInput[index] = "";
        return invalidInput;
    }

    public String[] NegativeInvalidInput( int index )
    {
        String[] invalidInput = input.clone();
        invalidInput[index] = "-" + invalidInput[index];
        return invalidInput;
    }

    @DataProvider( name = "ValidInput003B" )
    public static Object[][] ValidInput003B()
    {
        String[] input1 = {"VEST", "003B", "20130101", "1000", "0.50" };

        return new Object[][] { { input1 }, };
    }

    @Test
    public void whenInitializedThenReturnRows() throws Exception
    {
        boolean testInput = true;

        try
        {
            InputTest test = new InputTest();
            HandleInput handleInput = new HandleInput();
            String[][] InputVerify = handleInput.getInput();

            testInput &= ( InputVerify.length == rows.length );

            for( int index = 0; index < InputVerify.length; index++ )
            {
                // Iterate through each field
                for( StockInvestment.Fields field: StockInvestment.Fields.values() )
                {
                    boolean b = rows[index][field.getIndex()].equals( InputVerify[index][field.getIndex()] );
                    testInput &= b;
                }
            }

            //stream.close();
        }
        finally
        {
            //Reset System instream
            System.setIn( stdin );
        }

        assertEquals( testInput, true );
    }

    @Test
    public void whenConstructorCalledThenNoException() throws Exception
    {
        try
        {
            InputTest test = new InputTest();
            HandleInput handleInput = new HandleInput();
            String[][] result = handleInput.getInput();
            String[] marketInfo = handleInput.getMarketData();

            for( String[] row : result )
            {
                for( String field : row )
                    System.out.print( field + " " );

                System.out.println();
            }

            for( String s : marketInfo )
                System.out.print( s + " " );

            //stream.close();
        }
        finally
        {
            //Reset System instream
            System.setIn( stdin );
        }
    }

    public InputTest() throws Exception
    {
        stdin = System.in;
        //Give the file path
        if( stream == null )
            stream = new FileInputStream( "input.def" );

        System.setIn( stream );
    }
}
