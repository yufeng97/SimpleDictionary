package unimelb.comp90015.simpledictionary.dictionary;

public interface Dictionary {

    String query(String word) throws WordNotFoundException;

    boolean add(String word, String description) throws DuplicatedWordException;

    boolean remove(String word) throws WordNotFoundException;

    boolean update(String word, String description) throws WordNotFoundException, DuplicatedWordException;
}
