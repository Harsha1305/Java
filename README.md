# Java
Java is a high level , general purpose , object oriented programming lanaguage 

# Program
It is a set of instruction that tells a computer what to do , we use of programs to interact with computers 

# Programming Language
Computers are machine , they don't understand the human lanaguage. Programs are written in a language a computer can understand 

# Machine Language
A computer native language which used 0 & 1. Every instruction should be written in machine language before it can be executed

# add 2,3 ,result -----> Assembler ------------> can be executed

# High Level Language
A new Generation of programming language which uses english words
It is machine independent, your program will run on any machine

# Compiler
Translates all the source code into machine code

Source Code ------> Compiler -----> Machine code -------> Executor -----------> Output

# Interpreter
Transaltes each statement into machine code and executes

Statement ---------> Interpreter -----------> Output

# JDK
JDK is a cross platformed software developement enviroment that offers a collection of tools and libraries necessary for developing 
It is core package used in java along with JVM and JRE

# JVM Architecture
The Java Virtual Machine (JVM) is a core component of the Java Runtime Environment (JRE) that allows Java programs to run on any platform without modification. JVM acts as an interpreter between Java bytecode and the underlying hardware, providing Java’s famous Write Once, Run Anywhere (WORA) capability.

Java source (.java) -> compiled by javac -> bytecode (.class)
JVM loads the bytecode, verifies it, links it, and then executes it
Execution may involve interpreting bytecode or using Just-In-Time (JIT) compilation to convert “hot” code into native machine code for performance
Garbage collection runs in the background to reclaim memory from unused objects

                 +---------------------------+
                 |        .java Source       |
                 +-------------+-------------+
                               |
                               v
                 +---------------------------+
                 |       javac Compiler      |
                 +-------------+-------------+
                               |
                               v
                 +---------------------------+
                 |       .class Bytecode     |
                 +-------------+-------------+
                               |
                               v
+------------------------------------------------------+
|                CLASS LOADER SUBSYSTEM                 |
|  Bootstrap -> Extension/Platform -> Application       |
|  Loading -> Linking(Verify/Prepare/Resolve) -> Init   |
+-------------------------+----------------------------+
                          |
                          v
+------------------------------------------------------+
|               RUNTIME DATA AREAS (MEMORY)             |
|  Shared:                                              |
|    - Heap (Objects/Arrays, Young+Old, GC managed)     |
|    - Method Area / Metaspace (Class metadata, statics)|
|  Thread-Private:                                      |
|    - Java Stack (Frames: locals, operand stack, return)|
|    - PC Register (current instruction)                |
|    - Native Method Stack (JNI calls)                  |
+-------------------------+----------------------------+
                          |
                          v
+------------------------------------------------------+
|                   EXECUTION ENGINE                    |
|  - Interpreter (executes bytecode)                    |
|  - JIT Compiler (hot code -> native, optimized)       |
|  - Garbage Collector (reclaims heap objects)          |
+-------------------+--------------------+-------------+
                    |                    |
                    v                    v
               +---------+        +------------------+
               |  JNI    |------->| Native Libraries |
               +---------+        +------------------+
                          |
                          v
                   +-------------+
                   | OS/Hardware |
                   +-------------+
1. Loading
Reads .class files and stores class metadata in the Method Area.
Creates a Class object in the heap representing the loaded class.

2. Linking: Responsible for preparing the loaded class for execution. It includes three steps:

Verification: Ensures the bytecode follows JVM rules and is safe to execute.
Preparation: Allocates memory for static variables and assigns default values.
Resolution: Converts symbolic references into direct references in memory.
3. Initialization

Assigns actual values to static variables.
Executes static blocks defined in the class.
Class Loader Types
Bootstrap Class Loader: Loads core Java classes (JAVA_HOME/lib).
Extension Class Loader: Loads classes from extensions directory (JAVA_HOME/jre/lib/ext).
System/Application Class Loader: Loads classes from the application classpath.

