module com.example.projekt_sew_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekt_sew_3 to javafx.fxml;
    exports com.example.projekt_sew_3;
}