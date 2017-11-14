package utgenerator;

import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utgenerator.bean.Parameter;
import utgenerator.wrk.WrkOneParameter;
import utgenerator.wrk.WrkStringManipulation;
import utgenerator.wrk.WrkTwoParameters;

/**
 * This is the main controller.
 *
 * @author Guduche
 * @version 1.0.1
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField txtMethod;
    @FXML
    private TextField txtFirstParameter1;
    @FXML
    private TextField txtFirstParameter2;
    @FXML
    private TextField txtFirstParameter3;
    @FXML
    private TextField txtFirstParameter4;
    @FXML
    private TextField txtFirstParameter5;
    @FXML
    private TextField txtFirstParameter6;
    @FXML
    private TextField txtFirstParameter7;
    @FXML
    private TextField txtFirstParameter8;
    @FXML
    private TextField txtFirstParameter9;
    @FXML
    private TextField txtSecondParameter1;
    @FXML
    private TextField txtSecondParameter2;
    @FXML
    private TextField txtSecondParameter3;
    @FXML
    private TextField txtSecondParameter4;
    @FXML
    private TextField txtSecondParameter5;
    @FXML
    private TextField txtSecondParameter6;
    @FXML
    private TextField txtSecondParameter7;
    @FXML
    private TextField txtSecondParameter8;
    @FXML
    private TextField txtSecondParameter9;
    @FXML
    private TextArea txtOut;
    @FXML
    private TextField txtClass;
    @FXML
    private Label txtFirstPossiblityAL;
    @FXML
    private Label txtSecondPossiblityAL;
    @FXML
    private CheckBox checkBoxFirstParameterAL;
    @FXML
    private CheckBox checkBoxSecondParameterAL;
    @FXML
    private CheckBox checkBoxResultatAL;
    @FXML
    private TextField txtResultat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Generate the unit tests.
     */
    @FXML
    private void onActionGenerate() {
        //Change the case of the method and the class
        WrkStringManipulation stringManipulation = new WrkStringManipulation();
        txtMethod.setText(stringManipulation.firstLetterToLowerCase(txtMethod.getText()));
        txtClass.setText(stringManipulation.firstLetterToUpperCase(txtClass.getText()));

        //Add all of the possibilities of the first parameter
        Parameter firstParameter = new Parameter();
        firstParameter.addPossibility(txtFirstParameter1.getText());
        firstParameter.addPossibility(txtFirstParameter2.getText());
        firstParameter.addPossibility(txtFirstParameter3.getText());
        firstParameter.addPossibility(txtFirstParameter4.getText());
        firstParameter.addPossibility(txtFirstParameter5.getText());
        firstParameter.addPossibility(txtFirstParameter6.getText());
        firstParameter.addPossibility(txtFirstParameter7.getText());
        firstParameter.addPossibility(txtFirstParameter8.getText());

        //Add all of the possibilities of the first parameter
        Parameter secondParameter = new Parameter();
        secondParameter.addPossibility(txtSecondParameter1.getText());
        secondParameter.addPossibility(txtSecondParameter2.getText());
        secondParameter.addPossibility(txtSecondParameter3.getText());
        secondParameter.addPossibility(txtSecondParameter4.getText());
        secondParameter.addPossibility(txtSecondParameter5.getText());
        secondParameter.addPossibility(txtSecondParameter6.getText());
        secondParameter.addPossibility(txtSecondParameter7.getText());
        secondParameter.addPossibility(txtSecondParameter8.getText());

        String firstParameterALType;
        boolean isFirstParameterAL;
        String secondParameterALType;
        boolean isSecondParameterAL;

        //If there isn't any ArrayList for the first parameter, add the ninth possibility
        if (checkBoxFirstParameterAL.isSelected()) {
            firstParameterALType = txtFirstParameter9.getText();
            isFirstParameterAL = true;
        } else {
            firstParameter.addPossibility(txtFirstParameter9.getText());
            firstParameterALType = "";
            isFirstParameterAL = false;
        }

        //If there isn't any ArrayList for the second parameter, add the ninth possibility
        if (checkBoxSecondParameterAL.isSelected()) {
            secondParameterALType = txtSecondParameter9.getText();
            isSecondParameterAL = true;
        } else {
            secondParameter.addPossibility(txtSecondParameter9.getText());
            secondParameterALType = "";
            isSecondParameterAL = false;
        }

        //Check if there is one or two parameter and call the corresponding worker
        //If there isn't any parameter, display an error
        if (firstParameter.getNumberOfPossibilities() > 0 && secondParameter.getNumberOfPossibilities() > 0) {
            WrkTwoParameters wrk = new WrkTwoParameters();

            wrk.setSecondParameter(secondParameter);
            wrk.setSecondParameterALType(secondParameterALType);
            wrk.setIsSecondParameterAL(isSecondParameterAL);

            wrk.setFirstParameter(firstParameter);
            wrk.setTxtMethod(txtMethod.getText());
            wrk.setTxtClass(txtClass.getText());
            wrk.setFirstParameterALType(firstParameterALType);
            wrk.setIsFirstParameterAL(isFirstParameterAL);
            txtOut.setText(wrk.generateUT());
        } else if (firstParameter.getNumberOfPossibilities() > 0) {
            WrkOneParameter wrk = new WrkOneParameter();

            wrk.setFirstParameter(firstParameter);
            wrk.setTxtMethod(txtMethod.getText());
            wrk.setTxtClass(txtClass.getText());
            wrk.setFirstParameterALType(firstParameterALType);
            wrk.setIsFirstParameterAL(isFirstParameterAL);
            txtOut.setText(wrk.generateUT());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You must enter parameters");
            alert.showAndWait();
        }
    }

    /**
     * Reset all the possibilities of the first parameter
     */
    @FXML
    private void actionRadioButtonFirstParameterVoid() {
        //Reset all the possibilities of the first parameter
        txtFirstParameter1.setText("");
        txtFirstParameter2.setText("");
        txtFirstParameter3.setText("");
        txtFirstParameter4.setText("");
        txtFirstParameter5.setText("");
        txtFirstParameter6.setText("");
        txtFirstParameter7.setText("");
        txtFirstParameter8.setText("");
        txtFirstParameter9.setText("");
    }

    /**
     * Predefine the possibilities of the first parameter for a String
     */
    @FXML
    private void actionRadioButtonFirstParameterString() {
        txtFirstParameter1.setText("null");
        txtFirstParameter2.setText("\"\"");
        txtFirstParameter3.setText("");
        txtFirstParameter4.setText("");
        txtFirstParameter5.setText("");
        txtFirstParameter6.setText("");
        txtFirstParameter7.setText("");
        txtFirstParameter8.setText("");
        txtFirstParameter9.setText("");
    }

    @FXML
    private void actionRadioButtonFirstParameterInt() {
        //Predefine the possibilities of the first parameter for an Integer
        txtFirstParameter1.setText("Integer.MIN_VALUE");
        txtFirstParameter2.setText("Integer.MIN_VALUE + 1");
        txtFirstParameter3.setText("-1");
        txtFirstParameter4.setText("0");
        txtFirstParameter5.setText("1");
        txtFirstParameter6.setText("Integer.MAX_VALUE - 1");
        txtFirstParameter7.setText("Integer.MAX_VALUE");
        txtFirstParameter8.setText("");
        txtFirstParameter9.setText("");
    }

    /**
     * If the checkbox of the first parameter is checked, add the "Contains"
     * label to the last possibility. Else delete the "Contains" label.
     */
    @FXML
    private void actionCheckBoxFirstParameterAL() {
        if (checkBoxFirstParameterAL.isSelected()) {
            txtFirstPossiblityAL.setText("Contains ");
            txtFirstParameter9.setText("");
        } else {
            txtFirstPossiblityAL.setText("");
            txtFirstParameter9.setText("");
        }
    }

    /**
     * Reset all the possibilities of the second parameter
     */
    @FXML
    private void actionRadioButtonSecondParameterVoid() {
        txtSecondParameter1.setText("");
        txtSecondParameter2.setText("");
        txtSecondParameter3.setText("");
        txtSecondParameter4.setText("");
        txtSecondParameter5.setText("");
        txtSecondParameter6.setText("");
        txtSecondParameter7.setText("");
        txtSecondParameter8.setText("");
        txtSecondParameter9.setText("");
    }

    /**
     * Predefine the possibilities of the second parameter for a String
     */
    @FXML
    private void actionRadioButtonSecondParameterString() {
        txtSecondParameter1.setText("null");
        txtSecondParameter2.setText("\"\"");
        txtSecondParameter3.setText("");
        txtSecondParameter4.setText("");
        txtSecondParameter5.setText("");
        txtSecondParameter6.setText("");
        txtSecondParameter7.setText("");
        txtSecondParameter8.setText("");
        txtSecondParameter9.setText("");
    }

    /**
     * Predefine the possibilities of the second parameter for a Integer
     */
    @FXML
    private void actionRadioButtonSecondParameterInt() {
        txtSecondParameter1.setText("Integer.MIN_VALUE");
        txtSecondParameter2.setText("Integer.MIN_VALUE + 1");
        txtSecondParameter3.setText("-1");
        txtSecondParameter4.setText("0");
        txtSecondParameter5.setText("1");
        txtSecondParameter6.setText("Integer.MAX_VALUE - 1");
        txtSecondParameter7.setText("Integer.MAX_VALUE");
        txtSecondParameter8.setText("");
        txtSecondParameter9.setText("");
    }

    /**
     * If the checkbox of the second parameter is checked, add the "Contains"
     * label to the last possibility. Else delete the "Contains" label.
     */
    @FXML
    private void actionCheckBoxSecondParameterAL() {
        if (checkBoxSecondParameterAL.isSelected()) {
            txtSecondPossiblityAL.setText("Contains ");
            txtSecondParameter9.setText("");
        } else {
            txtSecondPossiblityAL.setText("");
            txtSecondParameter9.setText("");
        }
    }

    /**
     * If the checkbox of the result is checked, set to editable the text area
     * for the type of the Arraylist. Else, set it to not editable.
     */
    @FXML
    private void actionCheckBoxResultatAL() {
        if (checkBoxResultatAL.isSelected()) {
            txtResultat.setEditable(true);
        } else {
            txtResultat.setText("");
            txtResultat.setEditable(false);
        }
    }

    /**
     * Display an alert for the about button
     */
    @FXML
    private void onActionAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About...");
        alert.setHeaderText(null);
        alert.setContentText("Author: Guduche\nVersion: 1.0.1");
        alert.getButtonTypes().clear();
        ButtonType boutonOk = new ButtonType("Ok");
        alert.getButtonTypes().add(boutonOk);
        ButtonType boutonGithub = new ButtonType("Github project");
        alert.getButtonTypes().add(boutonGithub);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == boutonGithub) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI("https://github.com/Guduche/UTGenerator"));
            } catch (Exception e) {
            }
        }
    }
}
