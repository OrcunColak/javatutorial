# Volatile
The singleton needs to be volatile. This is broken

```java
// This is broken in Java
public class MyClass {
    private static MyClass instance;

    private MyClass() {
        // Private constructor to enforce Singleton pattern
    }

    public static MyClass getInstance() {
        if (instance == null) {
            synchronized (MyClass.class) {
                if (instance == null) {
                    instance = new MyClass();
                }
            }
        }
        return instance;
    }
}
```
This is correct
```java
// Fix
public class MyClass {
    private static volatile MyClass instance; // <<< FIX: Add 'volatile'

    private MyClass() {
        // Private constructor to enforce Singleton pattern
    }

    public static MyClass getInstance() {
        if (instance == null) {
            synchronized (MyClass.class) {
                if (instance == null) {
                    instance = new MyClass();
                }
            }
        }
        return instance;
    }
}
```