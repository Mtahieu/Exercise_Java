This is the repo containing the solutions of a provided exercise.
It was a task about Apache Camel and I only tracked the important changes that I made to the code, uploading three classes in total. 

Notes to task 1:
In this case, Camel serves as a routing tool. It controls and defines information flow between seperate destinations.
In the provided example a .xml files are queried and moved to different destinations according to condition matching (is city == London?).
Interestingly, the camel process does not stop there and is responsive to all changes. So it listenes actively to changes and will always move data.


Comments:
Most of the time I spent for setting everything up. Java and Maven have not been installed on my machine since my university course "Softwaretechnik". I included camel-jsonpath and camel-http as dependencies in the POM. Understanding and following the information flow within exchanges of API requests for example where my other greatest challenge.
