import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class implements Bing and WordAPI API's for SpellChecking and Synonym Finding
 * @author 404 Not Found
 * @version 0.2
 */
public class SpellCheckerOnline extends SpellChecker {

    private File keyfile = new File("keys.txt");
    private String keyString;
    Key key;
    private final String AZURE_HOST = "https://api.cognitive.microsoft.com";
    private final String AZURE_PATH = "/bing/v7.0/spellcheck";
    private final String RAPID_HOST = "https://wordsapiv1.p.mashape.com/words/";
    private final String RAPID_KEY = "byjx0jbnsCmshOtPa2nc7z1UqWtOp1dXVg1jsns8hO1eK3QuGn";
    private String language;
    private String mode = "Proof";
    private URL url;
    private HttpsURLConnection connection;
    private ArrayList<Word> wordList;
    private JSONObject json;
    private JSONArray jsonArray;

    /**
     *
     * @return true if the computer is connected, false if not
     * @throws InterruptedException if process gets interrupted
     * @throws IOException if there is something wrong with Google
     */
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

    /**
     * GrammarCheck is done within the spellCheck function
     * @param str String to run on
     * @param lang Language to operate
     * @return always null
     */
    @Override
    public ArrayList<Word> grammarCheck(String str, Language lang) {
        return null;
    }

    /**
     * SpellChecker that uses Bing SpellChecking API
     * @param str String to run on
     * @param lang Language to operate
     * @return ArrayList of word objects
     * @throws Exception if there is something wrong
     */
    @Override
    public ArrayList<Word> spellCheck(String str, Language lang) throws Exception{
        wordList = new ArrayList<Word>();
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
        System.out.println(jsonLine);
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

    /**
     * Synonym Finder that uses WordsAPI
     * @param str String to find synonyms of
     * @param lang language to search
     * @return ArrayList of Word objects
     */
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

    /**
     * Initializes Key for the Bing API
     * @throws IOException if the key.txt is not accessible
     */
    public void initializeKey() throws IOException{
        key = new Key(keyfile);
        keyString = key.getKeyString();
    }

    /**
     * Sets language of the SpellChecker
     * @param lang Language, supports only ENGLISH and TURKISH
     * @return Language String
     */
    private String setLanguage(Language lang){
        if(lang == Language.ENGLISH){
            mode = "proof";
            return "en-us";
        }
        else{
            mode = "spell";
            return "tr";

        }
    }


}
