# War Card Game

#### **Author**: Aditya Singh Raghav 

##### **Version:** 1.0

##### **External Dependencies:**
1. JUnit 3.8.1

##### **Building from Source:** 

The plugin uses `Maven` for its build system.

In order to create a distribution, simply run the `mvn clean package` command in the `WarGame` directory.

The distribution will be created under the `target/` directory in that project.

To create the distribution without running test use the `-Dmaven.test.skip=true` flag.

##### **Running the Program:**

To run the War Game program run the command `java -jar WarGame-1.0.jar` in the `target` directory

##### **Building without Maven:**

In case Maven is not available the program can also be built by executing the command `javac \org\WarGameApp\WarGame.java` in the `pathto:\WarGame\src\main\java` directory.
It can then be run using the command `java org.WarGameApp.WarGame` in the same directory.