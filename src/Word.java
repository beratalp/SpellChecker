import java.util.ArrayList;

public class Word {
    private int index;
    private ArrayList<String> suggestions = new ArrayList<>();
    private String orig;
    private boolean isWrong = false;

    public Word(){
        super();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void addSuggestion(String str){
        suggestions.add(str.replace('-',' '));
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public boolean isWrong() {
        return isWrong;
    }

    public void setWrong(boolean wrong) {
        isWrong = wrong;
    }
}
