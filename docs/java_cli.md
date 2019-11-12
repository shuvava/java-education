# Java CLI

* compilation
``sh
cd <projectFolder>/src
javac ch01/sec01/HelloWorld.java -verbose
``
* execution
```sh
cd <projectFolder>/src
java ch01.sec01.HelloWorld
```

* see defined classes
```sh
cd <projectFolder>/src
javap ch01.sec01.HelloWorld
```
## JShell

* search 

1. type key word `Duration`
1. If you need to import another class, you can type the import statement at
   the JShell prompt. Or, more conveniently, you can have JShell search for it,
   by typing Shift+Tab and the I key. For example, type Duration followed by
   Shift+Tab and the I key. You get a list of potential actions:
     ```sh
       jshell> Duration
       0: Do nothing
       1: import: java.time.Duration
       2: import: javafx.util.Duration
       3: import: javax.xml.datatype.Duration
       Choice:
    ```
1. `/help` for help
1. `/exit` for exit

