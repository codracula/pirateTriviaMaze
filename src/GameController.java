import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

public class GameController extends JPanel implements PropertyChangeListener {
    //UI specific settings margin, button size, etc

    private String[][] travelMap;
    private JButton[][] buttonMap;
    private Maze ma;

    public GameController() throws IOException {
        super();

        buildComponents();
        createAndShowUI();
    }

    public void buildComponents() throws IOException {
        Maze ma = new Maze(6,2,1);
        buttonMap = new JButton[ma.myRowCount][ma.myColCount];

        //populate fog of war
        for(int i = 0; i< ma.myRowCount; i++){
            for(int j = 0; j < ma.myColCount; i++){
//                setLayout(new BorderLayout());
//                JLabel background = new JLabel(new ImageIcon("images/roomSolid.png"));
//                add(background);
//                background.setLayout(new FlowLayout());
//                l1=new JLabel("Here is a button");
//                b1=new JButton("I am a button");
//                background.add(b1);//
//                buttonMap[i][j] = new JButton("");
//                background.add(buttonMap[i][j]);
                if (ma.myMaze[i][j].getOccupant() != "P"){
                    travelMap[i][j] = "X";


                } else {
                    travelMap[i][j] = "O";
                    //show empty room

                }
            }
        }
        //draw bg to cover room
//                    BufferedImage buttonIcon = ImageIO.read(new File("./images/roomSolid.png"));
//                    JButton button = new JButton(new ImageIcon(buttonIcon));
//                    button.setBorderPainted(false);
//                    button.setFocusPainted(false);
//                    button.setContentAreaFilled(false);
//                    button.addMouseListener(new MouseAdapter() {
//                        @Override
//                        public void mouseClicked(MouseEvent e) {
//                            //add mouse listener
//                        }
//                    });
        //ask for user move
    }
    public void askUserDirection(){
        //if i = 0 , no up movement
        //if i = max, no down movement
        //if j = 0, no left movement
        //if j = max, no right movement
        Scanner scan = new Scanner(System.in);
        System.out.print("Player's available option: ");

        if (ma.myCurrentLoc.get(0).equals(0) && ma.myCurrentLoc.get(1).equals(0)){

            System.out.print("\'s\' to move down" + ", ");
            System.out.print("\'d\' to move right");
            String sc = scan.nextLine();
            if (sc == "s"){
                //move down
                int i = Integer.toString(ma.myCurrentLoc.get(0)) ;
                ma.myCurrentLoc.set(0) = i+1;
            } else if (sc == "d"){
                //move right
            }

        } else if (ma.myCurrentLoc.get(0).equals(0) && ma.myCurrentLoc.get(1).equals(7)){
            System.out.print("\'s\' to move down" + ", ");
            System.out.print("\'a\' to move left");
            String inputDirection = scan.nextLine();
        } else if (ma.myCurrentLoc.get(0).equals(7) && ma.myCurrentLoc.get(1).equals(7)){
            System.out.print("\'w\' to move up" + ", ");
            System.out.print("\'a\' to move left");
            String inputDirection = scan.nextLine();
        } else if (ma.myCurrentLoc.get(0).equals(7) && ma.myCurrentLoc.get(1).equals(0)){
            System.out.print("\'w\' to move up" + ", ");
            System.out.print("\'d\' to move right");
            String inputDirection = scan.nextLine();
        }

        String inputDirection = scan.nextLine();
    }
    public void createAndShowUI(){

        final JFrame frame = new JFrame("Pirates of the Euclideans");
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setPreferredSize(new Dimension(850, 500));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
    }
}
