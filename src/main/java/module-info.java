module com.example.programming_cw_final {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.programming_cw_final to javafx.fxml;
    exports com.example.programming_cw_final;
}