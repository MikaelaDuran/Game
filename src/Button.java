import javax.swing.*;
import java.awt.*;

// Klass för att skapa knapparna i spelet
public class Button extends JButton {
    public Button(String text) {
        super(text);
        setBackground(Color.gray);
        setForeground(Color.WHITE);
        setFont(new Font("Comic Sans", Font.BOLD, 24));
        setFocusable(false);
        setFocusPainted(false);
    }
}
