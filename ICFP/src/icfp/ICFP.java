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
            WebFunctions.myproblems(key);
        } catch (Exception e) {System.out.println(e);}
    }
}
