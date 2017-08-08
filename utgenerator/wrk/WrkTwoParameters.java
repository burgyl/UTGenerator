package utgenerator.wrk;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import utgenerator.bean.Parameter;

/**
 * Worker for generating the unit tests for two parameters.
 *
 * @author Guduche
 */
public class WrkTwoParameters extends WrkParameters {

    public WrkTwoParameters() {
        super();
    }

    public void setSecondParameter(Parameter secondParameter) {
        this.secondParameter = secondParameter;
    }

    public void setSecondParameterALType(String secondParameterALType) {
        this.secondParameterALType = secondParameterALType;
    }

    public void setIsSecondParameterAL(boolean isSecondParameterAL) {
        this.isSecondParameterAL = isSecondParameterAL;
    }

    /**
     * Asks the results.
     */
    @Override
    protected void askResults() {
        for (int i = 0; i < firstParameter.getNumberOfPossibilities(); i++) {
            for (int j = 0; j < secondParameter.getNumberOfPossibilities(); j++) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Give the result");
                dialog.setHeaderText(firstParameter.getPossibilities(i) + " | " + secondParameter.getPossibilities(j));
                dialog.setContentText("Result:");
                Optional<String> result = dialog.showAndWait();
                results.add(result.get());
            }
        }
    }

    /**
     * Add all the comments part.
     */
    @Override
    protected void addComments() {
        //Add the header of the table in comments
        String view = "//  ------------------------------------------------------------------------\n    //          Parameter\n    //   Num    1";
        for (int i = 0; i < firstParameter.getBiggestPossibilityLength() - 1; i++) {
            view += " ";
        }
        view += "2";
        for (int i = 0; i < secondParameter.getBiggestPossibilityLength() - 1; i++) {
            view += " ";
        }
        view += "Result\n    //  ------------------------------------------------------------------------\n";

        //Add the table in comments
        counter = 1;
        for (int i = 0; i < firstParameter.getNumberOfPossibilities(); i++) {
            for (int j = 0; j < secondParameter.getNumberOfPossibilities(); j++) {
                if (counter < 10) {
                    view += "    //   0" + counter + "     ";
                } else {
                    view += "    //   " + counter + "     ";
                }
                view += firstParameter.getFormatPossibilities(i) + secondParameter.getFormatPossibilities(j) + results.get(counter - 1) + "\n";
                counter++;
            }
            if (i != (firstParameter.getNumberOfPossibilities() - 1)) {
                view += "    //  ------------------------------------------------------------------------\n";
            }
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

        //Check if there is an ArrayList in the possibilities of the second parameter
        if (isSecondParameterAL) {
            ut += "        ArrayList<" + secondParameterALType + "> alSecondParameter = new ArrayList<>();\n";
        }

        //Check if there is an ArrayList in the results
        if (isResultAL) {
            ut += "        ArrayList<" + resultALType + "> resultat;\n";
        }
    }

    /**
     * Add the tests
     */
    @Override
    protected void addTests() {
        counter = 1;
        for (int i = 0; i < firstParameter.getNumberOfPossibilities(); i++) {
            if (i != 0) {
                ut += "        //  ------------------------------------------------------------------------\n";
            }
            for (int j = 0; j < secondParameter.getNumberOfPossibilities(); j++) {
                if (counter < 10) {
                    ut += "        //   0" + counter + "     ";
                } else {
                    ut += "        //   " + counter + "     ";
                }
                ut += firstParameter.getFormatPossibilities(i) + secondParameter.getFormatPossibilities(j) + results.get(counter - 1) + "\n";

                //Declare, initialize and fill the ArrayList if it's an ArrayList
                String firstPossibility = firstParameter.getPossibilities(i);
                if (firstPossibility.charAt(0) == '[') {
                    ut += "        alFirstParameter.clear();\n";
                    firstPossibility = firstPossibility.substring(1, firstPossibility.length() - 1);
                    String[] alFirstPossibilityContent = firstPossibility.split(",");
                    for (int k = 0; k < alFirstPossibilityContent.length; k++) {
                        if (!"".equals(alFirstPossibilityContent[k])) {
                            ut += "        alFirstParameter.add(" + alFirstPossibilityContent[k] + ");\n";
                        }
                    }
                }

                //Declare, initialize and fill the ArrayList if it's an ArrayList
                String secondPossibility = secondParameter.getPossibilities(j);
                if (secondPossibility.charAt(0) == '[') {
                    ut += "        alSecondParameter.clear();\n";
                    secondPossibility = secondPossibility.substring(1, secondPossibility.length() - 1);
                    String[] alSecondPossibilityContent = secondPossibility.split(",");
                    for (int k = 0; k < alSecondPossibilityContent.length; k++) {
                        if (!"".equals(alSecondPossibilityContent[k])) {
                            ut += "        alSecondParameter.add(" + alSecondPossibilityContent[k] + ");\n";
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
                        ut += ", ";
                        if (secondParameter.getPossibilities(j).charAt(0) == '[') {
                            ut += "alSecondParameter";
                        } else {
                            ut += secondParameter.getPossibilities(j);
                        }

                        String tmp1 = results.get(counter - 1);
                        String tmp2 = tmp1.substring(1, tmp1.length() - 1);
                        String[] alResultsContent = tmp2.split(",");
                        ut += ");\n        assertNotNull(resultat);\n        assertTrue(resultat.size()==" + alResultsContent.length + ");\n";
                        for (int k = 0; k < alResultsContent.length; k++) {
                            ///If the result is true, false or null, use the appropriate methode
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
                        ut += ", ";
                        if (secondParameter.getPossibilities(j).charAt(0) == '[') {
                            ut += "alSecondParameter";
                        } else {
                            ut += secondParameter.getPossibilities(j);
                        }

                        ut += "));\n";
                    }
                }

                counter++;
            }
        }
    }

    /**
     * Geneate the unit tests for two parameters.
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

    private Parameter secondParameter;
    private String secondParameterALType;
    private boolean isSecondParameterAL;
}
