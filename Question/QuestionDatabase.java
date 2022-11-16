import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.*;

public class QuestionDatabase {

    private final ArrayList<Question> questionList = new ArrayList<>();
    private static final Random random = new Random();

    public void setQuestion(final String theCategory) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:TriviaQuestions.db");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM QUESTIONS");
            while(rs.next()) {
                String cate = rs.getString("CATEGORY");
                int diff = rs.getInt("DIFFICULTY");
                String ques = rs.getString("QUESTION");
                String choices = rs.getString("CHOICES");
                String answer = rs.getString("ANSWER");
                Question question = new Question(cate, diff, ques, choices, answer);
                questionList.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println(questionList.toString());
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    //add index value in parameters
    public void removeQuestion(final int theIndex) {
        questionList.remove(theIndex);
    }

    public String getCategory(final int theIndex) {
        return questionList.get(theIndex).getMyCategory();
    }

    public int getDifficulty(final int theIndex) {
        return questionList.get(theIndex).getMyDifficulty();
    }

//    //puts choices into an array
//    public ArrayList<String> setChoices(final String theChoices) {
//        String[] elements = theChoices.split(",");
//        List<String> list = Arrays.asList(elements);
//        ArrayList<String> choiceArray = new ArrayList<>(list);
//        return choiceArray;
//    }

    public String getChoices(final int theIndex) {
        return questionList.get(theIndex).getMyChoices();
    }

    public String getAnswer(final int theIndex) {
        return questionList.get(theIndex).getMyAnswer();
    }

}
