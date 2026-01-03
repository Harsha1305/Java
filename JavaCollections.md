# ArrayList
ArrayList is a resizable array implementation
ArrayList grows dynamically and ensure that there is always a space to add elements
The Backing dtata structure of ArrayList is an Array of Object class
ArrayList class in java has 3 constructors
--> ArrayList()
--> ArrayList(int capacity)
ArrayList(Collection c)

STACK (thread stack)
-------------------
list  ───────────────►  (reference)

HEAP
----
ArrayList object
+-------------------------+
| size = 0                |
| elementData ────────────┼──► Object[] array
+-------------------------+

Object[] elementData (capacity = 10 by default)
Index:   0     1     2     3     4     5     6     7     8     9
Value:  null  null  null  null  null  null  null  null  null  null


------------------------------------------------------------------------------
Adding Element


ArrayList object
+-------------------------+
| size = 3                |
| elementData ────────────┼──► Object[]
+-------------------------+

Object[] elementData (capacity = 10)
Index:   0     1     2     3     4     ...
Value:  "A"   "B"   "C"   null  null

--------------------------------------------------------------------------------
Use when:
--> You need fast random access (get(i)).
--> You mostly append and iterate.
--> Read-heavy workloads (UI lists, results from DB, cached lists).

Advantages:
--> get/set by index: O(1)
--> Fast iteration (good CPU cache locality)
--> Lower memory than linked structures

Disadvantages:
--> Insert/remove in middle: O(n) shifting
--> Resizing copies elements occasionally
--> Not thread-safe by default

# Linked List
 In Java LinkedList is implemented using Double Linked List
 Elements are not stored in contiguous memory
 Each node consists of three parts:
 --> Node Value
 --> Reference to the previous node
 --> Reference to the next node
 Maintain insertion order just like an array
 Adding and removing of an element is much faster
 It is not synchronized and not thread safe