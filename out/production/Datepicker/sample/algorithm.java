public class algorithm {

    public static void main(String args[]){
        // what is the correct way of dealing with it ? tax goes into standard pay ?

        //standing charge - everyone pays regardless
        //tax on the standing charge - everyone pays

        //usage
        //tax on usage

        System.out.println("ts");
        User j = new User("john", 22);
        System.out.println(j.toString());

        //have everyones total days stayed / divide by / bill


    }

    public static class User{

        String name;
        int bill;

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
            return name + "," + bill;
        }

    }

}