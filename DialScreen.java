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
 * @author Filippo-TheAppExpert
 */
public class DialScreen extends Screen implements KeypadClickListener {

    private final JTextField numberField;
    private final String keyPads[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "0", "*"};
    private final ScreenChangeListener listener;
    private final JPanel clearPanel, keyPadField;

    public DialScreen(ScreenChangeListener listener) {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        this.listener = listener;

        this.numberField = new JTextField();
        this.numberField.setFont(new java.awt.Font("Century Gothic", 0, 22));
        this.numberField.setPreferredSize(new Dimension(SCREEN_WIDTH - 70, 43));
        this.numberField.setEditable(false);
		this.numberField.setBackground(Color.WHITE);

        keyPadField = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        keyPadField.setPreferredSize(new Dimension(SCREEN_WIDTH, 50));
        keyPadField.setBackground(this.numberField.getBackground());
		keyPadField.setBorder(this.numberField.getBorder());
        this.numberField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
		keyPadField.add(this.numberField);
		
        clearPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        clearPanel.setPreferredSize(new Dimension(66, 45));
        clearPanel.setOpaque(false);

        JLabel clearBtn = new JLabel();
        clearBtn.setPreferredSize(new Dimension(56, 45));
        clearBtn.setIcon(new javax.swing.ImageIcon("images/clear.png"));
        clearBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String numbers = numberField.getText();

                if (numbers.isEmpty()) {
                    clearPanel.setVisible(false);
                } else {
                    numberField.setText(numbers.substring(0, numbers.length() - 1));
                    if (numberField.getText().isEmpty()) {
                        clearPanel.setVisible(false);
                    }
                }
            }
        });

        clearPanel.add(clearBtn);
        keyPadField.add(clearPanel);
        clearPanel.setVisible(false);

        add(keyPadField);
        add(new ButtonsGrid(DialScreen.this));
        JLabel label = new JLabel(new javax.swing.ImageIcon("images/call_normal.png"));
        label.addMouseListener(DialScreen.this);
        add(label);
    }

    @Override
    protected void onDraw(Graphics2D g2D) {
        super.onDraw(g2D);
        g2D.setColor(Color.cyan);
        //g2D.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/call_hover.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/call_normal.png"));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/call_pressed.png"));
            if (validateTelephoneNumber()) {
                decreaseSound();
                playSound("sounds/ringing.wav");
                listener.onScreenChange(new CallingScreen(listener), numberField.getText());
            } else {
                JPopupMenu popup = new JPopupMenu();
                JPanel warningPanel = new JPanel(new GridBagLayout());
                warningPanel.setOpaque(false);
                warningPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, 25));
                JLabel warningLabel = new JLabel("Please specify a valid Telephone number!");
                warningLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
                warningLabel.setPreferredSize(new Dimension(SCREEN_WIDTH, 25));
                warningLabel.setForeground(Color.RED.brighter());
                warningLabel.setFont(new java.awt.Font("Century Gothic", 0, 11));
                warningPanel.add(warningLabel);
                popup.add(warningPanel);
                popup.show(this.keyPadField, 0, 45);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/call_normal.png"));
        }
    }

    private boolean validateTelephoneNumber() {
        return !this.numberField.getText().isEmpty();
    }

    @Override
    public String[] getKeyPads() {
        return this.keyPads;
    }

    @Override
    public JTextField getNumberField() {
        return this.numberField;
    }

    @Override
    public JPanel getClearPanel() {
        return this.clearPanel;
    }

    @Override
    public void onEnableClearPanel(boolean status) {
        setClearButtonStatus(status);
    }

    private void setClearButtonStatus(boolean status) {
        this.clearPanel.setVisible(status);
        if (!numberField.getText().isEmpty()) {
            clearPanel.setVisible(true);
        }
    }
}
