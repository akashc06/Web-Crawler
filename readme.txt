INFORMATION RETRIEVAL - HW1

All the tasks are coded in JAVA, version 8.

Tasks 1 and 2 have seed as https://en.wikipedia.org/wiki/Sustainable_energy.
Task 3 has seed as https://en.wikipedia.org/wiki/Solar_power.

The .java files are in the folder named "Java Files".
The text files are in the folder named "Text Files".

Running these codes requires a library - jsoup-1.9.2.jar

The Jsoup library has been included in the .rar files.
The library is found in all the 4 .rar files.

The link to download the library if need is 
https://jsoup.org/download  

***********************************************************************************************************************
To Install Java

Download Java 8 from 
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

***********************************************************************************************************************
HOW TO RUN PROGRAMS:

Task 1-

Type the following commands in the command prompt

1. Compile the code using
   javac -d . -cp jsoup-1.9.2.jar task1.java

2. Execute the code using
   java -cp ./jsoup-1.9.2.jar;. task1 https://en.wikipedia.org/wiki/Sustainable_energy


			***************************************

Task 2-A

Type the following commands in the command prompt

1. Compile the code using
   javac -d . -cp jsoup-1.9.2.jar task2a.java

2. Execute the code using
   java -cp ./jsoup-1.9.2.jar;. task2a https://en.wikipedia.org/wiki/Sustainable_energy solar

			****************************************

Task 2-B

Type the following commands in the command prompt

1. Compile the code using
   javac -d . -cp jsoup-1.9.2.jar task2b.java

2. Execute the code using
   java -cp ./jsoup-1.9.2.jar;. com.extra.task2b https://en.wikipedia.org/wiki/Sustainable_energy solar

			*****************************************

Task 3

Type the following commands in the command prompt

1. Compile the code using
   javac -d . -cp jsoup-1.9.2.jar task3.java

2. Execute the code using
   java -cp ./jsoup-1.9.2.jar;. task3 https://en.wikipedia.org/wiki/Solar_power

*****************************************************************************************************************************

Sources of Information:

1. https://jsoup.org/cookbook/extracting-data/selector-syntax
2. https://www.youtube.com/watch?v=TIdF6_MvHzM
3. http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
4. http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
5. http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/