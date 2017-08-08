package utgenerator.bean;

import java.util.ArrayList;

/**
 * This class is a parameter of the method. It contains all of the possibilities
 * for this parameter
 *
 * @author Guduche
 */
public class Parameter {

    /**
     * Constructor of the class Parameter.
     */
    public Parameter() {
        possibilities = new ArrayList<>();
        biggestPossibilityLength = 0;
    }

    /**
     * Add a possibility to the parameter.
     *
     * @param possibilityToAdd the possibility you want to add
     */
    public void addPossibility(String possibilityToAdd) {
        if (!possibilityToAdd.isEmpty()) {
            possibilities.add(possibilityToAdd);
            if (possibilityToAdd.length() + 4 > biggestPossibilityLength) {
                biggestPossibilityLength = possibilityToAdd.length() + 4;
            }
        }
    }

    /**
     * Get a possibility of the parameter.
     *
     * @param index where the possiblity is in the table of possibilities
     * @return the possiblity
     */
    public String getPossibilities(int index) {
        return possibilities.get(index);
    }

    /**
     * Get a possibility formated of the parameter.
     *
     * @param index where the possiblity is in the table of possibilities
     * @return the possiblity formated
     */
    public String getFormatPossibilities(int index) {
        String result = possibilities.get(index);
        int tmp = biggestPossibilityLength - result.length();
        for (int i = 0; i < tmp; i++) {
            result += " ";
        }
        return result;
    }

    /**
     * Get the biggest length of all the possiblities.
     *
     * @return biggest length
     */
    public int getBiggestPossibilityLength() {
        return biggestPossibilityLength;
    }

    /**
     * Get the number of possibilities of this parameter.
     *
     * @return number of possibilities
     */
    public int getNumberOfPossibilities() {
        return possibilities.size();
    }

    private ArrayList<String> possibilities;
    private int biggestPossibilityLength;
}
