package view;

import model.Maze;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

public class GameViewUI extends JFrame {
    //UI specific settings margin, button size, etc
    private JLabel name = new JLabel("Name: ");
    private JTextField nameText = new JTextField(10);

    private JLabel live = new JLabel("live: ");
    private JTextField liveText = new JTextField(2);

    private JLabel hint = new JLabel("hint:");
    private JTextField hintText = new JTextField(2);
    //-----------------------------
    private JTextArea mazeText = new JTextArea(4, 7);
    //-----------------------------
    private JButton up = new JButton("up");
    private JButton down = new JButton("down");
    private JButton left = new JButton("left");
    private JButton right = new JButton("right");
    //-----------------------------
    private String[][] travelMap;
    private JButton[][] buttonMap;
    private Scanner scan;

    public GameViewUI() throws IOException {
        //super();
        JPanel gamePanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850, 500);
        gamePanel.add(name);
        gamePanel.add(nameText);
        gamePanel.add(live);
        gamePanel.add(liveText);
        gamePanel.add(hint);
        gamePanel.add(hintText);
        gamePanel.add(mazeText);
        gamePanel.add(up);
        gamePanel.add(down);
        gamePanel.add(left);
        gamePanel.add(right);
        this.add(gamePanel);
        //-------------------------------

        buildComponents();

        scan = new Scanner(System.in);
    }

    public void buildComponents() throws IOException {

    }
    private void userInputMove(){

        System.out.print("Player's available option: ");

    }
    public void pDirection(){
        //if i = 0 , no up movement
        //if i = max, no down movement
        //if j = 0, no left movement
        //if j = max, no right movement

//
//        if (ma.getPCurrent(0) == 0 && ma.getPCurrent(1) == 0){ //top left
//
//            System.out.print("\'s\' to move down" + ", ");
//            System.out.print("\'d\' to move right");
//
//        } else if (ma.getPCurrent(0) == 0 && ma.getPCurrent(1) == ma.getMazeC() - 1){  //top right
//            System.out.print("\'s\' to move down" + ", ");
//            System.out.print("\'a\' to move left");
//            String inputDirection = scan.nextLine();
//        } else if (ma.getPCurrent(0) == ma.getMazeR() - 1
//                && ma.getPCurrent(1) == ma.getMazeC() - 1){  //bottom right
//            System.out.print("\'w\' to move up" + ", ");
//            System.out.print("\'a\' to move left");
//            String inputDirection = scan.nextLine();
//        } else if (ma.getPCurrent(0) == ma.getMazeR() - 1
//                && ma.getPCurrent(1) == 0){  //bottom left
//            System.out.print("\'w\' to move up" + ", ");
//            System.out.print("\'d\' to move right");
//            String inputDirection = scan.nextLine();
//        } else if (ma.getPCurrent(1) == 0){  //left
//            System.out.print("\'w\' to move up" + ", ");
//            System.out.print("\'s\' to move down" + ", ");
//            System.out.print("\'d\' to move right");
//        } else if (ma.getPCurrent(1) == ma.getMazeR() - 1){ //right
//            System.out.print("\'w\' to move up" + ", ");
//            System.out.print("\'s\' to move down" + ", ");
//            System.out.print("\'a\' to move left");
//        } else if (ma.getPCurrent(0) == 0){  //top
//            System.out.print("\'s\' to move down" + ", ");
//            System.out.print("\'a\' to move left");
//            System.out.print("\'d\' to move right");
//        } else if (ma.getPCurrent(0) == ma.getMazeR() - 1){  //bottom
//            System.out.print("\'s\' to move down" + ", ");
//            System.out.print("\'a\' to move left");
//            System.out.print("\'d\' to move right");
//        } else if (ma.getPCurrent(0) > 0 && ma.getPCurrent(0) < ma.getMazeR() - 1
//                && ma.getPCurrent(1) > 0 && ma.getPCurrent(1) < ma.getMazeC() - 1){
//            System.out.print("\'w\' to move up" + ", ");
//            System.out.print("\'s\' to move down" + ", ");
//            System.out.print("\'a\' to move left");
//            System.out.print("\'d\' to move right");
//        }

    }
    public void createAndShowUI(){

//        final JFrame frame = new JFrame("Pirates of the Euclideans");
//        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
//        frame.setPreferredSize(new Dimension(850, 500));
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

//                setLayout(new BorderLayout());
//                JLabel background = new JLabel(new ImageIcon("images/roomSolid.png"));
//                add(background);
//                background.setLayout(new FlowLayout());
//                l1=new JLabel("Here is a button");
//                b1=new JButton("I am a button");
//                background.add(b1);//
//                buttonMap[i][j] = new JButton("");
//                background.add(buttonMap[i][j]);
    }

}
