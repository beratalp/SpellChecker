import java.io.*;
import java.util.ArrayList;
import java.net.*;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.*;
import org.json.simple.parser.*;


public class SpellCheckerOnline extends SpellChecker {

    private File keyfile = new File("keys.txt");
    private String keyString;
    Key key;
    private final String HOST = "https://api.cognitive.microsoft.com";
    private final String PATH = "/bing/v7.0/spellcheck";
    private String language;
    private String mode;
    private URL url;
    private HttpsURLConnection connection;
    private ArrayList<Word> wordList;
    private JSONObject json;
    private JSONArray jsonArray;

    public static boolean isConnectionWorks() throws InterruptedException, IOException {
        String command;
        if (System.getProperty("os.name").startsWith("Windows")){
            command = "ping -n 1 www.google.com";
        }
        else{
            command = "ping -c 1 www.google.com";
        }
        Process p1 = Runtime.getRuntime().exec(command);
        int returnVal = p1.waitFor();
        boolean reachable = (returnVal==0);
        return reachable;
    }

    @Override
    public ArrayList<Word> grammarCheck(String str, Language lang) {
        return null;
    }

    @Override
    public ArrayList<Word> spellCheck(String str, Language lang) throws Exception{
        wordList = new ArrayList<Word>();
        mode = "proof";
        language = setLanguage(lang);
        String params = "?mkt=" + language + "&mode=" + mode;
        url = new URL(HOST + PATH + params);
        initializeKey();
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", "" + str.length() + 5);
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", keyString);
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes("text=" + str);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String jsonLine = "";
        while ((line = in.readLine()) != null){
            jsonLine += line;
        }
        in.close();
        json = (JSONObject) new JSONParser().parse(jsonLine);
        jsonArray = (JSONArray) (json.get("flaggedTokens"));
        for(int i = 0; i < jsonArray.size(); i++){
            Word word = new Word();
            JSONArray suggestions = (JSONArray) ((JSONObject)jsonArray.get(i)).get("suggestions");
            word.setOrig((String) ((JSONObject)jsonArray.get(i)).get("token"));
            word.setIndex(Integer.parseInt((((JSONObject) jsonArray.get(i)).get("offset")).toString()));
            for(int j = 0; j < suggestions.size(); j++){
                word.addSuggestion((String) ((JSONObject) suggestions.get(j)).get("suggestion"));
            }
            wordList.add(word);
        }
        return wordList;
    }

    @Override
    public ArrayList<Word> findSynonyms(String str, Language lang) {
        Scanner stringScanner = new Scanner(str);
        while(stringScanner.hasNext()){

        }
        return null;
    }

    public void initializeKey() throws IOException{
        key = new Key(keyfile);
        keyString = key.getKeyString();
        System.out.println(keyString);
    }

    private String setLanguage(Language lang){
        if(lang == Language.ENGLISH){
            return "en-us";
        }
        else{
            return "tr-TR";
        }
    }


}
