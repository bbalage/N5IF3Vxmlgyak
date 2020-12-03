package main;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ObjectN5IF3V {

    public static void main(String[] args){
        JSONParser jsonParser = new JSONParser();
        try{
            FileReader reader = new FileReader("JSONN5IF3V.json");
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.get("name"));
        }
        catch(Exception exc){
            exc.printStackTrace();
        }

    }

}
