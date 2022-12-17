package model;

import java.sql.*;
import java.util.*;

/**
 * Connects to SQLite and accesses table of Questions
 * @author Juno L.
 */
public class QuestionDatabase {

    /**
     * initialize arrayList of questions
     */
    private final ArrayList<Question> myQuestionList = new ArrayList<>(); // Naming conventinos!!

    /**
     * connects to sqlite
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
     * puts database question sinto an arraylist
     * @param theCategory: category of questions to be selected
     * @param theDifficulty: difficult 1-3 of question (depends on mon type)
     * @return a List of Questions given the parameters
     */
    public void setQuestionList(final String theCategory, final int theDifficulty) {
        String sql = "SELECT Category, Difficulty, Question, Choices, Answer FROM Questions WHERE (Category = ? AND Difficulty = ?)";
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
    }

    /**
     * returns arraylist of questions
     * @return a list of Questions given the parameters in setQuestionList
     */
    public ArrayList<Question> getQuestionList() {
        return myQuestionList;
    }

}