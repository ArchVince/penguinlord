/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;

/**
 *
 * @author marcus
 */
public class GuessRequest {
    private String _id;
    private String _program;
    
    public GuessRequest(String i, String p) {
        _id=i;
        _program=p;
    }
    
    public String getid() { return _id; }
    public String getprogram() { return _program; }
    
    public void setid(String i) { _id = i; }
    public void setprogram(String p) { _program = p; }
}
