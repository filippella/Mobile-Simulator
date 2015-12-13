/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author felipo.ash
 */
public class KeypadView extends JLabel {

    private String subTxt;
    
    public KeypadView(ImageIcon icon, String txt, String subTxt) {
        super(icon);
        setFont(new java.awt.Font("Century Gothic", 0, 25));
        setText(txt);
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        this.subTxt = subTxt;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Font font = new Font("Arial", Font.PLAIN, 8);
        g2D.setFont(font);
        FontMetrics metrics = g2D.getFontMetrics(font);
        int x = (80 - metrics.stringWidth(subTxt)) / 2;
        g2D.drawString(subTxt, x, 62);
    }

}
