
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author Filippo-TheAppExpert
 * <p>
 * Screen.java is the abstraction of all screen example
 * {@link DialScreen}, {@link MobileScreen}</p>
 */
public abstract class Screen extends JPanel implements ScreenHelper, MouseListener {

    private final SoundManager soundManager;
    protected String telephoneNumber;

    public Screen() {
        this.soundManager = SoundManager.getSoundManager();
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Font font = new Font("Century Gothic", Font.PLAIN, 12);
        g2D.setFont(font);
        onDraw(g2D);
    }

    protected void onDraw(Graphics2D g2D) {

    }

    /**
     * This method takes a sound path and pass it to the {@link SoundManager} to
     * play it.
     *
     * @param sound the path of the sound
     */
    protected void playSound(String sound) {
        if (soundManager == null) {
            System.out.println("Manager is null");
            return;
        }
        //soundManager.stopSound();
        soundManager.playSound(sound);
    }

    /**
     * This method is used to stop the current playing sound
     */
    protected void stopSound() {
        soundManager.stopSound();
    }

    /**
     * This method is used to display the current time in the
     * {@link StatusScreen}
     *
     * @return
     */
    protected String getDisplayTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    protected void decreaseSound() {
        this.soundManager.decreaseSound();
    }

    protected void increaseSound() {
        this.soundManager.increaseSound();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    protected Point getCenterOfTheScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point(screenSize.width / 2, screenSize.height / 2);
    }

    protected void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
