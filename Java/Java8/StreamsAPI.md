
1. A stream is not a data structure itself. It is a bunch of operations applied to a source. The source can be collections, arrays or I/O channels.
2. Streams don’t change the original data structure.
3. There can be zero or more intermediate operations that transform a stream into another stream.
4. Each intermediate operation is lazily executed.
5. Terminal operations produce the result of the stream.

<hr/>

2 ways to create Streams
1. Stream.of() method
   <pre><code>
     import java.util.stream.Stream;
     Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
     stream.forEach((s) -> System.out.println(s));
   </code></pre>
2. List.stream()
   <pre><code>
     List li1 = new ArrayList();
	   li1.addAll(Arrays.asList(1,2,3,4,5,6));
	   li1.stream().forEach((s) -> System.out.print(s));
   </code></pre>

The Stream Interfaces
1. Stream API defines Interfaces such as Stream, IntStream ,LongStream etc.,
2. Its best to use primitive stream interfaces for primitive types to avoid wrapping them to onbjects & then autoboxing.

Intermediate operations: These take functional Interfaces as input, dont produce any result & return a new Stream. Example: Filter, map
Terminal operations: These produces results. Example: toArray(...), count(), collect(...)
Operations can be classified as:
filtering
slicing
mapping
matching and finding
reduction
collect


<pre><code>
//FILTER
  list.stream()  // Created a stream from the list
      .filter(num -> num > 10)
      .forEach(System.out::println); 

//MAP, use mapToInt(), mapToDouble, mapToLong when resultant stream is a primitive
  list.stream()
      .map(name -> name.toUpperCase()) //map() takes an input of Function<T, R> type.
      .forEach(System.out::println)

//FLAT MAP - flatten a stream of collections to a stream of elements combined from all collections.
Like Stream<String[]> -> flatMap -> Stream<String>,  Stream<Set<String>> -> flatMap -> Stream<String> ,  Stream<List<String>> -> flatMap -> Stream<String>
Why flatten? Because intermediate functions wont work on Streams of collections but work only on streams of primitives or objects
   List<List<Integer>> fm = new ArrayList<>();
	 fm.add(Arrays.asList(1,2,3,4));
	 fm.add(Arrays.asList(6,7,8,9));
	 fm.add(Arrays.asList(10,6,7,8));
   fm.stream()
	  .flatMap(s -> s.stream())
	  .filter(s -> s%2 == 0)
	  .forEach(System.out::println);
</code></pre>

<h3>Method References</h3>
Like obj references but Lambda itself is a method reference, so these Method references are just shortended lambdas

<pre><code>
  // STATIC & INSTANCE METHOD REFERENCE
  list.stream()
      .mapToInt(str -> StreamDemo.getLength(str))
      .forEach(System.out::println);

  // Code with method reference.
  list.stream()
      .mapToInt(StreamDemo::getLength)  //IF getLength is not a static method instead a instance method then it would be demoObj::getLength
      .forEach(System.out::println);

  //CONSTRUCTOR REFERENCE
  list.stream()
      .map(name -> new Employee(name))
      .forEach(System.out::println);

   // Code with constructor reference
   list.stream()
       .map(Employee::new)
       .forEach(System.out::println);
</code></pre>

<h3> Optional Class </h3>


