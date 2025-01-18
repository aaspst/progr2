# **SmartCart Project**

## **Description**
The **SmartCart Project** is a Java-based application designed to optimize shopping by determining the supermarket with the lowest total price for a selected list of products. The application utilizes SQLite for data storage and integrates essential functionality for database handling and optimization.

---

## **Compilation Instructions**
To compile the project:

1. Ensure **Maven** is installed on your system.
2. Run the following command in the root directory of the project:
   ```bash
   mvn clean package

***There might occur a test failure in the first compilation,
because the database can't get recognised as it's being built while compiling. It gets fixed if you try to compile the program again.***

---

## **Execution Instructions**
1. Ensure that you have compiled the program.
2. Run the following command in the root directory of the project, where `pom.xml` is located:
   ```bash
   mvn exec:java

---

## **Usage Instructions**
1. **Check the boxes** of the products you are interested in (please **select at least 2**).
2. Scroll down to the bottom of the page and **select** your desired **location** from the dropdown menu.
3. Press the button **Find Best Supermarket**.
4. The result page will appear to inform you for your **selected produts and location**, what is the **best supermarket** and the **district** where it's located in, and your **total price**.
5. **Press close** to terminate the program.

---

## **Repository Structure**
 ```ultree
 project-root/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/smartcart/
│   │   │       ├── SQLFileExecutor.java
│   │   │       ├── OptimizationEngine.java
│   │   │       ├── SmartCartUI.java
│   │   │       └── ResultPage.java
│   │   └── resources/
│   │       ├── SmartCartDB.sql
│   │       ├── ClearDatabase.sql
│   │       ├── fonts/
│   │       │   └── Lobster-Regular.ttf
│   │       └── images/
│   │           └── supermarket.jpg
│   ├── test/
│       ├── java/
│       │   └── com/smartcart/
│       │       ├── SQLFileExecutorTest.java
│       │       ├── OptimizationEngineTest.java
│       │       └── ResultPageTest.java
├── pom.xml
├── README.md
├── SmartCart.sql
├── UML_diagram.png
├── LICENSE.txt

```

---

## **UML Diagram**
![uml image](https://github.com/aaspst/progr2/raw/main/UML_diagram.png)

---

## **Data Structures and Alorithms Overview**

### **Data Structures**
- SQLite database for storing product, supermarket, and pricing data.
- Java `Lists` for handling user inputs (product lists).

###  **Algorithms**
- SQL query for calculating the total price for selected products at each supermarket.
- Dynamic SQL placeholders for secure and efficient input handling.
- Robust error handling for missing data (selection of less than 2 products).

---

## Authors
[Georgios Symeon Dionysopoulos](mailto:8230033@aueb.gr),
[Karakatsanis Emmanouil](mailto:8230047@aueb.gr),
[Katsoulis Dimitrios](mailto:8230056@aueb.gr),
[Mpelogiannis Vasileios](mailto:8230103@aueb.gr),
[Mpinatsis Marios](mailto:823014@aueb.gr),
[Sotiropoulos Evangelos](mailto:8230148@aueb.gr),
[Tsagkaropoulou Aspasia](mailto:asptsagkarop@gmail.com)

---

## Contributing

Pull requests are welcome. Please make sure to update tests as appropriate.

---

## License
This project is licensed under the MIT License - click [here](LICENSE.txt) for details.
