package unimelb.comp90015.simpledictionary.dictionary;

public interface Dictionary {

    String query(String word);

    boolean add(String word, String description);

    boolean remove(String word);

    boolean update(String word, String description);
}
