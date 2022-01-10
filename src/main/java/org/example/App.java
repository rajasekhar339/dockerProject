package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.*;
import java.lang.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App 
{
    public static void main( String[] args ) throws JsonProcessingException {
        System.out.println("Hello World!");

        try {

            FileReader reader = new FileReader("/Users/gguthula/IdeaProjects/test/src/file.json");
            FileReader reader1 = new FileReader("/Users/gguthula/IdeaProjects/test/src/file1.json");

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONObject jsonObject1 = (JSONObject) jsonParser.parse(reader1);

            System.out.println(jsonObject);

            JSONObject addedObj = (JSONObject) jsonObject.get("Added");
            JSONObject newmemObject = (JSONObject) addedObj.get("newmem");
            JSONObject neemObject = (JSONObject) newmemObject.get("IDNew");

            long id = Long.valueOf((String) neemObject.get("id"));
            neemObject.put("id", 809809809);

            neemObject = (JSONObject) newmemObject.put("role","Engineer");

            System.out.println(jsonObject.toString());


            JsonDiff jd = new JsonDiff();
            jd.diff(jsonObject1.toString(), jsonObject.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
