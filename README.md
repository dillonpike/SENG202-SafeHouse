# SafeHouse

SafeHouse is an open-source crime analysis application that allow users to import crime data, view it, sort it, filter it, and most importantly analyse it.

## Authors

- Dillon Pike
- Oliver Johnson
- Karl Moore
- Sahil Negi

## Installation

Ensure you have Java 14 or later installed. You can check your version in Terminal or Command Prompt by entering:
```
java -version
```

Maven must also be installed and can be done by following the guide found here: https://maven.apache.org/install.html

The project can be downloaded by cloning the repository by entering the following into Terminal or Command Prompt.
```
git clone https://eng-git.canterbury.ac.nz/dkp33/seng202-team8.git
```

It can also be downloaded as a .zip file and then extracted.

## Running SafeHouse

SafeHouse can be run in Terminal or Command Prompt by navigating to the repository folder and building the project with:
```
mvn package
```
Then running the application with:
```
java -jar target/SafeHouse-1.0-SNAPSHOT.jar
```

## Importing SafeHouse into an IDE

It can also be imported into an IDE that supports Maven. To import into IntelliJ click File > New > Import from Existing Sources, select the repository folder and go through the dialogs.

Then do the following to finish setting up:
- In Project Settings > Project, set the Project SDK and language level to 14
- In Project Settings > Modules, set language level to 14
- In Project Settings > Modules, mark src/main/java as a source folder
- Mark src/main/resources as a resource folder
- Mark src/test/java as a test source folder
- Mark target as an excluded folder (Create this folder if it doesn't exist)
- In Settings > Build, Execution, Deployment > Build Tools > Maven, tick "Always update snapshots" and click apply. Then run the Maven build scripts from the popup in the bottom right.
- Click Add Configuration at the top right, select Application, then add seng202.team8.Main to the Main Class field

## Using SafeHouse

### Importing, Exporting, and Switching Datasets

To import a crime record dataset, click the import button on the map or table screen, then select a file. Imported files must be .csv files and follow the structure of testdata.csv in the src/main/resources folder.

If you’re doing this after you’ve already imported data or added a crime record, you’ll be prompted to pick whether you’d like to append the new records to the current dataset, or create a new one. These datasets can be switched using the selection box above the table or map.

To export a crime record dataset, click the export button on the map or table screen, then select a folder you’d like to save in. Your dataset will then be exported to a comma-separated values (csv) file named after the date and time of the export.

### Viewing Records in a Table

Crime records can be viewed in a table by navigating to the table screen. The table will show key attributes from all the records in the currently selected database. Clicking a record in the table will bring up extra information about it in the crime details panel to the right of the table.

### Viewing Records on a Map

Crime records can be viewed on a map by navigating to the map screen, filling in the number of records you’d like to display at the bottom, then clicking the map it button (if real-time updating isn’t ticked). Markers will then be placed on the map for each crime record in the currently selected dataset, up to the number given. Clicking a marker will display information about the crime record it represents.

### Adding, Editing, and Removing Records

Crime records can be added by clicking the add button on the table or map screen, filling in the fields in the popup, then clicking confirm. The added record will be added to the bottom of the table or displayed on the map with a marker if visible with the current filtering settings.

Crime records can be edited by navigating to the table screen, clicking a record, clicking the edit button, making some changes in the popup, then clicking confirm.

Crime records can be deleted by navigating to the table screen, clicking a record, then clicking delete. The crime record will then disappear from the table.

### Filtering Records

The crime records displayed in the table and on the map can be filtered by many attributes using the filtering panel on the left. Filtering criteria can be typed into the fields or selected using the buttons displayed in the panel.

The table and map will update as you input filtering criteria if real-time updating is ticked. This option can be unticked if it’s causing performance issues. When it’s unticked, click the map it button on the map screen or the refresh button on the table screen to apply the filtering criteria.
