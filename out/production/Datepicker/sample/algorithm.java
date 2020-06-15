import java.math.BigDecimal;
import java.util.ArrayList;

public class algorithm {

    public static void main(String args[]){
        // what is the correct way of dealing with it ? tax goes into standard pay ?

        //standing charge - everyone pays regardless
        //tax on the standing charge - everyone pays

        //usage
        //tax on usage

        System.out.println("ts");

        ArrayList<User> housemates = new ArrayList<>();
        housemates.add( new User("john", 34));
        housemates.add(new User("mark", 28));
        housemates.add(new User("victor", 34));
        housemates.add(new User("olivia", 32));
        housemates.add(new User("emma", 22));

        double billAmount = 234.76;

        int totalDays=0;
        for (User r:housemates) {
            totalDays += r.daysStayed;
        }
        System.out.println(totalDays);

        double billPerDay = billAmount / totalDays;

        for (User g:housemates) {
            g.calcBill(billPerDay);
            System.out.println(g.toString()); // keep on forgetting to put sout ontop of toString
        }


        //does the bills split add up to the bill - no rounding errors ?
        double billSplit = 0;
        for (User g:housemates) {
            billSplit += g.getBill();
        }
        //have everyones total days stayed / divide by / bill


    }

    /**
     * Bill split that divides the bill based on the number of days a person lived in the house
     */
    public static void daySplit(){

    }

    /**
     * Bill split that divides the bill based on the number of people in the house
     */
    public static void equalSplit(){

    }

    /**
     * Bill split that divides the bill based on the usage in the house, taking into account standing charges and tax
     */
    public static void Split(){

    }

    public static class User{

        String name;
        double bill;

        int daysStayed;

        public User(){
            name="k";
            bill=0;
            daysStayed=0;
        }

        public User(String name, int daysStayed){
            this.name = name;
            this.bill = 0;
            this.daysStayed = daysStayed;
        }

        public String toString(){
            return "Name: " + name + ", Bill: " + bill + ", Days: " + daysStayed;
        }

        public int getDays(){
            return daysStayed;
        }

        public double getBill(){
            return bill;
        }

        public void calcBill(double billPerDay){

            double billUNRounded = billPerDay * daysStayed; //round up to two decimal places
            BigDecimal bigDecimal = new BigDecimal(bill);
            BigDecimal bill3 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);   ////CONSIDER keeping BigDecimal used the whole time as its most accurate
            this.bill = bill3.doubleValue();


        }

    }

}