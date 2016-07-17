import org.testng.annotations.DataProvider;

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

    //@DataProvider( name = "InvalidInput" )
    public String[] InvalidInput( int index )
    {
        String[] invalidInput = input.clone();
        invalidInput[index] = "";
        return invalidInput;
    }

    @DataProvider( name = "ValidInput003B" )
    public static Object[][] ValidInput003B()
    {
        String[] input1 = {"VEST", "003B", "20130101", "1000", "0.50" };

        return new Object[][] { { input1 }, };
    }
}
