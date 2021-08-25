module safehousefx {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.swing;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
