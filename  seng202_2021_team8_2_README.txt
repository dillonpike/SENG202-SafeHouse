# SafeHouse

SafeHouse is an open-source crime analysis application that allow users to import crime data, view it, sort it,
filter it, and most importantly analyse it.

## Authors

- Dillon Pike
- Oliver Johnson
- Karl Moore
- Sahil Negi

## Installation

Ensure you have Java 14 or later installed. You can check your version in Terminal or Command Prompt by entering:
java -version


Maven must also be installed and can be done by following this guide: https://maven.apache.org/install.html

The project can be downloaded by cloning the repistory by entering the following into Terminal or Command Prompt.
git clone https://eng-git.canterbury.ac.nz/dkp33/seng202-team8.git


It can also be downloaded as a .zip file and then extracted.

## Running SafeHouse

SafeHouse can be run in Terminal or Command Prompt by navigating to the repository folder and building the project with:
mvn package

Then running the application with:
java -jar target/SafeHouse-1.0-SNAPSHOT.jar


## Importing SafeHouse into an IDE

It can also be imported into an IDE that supports Maven.
To import into IntelliJ click File > New > Import from Existing Sources,
select the repository folder and go through the dialogs.

Then do the following to finish setting up:
- In Project Settings > Project, set the Project SDK and language level to 14
- In Project Settings > Modules, set language level to 14
- In Project Settings > Modules, mark src/main/java as a source folder
- Mark src/main/resources as a resource folder
- Mark src/test/java as a test source folder
- Mark target as an excluded folder (Create this folder if it doesn't exist)
- In Settings > Build, Execution, Deployment > Build Tools > Maven, tick "Always update snapshots" and click apply.
  Then run the Maven build scripts from the popup in the bottom right.