import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Monster {
    List myMonsterType;
    private int MyM_AmaxCount;
    private int MyM_BmaxCount;
    private int MyM_CmaxCount;
    private List mList;

    public Monster(int theM_A, int theM_B, int theM_C){
        MyM_AmaxCount = theM_A;
        MyM_BmaxCount = theM_B;
        MyM_CmaxCount = theM_C;
        populateMonster();
        generateList();
    }

    private void populateMonster(){
        myMonsterType = new ArrayList<String>();
        myMonsterType.add("bandit");
        myMonsterType.add("guard");
        myMonsterType.add("gateKeeper");
    }

    private void generateList(){
        mList = new ArrayList<String>(myMonsterType.size());
        for (int i = MyM_AmaxCount; i > 0; i--){
            mList.add(myMonsterType.get(0));
        }
        for (int i = MyM_BmaxCount; i > 0; i--){
            mList.add(myMonsterType.get(1));
        }
        for (int i = MyM_CmaxCount; i > 0; i--){
            mList.add(myMonsterType.get(2));
        }
        Collections.shuffle(mList);
        System.out.println(mList);
    }

    public List<String> getmList(){
        return mList;
    }

    //set parameter monType to String for now in order to tell which monType
    //probably change in future
    //need to get category information from title screen as well, hardcoded for now
    public ArrayList<Question> setQuestion(String monType, String category) {
        QuestionDatabase question = new QuestionDatabase();
        switch (monType) {
            case "bandit" -> question.setQuestionList(category, 1);
            case "guard" -> question.setQuestionList(category, 2);
            case "gatekeeper" -> question.setQuestionList(category, 3);
        }
        System.out.println(question.getQuestionList().size());
        Random rand = new Random();
        int index = rand.nextInt(15);
        question.getQuestionList().get(index).toString();

        //asks the question and takes in user input
        //probably should move somewhere else later
        ArrayList<String> choices = question.getChoices(index);
        Scanner console = new Scanner(System.in);
        System.out.println("Enter your answer as a number");
        int userAnswer = console.nextInt();
        if (choices.get(userAnswer - 1).equals(question.getAnswer(index))) {
            System.out.println("Yay, you're correct!");
            //correct++;
        } else {
            System.out.println("Boo, you're wrong!");
            System.out.println("The answer is " + question.getAnswer(index));
            //wrong++;
        }
        //don't remove this tho
        question.removeQuestion(index);
        return question.getQuestionList();
    }

//    public Question getQuestion() {
//        return
//    }
}
