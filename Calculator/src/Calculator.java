import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Calculator {
    /**
     * the input Array List stores every button push, the output
     * is made of doubles and nulls, explained below
     */
    private ArrayList<String> input;
    private ArrayList<Double> output;
    /**
     * Labels, the Frame and Buttons, self-explanatory
     */
    private JLabel inputLabel, outputLabel;
    private JFrame frame;
    private JButton one, two, three, four,
            five, six, seven, eight, nine,
            zero, decimal, addition, division,
            multiplication, subtraction, equals,
            delete, clear, history;
    /**
     * This was the quickest way I could think of to use a Set
     * for quick access to operators
     */
    private final String[] ELEMENTS = {"/", "*", "+", "-"};
    private final HashSet<String> OPERATORS = new HashSet<>(Arrays.asList(ELEMENTS));
    /**
     * isDivision and isNegative helps reduce the calculations
     */
    private boolean isDivision = false, isNegative = false;
    /**
     * the operator count makes sure there are no more than 2 operators
     * at any time, at which the second one has to be a negative
     * indicator. the decimal lock, locks the decimal until an
     * operator is used, or CA is pressed or a decimal is deleted
     */
    private int operatorCount = 0;
    private boolean lockDecimal = false;
    /**
     * result displays the result of the equation and
     * isResult checks if there was a result of a
     * previous equation at which pressing an operator
     * immediately after the equals sign, starts the
     * equation with the previous result
     */
    private double result;
    private boolean isResult = false;

    /**
     * showHistory is a toggle button to show the
     * previous equations, the index signals the starting
     * point to the history array so nothing gets displayed
     * twice, the buttons and labels are self-explanatory
     */
    private int width, height;
    private boolean showHistory = false;
    private ArrayList<String> historyArray;
    private int index = 0;
    private JButton historyBtn1, historyBtn2, historyBtn3,
            historyBtn4, historyBtn5, historyBtn6;
    private JLabel historyLbl1, historyLbl2, historyLbl3,
            historyLbl4, historyLbl5, historyLbl6;
    //    private JScrollPane scrollPane;

    /**
     * After calling the constructor just run the program,
     * the calculator will pop up in a fixed window
     */
    Calculator() {
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
        this.historyArray = new ArrayList<>();

        this.frame = new JFrame("Calculator");
        this.width = 494;
        this.height = 637;
        this.frame.setSize(this.width, this.height);
        this.frame.getContentPane().setLayout(null);
        this.frame.getContentPane().setBackground(new Color(0,0,0));
        this.inputLabel = new JLabel();
        this.outputLabel = new JLabel();


//        this.scrollPane = new JScrollPane();
//        this.scrollPane.setBackground(new Color(100,0,0));
//        this.scrollPane.setBounds(480,0,490,600);
//        this.scrollPane.setLayout(new ScrollPaneLayout());
//        this.scrollPane.setBorder(null);
//        this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        this.one = new JButton("1");
        this.two = new JButton("2");
        this.three = new JButton("3");
        this.four = new JButton("4");
        this.five = new JButton("5");
        this.six = new JButton("6");
        this.seven = new JButton("7");
        this.eight = new JButton("8");
        this.nine = new JButton("9");
        this.zero = new JButton("0");
        this.decimal = new JButton(".");
        this.addition = new JButton("+");
        this.subtraction = new JButton("-");
        this.multiplication = new JButton("*");
        this.division = new JButton("/");
        this.equals = new JButton("=");
        this.delete = new JButton("DEL");
        this.clear = new JButton("CA");
        this.history = new JButton("HIST");

        this.historyBtn1 = new JButton();
        this.historyBtn1.setBounds(480,550,480,50);
        this.historyBtn1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyBtn1.setForeground(new Color(255,255,255));
        this.historyBtn1.setBackground(new Color(0,0,0));
        this.historyBtn1.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyBtn1.setBorder(null);
        this.historyBtn1.setFocusable(false);
        this.frame.add(this.historyBtn1);
        this.historyBtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                    input.add(historyBtn1.getText());
                    inputLabel.setText(historyBtn1.getText());
                }
            }
        });

        this.historyBtn2 = new JButton();
        this.historyBtn2.setBounds(480,450,480,50);
        this.historyBtn2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyBtn2.setForeground(new Color(255,255,255));
        this.historyBtn2.setBackground(new Color(0,0,0));
        this.historyBtn2.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyBtn2.setBorder(null);
        this.historyBtn2.setFocusable(false);
        this.frame.add(this.historyBtn2);
        this.historyBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                    input.add(historyBtn2.getText());
                    inputLabel.setText(historyBtn2.getText());
                }
            }
        });

        this.historyBtn3 = new JButton();
        this.historyBtn3.setBounds(480,350,480,50);
        this.historyBtn3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyBtn3.setForeground(new Color(255,255,255));
        this.historyBtn3.setBackground(new Color(0,0,0));
        this.historyBtn3.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyBtn3.setBorder(null);
        this.historyBtn3.setFocusable(false);
        this.frame.add(this.historyBtn3);
        this.historyBtn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                    input.add(historyBtn3.getText());
                    inputLabel.setText(historyBtn3.getText());
                }
            }
        });

        this.historyBtn4 = new JButton();
        this.historyBtn4.setBounds(480,250,480,50);
        this.historyBtn4.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyBtn4.setForeground(new Color(255,255,255));
        this.historyBtn4.setBackground(new Color(0,0,0));
        this.historyBtn4.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyBtn4.setBorder(null);
        this.historyBtn4.setFocusable(false);
        this.frame.add(this.historyBtn4);
        this.historyBtn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                    input.add(historyBtn4.getText());
                    inputLabel.setText(historyBtn4.getText());
                }
            }
        });

        this.historyBtn5 = new JButton();
        this.historyBtn5.setBounds(480,150,480,50);
        this.historyBtn5.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyBtn5.setForeground(new Color(255,255,255));
        this.historyBtn5.setBackground(new Color(0,0,0));
        this.historyBtn5.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyBtn5.setBorder(null);
        this.historyBtn5.setFocusable(false);
        this.frame.add(this.historyBtn5);
        this.historyBtn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                    input.add(historyBtn5.getText());
                    inputLabel.setText(historyBtn5.getText());
                }
            }
        });

        this.historyBtn6 = new JButton();
        this.historyBtn6.setBounds(480,50,480,50);
        this.historyBtn6.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyBtn6.setForeground(new Color(255,255,255));
        this.historyBtn6.setBackground(new Color(0,0,0));
        this.historyBtn6.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyBtn6.setBorder(null);
        this.historyBtn6.setFocusable(false);
        this.frame.add(this.historyBtn6);
        this.historyBtn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                    input.add(historyBtn6.getText());
                    inputLabel.setText(historyBtn6.getText());
                }
            }
        });

        this.historyLbl1 = new JLabel();
        this.historyLbl1.setBounds(480,500,480,50);
        this.historyLbl1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyLbl1.setForeground(new Color(255,255,255));
        this.historyLbl1.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyLbl1.setBorder(null);
        this.historyLbl1.setFocusable(false);
        this.frame.add(this.historyLbl1);
        this.historyLbl1.setText("Hello");

        this.historyLbl2 = new JLabel();
        this.historyLbl2.setBounds(480,400,480,50);
        this.historyLbl2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyLbl2.setForeground(new Color(255,255,255));
        this.historyLbl2.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyLbl2.setBorder(null);
        this.historyLbl2.setFocusable(false);
        this.frame.add(this.historyLbl2);

        this.historyLbl3 = new JLabel();
        this.historyLbl3.setBounds(480,300,480,50);
        this.historyLbl3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyLbl3.setForeground(new Color(255,255,255));
        this.historyLbl3.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyLbl3.setBorder(null);
        this.historyLbl3.setFocusable(false);
        this.frame.add(this.historyLbl3);

        this.historyLbl4 = new JLabel();
        this.historyLbl4.setBounds(480,200,480,50);
        this.historyLbl4.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyLbl4.setForeground(new Color(255,255,255));
        this.historyLbl4.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyLbl4.setBorder(null);
        this.historyLbl4.setFocusable(false);
        this.frame.add(this.historyLbl4);

        this.historyLbl5 = new JLabel();
        this.historyLbl5.setBounds(480,100,480,50);
        this.historyLbl5.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyLbl5.setForeground(new Color(255,255,255));
        this.historyLbl5.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyLbl5.setBorder(null);
        this.historyLbl5.setFocusable(false);
        this.frame.add(this.historyLbl5);

        this.historyLbl6 = new JLabel();
        this.historyLbl6.setBounds(480,0,480,50);
        this.historyLbl6.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.historyLbl6.setForeground(new Color(255,255,255));
        this.historyLbl6.setHorizontalAlignment(SwingConstants.RIGHT);
        this.historyLbl6.setBorder(null);
        this.historyLbl6.setFocusable(false);
        this.frame.add(this.historyLbl6);


        this.inputLabel.setBounds(0,0,480,50);
        this.inputLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.inputLabel.setForeground(new Color(255,255,255));
        this.inputLabel.setBorder(null);
        this.inputLabel.setFocusable(false);
        this.frame.add(this.inputLabel);

        this.outputLabel.setBounds(0,50,480,50);
        this.outputLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.outputLabel.setForeground(new Color(255,255,255));
        this.outputLabel.setBorder(null);
        this.outputLabel.setFocusable(false);
        this.frame.add(this.outputLabel);

        this.clear.setBounds(0,100,120,100);
        this.clear.setSelected(false);
        this.clear.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.clear.setBackground(new Color(255,106,0));
        this.clear.setBorder(new MatteBorder(4,4,4,4, new Color(245,96,0)));
        this.clear.setFocusPainted(false);
        this.frame.add(this.clear);
        this.clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });


        this.delete.setBounds(120,100,120,100);
        this.delete.setSelected(false);
        this.delete.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.delete.setBackground(new Color(255,106,0));
        this.delete.setBorder(new MatteBorder(4,4,4,4, new Color(245,96,0)));
        this.delete.setFocusPainted(false);
        this.frame.add(this.delete);
        this.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!input.isEmpty()) {
                    deleteLastElement();
                }
            }
        });

        this.history.setBounds(240, 100, 120, 100);
        this.history.setSelected(false);
        this.history.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.history.setBackground(new Color(255,106,0));
        this.history.setBorder(new MatteBorder(4,4,4,4, new Color(245,96,0)));
        this.history.setFocusPainted(false);
        this.frame.add(this.history);
        this.history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHistory = !showHistory;
                if (showHistory) {
                    width *= 2;
                    frame.setSize(width, height);
                } else {
                    width /= 2;
                    frame.setSize(width, height);
                }
            }
        });

        this.division.setBounds(360, 100, 120, 100);
        this.division.setSelected(false);
        this.division.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.division.setBackground(new Color(255, 183, 0));
        this.division.setBorder(new MatteBorder(4,4,4,4, new Color(245,173,0)));
        this.division.setFocusPainted(false);
        this.frame.add(this.division);
        this.division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeOperatorDuplicate();
                if (isResult) {
                    clear();
                    input.add(String.valueOf(result));
                }
                input.add("/");
                lockDecimal = false;
                operatorCount++;
                updateInputLabel();
            }
        });

        this.seven.setBounds(0, 200, 120,100);
        this.seven.setSelected(false);
        this.seven.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.seven.setBackground(new Color(181,181,181));
        this.seven.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.seven.setFocusPainted(false);
        this.frame.add(this.seven);
        this.seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("7");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.eight.setBounds(120,200,120,100);
        this.eight.setSelected(false);
        this.eight.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.eight.setBackground(new Color(181,181,181));
        this.eight.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.eight.setFocusPainted(false);
        this.frame.add(this.eight);
        this.eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("8");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.nine.setBounds(240,200,120,100);
        this.nine.setSelected(false);
        this.nine.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.nine.setBackground(new Color(181,181,181));
        this.nine.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.nine.setFocusPainted(false);
        this.frame.add(this.nine);
        this.nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("9");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.multiplication.setBounds(360,200,120,100);
        this.multiplication.setSelected(false);
        this.multiplication.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.multiplication.setBackground(new Color(255, 183, 0));
        this.multiplication.setBorder(new MatteBorder(4,4,4,4, new Color(245,173,0)));
        this.multiplication.setFocusPainted(false);
        this.frame.add(this.multiplication);
        this.multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeOperatorDuplicate();
                if (isResult) {
                    clear();
                    input.add(String.valueOf(result));
                }
                input.add("*");
                lockDecimal = false;
                operatorCount++;
                updateInputLabel();
            }
        });

        this.four.setBounds(0, 300, 120,100);
        this.four.setSelected(false);
        this.four.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.four.setBackground(new Color(181,181,181));
        this.four.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.four.setFocusPainted(false);
        this.frame.add(this.four);
        this.four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("4");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.five.setBounds(120,300,120,100);
        this.five.setSelected(false);
        this.five.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.five.setBackground(new Color(181,181,181));
        this.five.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.five.setFocusPainted(false);
        this.frame.add(this.five);
        this.five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("5");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.six.setBounds(240, 300, 120,100);
        this.six.setSelected(false);
        this.six.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.six.setBackground(new Color(181,181,181));
        this.six.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.six.setFocusPainted(false);
        this.frame.add(this.six);
        this.six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("6");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.subtraction.setBounds(360,300,120,100);
        this.subtraction.setSelected(false);
        this.subtraction.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.subtraction.setBackground(new Color(255, 183, 0));
        this.subtraction.setBorder(new MatteBorder(4,4,4,4, new Color(245,173,0)));
        this.subtraction.setFocusPainted(false);
        this.frame.add(this.subtraction);
        this.subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lockDecimal = false;
                if (isResult) {
                    clear();
                    input.add(String.valueOf(result));
                }
                dealWithMinus();
                updateInputLabel();
            }
        });

        this.one.setBounds(0,400,120,100);
        this.one.setSelected(false);
        this.one.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.one.setBackground(new Color(181,181,181));
        this.one.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.one.setFocusPainted(false);
        this.frame.add(this.one);
        this.one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("1");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.two.setBounds(120,400,120,100);
        this.two.setSelected(false);
        this.two.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.two.setBackground(new Color(181,181,181));
        this.two.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.two.setFocusPainted(false);
        this.frame.add(this.two);
        this.two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("2");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.three.setBounds(240, 400, 120, 100);
        this.three.setSelected(false);
        this.three.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.three.setBackground(new Color(181,181,181));
        this.three.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.three.setFocusPainted(false);
        this.frame.add(this.three);
        this.three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("3");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.addition.setBounds(360,400,120,100);
        this.addition.setSelected(false);
        this.addition.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.addition.setBackground(new Color(255, 183, 0));
        this.addition.setBorder(new MatteBorder(4,4,4,4, new Color(245,173,0)));
        this.addition.setFocusPainted(false);
        this.frame.add(this.addition);
        this.addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeOperatorDuplicate();
                if (isResult) {
                    clear();
                    input.add(String.valueOf(result));
                }
                input.add("+");
                lockDecimal = false;
                operatorCount++;
                updateInputLabel();
            }
        });

        this.decimal.setBounds(0,500,120,100);
        this.decimal.setSelected(false);
        this.decimal.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.decimal.setBackground(new Color(161,161,161));
        this.decimal.setBorder(new MatteBorder(4,4,4,4, new Color(151,151,151)));
        this.decimal.setFocusPainted(false);
        this.frame.add(this.decimal);
        this.decimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!lockDecimal) {
                    input.add(".");
                }
                lockDecimal = true;
                updateInputLabel();
            }
        });

        this.zero.setBounds(120,500,120,100);
        this.zero.setSelected(false);
        this.zero.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.zero.setBackground(new Color(181,181,181));
        this.zero.setBorder(new MatteBorder(4,4,4,4, new Color(171,171,171)));
        this.zero.setFocusPainted(false);
        this.frame.add(this.zero);
        this.zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isResult) {
                    clear();
                }
                input.add("0");
                operatorCount = 0;
                updateInputLabel();
            }
        });

        this.equals.setBounds(240,500,240,100);
        this.equals.setSelected(false);
        this.equals.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        this.equals.setBackground(new Color(255,106,0));
        this.equals.setBorder(new MatteBorder(4,4,4,4, new Color(245,96,0)));
        this.equals.setFocusPainted(false);
        this.frame.add(this.equals);
        this.equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evaluate();
                calculate();
                lockDecimal = false;
            }
        });



