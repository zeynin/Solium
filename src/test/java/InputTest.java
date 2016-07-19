import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Zeynin on 2016-07-17.
 */
public class InputTest
{
    private static String[] input = {"VEST", "001B", "20120101", "1000", "0.45"};
    public final int inputLength = input.length;

    @DataProvider( name = "ValidInput" )
    public static Object[][] ValidInput()
    {
        return new Object[][] { { input }, };
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
    public void testInput() throws Exception
    {
        InputStream stdin = null;
        try
        {
            stdin = System.in;
            //Give the file path
            FileInputStream stream = new FileInputStream( "input.def" );
            System.setIn( stream );
            HandleInput handleInput = new HandleInput();
            String[][] result = handleInput.getInput();
            String[] marketInfo = handleInput.getMarketData();

            for (String[] row : result)
            {
                for( String field : row )
                    System.out.print( field + " " );

                System.out.println();
            }

            for (String s : marketInfo)
                System.out.print( s + " " );

            stream.close();
        }
        finally
        {
            //Reset System instream
            System.setIn( stdin );
        }

    }
}
