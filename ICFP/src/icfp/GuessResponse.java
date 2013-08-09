/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;

/**
 *
 * @author marcus
 */
public class GuessResponse {
    private String _status;
    private String[] _values;
    private String _message;
    private boolean _lightning;
    
    public String getstatus() { return _status; }
    public String[] getvalues() { return _values; }
    public String getmessage() { return _message; }
    public boolean getlightning() { return _lightning; }
    
    public void setstatus(String s) { _status = s; }
    public void setvalues(String[] v) { _values = v; }
    public void setmessage(String m) { _message = m; }
    public void setlightning(boolean b) { _lightning = b; }
}
