# Singleton
### _The most common, yet not so well known design pattern._

According to wiki:
> In software engineering, the **singleton pattern** is a design pattern that restricts the instantiation of a class to one object.
> This is useful when exactly one object is needed to coordinate actions across the system.
> The concept is sometimes generalized to systems that operate more efficiently when only one object exists, or that restrict the instantiation to a certain number of objects.
> The term comes from the mathematical concept of a singleton.

Some even say that singleton is anti-pattern, wiki:
> There is criticism of the use of the singleton pattern, as some consider it an anti-pattern, judging that it is overused, introduces unnecessary restrictions in situations where a sole instance of a class is not actually required, and introduces global state into an application.

Which obviously may be true but only an anti-pattern if used incorrectly, though that is the definition of an anti-pattern itself.

## Common uses

Wiki:
> * The _Abstract Factory_, _Builder_, and _Prototype_ patterns can use Singletons in their implementation.
> * Facade objects are often singletons because only one Facade object is required.
> * State objects are often singletons.
> * Singletons are often preferred to global variables because:
>   * They do not pollute the global namespace (or, in languages with namespaces, their containing namespace) with unnecessary variables.
>   * They permit lazy allocation and initialization, whereas global variables in many languages will always consume resources.

## JAVA implementations

A lot of developers tend to relate singletons directly with IoC containers, like Spring's.
That's a bit unjustified and bias-like as singleton may be implemented in many different ways without involving any 3rd party container.
Quite a few caveats and mistakes my be done at the same time too.

### Lazy initialized singleton

Probably the most recognizable implementation:
* private constructor
* public getter which initializes when called for the 1st time
* lazy initialized static private `instance` field

How about concurrency issues?

### Thread-safer lazy initialized singleton

There is a risk of creation and returing more then one instance in multithreaded environment if more then one thread call `getInstance()` in the same time before singleton has been initialized for the 1st time.
To lower the risk of such issue `synchronized` keyword is added to `getInstance()` method which ensures the same instance will be returned among the threads.

Still quite simple and clean implementation but can it be trusted for sure?

### Thread-safe lazy initialized singleton

The problem is that an out-of-order write may allow the instance reference to be returned before the singleton constructor is executed.
That's why for full thread safety _double checked locking_ is being used in this implementation:

* `instance` has `volatile` keyword added
* there is one check if singleton has been already initialized before synchronization
* and another check after synchronization

According to wiki this method should not be used with older versions of JAVA than 1.5 because of subtle bugs.

The point is: it gets complex already, it's easy to forget the volatile statement and difficult to understand why it is necessary.

More about double checked locking and related issues: http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html

### Eagerly initialized singleton

Another widely recognized implementation:
* private constructor
* public getter to retrieve instance
* eagerly initialized static private `instance` field

Despite the name of the approach, in fact this effectively is lazy initialisation anyway. Since the static singleton won't be instantiated until the class is loaded.
And the class won't be loaded until it's needed (which will be right about the time that you first reference the getInstance() method).

Anyway that's one of advantages among a few:
* the static initializer is run when the class is initialized, after class loading but before the class is used by any thread.
* there is no need to synchronize the `getInstance()` method, meaning all threads will see the same instance and no (expensive) locking is required.
* the final keyword means that the instance cannot be redefined, ensuring that one (and only one) instance ever exists.

Actually we may even drop the `final` modifier as no other thread will get access to the class while the static members are initialized.
This is guaranteed by the compiler, in other words, all static initialization is done in a synchronized manner - only one thread.

There is potential drawback - the cost of instance creation in terms of time and resources should be considered.
However as we already said, it is not so different in terms of laziness so this shouldn't be a big concern.


### Public field singleton

Similar to the previous approach but a bit more concise by making the `INSTANCE` field access level `public`. Obviously this way instance field has to be `final`.
Having instance as a constant allows to remove accessor method `getInstance()`.

### Static block initialization

Similar to _eagerly initialized singleton_ previous but allowing to do some pre-processing, let say error-checking.

May be combined with _Public field singleton_ approach

### Initialization-on-demand (holder pattern)

Wiki:
>(...) efforts on the "Double-checked locking" idiom led to changes in the Java memory model in Java 5 and to what is generally regarded as the standard method to implement Singletons in Java.
>The technique known as the initialization-on-demand holder pattern is as lazy as possible, and works in all known versions of Java.
>It takes advantage of language guarantees about class initialization, and will therefore work correctly in all Java-compliant compilers and virtual machines.

>The nested class is referenced no earlier (and therefore loaded no earlier by the class loader) than the moment that `getInstance()` is called.
Thus, this solution is thread-safe without requiring special language constructs (i.e. `volatile` or `synchronized`).

### Enum based singleton

If constant why not go further and use `enum` then?

As `enum` is 1st class citizen among other classes in JAVA it can be used as well to implement singleton.
This is not so widely known but really handy way with the most OOB features while being the most concise.

Joshua Bloch explained this approach in his Effective Java Reloaded talk at Google I/O 2008 as follows:

>This approach is functionally equivalent to the public field approach, except that it is more concise, provides the serialization machinery for free, and provides an ironclad guarantee against multiple instantiation, even in the face of sophisticated serialization or reflection attacks. While this approach has yet to be widely adopted, a single-element enum type is the best way to implement a singleton.

As mentioned previously with Eagerly initialized singleton, here is similar situation.

`enum` instance fields are not "initialized by a compile-time constant expression". They can't be, because only String and primitive types are possible types for a compile-time constant expression.
That means that the class will be initialized when `INSTANCE` is first accessed - which is exactly the desired effect.

### A singleton with arguments

We've seen not once that singleton with arguments is handful with Dependency Injection.
However it seems that providing arguments to a such class makes it singleton no more.

The mistake perhaps comes from the fact that for example Spring names specific _scope_ of a _bean_ as "singleton".
Bear in mind that's the scope name and not the Design Pattern of the class being instantiated in the IoC container.

If there is a need to feed a singleton with some data there are two options:
1. it may be loaded once after instantiation with some `init(Type argument)` method.
2. it may be passed as argument(s) to the method being executed if the operation singleton performs is recurring, and with different parameters each time.

A singleton, by definition, if an object you want to be instantiated no more than once. If you are trying to feed parameters to the constructor, what is the point of the singleton?
Thus let me state this clear: **a singleton with constructor parameters is not a singleton**.

## How about Groovy?

The most of described JAVA approaches may be implemented in Groovy as well.
However Groovy comes with handful AST annotation `@Singleton` OOB.
This annotation may be used on any class to transform it into singleton.

---

### TODO:
- improve Singleton with arguments example
- read http://javarevisited.blogspot.gr/2012/07/why-enum-singleton-are-better-in-java.html
- read http://www.drdobbs.com/jvm/creating-and-destroying-java-objects-par/208403883?pgno=3
- what's the difference when static block used
- refresh volatile behaviour
- prepare Groovy example and read about @Singleton implementation
- read more about double-check-locking: http://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java
- add links to specific source files under each description