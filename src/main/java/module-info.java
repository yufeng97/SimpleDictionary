module unimelb.comp90015.simpledictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens unimelb.comp90015.simpledictionary to javafx.fxml;
    exports unimelb.comp90015.simpledictionary;
}