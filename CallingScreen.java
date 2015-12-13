/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author felipo.ash
 */
public class CallingScreen extends Screen {

    private final ScreenChangeListener listener;

    public CallingScreen(ScreenChangeListener listener) {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setLayout(new GridBagLayout());

        this.listener = listener;

        JLabel hangup = new JLabel(new ImageIcon("images/hangup_hover.png"));
        hangup.addMouseListener(CallingScreen.this);
        add(hangup);

        JLabel loud = new JLabel(new ImageIcon("images/loud.png"));
        loud.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                increaseSound();
                playSound("sounds/ringing.wav");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JLabel l = (JLabel) e.getSource();
                l.setIcon(new javax.swing.ImageIcon("images/loud_pressed.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                JLabel l = (JLabel) e.getSource();
                l.setIcon(new javax.swing.ImageIcon("images/loud_pressed.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                JLabel l = (JLabel) e.getSource();
                l.setIcon(new javax.swing.ImageIcon("images/loud.png"));
            }
        });

        add(loud);
    }

    @Override
    protected void onDraw(Graphics2D g2D) {
        super.onDraw(g2D);

        g2D.setFont(new java.awt.Font("Century Gothic", 0, 25));
        g2D.drawString(telephoneNumber, 20, 50);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/hangup.png"));
            playSound("sounds/hangup.wav");
            listener.onScreenChange(new DialScreen(listener), null);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/hangup.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel l = (JLabel) e.getSource();
            l.setIcon(new javax.swing.ImageIcon("images/hangup_hover.png"));
        }
    }
}
