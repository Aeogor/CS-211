public class Lab11
{
  public static void doStuff(Animal a)
  {
    System.out.println ("\nIn doStuff with a " + a.getType() );
    a.sayHello();  // LINE 6
    a.move();  // LINE 7
    a.say(); // LINE 8
  }

 public static void main (String[] args)
  {
    Animal a = new Animal("Animal");
    doStuff (a);   // LINE 14

    Dog d = new Dog("Dog");
    doStuff(d);    // LINE 17

    Bird b = new Bird("Bird");
    doStuff(b);    // LINE 20

    a = d;
    doStuff (a);   // LINE 23

    a = b;
    doStuff (a);   // LINE 26
  }
}


// Animal class - note should be in its own file of Animal.java
class Animal
{
  private String type;

  public Animal (String t)
  {
    type = t;
  }

  public String getType()
  {
    return type;
  }

  public void sayHello()
  {
    System.out.println("Hello.  I am a " + type + ".");
  }

  public void move()
  {
    System.out.println ("I am moving.");
  }

  public void say()
  {
    System.out.println ("I am speaking.");
  }
}

// Canine class - note should be in its own file of Canine.java
class Canine extends Animal
{
  public Canine (String t)
  {
    super(t);               // LINE 67
  }

  @Override public void move()
  {
    System.out.println ("I am running.");
  }
}


// Dog class - note should be in its own file of Dog.java
class Dog extends Canine
{
  private int age;

  public Dog (String t)
  {
    super(t);               // LINE 84
  }

  @Override public void say()
  {
    System.out.println ("Woof. Woof.");
  }
}

// Wolf class - note should be in its own file of Wolf.java
class Wolf extends Canine
{
  private int age;

  public Wolf (String t)
  {
    super(t);
  }

  @Override public void say()
  {
    System.out.println ("Winter is coming.");
  }
}


// Fox class - note should be in its own file of Fox.java
class Fox extends Canine
{
  private int age;

  public Fox (String t)
  {
    super(t);
  }
}


// Bird class - note should be in its own file of Bird.java
//
//  Note that the code used here contains a mistake.
//    adding in the @Override keyword helps to resolves the mistake in the code.
class Bird extends Animal
{
  public Bird (String t)
  {
    super(t);
  }

  public void move()
  {
    System.out.println ("I am flying.");
  }

  @Override public void say()
  {
    System.out.println ("Tweet. Tweet.");
  }
}
