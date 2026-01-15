# Java 8 
It is one of the most significant releases in the history of the Java programming language. Released on March 18, 2014, by Oracle.
It introduced revolutionary features that transformed the way Java is used, 
-> particularly in conjunction with functional programming
-> To enable parallel programming
-> More caompatible code for multi core processors

# Features of Java 8
-> Support for functional programming with Lambda expressions and functional interfaces.
-> Introduction of the Stream API for efficient data processing.
-> A new Date and Time API for better handling of time and date operations.
-> Default and static methods in interfaces for greater flexibility.
-> Base64 Encode Decode 
-> Method reference and constructor reference
-> Functional Interface
-> Optional Class
-> Java IO Improvements
-> Collection API Improvements

# What is Lambda Expression
Lambda function is an anonymous function
Not having modifiers
Not having having any return type
Not having any name

Steps to make any function into lambda expression
1. Remove Modifier
2. Remove return type
3. Remove method name
4. Place arrow

example:
private void add(int a, int b) {
    System.out.println(a+b);
}
                |
                |
(int a, int b) -> {System.out.println(a+b);}
                |
                |
(a,b) -> System.out.println(a+b);

If you have single parameter
private int getStringLength(String str) {
    return str.length();
}
                |
                |
(String str) -> return str.length();
                |
                |
(str) -> str.length();
                |
                |
 str -> str.length();

# Benefits of Lambda expression
To enable functional programming in java
To make code more readable, maintainable and concise code
To enable parallel processing
Jar file size reduction
Elimination of shadow variables

# Functional Interface
Interface having exactly single abstract method but can have any number of default and static methods.
We can invoke lamda expression by using functional interface.

# Advantages of Functional Interface
It restrict the interface to be a Functional Interface
So if people have already used some lambda expression and some new team member added another abstract method , all lambda expression will have errors

# Example 1:

@FunctionalInterface
public interface MyInterface
{
    public void sayHello();

    public void sayBye();    // Error , 2 abstract methods have been declared
}

@FunctionalInterface
public interface MyInterface
{
    public void sayHello();

    default void morning()
    {
        System.out.println("Good Morning");
    };

    public static void sayOk()
    {
        System.out.println("Okay.....");
    };
}

# Inheritence in Functional Interface
@FunctionalInterface
public interface ParentInterface
{
    public void sayHello();

    // Can be Overriden
    default void morning()
    {
        System.out.println("Good Morning");
    };

    // Can not be Overriden
    public static void sayOk()
    {
        System.out.println("Okay.....");
    };
}

public interface ChildInterface extends ParentInterface{
//   public void sayBye();  , Error , Parent class already contain one abstract method
     public static void sayHmm()
    {
        System.out.println("Hmmmm.....");
    };

}

# Example 2:
@FunctionalInterface
public interface TestInterface {
    void execute();
    
    // Hint: Every class in Java inherits from Object...
    boolean equals(Object obj); 
}

will these code compile
Answer: Yes 
It’s one of those "hidden" rules in Java 8 that catches everyone off guard.

The "Object Class" Exception
A functional interface can have abstract methods that override methods from the Object class (like equals, hashCode, or toString) and it does not count toward the "Single Abstract Method" limit.

Why? Because any implementation of that interface will be a class, and every class in Java automatically has an implementation of Object methods. Therefore, the method isn't "effectively abstract" in the final implementation.

# Example 3:

interface MyInterface{
    static void sayHello()
    {
        System.out.println("Hello from interface");
    }
}

public class MyClass implements MyInterface{
    public static void main(String[] args)
    {
        // MyClass.sayHello();  // Error
        MyInterface.sayHello(); // No Error
    }
}

# Example 4: 
Can we write static main method in interface
Answer: Yes , we can write static main method inside interface from Java 8

public interface MyInterface{
    public static void main(String[] args){
        System.out.println("Hello from Interface");
    }
}

# Example 5:
How to use lambda expression in functional interface 

@FunctionalInterface
interface ParentInterface {
    void sayHello();

    default void morning() {
        System.out.println("Good Morning");
    }

    static void sayOk() {
        System.out.println("Okay.....");
    }
}

