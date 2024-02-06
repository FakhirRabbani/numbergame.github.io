import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGame {
    private JFrame frame;
    private JTextField guessTextField;
    private JButton guessButton;
    private JLabel resultLabel;

    private int targetNumber;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new GuessingGame().initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        frame = new JFrame("Guessing Game");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Guess the Number (1-100):");
        titleLabel.setBounds(10, 20, 200, 20);
        frame.getContentPane().add(titleLabel);

        guessTextField = new JTextField();
        guessTextField.setBounds(220, 20, 50, 20);
        frame.getContentPane().add(guessTextField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(280, 20, 80, 20);
        frame.getContentPane().add(guessButton);

        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(10, 60, 300, 20);
        frame.getContentPane().add(resultLabel);

        // Initialize the game
        initializeGame();

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        frame.setVisible(true);
    }

    private void initializeGame() {
        // Generate a random number between 1 and 100
        targetNumber = (int) (Math.random() * 100) + 1;

        // Clear previous results
        resultLabel.setText("Result: ");
        guessTextField.setText("");
        guessTextField.setEditable(true);
    }

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(guessTextField.getText());

            if (guess < 1 || guess > 100) {
                resultLabel.setText("Please enter a number between 1 and 100.");
            } else {
                if (guess == targetNumber) {
                    resultLabel.setText("Congratulations! You guessed the correct number.");
                    guessTextField.setEditable(false);
                } else if (guess < targetNumber) {
                    resultLabel.setText("Too low. Try again.");
                } else {
                    resultLabel.setText("Too high. Try again.");
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }
}
