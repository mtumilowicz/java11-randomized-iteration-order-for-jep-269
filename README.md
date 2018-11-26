[![Build Status](https://travis-ci.com/mtumilowicz/java11-randomized-iteration-order-for-jep-269.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-randomized-iteration-order-for-jep-269)

# java11-randomized-iteration-order-for-jep-269

_Reference_: http://openjdk.java.net/jeps/269  
_Reference_: https://www.youtube.com/watch?v=q6zF3vf114M

# project description
1. iteration order for `Set` elements and `Map` keys
    * `HashSet`, `HashMap`: order is officially unspecified
    * however, usually consistent for long periods of time 
    (> 1 JDK release cycle)
    * inadvertent order dependencies can creep into code
    * lots of code probably has latent iteration order 
    dependencies
1. randomized operation order for `Set` / `Map` from JEP 269
    * structures obtained from static methods: `Set.xxx(...)`,
    `Map.xxx(...)`, for example:
        * `Set.of(...)`
        * `Map.ofEntries(...)`
    * predictably unpredictable
    * iteration order will be stable within JVM instance
    * but will change from one run to the next
1. applies only to new collections implementations - existing
collections will remain the same

# tests
Run multiple times tests (`RandomIterationOrderTest`):
1. `set_jep269`
    ```
    1
    5
    4
    3
    2
    ```
    ```
    1
    2
    3
    4
    5
    ```
    ```
    5
    4
    3
    2
    1
    ```
1. `old_hashSet`

    always:
    ```
    1
    2
    3
    4
    5
    ```
1. `map_jep269`
    ```
    5: 5
    4: 4
    3: 3
    2: 2
    1: 1
    ```
    ```
    1: 1
    2: 2
    3: 3
    4: 4
    5: 5
    ```
    ```
    4: 4
    3: 3
    2: 2
    1: 1
    5: 5
    ```
1. `old_hashMap`

    always:
    ```
    1: 1
    2: 2
    3: 3
    4: 4
    5: 5
    ```
# remark
**Note that order is stable within JVM instance**:
* `set_jep269_multiple_one_jvm_instance`
    ```
    Set.of(1, 2, 3, 4, 5).forEach(System.out::println);
    System.out.println("---");
    Set.of(1, 2, 3, 4, 5).forEach(System.out::println);
    System.out.println("---");
    Set.of(1, 2, 3, 4, 5).forEach(System.out::println);
    ```
    produces
    ```
    2
    1
    5
    4
    3
    ---
    2
    1
    5
    4
    3
    ---
    2
    1
    5
    4
    3
    ```