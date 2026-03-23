import java.util.*;
import java.io.*;
import java.sql.*;
public class Library_Management_System1 
{
    // -------------- postgrSQL details to connect with DB ---------------------------
   static final String url="jdbc:postgresql://localhost:5432/postgres";
   static final String user ="postgres";
   static final String password="Postgres123";
   static Scanner sc=new Scanner(System.in);
   // --------------------- ADD BOOK --------------------------
   public static void addBook()
   {
    try(Connection con=DriverManager.getConnection(url, user, password))
    {
        System.out.println("Enter Book name to add to the Library!!");
        String name=sc.nextLine();
        String sql="INSERT INTO books(name,is_issued) VALUES (?,?)";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setBoolean(2, false);
        int rows =pst.executeUpdate();
        System.out.println(rows + " Book added!! ");

    }
    catch(Exception l)
    {
        System.out.println(l.getMessage());
    }
   }

   // ---------------------  VIEW BOOK -----------------------

   public static void viewBooks()
   {
    try(Connection con=DriverManager.getConnection(url, user, password))
    {
        String sql="SELECT * FROM books ORDER BY id";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        System.out.println(" /n -------------- BOOKS -----------------");
        while(rs.next())
        {
            System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getBoolean("is_issued"));
        }
    }
    catch(Exception v)
    {
        System.out.println(v.getMessage());
    }
   }

   // ---------------------------- ISSUE BOOK ---------------------------

   public static void issue_book()
   {
    try(Connection con=DriverManager.getConnection(url, user, password))
    {
        System.out.println(" Enter Book ID to issue ");
        int id=Integer.parseInt(sc.nextLine());
        String sql="UPDATE books SET is_issued=true WHERE id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,id);
        int rows=pst.executeUpdate();
        System.out.println(rows>0?"Book Issued !!" : " Book Not Issued !!");

    }
    catch(Exception t)
    {
        System.out.println(t.getMessage());
    }
   }

   // ----------------------- RETURN BOOK ----------------------------
   public static void returnBook()
   {
    try(Connection con=DriverManager.getConnection(url, user, password))
    {
        System.out.println(" Enter Book Id to return to the Library !");
        int id=Integer.parseInt(sc.nextLine());
        String sql="UPDATE books SET is_issued=false WHERE id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,id);
        int rows=pst.executeUpdate();
        System.out.println(rows>0? "Book returned to the library !!" : " Book not Returned to the library");
    }
    catch(Exception z)
    {
        System.out.println(z.getMessage());
    }
}

// --------------------------- DELETE BOOK -------------------------------

public static void deletebook()
{
    try(Connection con=DriverManager.getConnection(url, user, password))
    {
        System.out.println(" Enter Book id to delete from the library System!!");
        int id=Integer.parseInt(sc.nextLine());
        String sql="DELETE FROM books WHERE id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setInt(1,id);
        int rows=pst.executeUpdate();
        System.out.println(rows>0 ? " Book deleted from the library !1" : " Book Not Found ~~~");
    }
    catch(Exception b)
    {
        System.out.println(b.getMessage());
    }
}
public static void main(String[] args) 
{
    int choice;

    do
    {
        System.out.println(" \n ================ LIBRARY MANAGEMENT SYSTEM ======================");
        System.out.println("1. Add Book to the library");
        System.out.println("2. View Books from the library");
        System.out.println("3. Issue book to the customer ");
        System.out.println("4. Return Book to the library");
        System.out.println("5. Delete Book from the library!");
        System.out.println(" 6. Enter 6 to exit the System!");
        try
        {
            System.out.println(" Enter Choice : ");
            choice=Integer.parseInt(sc.nextLine());
            switch(choice)
            {
                case 1->
                    addBook();
                case 2->
                    viewBooks();
                case 3 ->
                    issue_book();
                case 4 ->
                    returnBook();
                case 5 ->
                    deletebook();
               case 6 ->
                    System.out.println(" Exiting !..........");
               default ->
                    System.out.println(" Invalid Option ");
            }
        }
        catch(Exception g)
        {
            System.out.println(" Enter valid Number !!");
            choice=0;
        }
    } while(choice!=6);
    sc.close();
}
    
}