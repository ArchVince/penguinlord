/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;

/**
 *
 * @author marcus
 */
public class EvalIDRequest {
    private String _id;
    private String[] _arguments;
    
    public EvalIDRequest(String i, String[] s) {
        _id=i;
        _arguments=s;
    }
    
    public String getid() { return _id; }
    public String[] getarguments() { return _arguments; }
    
    public void setid(String i) { _id = i; }
    public void setarguments(String[] s) { _arguments = s; }
}
