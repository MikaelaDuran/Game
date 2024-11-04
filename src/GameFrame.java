import javax.swing.*;
import java.awt.*;

// Fönstret för själva spelet
public class GameFrame extends JFrame {

    // Variabler
    private NumberPuzzleGame puzzleGame;
    private JLabel timerLabel;
    private JLabel moveCountLabel;

    // Konstruktor som skapar fönstret och dess komponenter
    public GameFrame(String playerName) {
        setTitle("Number Puzzle");
        ImageIcon puzzle = new ImageIcon("src/resources/puzzle.png");
        setIconImage(puzzle.getImage());
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Etiketter för tid och drag
        timerLabel = new JLabel("Time: 00:00");
        moveCountLabel = new JLabel("Moves: 0");

        // Skapar spelet och lägger det i mitten
        puzzleGame = new NumberPuzzleGame(playerName, timerLabel, moveCountLabel);
        add(puzzleGame, BorderLayout.CENTER);

        // Skapar en panel för att hålla knappar högst upp i fönstret
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        // Skapar tre knappar
        JButton restartButton = new JButton("Restart");
        JButton exitButton = new JButton("Exit");
        JButton cheatButton = new JButton("Cheat");

        // Anpassar knapparnas utseende
        setupButton(restartButton);
        setupButton(exitButton);
        setupButton(cheatButton);

        // Funktionalitet för omstartsknappen
        restartButton.addActionListener(e -> puzzleGame.resetGame());

        // Funktionalitet för avslutningsknappen
        exitButton.addActionListener(e -> {
            ImageIcon exit = new ImageIcon(new ImageIcon("src/resources/exit.png").
                    getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

            String[] respons = {"Hell yeah!", "No, absolutely not"};
            int response = JOptionPane.showOptionDialog (
                    this, "Are you sure you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, exit, respons, 0);

            if (response == JOptionPane.YES_OPTION) {
                System.exit(0); // Stänger programmet om användaren bekräftar
            }
        });

        // Funktionalitet för fuskknappen
        cheatButton.addActionListener(e -> puzzleGame.cheat());

        // Lägger till knapparna och etiketter på topPanel
        topPanel.add(restartButton);
        topPanel.add(cheatButton);
        topPanel.add(exitButton);
        topPanel.add(moveCountLabel);
        topPanel.add(timerLabel);
        add(topPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    // Metod för att anpassa utseendet på knappar
    private void setupButton(JButton button) {
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Comic Sans", Font.BOLD, 12));
        button.setBackground(Color.lightGray);
        button.setForeground(Color.darkGray);
    }
}