# public static void main(String[] args)
Java's main() method is the starting point from where the JVM starts the execution of a Java program. JVM will not execute the code if the program is missing the main method. Hence, it is one of the most important methods of Java, and having a proper understanding of it is very important.

The Java compiler or JVM looks for the main method when it starts executing a Java program. The signature of the main method needs to be in a specific way for the JVM to recognize that method as its entry point. If we change the signature of the method, the program compiles but does not execute.

The execution of the Java program, the java.exe, is called. The Java.exe in turn makes Java Native Interface or JNI calls, and they load the JVM. The java.exe parses the command line, generates a new String array, and invokes the main() method. By default, the main thread is always a non-daemon thread.

1. Public 
It is an Access modifier, which specifies from where and who can access the method. Making the main() method public makes it globally available. It is made public so that JVM can invoke it from outside the class as it is not present in the current class.

2. Static
It is a keyword that is when associated with a method, making it a class-related method. The main() method is static so that JVM can invoke it without instantiating the class. This also saves the unnecessary wastage of memory which would have been used by the object declared only for calling the main() method by the JVM.

3. Void 
It is a keyword and is used to specify that a method does not return anything. As the main() method does not return anything, its return type is void. As soon as the main() method terminates, the Java program terminates too. Hence, it doesn't make any sense to return from the main() method as JVM can't do anything with its return value of it.

4. main 
It is the name of the Java main method. It is the identifier that the JVM looks for as the starting point of the Java program. It's not a keyword.
If we change the name while initiating main method, we will get an error.

5. String[] args 
It stores Java command-line arguments and is an array of type java.lang.String class. Here, the name of the String array is args but it is not fixed and the user can use any name in place of it. 

# Method Area & Heap Area:
Method Area: Stores class-level structures such as metadata, method bytecode, runtime constant pool, and static variables.

Heap Area: Stores all objects and arrays created at runtime and is managed by the garbage collector.
              +----------------------------------+
              |          JVM MEMORY              |
              +----------------------------------+

   +----------------------------------+   +----------------------------------+
   |          METHOD AREA             |   |              HEAP                |
   |        (Metaspace - Java 8+)     |   |                                  |
   |                                  |   |  +----------------------------+  |
   |  - Class metadata                |   |  |     Young Generation        |  |
   |  - Method bytecode               |   |  |  - Eden                     |  |
   |  - Runtime constant pool         |   |  |  - Survivor S0              |  |
   |  - Static variables              |   |  |  - Survivor S1              |  |
   |                                  |   |  +----------------------------+  |
   |  Loaded once per class           |   |                                  |
   |  Shared across threads           |   |  +----------------------------+  |
   |                                  |   |  |     Old Generation          |  |
   |                                  |   |  |  - Long-lived objects       |  |
   |                                  |   |  +----------------------------+  |
   +----------------------------------+   +----------------------------------+

        ↑ Loaded by ClassLoader                  ↑ Managed by Garbage Collector

# Heap Area – Divisions

1. Young Generation
new object → Eden → Survivor → Old Generation

Most objects die young
Minor GC happens here
Very fast garbage collection

2. Old Generation
Objects promoted from Survivor spaces
Major / Full GC happens here
GC is slower compared to Young Gen

# Package
A set of classes and interafxes grouped together
In simple words its a container of class

# Operators
1. Arithmetic Operator
To do some calculation (+,-,*,/,%,.........)

2. Relational Operator 
To do some comparisions (<,>, ! ,!= , <= ,>= ,...........)

3.Logical Operator
To combine condition together (AND , OR , NOT , .........)

#Conditions:
It is used to control what to execute in the program
If a condition is true -------> execute
If a condition is false ------> do anotherthing

# Loops
used to run some code more than once

#Methods
Divide our code into pieces to dome task
we can maintain less code in main
our program will be easier to maintain and debug
makes the development process easier by breaking our program into smaller pieces and solving them one by one

# Variables
It is used to store the values in our computer memory
To store some va;ues in the memory we need to reserve some spaces
Each variables has specific memory reservation

1) Declaration:
Allocating some spaces inside our memory, to allocate space we declare a variable
The type of the variable should be comapitable with the data inside it
The variable should be declared first before it can be used

