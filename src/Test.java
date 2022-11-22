public class Test {

    public static void main(String[] args){

       Character obj = new Character(3,  "Bard");
       Character.Inventory test = new Character.Inventory(1, 1);
       System.out.println(obj.toString());
       System.out.println(test.toString());
       System.out.println("------------------------");
       System.out.println("Testing useHintpass() if count at least 1... \n");
       obj.add();
       obj.displayQuestion();
       obj.hasLifeline();
       System.out.println("------------------------");

       Character obj2 = new Character(3,  "Bard");
       System.out.println(test.toString());
       System.out.println("------------------------");
       System.out.println("Testing useHintpass() if count = 0... \n");
       obj2.add();
       obj2.displayQuestion();
       obj2.hasLifeline();



    }
}
