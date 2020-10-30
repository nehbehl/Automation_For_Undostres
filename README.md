# AutomationForUndostres

This file holds basic steps required to execute and use the automation framework created for Un dos tres automation requirement.
It is a maven project and can be imported easily to any IDE, say Eclispe. It uses extent reporting for generating the automation execution report.
The pages and test classes use TestNG annotation for better management and execution. This framework supports multiple browsers like Chrome, FF and IE
and can be configured from the properties file placed under src/test/resources Properties folder. The locators of all the application pages are co-located under
Repository folder in an ApplicationLocators.xml file.

# Pre-requisite software
- Jdk 8+
- Apache Maven 3.5.0
- TestNG
- Eclipse IDE

# How to use

After importing the automation code, navigate to the test package under src/test/java in order to access the sample automation script.
Right click the automation script and run as TestNG in order execute the flow on the application.

# Browser drivers

This folder holds the driver executables required to launch automation code on different browsers. The driver script for same lies in utilities class under /src/test/java.
