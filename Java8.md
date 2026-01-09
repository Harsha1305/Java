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
class MyClass{
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

