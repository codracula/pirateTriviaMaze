package unitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterJunitTest {


    @Test
        /* default */ void testConstructor() {
        Monster mon = new Monster(5,4,3);

        assertEquals(5, mon.mAmaxCount, "monster count not match");
        assertEquals(4, mon.mBmaxCount, "monster count not match");
        assertEquals(3, mon.mCmaxCount, "monster count not match");
        //test populate and generate monster in here
//        assertTrue(room.isEmpty(), "room is not empty");
//        assertFalse(room.myIsVisited, "room has been visited");
    }

    @Test
        /* default */ void testPopulateMonster() {
        Monster mon = new Monster(3,2,1);
        mon.populateMonster();
        assertEquals("bandit", mon.myMonsterType.get(0), "bandit isn't on the list");
        assertEquals("guard", mon.myMonsterType.get(1), "guard isn't on the list");
        assertEquals("gateKeeper", mon.myMonsterType.get(2), "gatekeeper isn't on the list");
    }

    @Test
        /* default */ void testGenerateList() {
        Monster mon = new Monster(3,2,1);
        mon.populateMonster();
        mon.generateList();
        int countBandit = 0;
        for (int i = 0; i < mon.mList.size();i++){
            if (mon.mList.get(i) == "bandit"){
                countBandit++;
            }
        }
        assertEquals(countBandit, mon.mAmaxCount, "bandit counts aren't the same");
        //---------------------------------------------------
        int countGuard = 0;
        for (int i = 0; i < mon.mList.size();i++){
            if (mon.mList.get(i) == "guard"){
                countGuard++;
            }
        }
        assertEquals(countGuard, mon.mBmaxCount, "guard counts aren't the same");
        //---------------------------------------------------
        int countGateKeeper = 0;
        for (int i = 0; i < mon.mList.size();i++){
            if (mon.mList.get(i) == "gateKeeper"){
                countGateKeeper++;
            }
        }
        assertEquals(countGateKeeper, mon.mCmaxCount, "gateKeeper counts aren't the same");

        assertEquals(countBandit + countGuard + countGateKeeper, mon.mList.size(),
                "monster counts aren't the same");
    }

    @Test
        /* default */ void testMList() {

        List<String> testList = Arrays.asList("bandit", "bandit", "bandit", "guard", "guard", "gateKeeper");

        Monster mon = new Monster(3,2,1);
        mon.populateMonster();
        mon.generateList();
        assertNotNull(mon.toString(), "list is not null");
        assertEquals(6, mon.mList.size(), "monster list has correct count");
        int typeA = 0, typeB = 0, typeC = 0;

        for (int i = 0; i < testList.size(); i++){

            if (testList.get(i) == mon.myMonsterType.get(0)) {
                typeA++;
            }
            if (testList.get(i) == mon.myMonsterType.get(1)) {
                typeB++;
            }
            if (testList.get(i) == mon.myMonsterType.get(2)) {
                typeC++;
            }
        }

        assertEquals(typeA+typeB+typeC, mon.mList.size(), "mList same as testLIst");
    }
}
