package unimelb.comp90015.simpledictionary.client;

import javafx.fxml.FXML;

public class RemoveWordController extends AbstractController {

    @FXML
    protected void onRemoveBtnClick() {

    }

    private String makeDeleteRequest(String word) {
        return makeRequest("delete", word);
    }
}
