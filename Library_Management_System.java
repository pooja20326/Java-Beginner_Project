import java.util.*;
import java.io.*;

// ------------BOOK CLASS----------

class Book implements Comparable<Book>
{
    private int id;
    private String name;
    private boolean isIssued;
    public Book(int id,String name,boolean isIssued)
    {
        this.id=id;
        this.name=name;
        this.isIssued=isIssued;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public boolean isIssued()
    {
        return isIssued;
    }
    public void issueBook()
    {
        isIssued=true;
    }
    public void returnBook()
    {
        isIssued=false;
    }
    @Override
    public int compareTo(Book other)
    {
        return this.name.compareTo(other.name);
    }
    @Override
    public String toString()
    {
        return "ID : " + id + " name : " + name +" Issued : " + isIssued;
    }

    // -------------- FILE FORMAT ------------------
    public String toFileString()
    {
        return id +"," + name + "," + isIssued; 
    }
}

//-------------- LIBRARY SERVICE WITH FILE HANDLING & EXCEPTION-------------

class LibraryService
{
    private Map<Integer, Book> books=new HashMap<>();
    private final String fileName="books.txt";

    //------------------LOAD FROM FILE-------------
    public void loadFromFile()
    {
        File file=new File(fileName);
        if(!file.exists())
             return;
        try(BufferedReader br=new BufferedReader(new FileReader(file)))
        {
            String line;
            while((line=br.readLine())!=null)
            {
                String[] part=line.split(",");
                int id=Integer.parseInt(part[0].trim());
                String name=part[1].trim();
                boolean isIssued=Boolean.parseBoolean(part[2].trim());
                books.put(id,new Book(id,name,isIssued));

            }
        }
        catch(IOException e)
        {
            System.out.println(" error in loading file !!!" +e.getMessage());
        }
    }
    // ---------- ADD BOOK -------------
    public void addBook(int id, String name)
    {
        if(books.containsKey(id))
        {
            System.out.println("Book already exists!!");
            return;
        }
        books.put(id, new Book(id,name,false));
        System.out.println("Book Added SuccessFully!!!!");
    }
    // --------- VIEW --------
    public void viewBook()
    {
        if(books.isEmpty())
        {
            System.out.println("No books Available!!!!");
            return;
        }
        for(Book b:books.values())
        {
            System.out.println(b);
        }
    }

    // --------- ISSUE -----------
    public void issueBook(int id)
    {
        Book b=books.get(id);
        if(b==null)
        {
            System.out.println("Book Not Found!!");
        }
        else if(b.isIssued())
        {
            System.out.println("Already issued !!!");
        }
        else
        {
            b.issueBook();
            System.out.println("Book issued");
        }
    }

    // -------- RETURN ------------
     public void returnBook(int id)
     {
        Book b=books.get(id);
        if(b==null)
        {
            System.out.println("Book not Found !!");
        }
        else if(!b.isIssued())
        {
            System.out.println("Not Issued!!");
        }
        else
        {
            b.returnBook();
            System.out.println("Book returned!!!!!");
        }
    }
    // ------------ DELETE --------------
    public void deleteBook(int id)
    {
        if(books.remove(id)!=null)
        {
            System.out.println("Book Deleted!!!!");
        }
        else
        {
            System.out.println("Book Not found!!!!");
        }
    }

    // ------- SORT -------
    public void sortBook()
    {
        List<Book> list=new ArrayList<>(books.values());
        Collections.sort(list);
        for(Book b: list)
        {
            System.out.println(b);
        }
    }
    public void saveToFile()
    {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(fileName)))
        {
            for(Book b:books.values())
            {
                bw.write(b.toFileString());
                bw.newLine();
            }
        }
        catch (IOException e) 
        {
            System.out.println(" Error saving file : "+e.getMessage());
        }
    }
}

// -------- MAIN WITH EXCEPTION HANDLING -----------
public class Library_Management_System 
{
public static void main(String[] args) 
{
    Scanner sc=new Scanner(System.in);
    LibraryService lib=new LibraryService();
    lib.loadFromFile(); // -------- LOAD AT START -------
    int choice=0;
    do
    {
        System.out.println("\n --------- LIBRARY MENU ------------");
        System.out.println(" 1. Add Book ");
        System.out.println(" 2. View Book ");
        System.out.println(" 3. Issue Book ");
        System.out.println(" 4. Return Book ");
        System.out.println(" 5. Delete Book ");
        System.out.println(" 6. Sort Book ");
        System.out.println(" 7. Exit");
        try
        {
            System.out.println("Enter Choice ");
            choice=Integer.parseInt(sc.nextLine());
            switch(choice)
            {
                case 1 ->
                {
                    System.out.println("Enter Id : ");
                    int id=Integer.parseInt(sc.nextLine());
                    System.out.println("Enter Name : ");
                    String name=sc.nextLine();
                    lib.addBook(id,name);
                    lib.saveToFile();
                }
                case 2 ->
                {
                    lib.viewBook();
                }
                case 3->
                {
                    System.out.println("Enter ID : ");
                    int id=Integer.parseInt(sc.nextLine());
                    lib.issueBook(id);
                    lib.saveToFile();
                }
                case 4->
                {
                    System.out.println("Enter ID : ");
                    int id=Integer.parseInt(sc.nextLine());
                    lib.returnBook(id);
                    lib.saveToFile();
                }
                case 5->
                {
                    System.out.println("Enter ID : ");
                    int id=Integer.parseInt(sc.nextLine());
                    lib.deleteBook(id);
                    lib.saveToFile();
                }
                case 6->
                {
                    lib.sortBook();
                }
                case 7->
                {
                    System.out.println(" Exiting....................");
                }
                default ->
                {
                    System.out.println("Invalid Choice");
                }
            }
        }
        catch(NumberFormatException e)
        {

         System.out.println("Please Enter valid number :");
        }
        
    }while(choice!=7);
    sc.close();

}
}
    


 