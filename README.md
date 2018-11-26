# java11-randomized-iteration-order-for-jep-269

_Reference_: java11-randomized-iteration-order-for-jep-269  
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