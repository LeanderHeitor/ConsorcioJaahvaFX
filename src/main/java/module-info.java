module com.example.consorciojaahvafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.consorciojaahvafx to javafx.fxml;
    exports com.example.consorciojaahvafx;
}