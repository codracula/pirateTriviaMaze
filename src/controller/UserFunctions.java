package controller;

import model.Maze;

import javax.sound.sampled.*;
import java.io.*;

public class UserFunctions implements Serializable{
    private Clip myAudio;
    private Maze myMaze;


//    public void loadLastGame() {
//        try{
//            ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));
//            //myMaze = new Maze(4, 7, 6, 2, 1, 4);
//            myMaze.loadMaze(in);
//            //myPlayer = new Player(5);
//            //myPlayer.loadPlayer(in);
//            in.close();
//        }catch(Exception e){System.out.println(e);}
//    }

//    public void saveGame() throws IOException {
//        //try {
//            FileOutputStream fout = new FileOutputStream("f.txt");
//            ObjectOutputStream out=new ObjectOutputStream(fout);
//            //myMaze = new Maze(4, 7, 6, 2, 1, 4);
//            myMaze.saveMaze(out);
//            //myPlayer.savePlayer(out);
//            System.out.println("game saved");
//        //} catch(Exception ex){System.out.println("error saving");}
//    }
    public void stopMusic() {
        try {
            if(myAudio.isActive()){
                System.out.print("Stopping music... ");
                myAudio.stop();

            }
        }catch (NullPointerException e){
            System.out.println("There is no music playing currently");

        }

    }
    public void playMusic() {
        try {
            File music = new File("C:/Users/Steven/IdeaProjects/Maze/audio.wav");
            if (music.exists()) {
                System.out.print("Playing music... \n");
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
                myAudio = AudioSystem.getClip();
                myAudio.open(audioInput);
                myAudio.start();

                myAudio.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("An error has occurred.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}