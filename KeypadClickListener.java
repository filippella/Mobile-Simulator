/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import javax.swing.*;

/**
 *
 * @author felipo.ash
 */
public interface KeypadClickListener {

    /**
     * This method is responsible to get all the values of the keypad
     * 
     * @return 
     */
    String[] getKeyPads();

    /**
     * This method retrieves the keypad number {@link JTextField}
     * @return 
     */
    JTextField getNumberField();

    /**
     * This method is responsible for getting the clear panel view
     * 
     * @return {@see JPanel}
     */
    JPanel getClearPanel();

    /**
     * This method calls back the visibility status of the clear button
     * Example: we set the values using {@link DialScreen#setClearButtonStatus
     * 
     * @param status true if we need to show it
     */
    void onEnableClearPanel(boolean status);
}
