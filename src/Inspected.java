import java.util.ArrayList;

public class Inspected {
    private int index;
    private ArrayList<String> suggestions;

    public Inspected(){
        super();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void addSuggestion(String str){
        suggestions.add(str);
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }
}
