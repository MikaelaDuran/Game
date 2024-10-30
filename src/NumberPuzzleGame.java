import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

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
            timerLabel.setText("Time: 00:00");
            moveCountLabel.setText("Moves: 0");
        }
    }

    // Flyttar en knapp om den är bredvid den tomma knappen
    private void moveButton(int index) {
        if (canMove(index)) {
            buttons[emptyIndex].setText(buttons[index].getText());
            buttons[index].setText("");
            emptyIndex = index;
            moveCount++; // Öka dragräknaren
            moveCountLabel.setText("Moves: " + moveCount); // Uppdatera dragantalet

            if (isSolved()) { // Kontrollerar om spelet är löst
                timer.stop();

                ImageIcon happy = new ImageIcon(new ImageIcon("src/resources/smiley.png").
                        getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

                String[] responses = {"Thank you!"};
                JOptionPane.showOptionDialog(this,"Congratulations " + playerName + ", you won!",
                        "Message to the legend winner"
                        ,JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, happy, responses, 0);
            }
        }
    }

    // Kontrollerar om en knapp kan flyttas till en tom ruta
    private boolean canMove(int index) {
        if (index == emptyIndex)
            return false;

        int rowDiff = Math.abs(index / 4 - emptyIndex / 4); // index / 4 ger radpositionen för index
        int colDiff = Math.abs(index % 4 - emptyIndex % 4); // index % 4 ger kolumnpositionen för index

        // Här summeras rowDiff och colDiff. Om summan är 1 innebär det att knappen är precis intill den tomma rutan,
        // antingen i samma rad eller kolumn (men inte diagonalt).
        //Om rowDiff + colDiff == 1 returnerar metoden true, vilket betyder att knappen kan flyttas.
        return (rowDiff + colDiff) == 1;
    }

    // Kontrollerar om spelet är löst
    private boolean isSolved() {
        // Kontrollera om knapparna är i rätt ordning
        for (int i = 0; i < 15; i++) {
            if (!buttons[i].getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return buttons[15].getText().isEmpty(); // Sista knappen ska vara tom
    }
    // Startar timern som räknar upp tiden för varje sekund
    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000; // Öka den förflutna tiden med 1 sekund
                int minutes = (elapsedTime / (1000 * 60)) % 60; // Beräkna minuter
                int seconds = (elapsedTime / 1000) % 60;        // Beräkna sekunder

                // Formatera tiden till mm:ss
                String timeFormatted = String.format("%02d:%02d", minutes, seconds);
                timerLabel.setText("Time: " + timeFormatted); // Uppdatera timerLabel
            }
        });
        timer.start(); // Starta timern
    }

    // Aktiverar "fusk" genom att lösa pusslet för spelaren
    public void cheat() {
        // Återställ knapparna till ett sorterat tillstånd
        for (int i = 0; i < 15; i++) {
            buttons[i].setText(String.valueOf(i + 1));
        }
        buttons[15].setText(""); // Sista knappen ska vara tom
        emptyIndex = 15; // Uppdatera tom index

        ImageIcon cheat = new ImageIcon(new ImageIcon("src/resources/cheat.jpg").
                getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

        String[] respons = {"OMG YES"};
        JOptionPane.showOptionDialog(this,"Cheatmode activated!", "CHEATMODE"
                ,JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, cheat, respons, 0);
    }



    public static void main(String[] args) {


    }
}
