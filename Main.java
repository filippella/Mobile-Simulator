/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import javax.swing.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
		JFrame mobileFrame = new JFrame("Mobile Frame");
                mobileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mobileFrame.setResizable(false);
                mobileFrame.add(new MobileScreen());
                mobileFrame.pack();
				mobileFrame.setIconImage(new javax.swing.ImageIcon("images/icon.png").getImage());
                mobileFrame.setLocationRelativeTo(null);
                mobileFrame.setVisible(true);
    }
}
