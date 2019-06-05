public class Lab8a
{
  // FIRST overloaded max method
  public static int max (int a, int b)
  {
    if (a > b)
      return a;
    else
      return b;
  }
  
  // SECOND overloaded max method
  public static double max (double a, double b)
  {
    if (a > b)
      return a;
    else
      return b;
  }
  
  // THIRD overloaded max method
  public static String max (String a, String b)
  {
    if (a.compareTo(b) < 0)
      return a;
    else
      return b;
  }
  
  // FOURTH overloaded max method
  public static int max (int[] a)
  {
    int m = a[0];
    for (int i = 0 ; i < a.length ; i++)
      if (a[i] > m)
        m = a[i];
    
    return m;
  }
  
  public static void main (String[] args)
  {
    System.out.println ("L1: The larger value is: " + max(2, 3));
    
    System.out.println ("L2: The larger value is: " + 
                        max("hello", "goodbye"));
    
    System.out.println ("L3: The larger value is: " + 
                        max(8.201, 3.54));
    
    int[] arr = new int[10];
    for (int i = 0 ; i < arr.length ; i++)
      arr[i] = (int)(Math.random() * 100);
    System.out.println ("L4: The largest value is: " + max(arr));
    
  }
}
  