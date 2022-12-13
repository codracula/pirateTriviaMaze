package model;

import java.sql.*;
import java.util.*;

/**
 * Connects to SQLite and accesses table of Questions
 * @author Juno L.
 */
public class QuestionDatabase {

    private final ArrayList<Question> myQuestionList = new ArrayList<>(); // Naming conventinos!!

    /**
     * @return Connection to database
     */
    private Connection connect() {
        String url = "jdbc:sqlite:TriviaQuestions.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * selects all of the contents of the database table
     */
    public void selectAll() {
        String sql = "SELECT * FROM QUESTIONS";
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param theCategory: category of questions to be selected
     * @param theDifficulty: difficult 1-3 of question (depends on mon type)
     * @return a List of Questions given the parameters
     */
    public ArrayList<Question> setQuestionList(final String theCategory, final int theDifficulty) {
        String sql = "SELECT Category, Difficulty, Question, Choices, Answer FROM Questions WHERE Category = ? AND Difficulty = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, theCategory);
            pstmt.setInt(2, theDifficulty);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String cate = rs.getString("CATEGORY");
                int diff = rs.getInt("DIFFICULTY");
                String ques = rs.getString("QUESTION");
                String choices = rs.getString("CHOICES");
                String answer = rs.getString("ANSWER");
                Question question = new Question(cate, diff, ques, choices, answer);
                myQuestionList.add(question);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return myQuestionList; //???
    }

    /**
     * @return a list of Questions given the parameters in setQuestionList
     */
    public ArrayList<Question> getQuestionList() {
        return myQuestionList;
    }

    /**
     * removes
     * @param theIndex: relevant index of the List of Questions
     */
    public void removeQuestion(final int theIndex) {
        myQuestionList.remove(theIndex);
    }

    /**
     * @param theIndex: relevant index of the List of Questions
     * @return the category of
     */
    public String getCategory(final int theIndex) {
        return myQuestionList.get(theIndex).getMyCategory();
    }

    public int getDifficulty(final int theIndex) {
        return myQuestionList.get(theIndex).getMyDifficulty();
    }

    //returns just the question as a string
    public String getQuestion(final int theIndex) {
        return myQuestionList.get(theIndex).getMyQuestion();
    }

    //returns the question object that contains question, choices, etc
    public Question getQuestionSet(final int theIndex) {
        return myQuestionList.get(theIndex);
    }

    /**
     * @param theIndex: relevant index of the List of Questions
     * @return choices of the current question in the form of ArrayList
     */
    public ArrayList<String> getChoices(final int theIndex) {
        return myQuestionList.get(theIndex).setChoices();
    }

    /**
     * @param theIndex: relevant index of the List of Questions
     * @return the answer of the current question given by theIndex
     */
    public String getAnswer(final int theIndex) {
        return myQuestionList.get(theIndex).getMyAnswer();
    }

}