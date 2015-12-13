/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.awt.*;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class MobileScreen extends Screen implements ScreenChangeListener {

    private Screen statusScreen, dialScreen, callingScreen;

    public MobileScreen() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        initScreens();
        add(this.statusScreen);
        add(this.dialScreen);
    }

    @Override
    public void onScreenChange(Screen screen, String telephoneNumber) {

        if (screen instanceof DialScreen) {
            this.dialScreen = screen;
            remove(this.callingScreen);
        } else if (screen instanceof CallingScreen) {
            this.callingScreen = screen;
            this.callingScreen.setTelephoneNumber(telephoneNumber);
            remove(this.dialScreen);
        }
        
        add(screen);
        invalidate();
        validate();
        repaint();
    }

    private void initScreens() {
        this.statusScreen = new StatusScreen();
        this.callingScreen = new CallingScreen(MobileScreen.this);
        this.dialScreen = new DialScreen(MobileScreen.this);
    }
}
