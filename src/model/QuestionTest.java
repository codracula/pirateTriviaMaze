package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private String category = "Arithmetic";
    private int diff = 2;
    private String quest = "Solve 10 / 2";
    private String choices = "10, 2, 5, 1";
    private String answer = "5";
    Question question = new Question(category, diff, quest, choices, answer);

    @Test
    void testGetMyCategory() {
        assertEquals("Arithmetic", question.getMyCategory());
    }

    @Test
    void testGetMyDifficulty() {
        assertEquals(2, question.getMyDifficulty());
    }

    @Test
    void testGetMyQuestion() {
        assertEquals("Solve 10 / 2", question.getMyQuestion());
    }

    @Test
    void testSetChoices() {
        assertEquals("[10, 2, 5, 1]", question.setChoices().toString());
    }

    @Test
    void testGetMyChoices() {
        assertEquals("10, 2, 5, 1", question.getMyChoices());
    }

    @Test
    void testGetMyAnswer() {
        assertEquals("5", question.getMyAnswer());
    }
}