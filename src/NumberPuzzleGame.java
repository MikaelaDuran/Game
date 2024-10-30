import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Logik för spelet och kontroll av vinsten och fuskläge
public class NumberPuzzleGame extends JPanel {

    private Button[] buttons;
    private int emptyIndex;
    private String playerName;
    private int moveCount;
    private Timer timer;
    private int elapsedTime;
    private JLabel timerLabel;
    private JLabel moveCountLabel;


    public NumberPuzzleGame(String playerName, JLabel timerLabel, JLabel moveCountLabel) {
        this.playerName = playerName;
        this.timerLabel = timerLabel;
        this.moveCountLabel = moveCountLabel;
        setLayout(new GridLayout(4, 4));
        buttons = new Button[16];
        moveCount = 0;
        elapsedTime = 0;
        startGame();
        startTimer();
    }
    private void startGame() {
        // Skapar vi en lista med nummer för att slumpa rutorna
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 16; i++) numbers.add(i); // 1 - 15
        numbers.add(0); // Lägg till tom ruta
        Collections.shuffle(numbers); // Slumpa ordningen

        // Skapar vi knappar och lägger till dom i panelen
        for (int i = 0; i < buttons.length; i++) {
            if (numbers.get(i) == 0) {
                buttons[i] = new Button(""); // Tom knapp
                emptyIndex = i;
            } else {
                buttons[i] = new Button(String.valueOf(numbers.get(i)));
            }
            final int index = i; // Index för knappen
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveButton(index); // Flytta knappen om möjligt
                }
            });
            add(buttons[i]);
        }
    }

    // Återställer spelet
    public void resetGame() {


        ImageIcon restart = new ImageIcon(new ImageIcon("src/resources/reset.png").
                getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        String[] respons = {"Yes, please", "Lemme think, no"};
        int response = JOptionPane.showOptionDialog (
                this,
                "Do you want to restart the game?",
                "Restart game",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, restart, respons, 0);

        if (response == JOptionPane.YES_OPTION) {
            removeAll();
            moveCount = 0;
            elapsedTime = 0;
            startGame();
            revalidate();
            repaint();
            timerLabel.setText("Time: 0");
            moveCountLabel.setText("Moves: 0");
        }
    }

    public static void main(String[] args) {

    }

    public void cheat() {
    }

    public void resetGame() {
    }
}
