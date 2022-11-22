//import Model.Character;
//import Model.Maze;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class UserFunctions {



    //private static Character character;
    //private static Maze maze;
    static Clip myAudio;

    public static void main(String [] args){
        playMusic();
    }
    public static void stopMusic() {
        try {
            if(myAudio.isActive()){
                System.out.print("Stopping music... ");
                myAudio.stop();
                new TitleScreen();
            }
        }catch (NullPointerException e){
            System.out.println("There is no music playing currently");
            new TitleScreen();
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
                new TitleScreen();
                myAudio.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("An error has occurred.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}


