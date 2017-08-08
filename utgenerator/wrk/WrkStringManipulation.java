/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utgenerator.wrk;

/**
 *
 * @author Guduche
 */
public class WrkStringManipulation {

    public WrkStringManipulation() {
    }
    
    public String firstLetterToUpperCase(String texte) {
        String result = null;
        if (texte != null) {
            if (texte.isEmpty()) {
                result = "";
            } else {
                result = texte.substring(0, 1).toUpperCase() + texte.substring(1);
            }
        }
        return result;
    }

    public String firstLetterToLowerCase(String texte) {
        String result = null;
        if (texte != null) {
            if (texte.isEmpty()) {
                result = "";
            } else {
                result = texte.substring(0, 1).toLowerCase() + texte.substring(1);
            }
        }
        return result;
    }
}
