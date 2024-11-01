import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Start fönstret för att ta emot spelarens namn
public class StartFrame extends JFrame {

    private JTextField nameField; // Textfält för att mata in spelarens namn

    // Konstruktor som konfigurerar startfönstret
    public StartFrame() {
        setTitle("Number Puzzle");
        ImageIcon puzzle = new ImageIcon("src/resources/puzzle.png");
        setIconImage(puzzle.getImage());
        setSize(300, 150);
        setResizable(false);
        setFocusable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);

        // Etikett och textfält för att mata in spelarens namn
        JLabel label = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        JButton playButton = new JButton("PLAY");

        // Konfigurering av utseendet för play-knappen
        playButton.setFocusable(false);
        playButton.setFont(new Font("Comic Sans", Font.BOLD, 20));
        playButton.setBackground(Color.lightGray);
        playButton.setForeground(Color.white);

        // Konfigurering av etiketten
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        label.setForeground(Color.black);
        label.setBackground(Color.lightGray);

        // ActionListener för play-knappen som startar spelet när knappen klickas
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // KeyListener för att starta spelet när Enter-tangenten trycks i nameField
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startGame();
                }
            }
        });

        // Lägg till komponenter i fönstret
        add(label);
        add(nameField);
        add(playButton);
        setVisible(true);
    }

    // Metod som startar spelet och stänger startfönstret
    private void startGame() {
        String playerName = nameField.getText();
        new GameFrame(playerName);
        dispose(); // Stänger startfönstret
    }
}
