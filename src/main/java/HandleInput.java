import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Zeynin on 2016-07-17.
 */
public class HandleInput
{
    private static String[][] input = null;
    private static String[] marketData = null;

    public String[][] getInput()
    {
        return input;
    }

    public String[] getMarketData()
    {
        return marketData;
    }

    public HandleInput()
    {
        super();

        try
        {
            BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
            String line;

            // The first line is the number of rows to expect
            int rows = NumberUtils.toInt( br.readLine() );
            input = new String[rows][];

            for( int i = 0; i < rows; i++ )
            {
                input[i] = br.readLine().split( "," );
            }

            while( ( line = br.readLine() ) != null )
            {
                marketData = line.split( "," );
            }

            br.close();
        }
        catch( IOException e )
        {
            // illegal System.out.println( "Exception: " + e );
        }
    }

}
