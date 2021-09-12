package seng202.team8.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import seng202.team8.model.CrimeRecord;
import seng202.team8.view.StartGUI;

class TableControllerTest {
	
	
	// TODO
	@Test
	public void testGetCrimeRecordData() {
        TableController tableCon = new TableController();
        //tableCon.openTable();
        TableView<CrimeRecord> crimes = tableCon.getTable();
        CrimeRecord loitering = new CrimeRecord("JE266628", 6, 15, 2021,
				"09:30:00 AM", "080XX S DREXEL AVE" , "0820",
				"OTHER OFFENSE", "LOITERING", "STREET",
				0, 0, 631, 8, "06",
				41.748486365f, (float) -87.602675062);
		ObservableList<CrimeRecord> observableRecords = FXCollections.observableArrayList();
		observableRecords.add(loitering);
		crimes.setItems(observableRecords);
		System.out.println(crimes.getItems().get(0));
		assertEquals(0, 0);
	}

}
