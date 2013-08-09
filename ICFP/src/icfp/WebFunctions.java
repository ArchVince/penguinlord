/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;
import java.net.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import java.io.OutputStream;
/**
 *
 * @author marcus
 */


public class WebFunctions {
    public static ReadProblem[] myproblems(String key) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL("http://icfpc2013.cloudapp.net/myproblems?auth=" + key);
        ReadProblem[] problemlist = mapper.readValue(url.openStream(), ReadProblem[].class);
        return problemlist;
    }
    public static EvalResponse evalbyID(String ID, String[] arguments, String key) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        EvalIDRequest req = new EvalIDRequest(ID,arguments);
        String rawData = "" + mapper.writeValueAsString(req);
        URL url = new URL("http://icfpc2013.cloudapp.net/eval?auth=" + key);
        String type = "application/x-www-form-urlencoded";
        String encodedData = URLEncoder.encode( rawData ); 
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", type );
        conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
        OutputStream os = conn.getOutputStream();
        os.write( rawData.getBytes() );
        os.close();
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) 
            return mapper.readValue(conn.getInputStream(),EvalResponse.class);
        else {System.out.println("Error: " + responseCode); return null;}
    }
    public static EvalResponse evalbyProgram(String program, String[] arguments, String key) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        EvalProgramRequest req = new EvalProgramRequest(program,arguments);
        String rawData = "" + mapper.writeValueAsString(req);
        URL url = new URL("http://icfpc2013.cloudapp.net/eval?auth=" + key);
        String type = "application/x-www-form-urlencoded";
        String encodedData = URLEncoder.encode( rawData ); 
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", type );
        conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
        OutputStream os = conn.getOutputStream();
        os.write( rawData.getBytes() );
        os.close();
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) 
            return mapper.readValue(conn.getInputStream(),EvalResponse.class);
        else {System.out.println("Error: " + responseCode); return null;}
    }
}
