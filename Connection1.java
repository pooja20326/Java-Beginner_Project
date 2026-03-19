import java.sql.*;
public class Connection1
{
     public static void main(String[] args)
    {
        // Step 1 : Declare Connection Object
        Connection con=null;
        try
        {
            // Step 2 : Connect to Postgres...
            con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
            
            "postgres",
            "Postgres123"
            );
            // Step 3 : IF CONNECTED
            if(con!=null)
            {
                System.out.println(" Connected to PostgreSQL Successfully!!!!");
            }
        }
        catch(SQLException e)
        {
             System.out.println("Connection FAILED !!!!!!!!");
        }
        finally
        {
            try
            {
                if(con!=null)
                {
                    con.close();
                }
            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}