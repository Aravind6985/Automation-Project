# Automation-Project
Website: NOP Commerce Demo 
Framework: Maven Framework using TestNG
Report: Extent Report
POM Design 

The elements are stored in a seperate page creating repository for easy access and modification.
Common tests and methods are created in a seperate base class for easy understanding.
Another support class is cretaed for ease of maintenance and modifications which assist the test page while executing the script.
Also, a report is created for the entire project using Extent Report.

There are 2 test included.
1. Create a new customer
   In this the email, password, first name & last name is generated using RandomStringUtil method for random credentials and name because the website requires unique emailid.
   Other things are dropdown handling, date picker, title verification, text verification, etc....
3. Create a new product Category
   In this test, there requres a file upload which is done using Robot class, dropdowns are handled. 
