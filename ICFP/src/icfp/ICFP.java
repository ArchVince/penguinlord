/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icfp;
/**
 *
 * @author marcus
 */
public class ICFP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String key = Key.getCode();
        try { 
            String[] arguments = {"0x00000000000001", "0xEFFFFFFFFFFFFF"};
            //Obviously change this
            System.out.println(WebFunctions.evalbyProgram("(lambda (x) (shl1 x))",arguments, key).getstatus());
        } catch (Exception e) {e.printStackTrace();}
    }
}
