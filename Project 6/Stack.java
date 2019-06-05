// @Author - Srinivas C Lingutla, Net-Id: slingu2 , UIN: 655115444, UIC


public class Stack { //stack linked list to contain the

  public static class Node1 {
      String item;
      Node1 next;
   }

   private Node1 top;

   public Stack() {
     top = null;
   }


   public void push( String N ) { //add a new linked list to the front of the linked list
      Node1 newTop;
      newTop = new Node1();
      newTop.item = N;
      newTop.next = top;
      top = newTop;
   }


   public void pop() { //pop the front of the linked list
      if ( top == null )
         System.out.println("LIST is empty!");
      top = top.next;
   }

   public String top() { //get the top of the linked list
      if(top == null){
          System.out.println("LIST is empty!");
          return "-999";
      }
      String topItem = top.item;
      return topItem;
   }

   public boolean IsInStack(String fname) //check whether the filename is in stack or not
   {
     Node1 tempTop = top;
     while(tempTop != null)
     {
       if((tempTop.item).equals(fname))
          return true;
       else
          tempTop = tempTop.next;
     }
     return false;
   }


   public int isEmpty() { //check whether the stack is empty or not
      if (top == null)  return 1;

      else  return 0;
   }

}
