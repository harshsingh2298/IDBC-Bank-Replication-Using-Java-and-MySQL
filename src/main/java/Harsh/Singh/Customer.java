package Harsh.Singh;

public class Customer
{
                                                         //   Attributes of Customer class
        private String name;
        private int mobNo;
        private int age;


    public Customer() {}                                     //   Blank Constructor

    public Customer(String name, int mobNo,int age) {
        this.name = name;
        this.mobNo = mobNo;
        this.age=age;

    }
        // Getters and Setters for attributes


        public int getAge() {return age;}

        public void setAge(int age) {this.age = age;}


        public String getName() {
        return name;
    }

        public void setName(String name) {
        this.name = name;
    }

        public int getMobNo() {
        return mobNo;
    }

        public void setMobNo(int mobNo) {
        this.mobNo = mobNo;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", mobNo=" + mobNo +
                ", age=" + age +
                '}';
    }
}
