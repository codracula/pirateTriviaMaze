package model;

import java.sql.*;
import java.util.*;

public class QuestionDatabase {

    private final ArrayList<Question> myQuestionList = new ArrayList<>();
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

    private void selectAll() {
        String sql = "SELECT * FROM QUESTIONS";
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void setQuestionList(final String theCategory, final int theDifficulty) {
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
    }

    public ArrayList<Question> getQuestionList() {
        return myQuestionList;
    }

    //add index value in parameters
    void removeQuestion(final int theIndex) {
        myQuestionList.remove(theIndex);
    }

    private String getCategory(final int theIndex) {
        return myQuestionList.get(theIndex).getMyCategory();
    }

    private int getDifficulty(final int theIndex) {
        return myQuestionList.get(theIndex).getMyDifficulty();
    }

    //returns just the question as a string
    private String getQuestion(final int theIndex) {
        return myQuestionList.get(theIndex).getMyQuestion();
    }

    //returns the question object that contains question, choices, etc
    Question getQuestionSet(final int theIndex) {
        return myQuestionList.get(theIndex);
    }

    public ArrayList<String> getChoices(final int theIndex) {
        return myQuestionList.get(theIndex).setChoices();
    }

    public String getAnswer(final int theIndex) {
        return myQuestionList.get(theIndex).getMyAnswer();
    }

}

