import java.util.TreeMap;

/**
 * Created by Zeynin on 2016-07-18.
 */
public class Employees
{
    //private Employee[] employees;
    TreeMap<String, Employee> employees;

    public Employees( String[][] input/*, String[] marketData*/ ) throws Exception
    {
        super();
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
                //employee.calculateEmployeeCashGain( marketData );
            }
            else
            {
                Employee employee = new Employee( row );
                employees.put( employeeId, employee );
                //employee.calculateEmployeeCashGain( marketData );
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

    //public void outputEmployees()
    @Override
    public String toString()
    {
        String s = "";

        for( Employee employee : employees.values() )
        {
            //System.out.println( employee );
            s += employee + "\n";
        }

        return s;
    }

    public void calculateTotalGain( String[] marketData ) throws Exception
    {
        for( Employee employee : employees.values() )
        {
            employee.calculateEmployeeCashGain( marketData );
        }
    }
}
