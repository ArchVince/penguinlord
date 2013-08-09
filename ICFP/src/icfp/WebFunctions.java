/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;
import java.net.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import java.util.List;
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
}
