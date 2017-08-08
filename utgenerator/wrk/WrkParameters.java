package utgenerator.wrk;

import java.util.ArrayList;
import utgenerator.bean.Parameter;

/**
 * Worker for generating the unit tests.
 *
 * @author Guduche
 */
public abstract class WrkParameters {

    public WrkParameters() {
        stringManipulation = new WrkStringManipulation();
        results = new ArrayList<>();
        ut = "";
        counter = 0;
    }

    public void setFirstParameter(Parameter firstParameter) {
        this.firstParameter = firstParameter;
    }

    public void setTxtMethod(String txtMethod) {
        this.txtMethod = txtMethod;
    }

    public void setTxtClass(String txtClass) {
        this.txtClass = txtClass;
    }

    public void setFirstParameterALType(String firstParameterALType) {
        this.firstParameterALType = firstParameterALType;
    }

    public void setIsFirstParameterAL(boolean isFirstParameterAL) {
        this.isFirstParameterAL = isFirstParameterAL;
    }

    /**
     * Asks the results.
     */
    protected abstract void askResults();

    /**
     * Add all the comments part.
     */
    protected abstract void addComments();

    /**
     * Add the part between the comments and the tests.
     */
    protected abstract void addPartBetweenCommentsAndTests();

    /**
     * Add the tests.
     */
    protected abstract void addTests();

    /**
     * Geneate the unit tests.
     *
     * @return the character string containing the unit tests
     */
    public abstract String generateUT();

    protected Parameter firstParameter;
    protected String txtMethod;
    protected String txtClass;
    protected String firstParameterALType;
    protected String resultALType;
    protected boolean isFirstParameterAL;
    protected boolean isResultAL;
    protected WrkStringManipulation stringManipulation;
    protected ArrayList<String> results;
    protected String ut;
    protected int counter;
}
