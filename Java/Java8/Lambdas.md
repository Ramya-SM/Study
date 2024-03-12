<html style="code-fold:true;">
In many cases there is no need of creating multiple preexisting classes, an anonymous class object created in the code where required should be enough.
Anonymous class when has just one overriden method & simple code then simplified version of it is Lambda functions, enabling functions to be passed as arguments.

Example:  someMethod(() -> System.out.Println("something"));

Ideal candidates to be Lambdas
1. Functional interface
2. Has only one abstract method to override

<br/>
<pre>
<code>
import java.util.Collections;
import **java.util.Comparator**;
import java.util.List;
public class PersonService {
    public static List<Person> getPersons(List<Person> persons){
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        return persons;
}} <br/>
The whole comparator class can be replaced by Lambda function <br/>
Collections.sort(persons, (p1, p2) -> p1.getName().compareTo(p2.getName()));
</code>
</pre>

<hr/>

<h3> Inbuilt Functional Interfaces </h3>

  1. Java 8 provides some in-built functional interfaces in the java.util.function package
  2. There are 43 such & can be divided into categories such as 
      * Predicate
      * Supplier
      * Consumer

<h3> Introduction to the Predicate functional interface </h3>
The Predicate<T> interface has few static/default methods & one abstract method test()
an(), isEqual(), or(), negate() , test()
type of Predicate : Predicate, BiPredicate, IntPredicate, Longredicate, DoublePredicate
<pre>
  <code>
      Predicate<Person> lessThan18 = (p) -> p.age < 18;
	    Predicate<Person> moreThan60 = (p) -> p.age > 60;
	    Predicate<Person> moreThan50 = (p) -> p.age > 50;
	    BiPredicate<Person, Integer> biP = (p, minAge) -> p.age < minAge;
	    
	    System.out.println("is minor? " + lessThan18.test(p1));
	    System.out.println("is an adult? " + lessThan18.negate().test(p1));
	    System.out.println("is not midage? " + lessThan18.negate().or(moreThan60.negate()).test(p1));
	    System.out.println("is near retirement/retired? " + moreThan60.and(moreThan50).test(p1));
	    System.out.println("a teen? " + biP.test(p1, 20));
  </code>
</pre>
<hr/>

<h3> Introduction to the Supplier functional interface </h3>
useful when no arg to be provided but get values
get(), getAsInt(), getAsLong(), getAsDouble(), getAsBoolean()
<pre>
  <code>
    Supplier<Person> supplier = () -> new Person("Alex", 23);
    Predicate<Person> predicate = (p) -> p.age > 18;
    predicate.test(supplier.get());

    DoubleSupplier dSupplier = () -> (int)(Math.random() * 10); 
    System.out.println(dSupplier.getAsDouble());
  </code>
</pre>

<h3> Introduction to the Consumer functional interface </h3>
1. take in a parameter and do not produce anything. 
2. Has an abstract method accept(ref) , a default method andThen(Consumer)
Consumer, DoubleConsumer, IntConsumer, LongConsumer, BiConsumer, ObjIntConsumer, ObjDoubleConsumer, ObjLongConsumer
accept(ref), accept(double), accept(int), accept(long), accept(ref,ref),accept(ref, int)....so on

<pre>
  <code>
    Consumer c1 = (arg) -> System.out.println("Hey "+ arg + "!"); 
    Consumer c2 = (arg) -> System.out.println("Your useID is - "+ arg+(int)Math.random()); 
	  c1.andThen(c2).accept("ABC");
  </code>
</pre>

<h3> Introduction to the functional functional interface </h3>
takes an object of type T and returns an object of type R.
(Predicate returns Boolean, Consumer returns nothing but takes args, Supplier takes nothing returns values, functional takes&returns)
  Interfaces -> Function  , IntFunction , BiFunction        , ToIntBiFunction          , LongToIntFunction/DoubleToIntFunction/ToIntFunction  ...same for long/double
  Methods    -> apply(ref), R apply(int), R apply(ref1,ref2), int applyAsInt(ref1,ref2), int applyAsInt(long/double/ref) 
has other methods like andThen(Function T, Function after), compose(Function T, Function before), identity() always returns its input argument

<pre>
  <code>
    Function<String,Integer> f1 = (str) -> str.length();
	  Function<Integer,Integer> f2 = (val) -> 16 - val;
	  System.out.println(f1.apply("Test"));
	  System.out.println(f2.compose(f1).apply("Test"));

    BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b; 
    System.out.println("Sum = " + add.apply(2, 3))
  </code>
</pre>

<h3> Introduction to the Unary, Binary operator </h3>
Unary: Implements Functional interface, so takes a value & returns a value of same type
Binary: Implements BiFunction interface, but unlike BiFunction it takes only one type of params

<pre><code >
	IntUnaryOperator operator = num -> num * num;
	System.out.println(operator.applyAsInt(25));
	IntBinaryOperator op = (o1, o2) -> o1 + o2;
	System.out.println(op.applyAsInt(5, 10));
</code></pre>

<h3> Capturing Lambdas </h3>
When lambdas use the static variable / instance variable / local variable(which is final/effectively final(not changed after initialization))
<pre><code>
	int i=9;
	Function<Integer> f = (val) -> val * i;
	f.apply(10);	
</code></pre>
















