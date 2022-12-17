package model;


import org.junit.jupiter.api.Test;
import view.GameView;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    @Test
    void testGameModel() {  //done
        GameView view = new GameView();
        GameModel model = new GameModel(view);
        model.myMaze.myCurrentRow = 4;
        model.myMaze.myCurrentCol = 7;
        assertEquals(7, model.myMaze.myColCount, "not equal");
        assertEquals(4, model.myMaze.myRowCount, "not equal");
        assertEquals(6, model.myMaze.myMon.myM_AmaxCount, "not equal");
        assertEquals(2, model.myMaze.myMon.myM_BmaxCount, "not equal");
        assertEquals(0, model.myMaze.myMon.myM_CmaxCount, "not equal");
        assertEquals(4, model.myMaze.myCurrentRow, "not equal");
        assertEquals(7, model.myMaze.myCurrentCol, "not equal");

    }
    @Test
    void testGetMyMaze() {  //done
        GameView view = new GameView();
        GameModel model2 = new GameModel(view);
        assertNotNull( model2.myMaze, "maze is null");
    }
    @Test
    void testGetExit() {    //done
        GameView view = new GameView();
        GameModel model3 = new GameModel(view);
        model3.getExit();
        assertNotNull(model3.myMaze.myExitCol, "col position for exit is null");
        assertNotNull(model3.myMaze.myExitRow, "row position for exit is null");
    }
    @Test
    void testGetPlayerRow() {   //done
        GameView view = new GameView();
        GameModel model4 = new GameModel(view);
        assertEquals(model4.myMaze.myCurrentRow, model4.myMaze.myPlayerSpawnRow, "current row not equal");
    }
    @Test
    void testGetPlayerCol() {   //done
        GameView view = new GameView();
        GameModel model5 = new GameModel(view);

        assertEquals(model5.myMaze.myCurrentCol, model5.myMaze.myPlayerSpawnCol, "cureent col not equal");
    }

    @Test
    void testGetMyPlayer() {    //done
        GameView view = new GameView();
        GameModel model6 = new GameModel(view);
        assertNotNull(model6.myPlayer, "Character is null");
        assertEquals(3, model6.myPlayer.getLives(), "player lives are not equal");
    }

    @Test
    void testDecMyLive() {  //done
        GameView view = new GameView();
        GameModel model7 = new GameModel(view);
        assertEquals(3, model7.myPlayer.getLives(), "player lives are not 3");
        model7.decMyLive();
        assertEquals(2, model7.myPlayer.getLives(), "player lives not decrease");
    }

    @Test
    void testSetCurrentP() {    //done
        GameView view = new GameView();
        GameModel model8 = new GameModel(view);
        int col = model8.getPlayerCol();
        int row = model8.getPlayerRow();
        model8.setCurrentP();
        model8.myMaze.getOccupant(row, col);
        assertNotNull(model8.myMaze.getOccupant(row, col), "current room isn't null");
        assertEquals("P", model8.myMaze.getOccupant(row, col), "player not set in current spot");
    }

    @Test
    void testMoveLeft() {
        GameView view = new GameView();
        GameModel model9 = new GameModel(view);
        //set player location for testing
        model9.myMaze.setPlayerLocation(2,2);
        int col = model9.getPlayerCol();
        int row = model9.getPlayerRow();
        //clear room around player position
        model9.myMaze.roomSetEmpty(col-1, row);
        model9.myMaze.roomSetEmpty(col+1, row);
        model9.myMaze.roomSetEmpty(col, row-1);
        model9.myMaze.roomSetEmpty(col, row+1);

        //double check current position for player
        assertEquals("P", model9.myMaze.getOccupant(row, col), "player not set in current spot");
        assertFalse(model9.myMaze.roomCheckEmpty(row, col), "previous position not empty");
        model9.moveLeft();
        assertTrue(model9.myMaze.roomCheckEmpty(row, col + 1), "previous position not empty");
        assertEquals(2, model9.myMaze.myCurrentRow, "player not set in current spot");
        assertEquals(1, model9.myMaze.myCurrentCol, "player not set in current spot");

    }

    @Test
    void testMoveRight() {
        GameView view = new GameView();
        GameModel model10 = new GameModel(view);
        //set player location for testing
        model10.myMaze.setPlayerLocation(2,2);
        int col = model10.getPlayerCol();
        int row = model10.getPlayerRow();
        //clear room around player position
        model10.myMaze.roomSetEmpty(col - 1, row);
        model10.myMaze.roomSetEmpty(col + 1, row);
        model10.myMaze.roomSetEmpty(col, row - 1);
        model10.myMaze.roomSetEmpty(col, row + 1);

        //double check current position for player
        assertEquals("P", model10.myMaze.getOccupant(row, col), "player not set in current spot");
        assertFalse(model10.myMaze.roomCheckEmpty(row, col), "previous position not empty");
        model10.moveRight();
        assertTrue(model10.myMaze.roomCheckEmpty(row, col - 1), "previous position not empty");
        assertEquals(2, model10.myMaze.myCurrentRow, "player not set in current spot");
        assertEquals(3, model10.myMaze.myCurrentCol, "player not set in current spot");

    }
    @Test
    void testMoveUp() {
        GameView view = new GameView();
        GameModel model11 = new GameModel(view);

        //set player location for testing
        model11.myMaze.setPlayerLocation(2,2);
        int col = model11.getPlayerCol();
        int row = model11.getPlayerRow();
        //clear room around player position
        model11.myMaze.roomSetEmpty(col - 1, row);
        model11.myMaze.roomSetEmpty(col + 1, row);
        model11.myMaze.roomSetEmpty(col, row - 1);
        model11.myMaze.roomSetEmpty(col, row + 1);

        //double check current position for player
        assertEquals("P", model11.myMaze.getOccupant(row, col), "player not set in current spot");
        assertFalse(model11.myMaze.roomCheckEmpty(row, col), "previous position not empty");
        model11.moveUp();
        assertTrue(model11.myMaze.roomCheckEmpty(row + 1, col), "previous position not empty");
        assertEquals(1, model11.myMaze.myCurrentRow, "player not set in current spot");
        assertEquals(2, model11.myMaze.myCurrentCol, "player not set in current spot");

    }
    @Test
    void testMoveDown() {
        GameView view = new GameView();
        GameModel model12 = new GameModel(view);
        //set player location for testing
        model12.myMaze.setPlayerLocation(2,2);
        int col = model12.getPlayerCol();
        int row = model12.getPlayerRow();

        //clear room around player position
        model12.myMaze.roomSetEmpty(col - 1, row);
        model12.myMaze.roomSetEmpty(col + 1, row);
        model12.myMaze.roomSetEmpty(col, row - 1);
        model12.myMaze.roomSetEmpty(col, row + 1);

        //double check current position for player
        assertEquals("P", model12.myMaze.getOccupant(row, col), "player not set in current spot");
        assertFalse(model12.myMaze.roomCheckEmpty(row, col), "previous position not empty");
        model12.moveDown();
        assertTrue(model12.myMaze.roomCheckEmpty(row - 1, col), "previous position not empty");
        assertEquals(3, model12.myMaze.myCurrentRow, "player not set in current spot");
        assertEquals(2, model12.myMaze.myCurrentCol, "player not set in current spot");
    }
    @Test
    void testRoomActivity() {
        GameView view = new GameView();
        GameModel model13 = new GameModel(view);
        Character ch = new Character();
        assertEquals(0, Character.Inventory.getMyKeyCount(), "player spawns with key count");
        //set player position to test activity
        model13.myMaze.setPlayerLocation(2,2);
        model13.myMaze.setOccupant(2,3,"K");
        model13.moveRight();
        model13.roomActivity();
        assertEquals(1, Character.Inventory.getMyKeyCount(), "player key count not increase");
        //move away to check key in the room
        model13.moveRight();
        assertTrue(model13.myMaze.roomCheckEmpty(model13.myMaze.myCurrentRow, model13.myMaze.myCurrentRow), "key is not picked up");

    }

    @Test
    void testMonEncounter() {
//        GameView view = new GameView();
//        GameModel model13 = new GameModel(view);
//        QuestionDatabase myQuestionData = new QuestionDatabase();
//        Character myPlayer = new Character();
//        ArrayList<Question> myBanditQ;
//        model13.myMaze.getMon().setQuestion("bandit", "Geography", myQuestionData);
//        myBanditQ = myQuestionData.getQuestionList();
//        model13.monEncounter(myBanditQ);
//        int correct = 0;
//        if (correct == 2) {
//            int oldCount = myPlayer.getHintpassCount();
//            model13.monEncounter(myBanditQ);
//            assertTrue(oldCount - 1 == myPlayer.getHintpassCount()); //show that a hint pass was used
//        } else if (correct == 0) {
//            int oldCount = myPlayer.getLives();
//            assertTrue(oldCount - 1 == myPlayer.getLives());
//        }
    }

    @Test
    void testHintPassChance() {
//        GameView view = new GameView();
//        GameModel model14 = new GameModel(view);
//
//        assertTrue(model14.myMaze.getMon().hintPassChance("gatekeeper"));
    }

    @Test
    void testSetQuestion() {
        GameView view = new GameView();
        GameModel model15 = new GameModel(view);
        QuestionDatabase myQuestionData = new QuestionDatabase();

        ArrayList<Question> myBanditQ;
        model15.myMaze.getMon().setQuestion("bandit", "Geography", myQuestionData);
        myBanditQ = myQuestionData.getQuestionList();
        assertTrue(myBanditQ.size() > 0);
    }

    @Test
    void testGetQuestion() {
//        GameView view = new GameView();
//        GameModel model16 = new GameModel(view);
//        QuestionDatabase myQuestionData = new QuestionDatabase();
//
//        myQuestionData.setQuestionList("Test", 0);
//        ArrayList<Question> monsterQ = myQuestionData.getQuestionList();
//        int beforeLength = monsterQ.size();
//        assertEquals("Test", model16.getQuestion(monsterQ).getMyAnswer());
//        assertTrue(beforeLength - 1 == monsterQ.size(), "question removed");
    }

    @Test
    void testGetQuestionIndex() {
//        GameView view = new GameView();
//        GameModel model17 = new GameModel(view);
//        QuestionDatabase myQuestionData = new QuestionDatabase();
//
//
//        myQuestionData.setQuestionList("Test", 0);
//        ArrayList<Question> monsterQ = myQuestionData.getQuestionList();
//        int index = model17.getQuestionIndex(monsterQ);
//        assertTrue(index <= monsterQ.size());
    }

    @Test
    void testDoBossFight() {
        GameView view = new GameView();
        GameModel model17 = new GameModel(view);

        if (model17.myMaze.getColPos() == model17.myMaze.getExitCol() && model17.myMaze.getRowPos() == model17.myMaze.getExitRow()) {
            assertTrue(model17.doBossFight(), "player is at exit and ready for boss fight");
        } else {
            assertFalse(model17.doBossFight(), "player is not at exit, not ready for boss fight");
        }
    }
}