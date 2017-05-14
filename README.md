# CPIO v1.0
Java methods for GPIO access on CHIP

### Status
CPIO is still in developement. Version 1.0 acts as expected. I am still working of testing, cleaning, and making it easier to use. Future versions WILL NOT necessarily be backwards compatible. Have features or fixes? Feel free to submit Pull Requests!
### Goal
GPIO access for Python is great, I don't particularly like Python though. My goal here is to make a simple to use set of methods that allows GPIO programming with Java on the CHIP computer by Next Thing Co.
### Setup
Download the CPIO.java file, this class contains the required methods and constructor for the CPIO object. To use this in a project, just place CPIO.java into the project directory. I am currently developing for my CHIP which is running kernel 4.4.13-ntc-mlc; I will add automatic support for other kernels in the future.
### Usage
CPIO is simple to use! Pins are controlled by creating a CPIO object which corresponds to a given pin. Use the four methods to change the properties of the pin. See Test.java for an example of all the uses and syntax. CPIO.java also contains a bit of documentation to help you out also.

See CHANGELOG for changes by version aand planned features
