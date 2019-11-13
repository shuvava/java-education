# Java CLI

## compilation

When class files are read from a file system, the path name needs to match the package name. For example, the file Employee.class must be in a subdirectory
com/horstmann/corejava.
you arrange the source files in the same way and compile from the directory that contains the initial package names, then the class files are automatically
put in the correct place. Suppose the EmployeeDemo class makes use of Employee objects, and you compile it as
`javac com/horstmann/corejava/EmployeeDemo.java`
The compiler generates class files com/horstmann/corejava/EmployeeDemo.class and com/horstmann/corejava/Employee.class. You run the program by specifying the fully
qualified class name: `java com.horstmann.corejava.EmployeeDemo`
``sh
cd <projectFolder>/src
javac ch01/sec01/HelloWorld.java -verbose
``
The javac and java programs have an option -cp (with a verbose version --class-path or, for backwards compatibility, -classpath). 
For example,

`java -cp .:../libs/lib1.jar:../libs/lib2.jar com.mycompany.MainClass`

This class path has three elements: the current directory (.) and two JAR files in the directory ../libs.

In Windows, use semicolons instead of colons to separate the path elements:
`java -cp .;..\libs\lib1.jar;..\libs\lib2.jar com.mycompany.MainClass`

If you have many JAR files, put them all in a directory and use a wildcard to include them all:
`java -cp .:../libs/\* com.mycompany.MainClass`

## execution
```sh
cd <projectFolder>/src
java ch01.sec01.HelloWorld
```
## see defined classes
```sh
cd <projectFolder>/src
javap ch01.sec01.HelloWorld
```

## packing (jar)

Instead of storing class files in the file system, you can place them into one or more archive files called JAR files. You can make such an archive with
the jar utility that is a part of the JDK. Its command-line options are similar to those of the Unix tar program.
`jar --create --verbose --file library.jar com/mycompany/*.class`

or, with short options,

`jar -c -v -f library.jar com/mycompany/*.class`

or, with tar-style options,

`jar cvf library.jar com/mycompany/*.class`

You can use JAR files to package a program, not just a library.
Generate the JAR file with

`jar -c -f program.jar -e com.mycompany.MainClass com/mycompany/*.class`

Then run the program as

`java -jar program.jar`

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