//        this.frame.getContentPane().add(this.scrollPane);
//        this.scrollPane.setVisible(true);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * resets the variables in case of CA or after an
     * equation result a number is entered
     */
    private void clear() {
        this.input.clear();
        this.output.clear();
        this.inputLabel.setText("");
        this.outputLabel.setText("");
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
        this.inputLabel.setText(sb.toString());
    }

    /**
     * Converts from String to double, using the String Builder
     * it builds up the numbers digit by digit, also deals with
     * operator, + is discarded, - is discarded as well since
     * 1-1 is 1+-1, therefore multiplies the second number by -1
     * * is replaced by null and / is converted to * since 5/5 is
     * 5 * 1/5, resulting in an Array List that only contains
     * positive and negative numbers, and also doubles, and null
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
                    if (this.outputLabel.getText().equals("NaN")) {
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
        if (!this.outputLabel.getText().equals("NaN")) {
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
                this.outputLabel.setText("NaN");
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
        outputLabel.setText(String.valueOf(this.result));
        isResult = true;
        addToHistory();
        displayHistory();
    }

    /**
     * Displays the history, the result is a button,
     * when clicked it starts a new equation starting
     * with it, also resets the history array so the
     * history is limited but still better than nothing.
     * I tried doing it differently, for now
     * this will do
     */
    private void displayHistory() {
        if (this.historyArray.size() > 12) {
            this.index = 0;
            this.historyBtn1.setText(this.historyArray.get(13));
            this.historyLbl1.setText(this.historyArray.get(12));
            this.historyArray.clear();
        }
        for (int i = this.index; i < this.historyArray.size(); i += 2) {
            switch (i) {
                case 0:
                    this.historyBtn1.setText(this.historyArray.get(i+1));
                    this.historyLbl1.setText(this.historyArray.get(i));
                    break;
                case 2:
                    this.historyBtn2.setText(this.historyArray.get(i+1));
                    this.historyLbl2.setText(this.historyArray.get(i));
                    break;
                case 4:
                    this.historyBtn3.setText(this.historyArray.get(i+1));
                    this.historyLbl3.setText(this.historyArray.get(i));
                    break;
                case 6:
                    this.historyBtn4.setText(this.historyArray.get(i+1));
                    this.historyLbl4.setText(this.historyArray.get(i));
                    break;
                case 8:
                    this.historyBtn5.setText(this.historyArray.get(i+1));
                    this.historyLbl5.setText(this.historyArray.get(i));
                    break;
                case 10:
                    this.historyBtn6.setText(this.historyArray.get(i+1));
                    this.historyLbl6.setText(this.historyArray.get(i));
                    break;
            }
            this.index += 2;
        }
    }

