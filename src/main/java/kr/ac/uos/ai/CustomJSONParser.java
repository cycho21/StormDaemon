package kr.ac.uos.ai;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @author Chan Yeon, Cho
 * @version 0.0.1 - SnapShot
 *          on 2016-05-31 enemy
 */

public class CustomJSONParser {

    private HashMap<String, Integer> result;
    private JSONObject jsonObject;
    private JSONParser jsonParser;
    private String currentLine;

    public CustomJSONParser() {
        result = new HashMap<String, Integer>();
        init();
    }

    private void init() {
        jsonParser = new JSONParser();
    }

    public void parse(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            while ((currentLine = bufferedReader.readLine()) != null) {
                parse2data(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // payload { pull_request { head { repo

    public void parse2data(String unparsedString) {
        Object obj = null;

        try {
            obj = jsonParser.parse(unparsedString);
            jsonObject = (JSONObject) obj;
            JSONObject payload = (JSONObject) jsonObject.get("payload");
//            String url = payload.get("url").toString();
            JSONObject pull_request = (JSONObject) payload.get("pull_request");

            if (pull_request != null) {
                JSONObject head = (JSONObject) pull_request.get("head");
                if (head != null) {
                    JSONObject repo = (JSONObject) head.get("repo");
                    if (repo != null) {
                        if(repo.get("language") != null) {
//                            System.out.println(repo.get("language"));
                            mergeMap(repo.get("language").toString());
                        }
                    }
                }
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void mergeMap(String language) {
        if(result.containsKey(language)){
            result.put(language, result.get(language) + 1);
        } else {
            result.put(language, 1);
        }
        System.out.println(result);
    }
}