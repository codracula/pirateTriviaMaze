package model;

import javax.sound.sampled.*;
import java.io.*;

public class UserFunctions {
    private static Clip myAudio;


    public static void loadLastGame() {
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));
            //maze.loadMaze(in);
            //myPlayer = new Player(5);
            //myPlayer.loadPlayer(in);
            in.close();
        }catch(Exception e){System.out.println(e);}
    }

    public static void saveGame(){
        try {
            FileOutputStream fout = new FileOutputStream("f.txt");
            ObjectOutputStream out=new ObjectOutputStream(fout);
            //maze.saveMaze(out);
            //myPlayer.savePlayer(out);
            System.out.println("game saved");
        } catch(Exception ex){System.out.println("error saving");}
    }
    public static void stopMusic() {
        try {
            if(myAudio.isActive()){
                System.out.print("Stopping music... ");
                myAudio.stop();

            }
        }catch (NullPointerException e){
            System.out.println("There is no music playing currently");

        }

    }
    public static void playMusic() {
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