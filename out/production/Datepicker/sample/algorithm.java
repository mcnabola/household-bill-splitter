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

        //have everyones total days stayed / divide by / bill


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

        public void calcBill(double billPerDay){
            this.bill = billPerDay * daysStayed;
        }

    }

}