interface ChildInterface extends ParentInterface {
    static void sayHmm() {
        System.out.println("Hmmmm.....");
    }
}

public class MyClass {
    public static void main(String[] args){

        ParentInterface parentInterface = () -> "Hello from Parent Interface using lambda Expressions";
        parentInterface.morning();
        parentInterface.sayOk();

        // Since ChildInterface is also functional interface (inherits sayHello only), The implmentaion of abstract method has to be done.
        ChildInterface childInterface = () -> "Hello from Child Interface using lambda Expressions";
        childInterface.satHmm();
    }
}

# Example 6:
@FunctionalInterface
interface TestInterface {
    void execute();
    boolean equals(Object obj); // ignored for SAM(Single Abstract Method) counting
}

public class Demo2 {
    public static void main(String[] args) {
        TestInterface t = () -> System.out.println("Execute called");
        t.execute();

        // equals() exists because everything is an Object anyway
        System.out.println(t.equals(t)); // true
    }
}

# Example 7: 
Two default methods conflict
interface A {
    default void greet() { System.out.println("A"); }
}

interface B {
    default void greet() { System.out.println("B"); }
}

class C implements A, B {
    @Override
    public void greet() {
        // MUST resolve the conflict explicitly
        A.super.greet(); // or B.super.greet();
        System.out.println("C");
    }
}

# How to use lambda expressions in Thread
Basic code:

class MyThread implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Thread " + i + " is running");
        }
    }
}
public class MultiThreads {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        Thread thread = new Thread(myThread);
        thread.run();
    }
}

Using Lambda Expressions:

public class MyThread{
    public static void main(String[] args){
        Runnable runnable = () -> {
            for(int i = 0; i < 10; i++)
            {
                System.out.println("Thread " + i + " is running");
            }
        };

        Thread thread = new Thread(runnable);
        thread.run();
    }
}

# Comparator

Basic Code:

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MyClass implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}
public class MyComparator {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Zebra", "Apple", "Mango");
        System.out.println(names);
        System.out.println("Before sorting:");
        Collections.sort(names, new MyClass());
        System.out.println("After sorting:");
        System.out.println(names);
    }
}

Lambda Expressions:
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyComparator {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Zebra", "Apple", "Mango");
        System.out.println(names);
        System.out.println("Before sorting:");
        Collections.sort(names,(a,b) -> b.compareTo(a));
        System.out.println("After sorting:");
        System.out.println(names);
    }
}

# Comparator Chaining:
import java.util.*;

class Employee {
    String name;
    String department;

    Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return department + ": " + name;
    }
}

public class MyClass {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Zack", "Sales"),
            new Employee("Alice", "IT"),
            new Employee("Bob", "IT"),
            new Employee("Charlie", "Sales")
        );

        // 1. Create the Comparator chain
        Comparator<Employee> employeeComparator = Comparator
                .comparing(Employee::getDepartment) // Primary sort
                .thenComparing(Employee::getName);   // Secondary sort

        // 2. Sort the list
        employees.sort(employeeComparator);

        employees.forEach(System.out::println);
    }
}

Java 7 Code:
// The "Ugly" Java 7 way:
public int compare(Employee e1, Employee e2) {
    int res = e1.getDepartment().compareTo(e2.getDepartment());
    if (res != 0) {
        return res;
    }
    return e1.getName().compareTo(e2.getName());
}

# Ananymous Inner Class

interface Employee {
    String getSalary();
}

public class AnonymousInnerClass {
    int x = 100;

    private void execute() {
        int y = 10;

//        A lambda does NOT create a new this
//        this refers to the enclosing instance of AnonymousInnerClass
        Employee employee = () -> {
//            y = 100;    //Here y value can't be updated , because y belongs to method variable and it throws compile-time error
//            x = 1000;   //Here x value can be updated , because x belongs to class member
            //Therefore: this.x → AnonymousInnerClass.x → 100
            System.out.println(this.x);
            return "1000";
        };
        System.out.println(employee.getSalary());
    }

