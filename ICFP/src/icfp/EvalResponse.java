/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;

/**
 *
 * @author marcus
 */
public class EvalResponse {
    private String _status;
    private String[] _outputs;
    private String _message;
    
    public String getstatus() { return _status; }
    public String[] getoutputs() { return _outputs; }
    public String getmessage() { return _message; }
    
    public void setstatus(String s) { _status = s; }
    public void setoutputs(String[] o) { _outputs = o; }
    public void setmessage(String m) { _message = m; }
}
