import java.util.*;
import java.io.*;
//------Student class---------
class Student
{
 private int id;
 private String name;
 private int marks;  
 public Student(int id, String name,int marks)
 {
    this.id=id;
    this.name=name;
    this.marks=marks;
 } 
 public int getId()
 {
    return id;
 }
 public String getName()
 {
    return name;
 }
 public int getMarks()
 {
    return marks;
 }
 public void setMarks(int marks)
 {
    this.marks=marks;
 }
 @Override
 public String toString()
 {
    return "ID : "+id+" , Name : "+ name + " Marks : " + marks;
 }

 //-----SAVE STUDENT IN A FILE-FRIENDLY FORMATE--------------

 public String toFileString()
 {
    return id+" , "+name+" , "+marks;
 }
}
//----------SYSTEM CLASS------------
class StudentManagementSystem
{
    private Map<Integer, Student> students=new HashMap<>();
    private final String filename="students.txt";

    //----LOAD STUDENTS FROM FILE-------
    public void loadFromFile()
    {
        File file=new File(filename);
        if(!file.exists()) return;
        
            try (BufferedReader br=new BufferedReader(new FileReader(file)))
            {
                String line;
                while((line=br.readLine())!=null)
                {
                    String[] parts=line.split(",");
                    int id=Integer.parseInt(parts[0].trim());
                    String name=parts[1].trim();
                    int marks=Integer.parseInt(parts[2].trim());
                    students.put(id,new Student(id,name,marks));
                }
            }
            catch(IOException e)
            {
                System.out.println("Error loading File : "+e.getMessage());
            }
        }
    

        //--------SAVE STUDENT TO FILE---------

        public void saveToFile()
        {
        
            try(BufferedWriter bw=new BufferedWriter(new FileWriter(filename)))
            {
               for(Student s : students.values())
               {
                 bw.write(s.toFileString());
                 bw.newLine();
               }
            }
            catch(IOException e)
            {
                System.out.println(" Error Saving FIle!!"+e.getMessage());
            }
       }
       
       //----ADD STUDENT------
        
       public void addStudent(int id, String name, int marks)
       {
        if(students.containsKey(id))
        {
            System.out.println("Studnet id already Existed!!!!!");
            return;
        }
        students.put(id, new Student(id,name,marks));
        System.out.println("Student Added Successfully");
       }
       //------VIEW STUDENT------
       public void ViewStudent()
       {
        if(students.isEmpty())
        {
         System.out.println("No Student Available");
         return;
        }
        for(Student s:students.values())
        {
            System.out.println(s);
        }
       }

       //--------SEARCH STUDENT BY ID-----------

       public void searchStudent(int id)
       {
         Student s=students.get(id);
         if(s==null)
         {
            System.out.println("Student Not Found");
         }
         else
        {
            System.out.println(s);
        }
       }

       //--------UPDATE MARKS-------
       
       public void updateMarks(int id, int marks)
       {
       Student s=students.get(id);
       if(s==null)
        {
            System.out.println("Student not found");
        } 
        else
        {
            s.setMarks(marks);
            System.out.println("Marks updated Successfully!!!!!");
        }
       }

       //---------Delete Student---------
       public void deleteStudent(int id)
       {
         if(students.remove(id)!=null)
         {
            System.out.println("Student Removed Successfully");
         }
         else
         {
            System.out.println("Student not found!!");
         }
       }

       //-----------Finding TOPPER STUDENT-----------
       public void findTopper()
       {
        if(students.isEmpty())
        {
            System.out.println("No Students Available");
            return;
        }
        Student topper=null;
        for(Student s:students.values())
        {
            if(topper==null||s.getMarks()>topper.getMarks())
            {
                topper=s;
            }
        }
        System.out.println("Topper Student :");
        System.out.println(topper);
    }

    // ----------SHOW TOP 3 STUDENTS----------------
    public void topThreeStudent()
    {
        if(students.isEmpty())
        {
            System.out.println("No Students Available");
            return;
        }
        List<Student> list=new ArrayList<>(students.values());
        list.sort((s1,s2)->{
            if(s1.getMarks()>s2.getMarks())
                return -1;
            else if(s1.getMarks()<s2.getMarks())
                return 1;
            else
                return 0;
        });
        System.out.println("Top 3 Student of a Class");
        int count=0;
        for(Student s:list)
        {
            System.out.println(s);
            count ++;
            if(count==3)
            {
                break;
            }
        }
    }
      
        //--------SORT STUDENT BY MARK-------------
       public void sortStudent()
       {
        List<Student> list=new ArrayList<>(students.values());
        list.sort((s1,s2)->
        {
           if(s1.getMarks()>s2.getMarks())
           {
             return -1;
           }
             
        else if(s1.getMarks()<s2.getMarks())
        {
            return 1;
        }
        else
        {

            return 0 ;
        }
        });
       
       for(Student s:list)
       {
        System.out.println(s);
       }
    }
}
       //-------MAIN CLASS--------
        public class Student_Management
        {
            public static void main(String[] args)
       {
         Scanner sc=new Scanner(System.in);
         StudentManagementSystem sm=new StudentManagementSystem();
         sm.loadFromFile();
         int choice;
         do
         {
            System.out.println("\n ---- Student Management System ----");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Search by Student");
            System.out.println("4. Update Mark");
            System.out.println("5. Delete Student");
            System.out.println("6. Topper of the Class");
            System.out.println("7. Sort Student by Marks");
            System.out.println("8. Show Top 3 Student of a class");
            System.out.println("9. Exit");
            System.out.println("Enter your choice");
            try
            {
                 choice=Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException e)
            {
                System.out.println(" please enter a valid number");
                continue;
            }
            switch (choice) {
                case 1 ->
                {
                    try
                    {
                          System.out.println("Enter id : ");
                          int id=Integer.parseInt(sc.nextLine());
                          System.out.println("Enter name");
                          String name=sc.nextLine();
                          System.out.println("Enter Mark");
                          int marks=Integer.parseInt(sc.nextLine());
                          sm.addStudent(id,name,marks);
                          sm.saveToFile();
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Invalid input plaese enter number for id and marks!!");
                    }
                    
                }
                case 2-> sm.ViewStudent();
                case 3->
                {
                   System.out.println("Enter id to search!!");
                   int id=Integer.parseInt(sc.nextLine());
                   sm.searchStudent(id); 
                }
                case 4 ->
                {
                    System.out.println("Enter id to update marks!!!");
                    int id=Integer.parseInt(sc.nextLine());
                    System.out.println("Enter mark to update!!");
                    int marks=Integer.parseInt(sc.nextLine());
                    sm.updateMarks(id , marks);
                    sm.saveToFile();
                }                    
                case 5 ->
                {
                    System.out.println("Enter id to delete!");
                    int id=Integer .parseInt(sc.nextLine());
                    sm.deleteStudent(id);
                    sm.saveToFile();
                }
                case 6->
                {
                    sm.findTopper();
                }
                case 7->
                {
                    sm.sortStudent();
                }
                case 8->
                {
                    sm.topThreeStudent();
                }
                case 9->
                {
                    System.out.println(" Exiting System.Goodbye!!!!");
                    return;
                }
            
                default->
                    System.out.println("Invalid Choice!");
            }

        }while(true);
       }
        
     }


       
    

