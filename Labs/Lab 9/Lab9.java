/** Complete the linked list code for
  *
  *  show()    - traverse the list printing out the value in each node
  *  insert()  - the values are to be inserted in increasing order
  *  remove()  - remove the node from the list with the first occurrence of parameter value
  *
  *  The C version of insert() and show() are contained in the file linked.c on the Notes page.
  *  The C version of remove() was to be written as part of Lab Exercise 6
  */

public class Lab9
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
    list1.showRR();

    list2.insert (51);
    list2.insert (68);
    list2.insert (24);
    list2.show();
    list2.insert (93);
    list2.show();
    list2.showRR();


    // remove some values
    list1.remove (10);
    list1.show();
    list1.remove (7);
    list1.show();
    list1.remove (3);
    list1.show();
    list1.remove (18);
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
    MyLNode curr = head;
    while(curr != null){
      System.out.printf(curr.getElem() + ", ");
      curr = curr.getNext();
    }
    System.out.println("");

  }

  public void insert (int v1)
  {
    MyLNode tmp = new MyLNode (v1);


     MyLNode curr = head;
     MyLNode prev = null;

     while ((curr != null) && (curr.getElem() < v1))
       {
        prev = curr;
        curr = curr.getNext();
       }

     tmp.setNext(curr);

     if (prev == null)
       {
        head = tmp;
       }
     else
       {
        prev.setNext(tmp);
       }

  }

  public void remove (int v1)
  {
    MyLNode curr = head;
    MyLNode prev = null;
    while(curr != null){

      if(curr.getElem() == v1){
        if(prev == null)
        {
          head = curr.getNext();
        }
        else
        {
          prev.setNext(curr.getNext());
        }

      }
      prev = curr;
      curr = curr.getNext();
    }
  }


  public void showRR(){

        MyLNode curr = head;
        reverseHelp(curr);
        System.out.println();
  }

  public void reverseHelp(MyLNode curr){
        if(curr != null){
            reverseHelp(curr.getNext());
            System.out.print(curr.getElem() + ", ");
        }
    }



}


class MyLNode
{
  private int elem;
  private MyLNode next;

  void setElem (int e)
  {
    elem = e;
  }

  int getElem ()
  {
    return elem;
  }

  void setNext (MyLNode n)
  {
    next = n;
  }

  MyLNode getNext()
  {
    return next;
  }

  public MyLNode (int v1)
  {
    elem = v1;
    next = null;
  }

  public MyLNode (int v1, MyLNode n)
  {
    elem = v1;
    next = n;
  }
}
