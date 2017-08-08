package utgenerator.wrk;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 * Worker for generating the unit tests for only one parameter.
 *
 * @author Guduche
 */
public class WrkOneParameter extends WrkParameters {

    public WrkOneParameter() {
        super();
    }

    /**
     * Asks the results.
     */
    @Override
    protected void askResults() {
        for (int i = 0; i < firstParameter.getNumberOfPossibilities(); i++) {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Give the result");
            dialog.setHeaderText(firstParameter.getPossibilities(i));
            dialog.setContentText("Result:");
            Optional<String> result = dialog.showAndWait();
            results.add(result.get());
        }
    }

    /**
     * Add all the comments part.
     */
    @Override
    protected void addComments() {
        //Add the header of the table in comments 
        ut = "//  ------------------------------------------------------------------------\n    //        Parameter\n    //   Num    1";
        for (int i = 0; i < firstParameter.getBiggestPossibilityLength() - 1; i++) {
            ut += " ";
        }
        ut += "Result\n    //  ------------------------------------------------------------------------\n";

        //Add the table in comments
        counter = 1;
        for (int i = 0; i < firstParameter.getNumberOfPossibilities(); i++) {
            if (counter < 10) {
                ut += "    //   0" + counter + "     ";
            } else {
                ut += "    //   " + counter + "     ";
            }
            ut += firstParameter.getFormatPossibilities(i) + results.get(counter - 1) + "\n";
            counter++;
        }
    }

    /**
     * Add the part between the comments and the tests.
     */
    @Override
    protected void addPartBetweenCommentsAndTests() {
        //Add the beginning of the method
        ut += "    /**\n    * Test of " + txtMethod + " method, of class " + txtClass + ".\n    */\n    @Test\n    public void test" + stringManipulation.firstLetterToUpperCase(txtMethod) + "() {\n        " + txtClass + " instance = new " + txtClass + "();\n";

        //Check if there is an ArrayList in the possibilities of the first parameter
        if (isFirstParameterAL) {
            ut += "        ArrayList<" + firstParameterALType + "> alFirstParameter = new ArrayList<>();\n";
        }

        //Check if there is an ArrayList in the results
        if (isResultAL) {
            ut += "        ArrayList<" + resultALType + "> resultats;\n";
        }
    }

    /**
     * Add the tests
     */
    @Override
    protected void addTests() {
        counter = 1;
        for (int i = 0; i < firstParameter.getNumberOfPossibilities(); i++) {
            if (counter < 10) {
                ut += "        //   0" + counter + "     ";
            } else {
                ut += "        //   " + counter + "     ";
            }
            ut += firstParameter.getFormatPossibilities(i) + results.get(counter - 1) + "\n";

            //Declare, initialize and fill the ArrayList if it's an ArrayList
            String thisPossibility = firstParameter.getPossibilities(i);
            if (thisPossibility.charAt(0) == '[') {
                ut += "        alFirstParameter.clear();\n";
                thisPossibility = thisPossibility.substring(1, thisPossibility.length() - 1);
                String[] alThisPossibilityContent = thisPossibility.split(",");
                for (int k = 0; k < alThisPossibilityContent.length; k++) {
                    if (!"".equals(alThisPossibilityContent[k])) {
                        ut += "        alFirstParameter.add(" + alThisPossibilityContent[k] + ");\n";
                    }
                }
            }
            if (results != null && !"".equals(results.get(counter - 1))) {
                if (results.get(counter - 1).charAt(0) == '[') {
                    //Put the name of the ArrayList or the possibility
                    ut += "        resultat = instance." + txtMethod + "(";
                    if (firstParameter.getPossibilities(i).charAt(0) == '[') {
                        ut += "alFirstParameter";
                    } else {
                        ut += firstParameter.getPossibilities(i);
                    }

                    String tmp1 = results.get(counter - 1);
                    String tmp2 = tmp1.substring(1, tmp1.length() - 1);
                    String[] alResultsContent = tmp2.split(",");
                    ut += ");\n        assertNotNull(resultat);\n        assertTrue(resultat.size()==" + alResultsContent.length + ");\n";
                    for (int k = 0; k < alResultsContent.length; k++) {
                        //If the result is true, false or null, use the appropriate methode
                        if (alResultsContent[k].equals("true")) {
                            ut += "        assertTrue(";
                        } else if (alResultsContent[k].equals("false")) {
                            ut += "        assertFalse(";
                        } else if (alResultsContent[k].equals("null")) {
                            ut += "        assertNull(";
                        } else {
                            ut += "        assertEquals(" + alResultsContent[k] + ", ";
                        }
                        ut += "resultat.get(" + k + "));\n";
                    }
                } else {
                    //If the result is true, false or null, use the appropriate methode
                    if (results.get(counter - 1).equals("true")) {
                        ut += "        assertTrue(";
                    } else if (results.get(counter - 1).equals("false")) {
                        ut += "        assertFalse(";
                    } else if (results.get(counter - 1).equals("null")) {
                        ut += "        assertNull(";
                    } else if (results.get(counter - 1).charAt(0) == '[') {
                        ut += "        assertEquals(tabResultat, ";
                    } else {
                        ut += "        assertEquals(" + results.get(counter - 1) + ", ";
                    }
                    ut += "instance." + txtMethod + "(";

                    //Put the name of the ArrayList or the possibility
                    if (firstParameter.getPossibilities(i).charAt(0) == '[') {
                        ut += "alFirstParameter";
                    } else {
                        ut += firstParameter.getPossibilities(i);
                    }

                    ut += "));\n";
                }
            }

            counter++;
        }
    }

    /**
     * Geneate the unit tests for one parameter.
     *
     * @return the character string containing the unit tests
     */
    @Override
    public String generateUT() {
        results.clear();
        ut = "";
        askResults();
        addComments();
        addPartBetweenCommentsAndTests();
        addTests();
        ut += "    }";
        return ut;
    }
}
