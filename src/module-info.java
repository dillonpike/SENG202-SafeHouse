module safehousefx {
	requires javafx.controls.18.ea.1;
	requires javafx.fxml.18.ea.1;
	requires javafx.graphics.18.ea.1;
	requires javafx.base.18.ea.1;
	requires javafx.swing.18.ea.1;
	requires junit;
	
	opens seng202.team8 to javafx.graphics, javafx.fxml;
	exports seng202.team8.view;
}