    Employee employee = new Employee() {
//        An anonymous inner class creates a new object
//        this refers to the anonymous Employee instance
        int x = 10;

        @Override
        public String getSalary() {
            //this.x → anonymous Employee.x → 10
            System.out.println(this.x);
            return "1000";
        }
    };

    public static void main(String[] args) {
        AnonymousInnerClass anonymousInnerClass = new AnonymousInnerClass();
        anonymousInnerClass.execute();

        System.out.println("anon salary = " + anonymousInnerClass.employee.getSalary());

    }
}

# Predicate
Predicate is a Functional interface which represents a boolean valued function

# Example 1:

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
public class MyClass{
    public static void main(String[] args){
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> isOdd = x -> x % 2 == 1;
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        for(Integer i : list){
            if(isEven.test(i)){
                System.out.println("Even Numbers in the list" + i);
            }
            else if(isOdd.test(i)){
                System.out.println("Odd Numbers in the list" + i);
            }
        }
    }
}

# Example 2:
import java.util.function.Predicate;
public class MyClass{
    public static void main(String[] args){
        Predicate<String> startsWithLetterH = x -> x.toLowerCase().charAt(0) == 'h';
        Predicate<String> endsWithLetterA = x -> x.toLowerCase().charAt(x.length() - 1) == 'a';
        Predicate<String> isTrueAndFalse = startsWithLetterH.and(endsWithLetterA);
        Predicate<String> isTrueOrFalse = startsWithLetterH.or(endsWithLetterA);
        System.out.println(isTrueOrFalse.test("Harsha"));
        System.out.println(isTrueOrFalse.test("Boba"));
         
    }
}

# Example 3:
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
public class MyClass{
    public static void main(String[] args){
        Predicate<Student> checkGradesAbove90 = x -> x.grade >= 90.00 && x.grade < 100.00;
        Predicate<Student> checkGradesAbove80 = x -> x.grade >= 80.00 && x.grade < 90.00;
        Predicate<Student> checkGradesAbove70 = x -> x.grade >= 70.00 && x.grade < 80.00;
        List<Student> students = new ArrayList<>();
        students.add(new Student("Harsha", 90.5, 10));
        students.add(new Student("Varun", 95.5, 10));
        students.add(new Student("Tharun", 80.5, 10));
        students.add(new Student("Pawan", 79.5, 10));
        for(Student student : students){
            if(checkGradesAbove90.test(student)) System.out.println(student.name + " has scored above 90");
            else if(checkGradesAbove80.test(student)) System.out.println(student.name + " has scored above 80");
            else if(checkGradesAbove70.test(student)) System.out.println(student.name + " has scored above 70");
            else System.out.println(student);
        }

    }

    private static class Student{
        private String name;
        private double grade;
        private int classYear;

        public Student(String name, double grade, int classYear){
            this.name = name;
            this.grade = grade;
            this.classYear = classYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getgrade() {
            return grade;
        }

        public void setgrade(double marks) {
            this.grade = marks;
        }

        public int getClassYear() {
            return classYear;
        }

        public void setClassYear(int classYear) {
            this.classYear = classYear;
        }

        @Override
        public String toString() {
            return "Student {" +
                    "name ='" + name + '\'' +
                    ", grade =" + grade +
                    ", classYear =" + classYear +
                    '}';
        }
    }
}

# Function
Represents a function that accepts one argument and produces a result.
Type parameters:
<T> – the type of the input to the function
<R> – the type of the result of the function

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
public class FunctionImpl{

    public static void main(String[] args){
        Function<String,String> substring = s -> s.substring(0, 3);
        Function<String,String> uppercase = s -> s.toUpperCase();
        System.out.println(substring.andThen(uppercase).apply("harsha"));

        Function<List<Student>,List<Student>> checkWithThePrefix = s -> {
            List<Student> list = new ArrayList<>();
            for(Student student : s){
                if(substring.apply(student.getName()).equalsIgnoreCase("har"))
                {
                    list.add(student);
                }
            }
            return list;
        };

        List<Student> students = new ArrayList<>();
        students.add(new Student("Harsha", 90.5, 10));
        students.add(new Student("Varun", 95.5, 10));
        students.add(new Student("Harshitha", 80.5, 10));
        students.add(new Student("Harshith", 79.5, 10));

        System.out.println(checkWithThePrefix.apply(students));
    }

