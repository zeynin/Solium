import java.util.TreeMap;

/**
 * Created by Zeynin on 2016-07-18.
 */
public class Employees
{
    //private Employee[] employees;
    TreeMap<String, Employee> employees;

    public Employees( String[][] input )
    {
        employees = new TreeMap<String, Employee>();

        for (String[] row : input)
        {
            if( employees.containsKey( row[StockInvestment.Fields.EMPLOYEE.getIndex()] ) )
            {
                // Add the stock to the employee
                Employee employee = employees.get( row[StockInvestment.Fields.EMPLOYEE.getIndex()] );
                employee.addStock( row );
            }
            else
            {
                employees.put( row[StockInvestment.Fields.EMPLOYEE.getIndex()], new Employee( row ) );
            }

            for( String field : row )
                System.out.print( field + " " );

            System.out.println();
        }
    }
}
