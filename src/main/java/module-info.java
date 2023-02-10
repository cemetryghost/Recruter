module com.example.kval_ex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.base;
    requires junit;


    opens com.example.kval_ex to javafx.fxml, javafx.base;
    exports com.example.kval_ex;
}