2) Initialization:
Assigning a value to a variable when decalring it

# Constants
A varibale whose value cannot b changed
To define a constant we use the final keyword
Constant namea re wirtten in upper case
yopu will get a syntax error if you try to change the value

Benifits of using Contants
The value will not be changed by accident
A descriptive name for constant makes easy to read and understand

#Identifiers
Identifiers are the names that identify the elements in a program
--> Name of class
--> Name of the method
--> Name of the variables

It must start with a letter , underscore or a dollor sign

# Primitive Data Types
Primitive data types are the most basic data types available in Java. There are eight primitive data types, each serving a specific purpose:

byte:
Size: 8-bit
Range: -128 to 127
Usage: Memory-efficient storage in large arrays.
byte b = 100;
short:
Size: 16-bit
Range: -32,768 to 32,767
Usage: Suitable for saving memory in large arrays.
short s = 10000;
int:
Size: 32-bit
Range: -231 to 231-1
Usage: Default choice for integer values.
int i = 100000;
long:
Size: 64-bit
Range: -263 to 263-1
Usage: For large integer values.
long l = 100000L;
float:
Size: 32-bit
Usage: For fractional numbers, with single precision.
float f = 234.5f;
double:
Size: 64-bit
Usage: For fractional numbers, with double precision.
double d = 123.4;
boolean:
Values: true or false
Usage: For simple flags and conditions.
boolean flag = true;
char:
Size: 16-bit
Range: 0 to 65,535 (Unicode characters)
Usage: For storing characters.
char c = 'A';
# Reference Data Types
Reference data types are objects that store references to the actual data. They include classes, interfaces, and arrays. Unlike primitive data types, reference data types do not store the value directly.

Example: Class and Array
public class ReferenceExample {
    public static void main(String[] args) {
        String str = "Hello, World!"; // Class type
        int[] numbers = {1, 2, 3, 4, 5}; // Array type

        System.out.println(str);
        for (int num : numbers) {
            System.out.println(num);
        }
    }
}

# Strings
String is the type of object that can store a sequence of characters enclosed by double quotes and every character is stored in 16 bits
A string acts the same as an array of characters

1. String literal (Static Memory)
To make Java more memory efficient (because no new objects are created if it exists already in the string constant pool). 

2. Using new keyword (Heap Memory)
String s = new String("Welcome");
In such a case, JVM will create a new string object in normal (non-pool) heap memory and the literal "Welcome" will be placed in the string constant pool. The variable s will refer to the object in the heap (non-pool)

# Classes that implement CharSequence include:
1. String
String is an immutable class in Java, which means that once a String object is created, its value cannot be changed. If you want to modify a string a new String object is created and the original remains unchanged.

2. StringBuffer
StringBuffer is a peer class of String, it is mutable in nature and it is thread safe class , we can use it when we have multi threaded environment and shared object of string buffer i.e, used by mutiple thread. As it is thread safe so there is extra overhead, so it is mainly used for multithreaded program.

3. StringBuilder
StringBuilder in Java represents an alternative to String and StringBuffer Class, as it creates a mutable sequence of characters and it is not thread safe. It is used only within the thread , so there is no extra overhead , so it is mainly used for single threaded program.

4. StringTokenizer
A StringTokenizer object internally maintains a current position within the string to be tokenized. Some operations advance this current position past the characters processed. A token is returned by taking a substring of the string that was used to create the StringTokenizer object.

Syntax:
StringTokenizer st = new StringTokenizer("Java String Example");

Constant Pool Area:
A String object which are created without new operator
Constant pool area does not allow duplicate

Non Constant Pool:
A String Object which are created using new operator are stored in non constant pool that is Heap memory
Non constant pool area allows duplicate obejct

# String methods
1. int length() Method
This method provides the total count of characters in the string.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.length());
    }
}
Output
13

2. charAt(int i) Method
This method returns the character at ith index.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.charAt(7));
    }
}
Output
W

