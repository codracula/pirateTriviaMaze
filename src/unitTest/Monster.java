package unitTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monster {
    protected List myMonsterType;
    protected int mAmaxCount;
    protected int mBmaxCount;
    protected int mCmaxCount;
    protected List mList;
    public Monster(int mA, int mB, int mC){

        mAmaxCount = mA;
        mBmaxCount = mB;
        mCmaxCount = mC;
        populateMonster();
        generateList();
    }
    protected void populateMonster(){
        myMonsterType = new ArrayList<String>();
        myMonsterType.add("bandit");
        myMonsterType.add("guard");
        myMonsterType.add("gateKeeper");
    }

    protected void generateList(){
        mList = new ArrayList<String>(myMonsterType.size());
        for (int i = mAmaxCount; i > 0; i--){
            mList.add(myMonsterType.get(0));
        }
        for (int i = mBmaxCount; i > 0; i--){
            mList.add(myMonsterType.get(1));
        }
        for (int i = mCmaxCount; i > 0; i--){
            mList.add(myMonsterType.get(2));
        }
        Collections.shuffle(mList);
        //System.out.println(mList);
    }
    public List<String> getmList(){
        return mList;
    }

}
