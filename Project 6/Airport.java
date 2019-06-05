// @Author - Srinivas C Lingutla, Net-Id: slingu2 , UIN: 655115444, UIC



public class Airport
{
  private int size;
  private LinkedList[] airports;
  private int index;

  public Airport(int val) //initialise the graph size to 10
  {
    size = val;
    airports = new LinkedList[val + 1];
    for(int i = 0; i < val + 1; i++)
    {
      airports[i] = new LinkedList(); //initialise the airpots
    }
  }

  public void Resize(int val) //perform the resizeing of the graph by reinitialising the airports array
  {
    size = val;
    airports = new LinkedList[val + 1];
    for(int i = 0; i <= val; i++)
    {
      airports[i] = new LinkedList();
    }
  }

  public void InsertEdge(int airport1, int airport2) //insert the edge to the airport at an index
  {
    if(airport1 > size || airport2 > size)
    {
      System.out.println("The aiport " + airport1 + " to airport " + airport2 + " is outside the range of airports. Ignoring the command");
      return;
    }


    airports[airport1].add(airport2);
  }

  public void DeleteEdge(int airport1, int airport2) //delete the edge to the airport at an index
  {
    if(airport1 > size || airport2 > size)
    {
      System.out.println("The aiport " + airport1 + " is outside the range of airports. Ignoring the command");
      return;
    }

    airports[airport1].remove(airport2);
  }

  public void ListALL() //this function lists all the airports and its edges
  {
    int i = 0;
    for(i = 1; i <= size ; i++)
    {
      System.out.println("Airport: " + i + " - " + airports[i].Print());
    }
  }

  public int[] GetAirportArray(int val) //this function return the edges in an int array format
  {
      int length = 0;
      length = airports[val].size();
      int array[] = new int[length];
      array = airports[val].toArray();
      return array;
  }


  public int NumberofEdges(int val) //return the number of edges
  {
    return airports[val].size();
  }

  public void MarkALLUnvisited() //make everything as unvisted
  { int i;
    for(i = 0; i < size ; i++)
    {
      airports[i].SetUnvisited(); ;
    }
  }

  public void Mark_as_visited(int val) //mark the node as visited
  {
    airports[val].SetVisited();
  }

  public boolean isVisited(int val) //boolean function to check whether it is visited
  {
    if(airports[val].visited == true)
      return true;
    else
      return false;

  }
    
    public class LinkedList //linked list class to store the edges for the linked list
    {
        
        private Node head;
        private int listCount;
        boolean visited;
        
        
        public LinkedList()
        {
            
            head  = null;
            listCount = 0;
            visited = false;
        }
        
        public void SetVisited()
        {
            visited = true;
        }
        
        public void SetUnvisited()
        {
            visited = false;
        }
        
        
        public void add(int data)
        {
            
            Node temp = new Node(data);
            
            if(head == null)
            {
                head = temp;
            }
            else
            {
                Node current = head;
                while(current.getNext() != null)
                {
                    current = current.getNext();
                }
                
                current.setNext(temp);
            }
            
            listCount++;
            
        }
        
        
        public int get(int index)
        {
            if(index <= 0)
                return -999;
            
            Node current = head.getNext();
            for(int i = 1; i < index; i++)
            {
                if(current.getNext() == null)
                    return -999;
                
                current = current.getNext();
            }
            return current.getData();
        }
        
        public boolean remove(int d) {
            Node current = head;
            Node previous = null;
            boolean deletedANode = false;
            
            while (current != null) {
                if (current.getData() == d)
                {
                    if (current == head)
                    {
                        head = head.getNext();
                    }
                    else
                    {
                        previous.setNext(current.getNext());
                    }
                    deletedANode = true;
                    break;
                }
                else
                {
                    previous = current;
                }
                current = current.getNext();
            }
            if(deletedANode == true) listCount--;
            return deletedANode;
        }
        
        public int size()
        {
            return listCount;
        }
        
        public String Print()
        {
            Node current = head;
            String output = "";
            while(current != null)
            {
                output += "[" + Integer.toString(current.getData()) + "]" + ", ";
                current = current.getNext();
            }
            return output;
        }
        
        public int[] toArray()
        {
            Node current = head;
            int array[] = new int[listCount];
            int i = 0;
            while(current != null)
            {
                array[i] = current.getData();
                i++;
                current = current.getNext();
            }
            return array;
        }
        
        
        
        private class Node //node to contain the next node and the integer data
        {
            
            Node next;
            int data;
            
            public Node(int newData)
            {
                next = null;
                data = newData;
            }
            
            public Node(int newData, Node newNext)
            {
                next = newNext;
                data = newData;
            }
            
            public int getData()
            {
                return data;
            }
            
            public void setData(int newData)
            {
                data = newData;
            }
            
            public Node getNext()
            {
                return next;
            }
            
            public void setNext(Node newNext)
            {
                next = newNext;
            }
        }


}
}
