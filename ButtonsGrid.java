/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author felipo.ash
 * <p>
 * ButtonsGrid.java is responsible for creating the grid of numerical
 * buttons</p>
 */
public class ButtonsGrid extends Screen {

    private KeypadClickListener listener;
    private String subKeys[] = {"", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ", "", "+", ""};

    /**
     * This constructor takes each keypad number text as parameter and assign
     * the value to the iterating grid Similarly number field is the text area
     * for the input numbers
     *
     * @param listener
     */
    public ButtonsGrid(KeypadClickListener listener) {
        super();
        this.setOpaque(false);
        setLayout(new GridLayout(4, 3, 5, 5));

        this.listener = listener;

        String keyPads[] = listener.getKeyPads();

        for (int i = 0; i < keyPads.length; i++) {
            String keyPad = keyPads[i];
            String subKey = subKeys[i];
            add(getButton(keyPad, subKey));
        }
    }

    /**
     * This method is responsible for creating a single number keypad with
     * different style having a Mouse click listener interface
     * {@link MouseListener}
     *
     * @param txt
     * @return
     */
    private KeypadView getButton(String txt, String subKey) {

        KeypadView view = new KeypadView(new javax.swing.ImageIcon("images/keypad_normal.png"),
                txt,
                subKey);
        view.addMouseListener(ButtonsGrid.this);
        return view;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/keypad_hover.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setFont(new java.awt.Font("Century Gothic", Font.PLAIN, 25));
            l.setIcon(new javax.swing.ImageIcon("images/keypad_normal.png"));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        listener.onEnableClearPanel(true);
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setFont(new java.awt.Font("Century Gothic", Font.BOLD, 25));
            l.setIcon(new javax.swing.ImageIcon("images/keypad_pressed.png"));
            handleKey(l.getText());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setFont(new java.awt.Font("Century Gothic", Font.PLAIN, 25));
            l.setIcon(new javax.swing.ImageIcon("images/keypad_normal.png"));
        }

        if (!listener.getNumberField().getText().isEmpty()) {
            listener.onEnableClearPanel(true);
        } else {
            listener.onEnableClearPanel(false);
        }
    }

    /**
     * This method is responsible for handling each keypad click. It plays the
     * specific keypad tone
     *
     * @param text the keypad text
     */
    private void handleKey(String text) {
        listener.getNumberField().setText(listener.getNumberField().getText() + text);
        switch (text) {
            case "0":
                playSound("sounds/tone0.wav");
                break;
            case "1":
                playSound("sounds/tone1.wav");
                break;
            case "2":
                playSound("sounds/tone2.wav");
                break;
            case "3":
                playSound("sounds/tone3.wav");
                break;
            case "4":
                playSound("sounds/tone4.wav");
                break;
            case "5":
                playSound("sounds/tone5.wav");
                break;
            case "6":
                playSound("sounds/tone6.wav");
                break;
            case "7":
                playSound("sounds/tone7.wav");
                break;
            case "8":
                playSound("sounds/tone8.wav");
                break;
            case "9":
                playSound("sounds/tone9.wav");
                break;
            case "#":
                playSound("sounds/hash.wav");
                break;
            case "*":
                playSound("sounds/star.wav");
                break;
        }
    }
}
