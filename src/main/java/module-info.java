module unimelb.comp90015.simpledictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens unimelb.comp90015.simpledictionary to javafx.fxml;
    exports unimelb.comp90015.simpledictionary;
    exports unimelb.comp90015.simpledictionary.client;
    opens unimelb.comp90015.simpledictionary.client to javafx.fxml;
    exports unimelb.comp90015.simpledictionary.util;
    opens unimelb.comp90015.simpledictionary.util to javafx.fxml;
}