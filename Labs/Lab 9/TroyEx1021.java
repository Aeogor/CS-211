// class to use the TroyStack class
public class TroyEx1021
{
  public static void main (String[] args)
  {
    // java style
    TroyStack l1 = new TroyStack();
    l1.addToFront( 5 );
    System.out.println ("The first value is: " + l1.getFront() );
    
    l1.addToFront ( 8 );
    System.out.println ("The first value is: " + l1.getFront() );
    
    TroyStack l2 = new TroyStack();
    l2.addToFront ( 4 );
    System.out.println ("The front value in l2 is: " + l2.getFront() );
    System.out.println ("The front value in l1 is: " + l1.getFront() );
    
    l1.addToFront ( 18 );
    l1.addToFront ( 28 );
    l1.addToFront ( 38 );
    l1.addToFront ( 48 );
    l1.addToFront ( 58 );
    
    while ( l1.isEmpty() == false )
    {
      System.out.println ("The front value is: " + l1.getFront() );
      l1.removeFromFront();
    }
  }
}

//  could exist in a file not named TroyNode.java
//    often the Node class is put into a List.java file
class TroyNode
{
  private   Integer  elem;
  private   TroyNode next;

  //  getters and setters
  public int getElem ()
  {
     return elem;
  }

  public void setElem (int e)
  {
     elem = e;
  }

    public TroyNode getNext ()
  {
     return next;
  }

  public void setNext (TroyNode n)
  {
     next = n;
  }

  // constructor
  TroyNode (int e, TroyNode n)
  {
     elem = e;
     next = n;
  }

  TroyNode ()
  {
     elem = -999;
     next = null;
  }
}


// The stack class which uses the Node class
//  Should contain the following methods
//    - a constructor     - intialize an empty list
//    - addToFront()      - push 
//    - getFront()        - top
//    - removeFromFront() - pop
//    - isEmpty()         - isEmpty
class TroyStack
{
  TroyNode head;

  TroyStack ()
  {
    head = null;
  }

  public void addToFront( int val)
  {
    head = new TroyNode (val, head );
  }
  
  public int getFront()
  {
    return head.getElem();
  }
  
  public void removeFromFront()
  {
    head = head.getNext();
  }
  
  public boolean isEmpty()
  {
    return (head == null);
  }

}



