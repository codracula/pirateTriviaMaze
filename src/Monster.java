import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monster {
    List myMonsterType;
    private int mAmaxCount;
    private int mBmaxCount;
    private int mCmaxCount;
    protected List mList;
    public Monster(){

        mAmaxCount = 6;
        mBmaxCount = 2;
        mCmaxCount = 1;
        populateMonster();
        generateList();
    }
    private void populateMonster(){
        myMonsterType = new ArrayList<String>();
        myMonsterType.add("bandit");
        myMonsterType.add("guard");
        myMonsterType.add("gateKeeper");
    }

    private void generateList(){
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
        System.out.println(mList);
    }
    public List<String> getmList(){
        return mList;
    }
//    public int getAspanwRate(){
//        return this.mAspawnRate;
//    }
//    public int getBspanwRate(){
//        return this.mBspawnRate;
//    }
//    public int getCspanwRate(){
//        return this.mCspawnRate;
//    }
//    public int getAmaxCount(){
//        return this.mAmaxCount;
//    }
//    public int getBmaxCount(){
//        return this.mBmaxCount;
//    }
//    public int getCmaxCount(){
//        return this.mCmaxCount;
//    }
}
