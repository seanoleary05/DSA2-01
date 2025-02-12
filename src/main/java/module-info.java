module org.example.dsa02ca1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.dsa02ca1 to javafx.fxml;
    exports org.example.dsa02ca1;
}