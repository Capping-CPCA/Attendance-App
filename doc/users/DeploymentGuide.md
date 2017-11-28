Attendance Client Deployment Guide
======================================


### On Windows Computers
1. Download & install the `Java JDK` for Windows distributions
  - Can be found [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  - Set the `JAVA_HOME`
    - Right-click on `This PC` and click the `Properties` option
    - Click on `Advanced system settings`
    - Click on `Environment Variables` under the `Advanced` tab
    - Click on `New` in the `System variables` section
    - `Variable name:` should be `JAVA_HOME`
    - `Variable value:` should point to your `JDK` installation
2. Check that you can run `java -version`
3. Download & Install `Maven`
  - Download the binary `zip` file from [here](https://maven.apache.org/download.cgi)
  - Extract the `.zip` file
  - Enter the directory so that your current directory shows the `apache-maven-3.5.2` directory
  - Move the `apache-maven-3.5.2` directory to a "safe" place where it won't get deleted. Preferably, `"C:\Program Files\"`
4. Download the `Attendance-App` project from Github
5. After extracting the java project go to the root directory of the project and run the following command
  - `"C:\Program Files\apache-maven-3.5.2\bin\maven" install`
  - `"C:\Program Files\apache-maven-3.5.2\bin\maven" package`
  - `"C:\Program Files\apache-maven-3.5.2\bin\maven" compile`