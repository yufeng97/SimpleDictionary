package unimelb.comp90015.simpledictionary.dictionary;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SimpleDictionary implements Dictionary {
    private String dictionaryFilePath;
    private JSONObject dictionary;;
    public SimpleDictionary(String dictionaryFilePath) {
        this.dictionaryFilePath = dictionaryFilePath;
        parseJson();
    }

    private void parseJson() {
        System.out.println(new File("").getAbsolutePath());
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(dictionaryFilePath)) {
            dictionary = (JSONObject) parser.parse(reader);
            System.out.println(dictionary);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String query(String word) {
        return null;
    }

    @Override
    public boolean add(String word, String description) {
        return false;
    }

    @Override
    public boolean remove(String word) {
        return false;
    }

    @Override
    public boolean update(String word, String description) {
        return false;
    }
}
