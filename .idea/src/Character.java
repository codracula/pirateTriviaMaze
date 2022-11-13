import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Character {
    private static int myLives;
    private final String myName;
    private String myHeroType;
    String[] questions = {"What sound does a duck make?"};
    ArrayList<String> answers = new ArrayList<>();
    String myAnswer;
//    String answers[] = {"Steven", "Kevin", "Josh", "Molly"};
    public Character(int theLives, String theName, String theHeroType) {
        myName = theName;
        myHeroType = theHeroType;
        myLives = theLives;
    }

    public String myHeroType(){

        return myHeroType;
    }

    public String HeroType() {
        return myHeroType;
    }

    public void add(){
        answers.add("Quack!");
        answers.add("Moo!");
        answers.add("Meow!");
        answers.add("Woof!");
    }
    public void decreaseLives(){
            myLives--;
    }
    public void hasLifeline(){
        if(Inventory.getMyHintpassCount() == 0){
            System.out.println("You do not have any hint passes available.");
        }else{
            System.out.println("You have a hintpass!");
            System.out.println("Splitting your choices 50/50...");
            System.out.println("\n");
            useHintpass();
            Inventory.myHintpassCount--;
        }
    }
//    public void useHintpass() {
////        System.out.println(answers.indexOf(myAnswer));
////        answers.remove(2);
////        answers.remove(2);
//        answers.remove(myAnswer);
//        ArrayList<String> realAnswer = new ArrayList<>();
//        realAnswer.add(myAnswer);
//        //Collections.shuffle(answers);
//        for (String answer : answers) {
//            System.out.println(answer);
//        }
//        System.out.println("\n");
//        System.out.println("Correct answer: " + myAnswer);
//    }

    public void useHintpass() {
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(myAnswer);
        answers.remove(myAnswer);
        tempArray.add(answers.get(0));
        Collections.shuffle(tempArray);
        for (String answer : tempArray) {
            System.out.println(answer);
        }
        System.out.println("\n");
        System.out.println("Correct answer: " + myAnswer + "\n");
    }

//    public void useHintpass() {
//        String correctAnswer = answers[0];
//        answers = removeElement(answers, 2);
//        answers = removeElement(answers, 2);
//        Collections.shuffle(Arrays.asList(answers));
//        System.out.println(Arrays.toString(answers));
//        System.out.println("Correct answer: " + correctAnswer + "\n");
//    }

//    public static String[] removeElement(String[] answers, int index) {
//        String[] result = new String[answers.length - 1];
//        System.arraycopy(answers, 0, result, 0, index);
//        if (answers.length != index) {
//            System.arraycopy(answers, index + 1, result, index, answers.length - index - 1);
//        }
//        return result;
//    }

    public void displayQuestion() {
        myAnswer = answers.get(0);
        System.out.println(Arrays.toString(questions));
        Collections.shuffle(answers);
        for (String answer : answers) {
            System.out.println(answer);
        }
        System.out.println("\n");
        System.out.println("Correct answer: " + myAnswer + "\n");
    }

    public static boolean isAlive(){
        return myLives != 0;
    }

    public String toString() {
        return ("Name: " + myName + ", " + "Lives: " +
                myLives + ", " + " Hero type: " + myHeroType);
    }

    public int getLives() {
        return myLives;
    }
    static class Inventory{
        private static int myHintpassCount; //how many passes does the player start with
        private static int myKeyCount; //how many keys does the player start with

        public Inventory(int theHintpassCount, int theKeyCount){
            myHintpassCount = theHintpassCount;
            myKeyCount = theKeyCount;
        }



        public static int getMyKeyCount() {
            return myKeyCount;
        }
        public static int getMyHintpassCount() {
            return myHintpassCount;
        }

        public static int setMyHintpassCount(int myHintpassCount) {
            Inventory.myHintpassCount = myHintpassCount;
            return myHintpassCount;
        }

        public static int setMyKeyCount(int myKeyCount) {
            Inventory.myKeyCount = myKeyCount;
            return myKeyCount;
        }

        public String toString() {
            return ("Key count: " + myKeyCount + ", " + "Hint pass count: " + myHintpassCount);
        }
    }
}