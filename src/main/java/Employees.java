import java.util.Set;
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
        String employeeId;

        for (String[] row : input)
        {
            employeeId = row[StockInvestment.Fields.EMPLOYEE.getIndex()];

            if( employees.containsKey( employeeId ) )
            {
                // Add the stock to the employee
                Employee employee = employees.get( employeeId );
                employee.addStock( row );
            }
            else
            {
                employees.put( employeeId, new Employee( row ) );
            }

            //for( String field : row )
            //    System.out.print( field + " " );

            //System.out.println();
        }
    }

    public TreeMap<String, Employee> getEmployees()
    {
        return employees;
    }

    //@Override
    //public String toString() {
    //    return firstName + " " + lastName + " " + salary;
    //}

    public void outputEmployees()
    {
        Set mapset = employees.entrySet();
        System.out.println( mapset );
    }
}