3. String substring(int i) Method
This method return the substring from the ith index character to end.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.substring(7));
    }
}
Output
World!

4. String substring(int i, int j) Method
This method returns the substring from i to j-1 index.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.substring(7, 12));
    }
}
Output
World

5. String concat( String str) Method
This method appends the given string to the end of the current string.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.concat("!!!"));
    }
}
Output
Hello, World!!!!

6. int indexOf(String s) Method
This method returns the index within the string of the first occurrence of the specified string. If the specified string s is not found in the input string, the method returns -1 by default.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.indexOf("World"));
    }
}
Output
7

7. int indexOf(String s, int i) Method
This method returns the index within the string of the first occurrence of the specified string, starting at the specified index.
public class Geeks {
    public static void main(String[] args) {
        String str = "Hello, World!";
        System.out.println(str.indexOf("l", 4));
    }
}
Output
10

8. int lastIndexOf(String s) Method
This method returns the index within the string of the last occurrence of the specified string. If the specified string s is not found in the input string, the method returns -1 by default.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.lastIndexOf("l"));
    }
}
Output
10

9. boolean equals(Object otherObj) Method
This method compares this string to the specified object.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.equals("Hello, World!"));
    }
}
Output
true

10. boolean equalsIgnoreCase(String anotherString) Method
This method checks if two strings are equal, without considering letter case.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.equalsIgnoreCase("hello, world!"));
    }
}
Output
true

11. int compareTo(String anotherString) Method
This method compares two string lexicographically.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.compareTo("Hello, Java!"));
    }
}
Output
13

12. int compareToIgnoreCase(String anotherString) Method
This method compares two string lexicographically, ignoring case considerations.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.compareToIgnoreCase("hello, java!"));
    }
}
Output
13

13. String toLowerCase() Method
This method converts all the characters in the String to lower case
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.toLowerCase());
    }
}
Output
hello, world!

14. String toUpperCase() Method
This method converts all the characters in the String to upper case.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.toUpperCase());
    }
}
Output
HELLO, WORLD!

15. String trim() Method
This method returns the copy of the String, by removing whitespaces at both ends. It does not modify the whitespace characters present between the text.
public class Geeks {
    public static void main(String[] args) {
        String s = "   Hello, Trim!   ";
        System.out.println("'" + s.trim() + "'");
    }
}
Output
'Hello, Trim!'

16. String replace(char oldChar, char newChar) Method
This method returns a new string where all instances of oldChar are replaced by newChar.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.replace('l', 'x'));
    }
}
Output
Hexxo, Worxd!

17. boolean contains(CharSequence sequence) Method
This method returns true if string contains the given string.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.contains("World"));
    }
}
Output
true

18. char[] toCharArray() Method
This method converts the string into a new character array.
public class Geeks {
    public static void main(String[] args) {
        String str = "Hello";
        char[] chars = str.toCharArray();
        for(char c : chars) {
            System.out.print(c + " ");
        }
    }
}
Output
H e l l o 

19. boolean startsWith(String prefix) Method
This method returns true if string starts with this prefix.
public class Geeks {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.startsWith("Hello"));
    }
}

Output
true


# Conditional Statement
if
else
nested-if
if , else if
switch
jump ---> break, continue, return 

# keywords
1. Data Type Keywords
Used to define variable types and specify the kind of data they can hold.
Keyword	Usage
boolean	Defines a variable that holds true or false.
byte	Defines an 8-bit signed integer.
char	Defines a 16-bit Unicode character.
short	Defines a 16-bit signed integer.
int	Defines a 32-bit signed integer.
long	Defines a 64-bit signed integer.
float	Defines a 32-bit floating-point number.
double	Defines a 64-bit floating-point number.
void	Specifies that a method does not return any value.

2. Control Flow Keywords
Used to control the execution flow of a program, including loops, branching, and jumps.
Keyword	Usage
if	Executes code when a condition is true.
else	Defines an alternate block when an if condition is false.
switch	Selects one block of code among multiple options.
case	Defines an individual branch in a switch statement.
default	Defines the block executed if no case matches.
for	Starts a for loop.
while	Starts a while loop.
do	Starts a do-while loop.
break	Terminates the nearest loop or switch.
continue	Skips to the next iteration of a loop.
return	Exits from a method and optionally returns a value.

