import java.util.ArrayList;

/**
 * This class is for handling all words in a certain String.
 * @author 404 Not Found
 * @verison 0.2
 */

public class Word {
    private int index;
    private ArrayList<String> suggestions = new ArrayList<>();
    private String orig;
    private boolean isWrong = false;

    /**
     * Constructors the Word class.
     */
    public Word(){
        super();
    }

    /**
     * This is method is for setting the index of the word.
     * @param index index of the word
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * this is method is for adding suggections
     * @param str suggestions of the word
     */
    public void addSuggestion(String str){
        suggestions.add(str.replace('-',' '));
    }

    /**
     * This method is for getting the index of the word
     * @return index of the word
     */
    public int getIndex() {
        return index;
    }

    /**
     * This method is for getting the suggestion list
     * @return suggestion ArrayList
     */
    public ArrayList<String> getSuggestions() {
        return suggestions;
    }

    /**
     * This method is for getting the original of the word
     * @return orig original version of the word
     */
    public String getOrig() {
        return orig;
    }

    /**
     * This method is for setting original
     * @param orig word
     */
    public void setOrig(String orig) {
        this.orig = orig;
    }

    /**
     * This method is for checking whether this word is correctly or not
     * return boolean
     */
    public boolean isWrong() {
        return isWrong;
    }

    /**
     * This method is for updating the status of the word
     * @param wrong status of the word. Misspelled or not.
     */
    public void setWrong(boolean wrong) {
        this.isWrong = wrong;
    }
}
