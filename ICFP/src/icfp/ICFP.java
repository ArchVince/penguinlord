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
            //Will need to change this appropriately
            System.out.println(WebFunctions.Guess("vKVGtougUz1LdHaURvYhv7WX","(lambda (x_1027) (fold x_1027 0 (lambda (x_1027 x_1028) (or 1 x_1027))))", key).getstatus());
        } catch (Exception e) {e.printStackTrace();}
    }
}
