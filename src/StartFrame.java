import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartFrame extends JFrame {
    private JTextField nameField;


    public StartFrame() {
        setTitle("Number Puzzle");
        ImageIcon puzzle = new ImageIcon("\"src/resources/puzzle.png\"");
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

        playButton.setFocusable(false);
        playButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        playButton.setBackground(Color.lightGray);
        playButton.setForeground(Color.white);

        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        label.setForeground(Color.black);
        label.setBackground(Color.lightGray);

        // ActionListener för playButton
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // KeyListener för nameField för att hantera Enter-tangenten
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startGame();
                }
            }
        });

        add(label);
        add(nameField);
        add(playButton);
        setVisible(true);
    }

    // Metod för att starta spelet
    private void startGame() {
        String playerName = nameField.getText();
        new GameFrame(playerName);
        dispose(); // Stänger startfönstret
    }
}
