import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Zeynin on 2016-07-17.
 */
public class HandleInput
{
    private static String[][] consoleInput = null;
    private static String[] marketInput = null;

    public String[][] getInput()
    {
        return consoleInput;
    }

    public String[] getMarketData()
    {
        return marketInput;
    }

    public HandleInput() throws Exception
    {
        super();

        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        String line;

        // The first line is the number of rows to expect
        int rows = NumberUtils.toInt( br.readLine() );
        consoleInput = new String[rows][];

        for( int i = 0; i < rows; i++ )
        {
            consoleInput[i] = br.readLine().split( "," );
        }

        while( ( line = br.readLine() ) != null )
        {
            marketInput = line.split( "," );
        }

        br.close();
    }

}
