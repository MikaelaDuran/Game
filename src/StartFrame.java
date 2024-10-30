import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    private JTextField nameField;


    public StartFrame() {
        setTitle("Number Puzzle");
        ImageIcon puzzle = new ImageIcon("");
        setIconImage(puzzle.getImage());
        setSize(300, 150);
        setResizable(false);
        setFocusable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);

        JLabel label = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        JButton playButton = new JButton("PLAY");

    }
}