    private static class Student {
        private String name;
        private double grade;
        private int classYear;

        public Student(String name, double grade, int classYear) {
            this.name = name;
            this.grade = grade;
            this.classYear = classYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getgrade() {
            return grade;
        }

        public void setgrade(double marks) {
            this.grade = marks;
        }

        public int getClassYear() {
            return classYear;
        }

        public void setClassYear(int classYear) {
            this.classYear = classYear;
        }

        @Override
        public String toString() {
            return "Student {" +
                    "name ='" + name + '\'' +
                    ", grade =" + grade +
                    ", classYear =" + classYear +
                    '}';
        }
    }
}

# Consumer:
Represents an operation that accepts a single input argument and returns no result. Unlike most other functional interfaces, Consumer is expected to operate via side-effects.
Type parameters:
<T> – the type of the input to the operation

Consumer<String> consumer = s -> System.out.println("s");
consumer.accept("Hello from consumer");

# Supplier:
Represents a supplier of results.
There is no requirement that a new or distinct result be returned each time the supplier is invoked.

Supplier<String> suplier = () -> "Hello from Supplier";
supplier.get();

# Example for Consumer and supplier:
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;
class FunctionImpl{

    public static void myFunction(){
        Function<String,String> substring = s -> s.substring(0, 3);
        Consumer<List<Student>> consumer = System.out::println;
        Supplier<String>  supplier = () -> "hello from supplier";

        Function<List<Student>,List<Student>> checkWithThePrefix = s -> {
            List<Student> list = new ArrayList<>();
            for(Student student : s){
                if(substring.apply(student.getName()).equalsIgnoreCase("har"))
                {
                    list.add(student);
                }
            }
            return list;
        };

        List<Student> students = new ArrayList<>();
        students.add(new Student("Harsha", 90.5, 10));
        students.add(new Student("Varun", 95.5, 10));
        students.add(new Student("Harshitha", 80.5, 10));
        students.add(new Student("Harshith", 79.5, 10));

        System.out.println(checkWithThePrefix.apply(students));
        System.out.print("Consumer output: ");
        consumer.accept(students);
        System.out.println(supplier.get());
    }

    private static class Student {
        private String name;
        private double grade;
        private int classYear;

        public Student(String name, double grade, int classYear) {
            this.name = name;
            this.grade = grade;
            this.classYear = classYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getgrade() {
            return grade;
        }

        public void setgrade(double marks) {
            this.grade = marks;
        }

        public int getClassYear() {
            return classYear;
        }

        public void setClassYear(int classYear) {
            this.classYear = classYear;
        }

        @Override
        public String toString() {
            return "Student {" +
                    "name ='" + name + '\'' +
                    ", grade =" + grade +
                    ", classYear =" + classYear +
                    '}';
        }
    }
}


# BiFunction:
It is similar to Function, But here it takes two input and gives one output
Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of Function.

BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;
System.out.println(biFunction.apply(2,3));

# BiPredicate:
It is similar to Predicate, But here it takes two input and gives one output that is boolean value.
Represents a predicate (boolean-valued function) of two arguments. This is the two-arity specialization of Predicate.

