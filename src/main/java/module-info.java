module com.example.consorciojaahvafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires kernel;
    requires layout;


    opens com.example.consorciojaahvafx to javafx.fxml;
    exports com.example.consorciojaahvafx;
}