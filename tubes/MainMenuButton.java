import javax.swing.*;
import java.awt.*;

public class MainMenuButton extends JButton {
    MainMenuButton(String text){
        this.setText(text);
        this.setFocusable(false);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFont(new Font("Comic Sans", Font.BOLD, 25));
    }
}
