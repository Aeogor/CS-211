/** Complete code for the linked list with values in increasing order.
  *
  * Add code for:
  *  getListLength()  - return the number of nodes in the list
  *  getNthElem()     - return the Elem at the Nth node in the list
  *
  *  setIterToFront() - have the Iterator refer to the first Node
  *  isIterAtEnd()    - check if the Iterator is null
  *  getValueAtIter() - return the value store in the Node that the iterator is referring to
  *  setIterToNext()  - reposition the Iterator to the next node in the list
  *                       (Iterator becomes null is no more nodes in the list
  *
  */

public class Lab10
{
  public static void main (String[] args)
  {
    MyList list1 = new MyList();
    MyList list2 = new MyList();

    // insert some values
    list1.insert (5);
    list1.insert (18);
    list1.insert (10);
    list1.show();
    list1.insert (3);
    list1.show();

    System.out.println("Length of the list: " + list1.getListLength());

    // duplicate the show method OUTSIDE of the class
    // loop through the list using an external (to the list) position variable
    for (int pos = 0 ; pos < list1.getListLength() ; pos++)
    {
      int value = list1.getNthElem (pos);
      System.out.print (value + " ");
    }
    System.out.println();

    // duplicate the show method OUTSIDE of the class
    // loop through the list using an internal (to the list) iterator data member

    System.out.print ("Iterator:  ");
    for (list1.setIterToFront() ; !list1.isIterAtEnd() ; list1.setIterToNext() )
    {
      int value = list1.getValueAtIter ();
      System.out.print (value + " ");
    }
    System.out.println();

    list2.insert (51);
    list2.insert (68);
    list2.insert (24);
    list2.show();
    list2.insert (93);
    list2.show();

    // remove some values
    list1.remove (10);


    list1.show();
    list1.remove (7);
    list1.show();
    list1.remove (3);
    list1.show();
    list1.show();

    list2.remove (93);
    list2.remove (24);
    list2.remove (51);
    list2.show();
    list2.remove (68);
    list2.show();
    list2.remove (3);
    list2.show();
    list2.remove (18);
    list2.show();
  }
}


class MyList
{
  private MyLNode head;

  public MyList()
  {
    head = null;
  }

  public void show()
  {
    MyLNode tmp = head;

    while (tmp != null)
    {
      System.out.print(tmp.elem + ", ");
      tmp = tmp.next;
    }
    System.out.println();
  }

  public void insert (int v1)
  {
    MyLNode tmp = new MyLNode (v1);

    MyLNode curr = head;
    MyLNode prev = null;

    while ((curr != null) && (curr.elem < v1))
    {
      prev = curr;
      curr = curr.next;
    }

    if ( prev == null )
    {
      head = tmp;
      tmp.next = curr;
    }
    else
    {
      prev.next = tmp;
      tmp.next = curr;
    }
  }

  public void remove (int v1)
  {
    MyLNode curr = head;
    MyLNode prev = null;

    while ((curr != null) && (curr.elem != v1))
    {
      prev = curr;
      curr = curr.next;
    }

    if ( curr == null )
    {
      // v1 does not occur in the list
      return;
    }

    if ( prev == null )
    {
      head = curr.next;
    }
    else
    {
      prev.next = curr.next;
    }
  }

  // methods to use with External position variable
  public int getListLength ()
  {

      int i = 0;
      MyLNode curr = head;
      while(curr != null){
          i++;
          curr = curr.next;
      }
      return i;
  }

  public int getNthElem( int n)
  {
    int i = 0;
    MyLNode curr = head;
    while(curr != null){
        if(n == i)
        {
          return curr.elem;
        }

        i++;
        curr = curr.next;
    }
    return -999;
  }

  // methods to use with INTERNAL iterator data member
  MyLNode iterVar;   // INTERNAL iterator data member
  public void setIterToFront ( )
  {
    iterVar = head;
  }

  public boolean isIterAtEnd ( )
  {
    if(iterVar == null)
      return true;
    else
      return false;
  }

  public int getValueAtIter ( )
  {
    if(iterVar != null)
      return iterVar.elem;
    else
      return -999;
  }

  public void setIterToNext ( )
  {
      iterVar = iterVar.next;
  }



}


class MyLNode
{
  int elem;
  MyLNode next;

  MyLNode (int v1)
  {
    elem = v1;
    next = null;
  }
}
