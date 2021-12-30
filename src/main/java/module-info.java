module unimelb.comp90015.simpledictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;
    requires com.google.gson;


    opens unimelb.comp90015.simpledictionary to javafx.fxml;
    exports unimelb.comp90015.simpledictionary;
}