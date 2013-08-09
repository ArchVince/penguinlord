/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;

/**
 *
 * @author marcus
 */
public class EvalProgramRequest {
     private String _program;
    private String[] _arguments;
    
    public EvalProgramRequest(String i, String[] s) {
        _program=i;
        _arguments=s;
    }
    
    public String getprogram() { return _program; }
    public String[] getarguments() { return _arguments; }
    
    public void setprogram(String i) { _program = i; }
    public void setarguments(String[] s) { _arguments = s; }
}
