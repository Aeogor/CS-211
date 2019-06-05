// @Author - Srinivas C Lingutla, Net-Id: slingu2 , UIN: 655115444, UIC


import java.io.*;
import java.util.*;

public class slingu2Proj6
{
  Stack fileList = new Stack();
  Airport graph = new Airport(10);

 public static void main (String[] args)
 {
   // set up an instance of the BufferedReader class to read from standard input
   BufferedReader br = new BufferedReader (new InputStreamReader (
        System.in));

   // set up the data needed for the airport adjcency list
   slingu2Proj6 airportData = new slingu2Proj6();

   // call the method that reads and parses the input
   airportData.processCommandLoop (br);

   System.out.println ("Goodbye");
 }




  public void processCommandLoop (BufferedReader br)
  {
    try {  //try-catch clauses are needed since BufferedReader and Scanner classes
           //  throw exceptions on errors
    String inline = br.readLine();   // get a line of input
    Scanner sc;

   // loop until all lines are read from the input
   while (inline != null )
   {
     sc = new Scanner (inline);   // process each line of input using the Scanner iterators
       String command = "";
       
       if ( sc.hasNext() == true )
             command = sc.next();
       
     System.out.println ("*" + command + "*");

     if (command.equals("q") == true)
       System.exit(1);

     else if (command.equals("?") == true)
       showCommands();

     else if (command.equals("t") == true)
       doTravel(sc);

     else if (command.equals("r") == true)
       doResize(sc);

     else if (command.equals("i") == true)
       doInsert(sc);

     else if (command.equals("d") == true)
       doDelete(sc);

     else if (command.equals("l") == true)
       doList(sc);

     else if (command.equals("f") == true)
       doFile(sc);

     else if (command.equals("#") == true)
       ;

     else
       System.out.println ("Command is not known: " + command);

     inline = br.readLine();   // get the next line of input

   }
  }
  catch (IOException ioe)
  {
    System.out.println ("Error in Reading - Assuming End of File");
  }
 }

 public void showCommands()
 {
   System.out.println ("The commands for this project are:");
   System.out.println ("  q ");
   System.out.println ("  ? ");
   System.out.println ("  # ");
   System.out.println ("  t <int1> <int2> ");
   System.out.println ("  r <int> ");
   System.out.println ("  i <int1> <int2> ");
   System.out.println ("  d <int1> <int2> ");
   System.out.println ("  l ");
   System.out.println ("  f <filename> ");
 }

 public void doTravel(Scanner sc)
 {
   int val1 = 0;
   int val2 = 0;

   if ( sc.hasNextInt() == true )
     val1 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }

   if ( sc.hasNextInt() == true )
     val2 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }

   System.out.println ("Performing the Travel Command from " + val1 +
                       " to " + val2);

   depthFirstSearchHelper(val1, val2);

 }

 public void doResize(Scanner sc)
 {
   int val1 = 0;

   if ( sc.hasNextInt() == true )
     val1 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }

    System.out.println ("Performing the Resize Command with " + val1 );

    graph.Resize(val1);

 }

 public void doInsert(Scanner sc)
 {
   int val1 = 0;
   int val2 = 0;

   if ( sc.hasNextInt() == true )
     val1 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }

   if ( sc.hasNextInt() == true )
     val2 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }

   System.out.println ("Performing the Insert Edge Command from " + val1 +
                       " to " + val2);
   graph.InsertEdge(val1, val2);  //insert the edge


   return;

 }

 public void doDelete(Scanner sc)
 {
   int val1 = 0;
   int val2 = 0;

   if ( sc.hasNextInt() == true )
     val1 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }

   if ( sc.hasNextInt() == true )
     val2 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }

   System.out.println ("Performing the Delete Edge Command from " + val1 +
                       " to " + val2);

   graph.DeleteEdge(val1, val2);  //delete the edge
 }

 public void doList(Scanner sc)
 {
   System.out.println ("Performing the DoList Command " );

   graph.ListALL();
 }

 public void doFile(Scanner sc)
 {
   String fname = null;

   if ( sc.hasNext() == true )
     fname = sc.next();
   else
   {
     System.out.println ("Filename expected");
     return;
   }

   System.out.println ("Performing the File command with file: " + fname);



   // next steps:  (print an error message and return if any step fails)
   //  1. verify the file name is not currently in use
   //  2. open the file
   //  3. create a new instance of BufferedReader
   //        BufferedReader br = new BufferedReader (new FileReader ("MyFileReader.txt"));
   //  4. recursively call processCommandLoop() with this new instance of BufferedReader as the parameter
   //  5. close the file when processCommandLoop() returns

   File file = new File(fname); //open the file


     if (fileList.IsInStack(fname) == true) //if it is in the stack, then return so it doesnt get processed
         return;
     else
         fileList.push(fname); //if not in stack push it on

    try {
       BufferedReader reader = new BufferedReader (new FileReader (fname)); //get new BufferedReader
       processCommandLoop (reader);  //call the processCommandLoop to read in the values of file
       fileList.pop(); //pop the stack once the stack is done
       reader.close(); //close the BufferedReader
       return;
    }
    catch (FileNotFoundException fnfe)
    {
      System.out.println ("File not found");
    }
    catch (Exception fnfe)
    {
      System.out.println ("General Error");
    }

 }
public void depthFirstSearchHelper (int x, int y)
{
    graph.MarkALLUnvisited(); //mark everything as unvisited
    if ( dfs (x, y) == true)  //if two airports are reachable
          System.out.println ("You can get from airport " + x + " to airport " + y + " in one or more flights");
    else
          System.out.println ("You can NOT get from airport " + x + " to airport " + y + " in one or more flights");
}

boolean dfs (int a, int b) //dfs function
{
  int length = 0;
  length = graph.NumberofEdges(a); //get the number of edges
  int array[] = new int[length]; //get the array of edges in the int array format

  array = graph.GetAirportArray(a); // get the array

  int i = 0;
  for(i = 0 ; i < length ; i++) //loop through the array
  {
    if(array[i] == b) //return if true
      return true;
    if(graph.isVisited(array[i]) == false)  //if false, mark as visited and recursivly call
    {
      graph.Mark_as_visited(array[i]);
      if(dfs(array[i], b) == true)
        return true;
    }
  }
  return false; //if not possible return false
}



}
