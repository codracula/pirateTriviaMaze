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
    private static void stopMusic() {
        if(myAudio.isActive()){
            System.out.print("Stopping music... ");
            myAudio.stop();
        }
        else{
            myAudio.start();
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
                JOptionPane.showMessageDialog(null, "Press OK to stop playing music.");
                stopMusic();

            } else {
                System.out.println("An error has occurred.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}