//    private void displayHistory2() {
//        int n = 50;
//        for (int i = 0; i < this.historyArray.size(); i += 2) {
//            JButton res = new JButton(this.historyArray.get(i+1));
//            res.setBounds(0,this.historyY,480,50);
//            res.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
//            res.setForeground(new Color(255,255,255));
//            res.setBackground(new Color(0,0,0));
//            res.setHorizontalAlignment(SwingConstants.RIGHT);
//            res.setBorder(null);
//            res.setFocusable(false);
////            this.scrollPane.add(res);
//            res.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (isResult) {
//                        clear();
//                        input.add(res.getText());
//                        inputLabel.setText(res.getText());
//                    }
//                }
//            });
//
//            this.historyY -= n;
//            JLabel equation = new JLabel(this.historyArray.get(i));
//            System.out.println(this.historyArray.get(i));
//            equation.setBounds(0,this.historyY,480,50);
//            equation.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
//            equation.setForeground(new Color(255,255,255));
//            equation.setHorizontalAlignment(SwingConstants.RIGHT);
//            equation.setBorder(null);
//            equation.setFocusable(false);
////            this.scrollPane.add(equation);
//            this.historyY -= n;
//        }
//        this.historyArray.clear();
//    }

    /**
     * appends the equation and the result to the
     * history array
     */
    private void addToHistory() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.input) {
            sb.append(str);
        }
        this.historyArray.add(sb + "=");
        this.historyArray.add(String.valueOf(this.result));
    }
}
