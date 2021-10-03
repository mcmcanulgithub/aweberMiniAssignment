# AWEBER Auto Test Assignment

The goal of this assignment was to automate testing of the my profile section of the wordpress product with any preferred tool or technology and then submit to github. The page we automated testing of can be found [here](https://wordpress.com/me)

## Repository

https://github.com/mcmcanulgithub/aweberMiniAssignment

## Environment Variables And Other Configuration

REQUIRED: Set up a free wordpress account and save off your username and password similar to how i have done in the code should you wish to use a different account.

REQUIRED: Set the CHROME_WEB_DRIVER_PATH variable to wherever the chrome web driver executable is stored.

REQUIRED: Set the WP_USERNAME and WP_PASSWORD variables to a username and password for an account that can be used to log into the wordpress page.

REQUIRED: Set the IMAGE_1 and IMAGE_2 variables to wherever the image files are stored for the photo upload test. They are uploaded along with the tests in the src/main/resources location, so  when setting up the environment variables on the SUT for the images they should both be pointed to that location.

REQUIRED: Set the JUNIT_HOME as a system environment variable to be able to run the tests as junits when pulling down and attempting to run this repo. The value it needs to be set to is wherever the JAR files are stored on your machine, which with the maven setup we have done you can just point to your development workspace. See the following for an example value - C:\Users\mcmca\eclipse-workspace

REQUIRED: Set the JAVA_HOME as a system environment variable to be able to run the java executables. This needs to give the path of your JDK installation on your machine. An example value could be the following - C:\Program Files\Unity\Hub\Editor\2021.1.16f1\Editor\Data\PlaybackEngines\AndroidPlayer\OpenJDK 

REQUIRED: Place the two image files named, p17bv6pi65kqsgs0 (1).png and p26kk7xa3jwu808c (1).png at the C drive location of the machine under test to be used as resources for the photo upload test.

REQUIRED: Install the chromedriver exe for the version of chrome on the machine under test. The verion you need to download can be found at the following link. There are also instructions contained within on how this is to be used in the automated tests. For this task, we have went with approach #2 listed out under the setup section and if extending this framework, same pattern can be followed. We placed the exe at the same location as the eclipse workspace for simplicity on this task, and whatever machine this framework is run on would need to have the chromedriver exe in the directory that matches the line of java code in the @before junit method that sets the system property for the chrome driver - [Guide](https://chromedriver.chromium.org/getting-started)

## Important Commands For Developers

> export CHROME_WEB_DRIVER_PATH="C:\\Users\\mcmca\\eclipse-workspace\\chromedriver.exe";

this will set the system environment variable on linux. same pattern can be followed for other system environment variables.

> mvn clean install

takes the project’s Java source code, compiles it, tests it and converts it into an executable Java program: a .jar file in this case. This kind of command can be used from a CI/CD loop in a jenkins job or be used to execute the tests on the command line independent of the IDE used to build this, Eclipse. This command pulls down all the dependencies in the process so will need to be used when the project is imported the first time during local development such as if this framework were to be modified.

There are more commands should the developer wish to run a subset of all the tests from the command line. See the following if interested - [commands](https://www.baeldung.com/junit-run-from-command-line)

## Getting Up And Running Locally

The above maven commands can be followed to run the project from the command line and if run from eclipse for java developers, you also have another way to run by right click on the MyProfileTest.java and do run as a junit test. Another option in eclipse is to right click on the pom file and do mvn clean install or mvn clean test as well. You need to have the JDK and JRE installed as well regardless of your IDE, and the following link has a useful guide on how to do this. Should you choose to use eclipse as your IDE, there are also some helpful pointers on setup as well. [Here](https://www.guru99.com/installing-selenium-webdriver.html) is the guide, see steps 1 and 2.

## About The Code

Selenium was the main framework chosen since it is good for automating UI tests and can access the DOM elements effectively. Junit was chosen as a framework to help orchestrate and better organize the tests. Java was the programming language chosen and maven is used as the dependency manager for streamlined source control, usability, and scalability for other folks should they wish to add onto this repo. Should one wish to add onto on this framework that was built such as adding tests for another webpage, one can simply create a new java class and it should run all the same using the maven commands listed under important commands for developers. The pom file is what pulls in the dependencies and can be added to should one wish to add more dependencies if updating this framework. 

useful link to ascertain and add more dependencies to pom if necessary - [link](https://mvnrepository.com/artifact/org.openqa.selenium/selenium/0.8.0)

useful link explaining maven project structure for those interested - [link](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

useful resources for selenium and maven - [link](https://stackoverflow.com/questions/16934566/for-selenium-webdriver-maven-and-initial-pom-xml-configuration)

interesting discussion on maven test location - [link](https://intellij-support.jetbrains.com/hc/en-us/community/posts/206850895-Classpath-in-run-configurations-source-root-vs-test-source-root)

excellent guide on setting up maven if using eclipse. steps should be loosely similar as well on other IDEs - [link](https://www.vogella.com/tutorials/EclipseMaven/article.html)

The test cases were designed as integrated functional UI tests. They all each test one functional UI component of the page, such as a test for first name text field, another test for last name field, another test for wordpress site profile link section, etc. They test the full workflow of that particular component along with all its permutations, such as adding, updating, deleting, cancelling etc. At the end of each permutation, a save is performed and then the value is validated. They were designed as 9 larger integrated tests instead of many individual tests because the tests for the different permutations each require certain data setups that are easier and more efficiently attained by having the permutations strategically grouped together. For example, can't really do a delete on a text field unless there is text already there. Before and teardown methods are also used which start-up/log in to the wordpress about me page and kill the browser respectively. These are used to keep the tests as independent of each other as possible in the event that some error or exception occurs. This makes the tests less brittle and de-couples them. 

## Test Names And Description
|Test Name                      | Test Description                                                 |
|-------------------------------|------------------------------------------------------------------|
|profileVerifyPublicDisplayName | Tests adding, updating, and deleting of text content for public display name field |
|profileVerifyAboutMe           | Tests adding, updating, and deleting of text content for about me field |
|profileVerifyWordpressSite     | Tests add, cancel, and delete workflows for the wordpress site link |
|profileVerifyGravatarProfile   | Tests hiding and unhiding toggle behaviour of gravatar profile |
|profileVerifyVerifyIconInformational | Tests displaying and hiding of informational text regarding gravatar photo |
|profileVerifyUrl               | Tests add, cancel, and delete workflows for the url link |
|profileVerifyChangePhoto       | Tests add, cancel, and reset workflows for the profile photo |
|profileVerifyLastName          | Tests adding, updating, and deleting of text content for last name field |
|profileVerifyFirstName         | Tests adding, updating, and deleting of text content for first name field |

## Authors

* Matt McAnulty