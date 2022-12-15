package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MonsterTest {


    @Test
        /* default */ void testConstructor() {
        Monsters mon = new Monsters(5,4,3);

        assertEquals(5, mon.myM_AmaxCount, "monster count not match");
        assertEquals(4, mon.myM_BmaxCount, "monster count not match");
        assertEquals(3, mon.myM_CmaxCount, "monster count not match");
    }

    @Test
        /* default */ void testPopulateMonster() {
        Monsters mon = new Monsters(3,2,1);
        mon.populateMonster();
        assertEquals("bandit", mon.myMonsterType.get(0), "bandit isn't on the list");
        assertEquals("guard", mon.myMonsterType.get(1), "guard isn't on the list");
        assertEquals("gateKeeper", mon.myMonsterType.get(2), "gatekeeper isn't on the list");
    }

    @Test
        /* default */ void testGenerateList() {
        Monsters mon = new Monsters(3,2,1);
        mon.populateMonster();
        mon.generateList();
        int countBandit = 0;
        for (int i = 0; i < mon.myMList.size();i++){
            if (mon.myMList.get(i) == "bandit"){
                countBandit++;
            }
        }
        assertEquals(countBandit, mon.myM_AmaxCount, "bandit counts aren't the same");
        //---------------------------------------------------
        int countGuard = 0;
        for (int i = 0; i < mon.myMList.size();i++){
            if (mon.myMList.get(i) == "guard"){
                countGuard++;
            }
        }
        assertEquals(countGuard, mon.myM_BmaxCount, "guard counts aren't the same");
        //---------------------------------------------------
        int countGateKeeper = 0;
        for (int i = 0; i < mon.myMList.size();i++){
            if (mon.myMList.get(i) == "gateKeeper"){
                countGateKeeper++;
            }
        }
        assertEquals(countGateKeeper, mon.myM_CmaxCount, "gateKeeper counts aren't the same");

        assertEquals(countBandit + countGuard + countGateKeeper, mon.myMList.size(),
                "monster counts aren't the same");
    }

    @Test
        /* default */ void testMList() {

        List<String> testList = Arrays.asList("bandit", "bandit", "bandit", "guard", "guard", "gateKeeper");

        Monsters mon = new Monsters(3,2,1);
        mon.populateMonster();
        mon.generateList();
        assertNotNull(mon.toString(), "list is not null");
        assertEquals(6, mon.myMList.size(), "monster list has correct count");
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

        assertEquals(typeA+typeB+typeC, mon.myMList.size(), "mList same as testLIst");
    }

//    @Test
//        /* default */ void testGetQuestion() {
//        Monsters mon = new Monsters(3,2,1);
//        mon.setQuestion(bandit, );
//        mon.populateMonster();
//        assertEquals("bandit", mon.myMonsterType.get(0), "bandit isn't on the list");
//        assertEquals("guard", mon.myMonsterType.get(1), "guard isn't on the list");
//        assertEquals("gateKeeper", mon.myMonsterType.get(2), "gatekeeper isn't on the list");
//    }
}
