package unimelb.comp90015.simpledictionary.dictionary;

public class DictionaryFactory {

    private static final DictionaryFactory factory = new DictionaryFactory();

    private DictionaryFactory() {}

    public static DictionaryFactory getInstance() {
        return factory;
    }

    public SimpleDictionary createSimpleDictionary(String dictionaryFilePath) {
        return new SimpleDictionary(dictionaryFilePath);
    }
}
