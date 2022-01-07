package unimelb.comp90015.simpledictionary.util;

public enum Error {
    INVALID_CLIENT_ARGUMENT("Invalid client argument", "command line should be in format:\n java –jar DictionaryClient.jar <server-address> <server-port>"),
    INVALID_SERVER_ARGUMENT("Invalid server argument", "command line should be in format:\n java –jar DictionaryServer.jar <port> <dictionary-file>"),
    INVALID_PORT("Invalid port", "Fail to parse port number, please check the parameter you input!"),
    INVALID_DICTIONARY_FORMAT("Fail to parse dictionary file", "The dictionary should be json formatted!"),
    ERROR_CONNECTION("Could not connect to server", "Connection to server failed"),
    ;

    private final String message;
    private final String title;

    private Error(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
