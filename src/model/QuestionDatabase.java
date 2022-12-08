package model;

import java.sql.*;
import java.util.*;

public class QuestionDatabase {

    private final ArrayList<Question> questionList = new ArrayList<>();
    //private static final Random random = new Random();

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
                questionList.add(question);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return questionList;
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

    //returns just the question as a string
    public String getQuestion(final int theIndex) {
        return questionList.get(theIndex).getMyQuestion();
    }

    //returns the question object that contains question, choices, etc
    public Question getQuestionSet(final int theIndex) {
        return questionList.get(theIndex);
    }

    public ArrayList<String> getChoices(final int theIndex) {
        return questionList.get(theIndex).setChoices();
    }

    public String getAnswer(final int theIndex) {
        return questionList.get(theIndex).getMyAnswer();
    }

}