3. Exception Handling Keywords
Used for handling and managing runtime errors in programs.
Keyword	Usage
try	Defines a block of code to test for exceptions.
catch	Defines a block to handle exceptions thrown by try.
finally	Defines a block that always executes after try and catch.
throw	Used to manually throw an exception.
throws	Declares the exceptions a method can throw.
assert	Tests assumptions during program execution for debugging.

4. Object-Oriented Keywords
Used to define classes, interfaces, and objects, as well as inheritance and encapsulation properties.
Keyword	Usage
class	Declares a class.
interface	Declares an interface.
extends	Indicates inheritance from a superclass.
implements	Indicates that a class implements an interface.
new	Creates new objects.
this	Refers to the current object.
super	Refers to the superclass of the current object.
abstract	Declares a class or method that must be implemented in a subclass.
final	Prevents inheritance, overriding, or modification.
static	Declares class-level variables or methods.
sealed	Restricts which classes can extend a given class.
permits	Specifies the subclasses allowed to extend a sealed class.

5. Access Control Keywords
Define the visibility or accessibility of classes, methods, and variables.
Keyword	Usage
public	Accessible from anywhere in the program.
protected	Accessible within the same package or by subclasses.
private	Accessible only within the same class.

6. Package and Import Keywords
Used to organize classes and access external code.
Keyword	Usage
package	Defines a namespace for classes.
import	Allows access to classes from other packages.

7. Multithreading and Synchronization Keywords
Used to handle concurrent execution of code and ensure thread safety.
Keyword	Usage
synchronized	Defines critical sections that only one thread can access at a time.
volatile	Indicates that a variable may change asynchronously.

8. Memory Management and Object Serialization Keywords
Handle object persistence, garbage collection, and native method calls.
Keyword	Usage
transient	Excludes a variable from serialization.
native	Specifies that a method is implemented in native (non-Java) code.

9. Modifier and Utility Keywords
Define additional behaviors and precision control.
Keyword	Usage
strictfp	Ensures consistent floating-point calculations across platforms.

10. Reserved (Unused) Keywords
These keywords are reserved but not currently used by Java.
Keyword	Usage
const	Reserved for future use; not currently implemented.
goto	Reserved for future use; not currently implemented.

11. Special Literals
Keyword	Usage
true	Represents the boolean value true.
false	Represents the boolean value false.
null	Represents the absence of any reference value.

# Loops
for loop
nested for loop
while loop
do while loop

# Recursion
The process of function calling itself again and again

# Java Class members
Static member
A class member declared using static keyword are called as static member
Member variable which are declared using static keyword
Member function which are declared using static keyword
Static member are loaded to java memory during class loading
The static members can be accessed any where in the program using class name

Non static member
A class member declared without using static keyword are called as static member
Member variable which are declared without using static keyword
Member function which are declared without using static keyword
Non static member are loaded to java memory during Object creation
The non static members can be accessed any where in the program by using Object reference name

# Scanner Class in Java
In Java, the Scanner class is present in the java.util package is used to obtain input for primitive types like int, double, etc., and strings. We can use this class to read input from a user or a file.
nextBoolean():Used for reading Boolean value                    
nextByte():Used for reading Byte value
nextDouble():Used for reading Double value
nextFloat():Used for reading Float value
nextInt():Used for reading Int value
nextLine():Used for reading Line value
nextLong():Used for reading Long value
nextShort():Used for reading Short value

# Arrays
In Java, an array is an important linear data structure that allows us to store multiple values of the same type.
Arrays in Java are objects, like all other objects in Java, arrays implicitly inherit from the java.lang.Object class. This allows you to invoke methods defined in Object (such as toString(), equals() and hashCode()).
Arrays have a built-in length property, which provides the number of elements in the array.