 BiPredicate<Integer, Integer> biPredicate = (x, y) -> x > y;
System.out.println(bipredicate.test(5,3));

# BiConsumer:
Represents an operation that accepts two input arguments and returns no result. This is the two-arity specialization of Consumer. Unlike most other functional interfaces, BiConsumer is expected to operate via side-effects.

BiConsumer<Integer, Integer> biConsumer = (x, y) -> System.out.println(x + y);

# UnaryOperator:
It is similar to what function does, but it takes one input and return same type of output.
Represents an operation on a single operand that produces a result of the same type as its operand. This is a specialization of Function for the case where the operand and result are of the same type.

UnaryOperator<Integer> unaryOperator = x -> x * x;
System.out.println(unaryOperator.apply(2));
# BinaryOperator:
It is similar to BiFunction , but it takes two same type of input and return one single output.
Represents an operation upon two operands of the same type, producing a result of the same type as the operands. This is a specialization of BiFunction for the case where the operands and the result are all of the same type.

BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
System.out.println(unarybinaryOperatorOperator.apply(2,3));

# Method reference and Constructor reference:
Method reference allows us to refer to a method without invoking it.
Which makes the code cleaner and more readable.
They can be used in place of lambda expressions

# Example 1:
public class MethodReferenceImpl{
    public static void print(String s){
        System.out.println(s);
    }
    public static void main(String[] args){
        List<String> string = Arrays.asList("alice", "bob", "calie");
//        string.forEach(x -> System.out.println(x));
        string.forEach(MethodReferenceImpl::print);
    }
}

# Example 2:
public class ConstructorReferenceImpl{
    public static void main(String[] args){
        List<Student> students = new ArrayList<>();
//        students.add(new Student("Harsha", 90.5, 10));
//        students.add(new Student("Varun", 95.5, 10));
//        students.add(new Student("Harshitha", 80.5, 10));
//        students.add(new Student("Harshith", 79.5, 10));

//        students.add(Student::new("Harsh", 90.5, 10)); // Error ConstructorReferenceImpl.Student is not a functional interface

        //Create a functional interface
        //Instead of using lambda expressions use Constructor Reference
        StudentFactory factory = Student::new;

        students.add(factory.create("Harsha", 90.5, 10));
        students.add(factory.create("Varun", 95.5, 10));
        students.add(factory.create("Harsh", 90.5, 10));

        students.sort(Comparator.comparing(s -> s.name));
        students.forEach(System.out::println);
    }
    @FunctionalInterface
    interface StudentFactory {
        Student create(String name, double grade, int classYear);
    }

    private static class Student {
        private String name;
        private double grade;
        private int classYear;

        public Student(String name, double grade, int classYear) {
            this.name = name;
            this.grade = grade;
            this.classYear = classYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getgrade() {
            return grade;
        }

        public void setgrade(double marks) {
            this.grade = marks;
        }

        public int getClassYear() {
            return classYear;
        }

        public void setClassYear(int classYear) {
            this.classYear = classYear;
        }

        @Override
        public String toString() {
            return "Student {" +
                    "name ='" + name + '\'' +
                    ", grade =" + grade +
                    ", classYear =" + classYear +
                    '}';
        }
    }
}

# Stream:
Collection---------> Stream ------> Declarative/Functional
A Stream in Java is a sequence of elements derived from a data source(Collections) that supports declarative, functional-style operations for processing data, without modifying the underlying source.

1. Stream ≠ Collection
Collection	          Stream
Stores data	          Processes data
Eager                 Lazy
Can                   be traversed multiple times	Can be consumed once
Mutable	              Immutable (no state change)

2. Stream is a view, not storage
List<Integer> list = List.of(1, 2, 3, 4);

Stream<Integer> stream = list.stream();

3.Declarative vs Imperative
Imperative (how)
for (int i : list) {
    if (i > 2) {
        System.out.println(i);
    }
}

Declarative (what)
list.stream()
    .filter(i -> i > 2)
    .forEach(System.out::println);
You describe what to do, not how to loop.

4.Functional-style operations

Streams use:
Predicate (filter)
Function (map)
Consumer (forEach)
Supplier

All operations are:
Stateless
Side-effect free (recommended)
Composable

5. Lazy evaluation (very important)
list.stream()
    .filter(i -> {
        System.out.println("filtering " + i);
        return i > 2;
    });

Nothing prints until a terminal operation like:
.forEach(System.out::println);

6. 
| Type         | Examples                       | Behavior           |
| ------------ | ------------------------------ | ------------------ |
| Intermediate | `filter`, `map`, `sorted`      | Lazy               |
| Terminal     | `forEach`, `collect`, `reduce` | Triggers execution |

# Advantages:
Readability: Streams provide a more readable and expressive way to perform operations on collections

Flexibility: Streams allow you to perform a variety of operations on collections, such as filtering, mapping and reducing, without having to write multiple loops or methods.

Parallelism: Streams can be processed in parallel, which can provide a significant performance boost for large collections.

Encapsulation: Streams encourage encapsulation, as you can perform a series of operations on a collection without modifying the original data.

