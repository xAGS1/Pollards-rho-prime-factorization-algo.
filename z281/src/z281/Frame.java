package z281;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {
    private final JTextField inputField;
    private final JButton findFactorsButton;
    private final JLabel resultLabel;

    public Frame() {
        inputField = new JTextField(10);
        findFactorsButton = new JButton("Find Prime Factors");
        resultLabel = new JLabel("Enter a number and press the button");

        //  resultLabel styles setting
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Change font, style, and size
        resultLabel.setForeground(Color.BLUE); // text color
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        resultLabel.setPreferredSize(new Dimension(350, 30)); 
        resultLabel.setOpaque(true); // Make the label background visible
        resultLabel.setBackground(new Color(230, 230, 250)); 
        //=======================================================

        //  components of the frame
        add(new JLabel("Number:"));
        add(inputField);
        add(findFactorsButton);
        add(resultLabel);

        findFactorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Factorization();
            }
        });
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Factorization();
                }
            }
        });
        // Frame Settings
        setTitle("Prime Factorization");
        setBounds(400, 200, 400, 200); // Size and position of frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    private void Factorization() {
        resultLabel.setText(""); // Clear previous result
        PrimeFact.factors.clear(); // Clear previous factors

        String inputText = inputField.getText().trim(); // Get user input as string
        try {
            long number = Long.parseLong(inputText);  // Change to long parsing
            if (number <= 1) {
                resultLabel.setForeground(Color.RED); // Set text color to red for error
                resultLabel.setText("Please enter a number greater than 1.");
                return;
            }

            // factors using PrimeFact class (Our method)
            PrimeFact.primeFact(number);

            // result text
            StringBuilder result = new StringBuilder("Prime factors: ");
            for (int i = 0; i < PrimeFact.factors.size(); i++) {
                result.append(PrimeFact.factors.get(i));
                if (i < PrimeFact.factors.size() - 1) {
                    result.append(", ");
                }
            }

            // display result
            resultLabel.setForeground(new Color(0, 128, 0)); // Set color to green for success
            resultLabel.setText(result.toString());

        } catch (NumberFormatException ex) {
            resultLabel.setForeground(Color.RED); // Set text color to red for error
            resultLabel.setText("Invalid input. Please enter a valid integer.");
        }
    }

}