Key features of Arrays
Store Primitives and Objects: Java arrays can hold both primitive types (like int, char, boolean, etc.) and objects (like String, Integer, etc.)
Contiguous Memory Allocation When we use arrays of primitive types, the elements are stored in contiguous locations. For non primitive types, references of items are stored at contiguous locations.
Zero-based Indexing: The first element of the array is at index 0.
Fixed Length: After creating an array, its size is fixed; we can not change it.

Disadvantages of Java Arrays
Fixed Size: Once an array is created, its size cannot be changed, which can lead to memory waste if the size is overestimated or insufficient storage if underestimated.
Type Homogeneity: Arrays can only store elements of the same data type, which may require additional handling for mixed types of data.
Insertion and Deletion: Inserting or deleting elements, especially in the middle of an array, can be costly as it may require shifting elements.

# Array methods

1. asList() Method
Definition: Converts an array into a fixed-size List.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3};
        List<Integer> list = Arrays.asList(arr);
        System.out.println(list);
    }
}
Output
[1, 2, 3]

2. binarySearch() Method
Definition: Searches for an element using Binary Search (array must be sorted).
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        System.out.println(Arrays.binarySearch(arr, 30));
    }
}
Output
2

3. copyOf() Method
Definition: Copies an array to a new array with the specified length.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[] copy = Arrays.copyOf(arr, 5);
        System.out.println(Arrays.toString(copy));
    }
}
Output
[1, 2, 3, 0, 0]

4. copyOfRange() Method
Definition: Copies a specific range from an array.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int[] copy = Arrays.copyOfRange(arr, 1, 4);
        System.out.println(Arrays.toString(copy));
    }
}
Output
[20, 30, 40]

5. equals() Method
Definition: Checks if two arrays are element-wise equal.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        System.out.println(Arrays.equals(a, b));
    }
}
Output
true

6. fill() Method
Definition: Assigns the given value to all elements of the array.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] arr = new int[4];
        Arrays.fill(arr, 7);
        System.out.println(Arrays.toString(arr));
    }
}
Output
[7, 7, 7, 7]

7. sort() Method
Definition: Sorts the array in ascending order.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
Output
[1, 2, 5, 8]

8. parallelSort() Method
Definition: Sorts the array using parallel processing.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {9, 3, 6, 1};
        Arrays.parallelSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
Output
[1, 3, 6, 9]

9. toString() Method
Definition: Returns a string representation of the array.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        System.out.println(Arrays.toString(arr));
    }
}
Output
[10, 20, 30]

10. mismatch() Method
Definition: Returns the index of the first mismatched element.
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = {1, 4, 3};
        System.out.println(Arrays.mismatch(a, b));
    }
}
Output
1

# Blocks
Blocks are used for initializationa and samll operations
Blocks are the java class memebers used to initialise member variable

1) Static block
Static blocks are used to initialise sttaic member variable
Static blocks are executed only once during class loading
We can declare multiple static blocks in the class and gets executed sequentially

2)Non Static block
Non Static blocks are used ti intialise non static member variable
Non Static blocks are executed dyring o bject creation
Non Sttaic block will be executed multiple times as object created
We can declare multiple non static blocks and it gets executed sequentially
Inside non static block we can access both static and non static member directly

# Constructor
Constructor are the special member of the class used to initailise non static member variable
It is used to set default or user-defined values for the object's attributes

A constructor has the same name as the class.
It does not have a return type, not even void.
It can accept parameters to initialize object properties.

1. Default Constructor
A default constructor has no parameters. It’s used to assign default values to an object. If no constructor is explicitly defined, Java provides a default constructor.
import java.io.*;
​
class Geeks{
​
    // Default Constructor
    Geeks(){
        
        System.out.println("Default constructor"); 
        
    }
    public static void main(String[] args){
        
        Geeks hello = new Geeks();
    }
}

Output
Default constructor
 Note: It is not necessary to write a constructor for a class because the Java compiler automatically creates a default constructor (a constructor with no arguments) if your class doesn’t have any. 