 # Example 1:
import java.util.Arrays;
import java.util.stream.IntStream;

 public class Approaches {
        public static void main(String[] args) {
            // Imperative approach
            int[] array = {1, 2, 3, 4, 5};
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] % 2 == 0) {
                    sum += array[i];
                }
            }
            System.out.println("sum using Imperative approach = " + sum);

            //Stream
            int[] arr = {1, 2, 3, 4, 5};
            IntStream intStream = Arrays.stream(arr).filter(i -> i % 2 == 0);
            System.out.println("--------------------------------------");
            // IntStream will contain stream of result after filtering
            //intStream = 2,4; 
            System.out.println("filter using Imperative approach = " + intStream.count());
            System.out.println("--------------------------------------");
            int sumSt = Arrays.stream(arr).filter(n -> n % 2 == 0).sum();
            System.out.println("sum using Imperative approach = " + sumSt);
        }
    }

# Example 2:
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
//        Approaches.approach1();
//        Approaches.approach2();
        Approaches.approach3();
    }
}

class Approaches {
    public static void approach1() {
        // Imperative approach
        int[] array = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                sum += array[i];
            }
        }
        System.out.println("sum using Imperative approach = " + sum);

        //Stream/ Declarative
        int[] arr = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(arr).filter(i -> i % 2 == 0);
        System.out.println("--------------------------------------");
        // IntStream will contain stream of result after filtering
        //intStream = 2,4;
        System.out.println("filter using Imperative approach = " + intStream.count());
        System.out.println("--------------------------------------");
        int sumSt = Arrays.stream(arr).filter(n -> n % 2 == 0).sum();
        System.out.println("sum using Imperative approach = " + sumSt);
    }

    public static void approach2() {
        // Stream.iterate(seed, UnaryOperator)
        // UnaryOperator<T> takes T and returns T --> it takes one input and return same type of output
        Stream<Integer> limit = Stream.iterate(0, i -> i + 1).limit(30);
        //forEach(Consumer consumer) --> which will consume but does not return
        limit.forEach(System.out::println);
        System.out.println("--------------------------------------");

        //map(function)  --> accepts one argument and produces a result
        Stream<Integer> limit2 = Stream.iterate(0, i -> i + 1).limit(30);
        limit2.map(x -> x / 5).distinct().forEach(System.out::println);
    }

    public static void approach3() {
        List<Integer> list = Arrays.asList(1, 22, 3, 4, 5, 65, 74, 8, 9, 81);
//        List<Integer> collect = list.stream().filter(i -> i % 2 == 0).map(x -> x * x).collect(Collectors.toList());
//        System.out.println(collect);
//        System.out.println("---------------------------------");
//        List<Integer> result = collect.stream().map(x -> x / 2).collect(Collectors.toList());
//        System.out.println(result);
//        System.out.println("---------------------------------");
//        result.stream().sorted().forEach(System.out::println);


        //we can write above code in one line
        System.out.println(list.stream().
                filter(x -> x % 2 == 0).
                map(x -> x * x).sorted().
                max((a, b) -> b - a).stream().count());
        System.out.println("-------------------------------------------");
        System.out.println(list.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .sorted()
                .max((a, b) -> b - a)
                .stream()
                .findAny().orElse(0));
        System.out.println("-------------------------------------------");
        System.out.println(list.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .sorted()
                .max((a, b) -> a - b)
                .stream()
                .findAny().orElse(0));
    }
}

# Data and Time:
# Limitation in java 7:
1. Mutable
2. Confusion
3. Zone time difference

Java 8 has introduced new classes
1. Local Date : Represents a date without a time zone
2. Local Time : Represents a time without a date or time zone
3. Local Date Time: Represents a date and time without a time zone
4. Zoned Date Time: Represents a date and time with a time zone
5. Instant : Represents an instantaneous point on the timeline, typically used for machine time stamps
6. Peroid : Represents a duration of time between two points in time
7. Duration : Represents a period of time between two dates
8. Date time formatter : Formats and parses dates and times

