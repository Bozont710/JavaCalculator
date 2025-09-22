import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Calculator {
    /**
     * frame for the app, panel houses the calculator panel and
     * the scrollPane both, scrollPane houses the history panel
     */
    private JFrame frame;
    private JPanel calculatorPanel, historyPanel, panel;
    private JScrollPane scrollPane;
    /**
     * storage for input, output and history, self-explanatory
     */
    private ArrayList<String> input, history;
    private ArrayList<Double> output;
    /**
     * the button labels for automation, and one JLabel and one
     * JButton for simpler implementation
     */
    private String[] labels = {"CA", "DEL", "HIST", "/",
            "7", "8", "9", "*", "4", "5", "6", "-", "1",
            "2", "3", "+", ".", "0", "="};
    private JLabel label;
    private JButton button;
    /**
     * the operators, self-explanatory
     */
    private final String[] ELEMENTS = {"*", "/", "+", "-"};
    private final HashSet<String> OPERATORS = new HashSet<>(Arrays.asList(ELEMENTS));
    /**
     * few booleans to check what to display, lock or what arithmetic
     * operation to perform
     */
    private boolean isDivision = false, isNegative = false,
            lockDecimal = false, isResult = false, showHistory = false;
    /**
     * operator count is responsible so only at most two operators are
     * present after each other in which case the second has to be a
     * negative indicator, n is for the Layout manager, it's complex
     * lets leave it at that
     */
    private int operatorCount = 0, x = 0, y = 0;
    private final int n = 600;
    /**
     * stores the result of an operation
     */
    private double result;
    /**
     * stuff to make things not too ugly, but ugly enough to
     * be obvious that I'm not a front end specialist
     */
    private Font font;
    private Color orange, yellow, gray;
    private MatteBorder orangeBorder, yellowBorder, grayBorder;
    /**
     * the quite complex layout manager mentioned above, works well
     * when you know what you are doing which I don't
     */
    private GridBagLayout layout;
    private GridBagConstraints constraints;

    /**
     * simple calculator, after an operation pressing an operator
     * the previous result will be placed before the operator, the
     * HIST button shows the history in which you can press any
     * previous result, and it will append the result to the current
     * operation if the last element is an operator otherwise starts
     * a new operation with the result as the first element
     */
    Calculator() {
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
        this.history = new ArrayList<>();

        this.frame = new JFrame("Calculator");
        this.frame.setSize(500, 650);
        this.font = new Font(Font.DIALOG, Font.BOLD, 20);

        this.orange = new Color(255, 106, 0);
        this.orangeBorder = new MatteBorder(4, 4, 4, 4, new Color(245, 96, 9));
        this.yellow = new Color(255, 183, 0);
        this.yellowBorder = new MatteBorder(4, 4, 4, 4, new Color(245, 173, 9));
        this.gray = new Color(181, 181, 181);
        this.grayBorder = new MatteBorder(4, 4, 4, 4, new Color(171, 171, 171));

        this.panel = new JPanel();
        this.panel.setSize(1000, 650);
        this.panel.setBackground(Color.BLACK);

        this.calculatorPanel = new JPanel();
        this.calculatorPanel.setBackground(Color.BLACK);
        this.calculatorPanel.setSize(500, 650);

        this.historyPanel = new JPanel();
        this.historyPanel.setBackground(Color.BLACK);
        this.historyPanel.setSize(500, 650);

        this.layout = new GridBagLayout();
        this.constraints = new GridBagConstraints();

        this.calculatorPanel.setLayout(this.layout);

        this.scrollPane = new JScrollPane(this.historyPanel);
        this.scrollPane.setBorder(null);
        this.scrollPane.setPreferredSize(new Dimension(500, 650));


        initCalculator();
        colorButtons();


        this.historyPanel.setLayout(new BoxLayout(this.historyPanel, BoxLayout.PAGE_AXIS));
        this.historyPanel.setBorder(new MatteBorder(10, 10, 10, 10, Color.BLACK));

        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.LINE_AXIS));

        this.panel.add(calculatorPanel);
        this.panel.add(scrollPane);

        this.frame.getContentPane().add(panel);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * places the input and output labels in the calculator, after
     * that it places the buttons in their corresponding space
     */
    private void initCalculator() {
        createLabel();
        this.y += this.n;
        createLabel();
        this.y += this.n;
        int count = 0;
        for (int i = 0; i < this.labels.length; i++) {
            createButton(this.labels[i]);
            this.x += this.n;
            count++;
            if (count == 4) {
                this.x = 0;
                this.y += this.n;
                count = 0;
            }
        }
    }

    /**
     * puts colors and borders on initialized buttons
     */
    private void colorButtons() {
        String lbl;
        for (int i = 2; i < 21; i++) {
            this.button = (JButton) this.calculatorPanel.getComponent(i);
            lbl = this.button.getText();
            switch(lbl) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "0":
                    this.button.setBackground(this.gray);
                    this.button.setBorder(this.grayBorder);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    this.button.setBackground(this.yellow);
                    this.button.setBorder(this.yellowBorder);
                    break;
                case ".":
                    this.button.setBackground(new Color(161,161,161));
                    this.button.setBorder(new MatteBorder(4,4,4,4, new Color(151,151,151)));
                    break;
                default:
                    this.button.setBackground(this.orange);
                    this.button.setBorder(this.orangeBorder);
                    break;
            }
        }
    }

    /**
     * responsible for creating the input and output labels,
     * when "nothing" is displayed in reality it's the text
     * "TEXT" in black so it doesn't show up, it was the simplest
     * way to stop the calculator from collapsing since an empty
     * string disturbs the space and everything gets pressed together
     */
    private void createLabel() {
        this.label = new JLabel("TEXT");
        this.label.setFont(this.font);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.constraints.gridx = this.x;
        this.constraints.gridy = this.y;
        this.constraints.gridwidth = 2000;
        this.constraints.gridheight = 300;
        this.constraints.ipadx = 82;
        this.constraints.ipady = 56;
        this.constraints.fill = GridBagConstraints.HORIZONTAL;


        this.layout.setConstraints(this.label, this.constraints);
        this.calculatorPanel.add(this.label);
    }

    /**
     * responsible for creating a button and setting it's display
     * to the passed string, also calls a method when pressed, at
     * which point their text is sent to the method
     * @param text the text to display
     */
    private void createButton(String text) {
        this.button = new JButton(text);
        this.button.setFont(this.font);
        this.button.setFocusPainted(false);
        if (text.equals("=")) {
            this.constraints.gridwidth = 1000;
        } else {
            this.constraints.gridwidth = 125;
        }
        this.constraints.gridheight = 125;
        this.constraints.ipadx = 82;
        this.constraints.ipady = 56;
        this.constraints.gridx = this.x;
        this.constraints.gridy = this.y;
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealWithButtons(e.getActionCommand());
            }
        });

        this.layout.setConstraints(this.button, this.constraints);
        this.calculatorPanel.add(this.button);
    }

    /**
     * defines which buttons do what, some buttons do the same,
     * so I made them fall through
     * @param str the displayed string of the pressed button
     */
    private void dealWithButtons(String str) {
        switch(str) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                if (this.isResult) {
                    clear();
                }
                this.input.add(str);
                this.operatorCount = 0;
                updateInputLabel();
                break;
            case "+":
            case "/":
            case "*":
                removeOperatorDuplicate();
                if (this.isResult) {
                    clear();
                    this.input.add(String.valueOf(this.result));
                }
                this.input.add(str);
                this.lockDecimal = false;
                this.operatorCount++;
                updateInputLabel();
                break;
            case "-":
                this.lockDecimal = false;
                if (this.isResult) {
                    clear();
                    this.input.add(String.valueOf(this.result));
                }
                dealWithMinus();
                updateInputLabel();
                break;
            case ".":
                if (!this.lockDecimal) {
                    input.add(".");
                }
                this.lockDecimal = true;
                updateInputLabel();
                break;
            case "CA":
                clear();
                break;
            case "DEL":
                deleteLastElement();
                break;
            case "HIST":
                this.showHistory = !this.showHistory;
                if (this.showHistory) {
                    this.frame.setSize(this.frame.getWidth() * 2, this.frame.getHeight());
                } else {
                    this.frame.setSize(this.frame.getWidth() / 2, this.frame.getHeight());
                }
                break;
            case "=":
                evaluate();
                if (!getOutputLabel().getText().equals("NaN")) {
                    calculate();
                } else {
                    this.isResult = true;
                }
                this.lockDecimal = false;
        }
    }

    /**
     * resets the variables in case of CA or after an
     * equation result a number is entered
     */
    private void clear() {
        this.input.clear();
        this.output.clear();
        getInputLabel().setText("TEXT");
        getInputLabel().setForeground(Color.BLACK);
        getOutputLabel().setText("TEXT");
        getOutputLabel().setForeground(Color.BLACK);
        this.operatorCount = 0;
        this.lockDecimal = false;
        this.isResult = false;
    }

    /**
     * Retrieves an element of the input ArrayList
     * @param index the index of the element to retrieve
     * @return The element as a String
     */
    private String getElementFromInput(int index) {
        return this.input.get(index);
    }


    /**
     * Retrieves the last index of the input ArrayList
     * @return the last index as an int, size-1
     */
    private int getLastIndexOfInput() {
        return (this.input.size() - 1);
    }

    /**
     * Deals with the subtraction operator, it allows one to
     * exist after any operator as a negative indicator, two
     * subtraction operators after each other melt into an
     * addition operator
     */
    private void dealWithMinus() {
        int lastElementIndex = getLastIndexOfInput();
        if (operatorCount == 2) {
            lastElementIndex--;
            this.input.remove(lastElementIndex);
            this.operatorCount--;
        }

        if ("-".equals(getElementFromInput(lastElementIndex))) {
            removeOperatorDuplicate();
            this.input.add("+");
            this.operatorCount++;
        } else {
            this.input.add("-");
            this.operatorCount++;
        }
    }


    /**
     * Deals with duplicate operators, simply removes the last operator
     * after it's called by any operator button, also updates the operator
     * count
     */
    private void removeOperatorDuplicate() {
        int lastElementIndex = getLastIndexOfInput();
        while (this.OPERATORS.contains(getElementFromInput(lastElementIndex))) {
            this.input.remove(lastElementIndex);
            this.operatorCount--;
            lastElementIndex--;
        }
    }

    /**
     * Deletes the last element from the input ArrayList,
     * also updates the decimal lock and operator count
     */
    private void deleteLastElement() {
        int index = getLastIndexOfInput();
        if (OPERATORS.contains(getElementFromInput(index))) {
            this.operatorCount--;
        }
        if (".".equals(getElementFromInput(index))) {
            this.lockDecimal = false;
        }
        this.input.remove(index);
        updateInputLabel();
    }

    /**
     * Simply updated the input label based on the input
     * ArrayList, using a String Builder
     */
    private void updateInputLabel() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.input) {
            sb.append(str);
        }
        getInputLabel().setText(sb.toString());
        getInputLabel().setForeground(Color.WHITE);
    }

    /**
     * Converts from String to double, using the String Builder
     * it builds up the numbers digit by digit, also deals with
     * operators, + is discarded, - is discarded as well since
     * 1-1 is 1+-1, therefore multiplies the second number by -1,
     * * is replaced by null and / is converted to * since 5/5 is
     * 5 * 1/5, resulting in an Array List that only contains
     * positive and negative doubles, and null which
     * signifies multiplication, division by zero results in
     * short-circuiting the equation and displays NaN
     */
    private void evaluate() {
        StringBuilder sb = new StringBuilder();
        double num;
        int size = getLastIndexOfInput();
        for (int i = 0; i <= size; i++) {
            if (this.OPERATORS.contains(getElementFromInput(i))) {
                if ("/".equals(getElementFromInput(i))) {
                    num = checkFormat(sb.toString());
                    if (getOutputLabel().getText().equals("NaN")) {
                        break;
                    }
                    this.output.add(num);
                    this.output.add(null);
                    sb.delete(0, sb.capacity());
                    this.isDivision = true;
                } else if ("-".equals(getElementFromInput(i))) {
                    if (!sb.isEmpty()) {
                        num = checkFormat(sb.toString());
                        this.output.add(num);
                        sb.delete(0, sb.capacity());
                    }
                    this.isNegative = true;
                } else if ("*".equals(getElementFromInput(i))) {
                    num = checkFormat(sb.toString());
                    this.output.add(num);
                    this.output.add(null);
                    sb.delete(0, sb.capacity());
                } else {
                    num = checkFormat(sb.toString());
                    this.output.add(num);
                    sb.delete(0, sb.capacity());
                }
            } else {
                sb.append(getElementFromInput(i));
            }
        }
        num = checkFormat(sb.toString());
        if (!getOutputLabel().getText().equals("NaN")) {
            this.output.add(num);
        }
    }

    /**
     * Checks if a number has to be converted to a
     * negative one or a fraction, also checks the
     * zero division
     * @param number the number to be checked based on global
     *               booleans
     * @return returns the number in a double format
     */
    private double checkFormat(String number) {
        double result = Double.parseDouble(number);
        if (this.isNegative) {
            this.isNegative = false;
            result *= -1;
        } else if (this.isDivision) {
            this.isDivision = false;
            if (result == 0.0) {
                getOutputLabel().setText("NaN");
                getOutputLabel().setForeground(Color.WHITE);
            } else {
                result = 1.0 / result;
            }
        }
        return result;
    }

    /**
     * Calculates the result, it first looks for null in the
     * output Array List then multiplies the adjacent numbers,
     * removes them and the null and replaces it with the result,
     * so the logical order is maintained, after it just sums up
     * the remaining numbers, which may include negative numbers
     * resulting in a subtraction
     */
    private void calculate() {
        int multiplicationIndex = this.output.indexOf(null);
        int left, right;

        while (multiplicationIndex != -1) {
            left = multiplicationIndex - 1;
            right = multiplicationIndex + 1;
            this.result = (this.output.get(left)) * (this.output.get(right));
            this.output.remove(left);
            this.output.remove(left);
            this.output.remove(left);
            this.output.add(left, this.result);
            multiplicationIndex = this.output.indexOf(null);
        }
        this.result = 0;
        for (int i = 0; i < this.output.size(); i++) {
            this.result += this.output.get(i);
        }
        getOutputLabel().setText(String.valueOf(this.result));
        getOutputLabel().setForeground(Color.WHITE);
        this.isResult = true;
        addToHistory();
        displayHistory();
    }

    /**
     * displays the equation calculated, NaN won't reach this
     * method, the equation is a simple label and the result is
     * a button which can be pressed, more on that in the class
     * constructor and historyElementPressed method, also
     * clears the history ArrayList
     */
    private void displayHistory() {
        this.label = new JLabel(this.history.get(0));
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setMinimumSize(new Dimension(500, 100));
        this.label.setBorder(new MatteBorder(15,15,15,15, Color.BLACK));
        this.historyPanel.add(this.label);

        this.button = new JButton(this.history.get(1));
        this.button.setForeground(Color.WHITE);
        this.button.setBackground(Color.BLACK);
        this.button.setFont(this.font);
        this.button.setMinimumSize(new Dimension(500, 100));
        this.button.setHorizontalAlignment(SwingConstants.LEFT);
        this.button.setBorder(new MatteBorder(15,15,15,15, Color.BLACK));
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyElementPressed(e.getActionCommand());
            }
        });

        this.historyPanel.add(this.button);
        this.history.clear();
    }

    /**
     * appends to the history ArrayList
     * self-explanatory
     */
    private void addToHistory() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.input) {
            sb.append(str);
        }
        this.history.add(sb + "=");
        this.history.add(String.valueOf(this.result));
    }

    /**
     * if in the input the last element is an operator
     * the pressed value will append to that equation,
     * otherwise starts a new one starting with the value
     * @param str the value of the button that was pressed
     */
    private void historyElementPressed(String str) {
        if (this.OPERATORS.contains(getElementFromInput(getLastIndexOfInput()))) {
            this.input.add(str);
            this.operatorCount = 0;
            updateInputLabel();
        } else {
            clear();
            this.input.add(str);
            updateInputLabel();
        }
    }

    /**
     * returns the input JLabel since it was made by a method
     * and doesn't have a corresponding name
     * @return returns the input JLabel
     */
    private JLabel getInputLabel() {
        return (JLabel) this.calculatorPanel.getComponent(0);
    }

    /**
     * returns the output JLabel since it was made by a method
     * and doesn't have a corresponding name
     * @return returns the output JLabel
     */
    private JLabel getOutputLabel() {
        return (JLabel) this.calculatorPanel.getComponent(1);
    }
}
