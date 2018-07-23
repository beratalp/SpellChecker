import java.io.*;
import java.util.ArrayList;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.*;
import org.json.simple.parser.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class SpellCheckerOnline extends SpellChecker {

    private File keyfile = new File("keys.txt");
    private String keyString;
    Key key;
    private final String AZURE_HOST = "https://api.cognitive.microsoft.com";
    private final String AZURE_PATH = "/bing/v7.0/spellcheck";
    private final String RAPID_HOST = "https://wordsapiv1.p.mashape.com/words/";
    private final String RAPID_KEY = "byjx0jbnsCmshOtPa2nc7z1UqWtOp1dXVg1jsns8hO1eK3QuGn";
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
        url = new URL(AZURE_HOST + AZURE_PATH + params);
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
            word.setWrong(true);
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
        HttpResponse<JsonNode>  response = null;
        HashMap <String, String> headers = new HashMap<>();
        headers.put("accept",  "application/json");
        headers.put("X-Mashape-Key", RAPID_KEY);
        ArrayList<Word> words = new ArrayList<>();
        int iterator = 0;
        while(stringScanner.hasNext()){
            String wordString = stringScanner.next();
            Word word = new Word();
            word.setOrig(wordString);
            word.setIndex(iterator);
            iterator += wordString.length();
            try{
                response = Unirest.get(RAPID_HOST + wordString + "/synonyms").headers(headers).asJson();
            }
            catch (Exception ex){
                //SpellChecker.Error(ex);
            }
            try {
                json = (JSONObject) new JSONParser().parse(response.getBody().toString());
                jsonArray = (JSONArray) json.get("synonyms");
                for(int i = 0; i < jsonArray.size(); i++){
                    word.addSuggestion((String) jsonArray.get(i));
                }

            }
            catch (Exception ex){
            }
            iterator ++;
            words.add(word);
        }
        return words;
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