# Example 1:
import java.time.LocalDate;
import java.time.Month;

public class Java8Date {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate customDate = LocalDate.of(2018, 1, 1);
        System.out.println(customDate);
        System.out.println("-----------------------------------------------");
        int day = today.getDayOfMonth();
        Month month = today.getMonth();
        int year = today.getYear();
        System.out.println("day: " + day + ", month: " + month + ", year: " + year);

        System.out.println("-----------------------------");
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);
        System.out.println("Tomorrow: " + tomorrow );
        System.out.println("Yesterday: " + yesterday);
    }
}

2018-01-01
-----------------------------------------------
day: 14, month: JANUARY, year: 2026
-----------------------------
Tomorrow: 2026-01-15
Yesterday: 2026-01-13

# Example 2:
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println("Current clock hour " + now.getHour());
        System.out.println("------------------------------");
        LocalTime customTime = LocalTime.of(15, 45, 59);
        System.out.println(customTime.getHour() + ":" + customTime.getMinute() + ":" + customTime.getSecond());
        System.out.println("-----------------------------");

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        System.out.println("Current zone " +  zonedDateTime.getZone());
        System.out.println("Current UTC time zone" + zonedDateTime.getOffset());
        System.out.println("------------------------------------");
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
        System.out.println("-------------------------------------");
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(ZoneId.of("Asia/Calcutta"));
        System.out.println("Current time in India : " + zonedDateTime2);
        System.out.println("--------------------------------------");
    }
}

09:49:25.988656600
Current clock hour 9
------------------------------
15:45:59
-----------------------------
2026-01-14T09:49:26.004296900-06:00[America/Chicago]
Current zone America/Chicago
Current UTC time zone-06:00
------------------------------------
Asia/Aden
America/Cuiaba
Etc/GMT+9
Etc/GMT+8
Africa/Nairobi
America/Marigot
Asia/Aqtau
Pacific/Kwajalein
America/El_Salvador
Asia/Pontianak
-------------------------------------
Current time in India : 2026-01-14T21:19:26.004296900+05:30[Asia/Calcutta]
--------------------------------------

import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Optional;

class Employee{
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private ZonedDateTime zonedDateTime;

    public Employee()
    {

    }
    public Employee(String firstName, String lastName, LocalDate dateOfBirth, ZonedDateTime zonedDateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.zonedDateTime = zonedDateTime;
    }

    public Optional<String> getFirstName() {
        return Optional.of(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Optional<String> getLastName() {
        return Optional.of(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }
}
public class OptionalClass {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDateOfBirth(LocalDate.of(1990, Month.JANUARY, 1));
        employee.setZonedDateTime(ZonedDateTime.now());

        if(employee.getFirstName() != null && employee.getLastName() != null)
        {
            System.out.println(employee);
        }

        if(employee.getFirstName().isPresent() &&  employee.getLastName().isPresent())
        {
            System.out.println(employee);
        }

        // IT IS WORKING AS SUB QUERRY --> if get firstname is present then go to flatmap --> lambda expressions decides the result
        // if get lastname is also not null , return Optional<Employee> from map function
        employee.getFirstName()
                .flatMap(f -> employee.getLastName().map(l -> employee))
                .ifPresent(System.out::println);

        // throw exception error if you didn't find the value using .get()
        String firstName = employee.getFirstName().isPresent() ? employee.getFirstName().get() : "NA";

        //ifPresent is a safe code Executes an action only if present
        // Never returns null
        employee.getFirstName().ifPresent(System.out::println);

    }
}

Employee{firstName='John', lastName='Doe', dateOfBirth=1990-01-01, zonedDateTime=2026-01-15T11:47:17.686099200-06:00[America/Chicago]}
Employee{firstName='John', lastName='Doe', dateOfBirth=1990-01-01, zonedDateTime=2026-01-15T11:47:17.686099200-06:00[America/Chicago]}
Employee{firstName='John', lastName='Doe', dateOfBirth=1990-01-01, zonedDateTime=2026-01-15T11:47:17.686099200-06:00[America/Chicago]}
John