2. Parameterized Constructor
A constructor that has parameters is known as parameterized constructor. If we want to initialize fields of the class with our own values, then use a parameterized constructor.
class Geeks{
​
    // data members of the class
    String name;
    int id;
​
    // Parameterized Constructor
    Geeks(String name, int id)
    {
        this.name = name;
        this.id = id;
    }
​
    // Method to display object data
    void display(){
        
        System.out.println("GeekName: " + name
                           + " and GeekId: " + id);
    }
​
    // main() method — placed inside the same class for
    // universal compatibility
    public static void main(String[] args){
        
        // This will invoke the parameterized constructor
        Geeks geek1 = new Geeks("Sweta", 68);
        geek1.display();
    }
}
Output
GeekName: Sweta and GeekId: 68

3. Copy Constructor in Java
Unlike other constructors copy constructor is passed with another object which copies the data available from the passed object to the newly created object.
import java.io.*;
​
class Geeks{
    
    // data members of the class
    String name;
    int id;
​
    // Parameterized Constructor
    Geeks(String name, int id)
    {
        this.name = name;
        this.id = id;
    }
​
    // Copy Constructor
    Geeks(Geeks obj2)
    {
        this.name = obj2.name;
        this.id = obj2.id;
    }
}
​
class GFG {
    public static void main(String[] args)
    {
        // This would invoke the parameterized constructor
        System.out.println("First Object");
        Geeks geek1 = new Geeks("Sweta", 68);
        System.out.println("GeekName: " + geek1.name
                           + " and GeekId: " + geek1.id);
​
        System.out.println();
​
        // This would invoke the copy constructor
        Geeks geek2 = new Geeks(geek1);
        System.out.println(
            "Copy Constructor used Second Object");
        System.out.println("GeekName: " + geek2.name
                           + " and GeekId: " + geek2.id);
    }
}
Output
First Object
GeekName: Sweta and GeekId: 68

Copy Constructor used Second Object
GeekName: Sweta and GeekId: 68
Note: Java does not provide a built-in copy constructor like C++. We can create our own by writing a constructor that takes an object of the same class as a parameter and copies its fields.

4. Private Constructor
A private constructor cannot be accessed from outside the class. It is commonly used in:
Singleton Pattern: To ensure only one instance of a class is created.
Utility/Helper Classes: To prevent instantiation of a class containing only static methods.

class GFG {
​
    // Private constructor
    private GFG(){
        
        System.out.println("Private constructor called");
    }
​
    // Static method
    public static void displayMessage(){
        
        System.out.println("Hello from GFG class!");
    }
}
​
class Main{
    
    public static void main(String[] args){
        
        // GFG u = new GFG(); // Error: constructor is
        // private
       GFG.displayMessage();
    }
}
Output
Hello from GFG class!

# Constructor Overloading
The process of declaring multiple constructor with same name and different parameter
The parameter should differ either in the length or type
This is a key concept in OOP related to constructors is constructor overloading. This allows us to create multiple constructors in the same class with different parameter lists.

import java.io.*;
​
class Geeks{
    
    // constructor with one argument
    Geeks(String name){
        
        System.out.println("Constructor with one "
                           + "argument - String: " + name);
    }
​
    // constructor with two arguments
    Geeks(String name, int age){
​
        System.out.println(
            "Constructor with two arguments: "
            + " String and Integer: " + name + " " + age);
    }
​
    // Constructor with one argument but with different
    // type than previous
    Geeks(long id)
    {
        System.out.println(
            "Constructor with one argument: "
            + "Long: " + id);
    }
}
​
class GFG {
    public static void main(String[] args){
        
        // Creating the objects of the class named 'Geek'
        // by passing different arguments
        Geeks geek2 = new Geeks("Sweta");
​
        // Invoke the constructor with two arguments
        Geeks geek3 = new Geeks("Amiya", 28);
​
        // Invoke the constructor with one argument of
        // type 'Long'.
        Geeks geek4 = new Geeks(325614567);
    }
}

Output:

Constructor with one argument - String: Sweta

Constructor with two arguments:  String and Integer: Amiya 28

Constructor with one argument: Long: 325614567

