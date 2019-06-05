// @Author - Srinivas C Lingutla, Net-Id: slingu2 , UIN: 655115444, UIC


public class Stack {

   private static class Node {
      int item;
      Node next;
   }

   private Node top;


   public void push( int N ) {
      Node newTop;
      newTop = new Node();
      newTop.item = N;
      newTop.next = top;
      top = newTop;
   }


   public void pop() {
      if ( top == null )
         System.out.println("Stack is empty!");
      top = top.next;
   }

   public int top() {
      if(top == null){
          System.out.println("Stack is empty!");
          return -999;
      }
      int topItem = top.item;
      return topItem;
   }


   public int isEmpty() {
      if (top == null)  return 1;

      else  return 0;
   }

}
