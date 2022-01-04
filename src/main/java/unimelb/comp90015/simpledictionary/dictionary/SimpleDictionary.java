package unimelb.comp90015.simpledictionary.dictionary;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimpleDictionary implements Dictionary {
    private final String dictionaryFilePath;
    private JSONObject dictionary;;
    public SimpleDictionary(String dictionaryFilePath) {
        this.dictionaryFilePath = dictionaryFilePath;
        parseJson();
    }

    private void parseJson() {
        try {
            String content = new Scanner(new File(dictionaryFilePath)).useDelimiter("\\Z").next();
            dictionary = new JSONObject(content);
            System.out.println(dictionary);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the dictionary file. Please check the file path");
            System.exit(1);
        } catch (JSONException e) {
            System.out.println("Fail to parse dictionary file. Please check the dictionary file and make sure is json format.");
            System.exit(1);
        }
    }

    @Override
    public String query(String word) {
        return dictionary.optString(word);
    }

    @Override
    public boolean add(String word, String description) {
        if (dictionary.has(word)) {
            return false;
        }
        dictionary.put(word, description);
        return true;
    }

    @Override
    public boolean remove(String word) {
        if (!dictionary.has(word)) {
            return false;
        }
        dictionary.remove(word);
        return true;
    }

    @Override
    public boolean update(String word, String description) {
        if (!dictionary.has(word)) {
            return false;
        }
        dictionary.put(word, description);
        return true;
    }
}
