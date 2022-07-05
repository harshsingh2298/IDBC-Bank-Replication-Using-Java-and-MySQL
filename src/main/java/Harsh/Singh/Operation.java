package Harsh.Singh;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Operation {
    Bank bank = new Bank();
    Random random = new Random();
    int transId = random.nextInt(9999);

    public long getRandomAccountNo() {
        long accountNo = Math.round(random.nextFloat() * Math.pow(10, 12));
        bank.setAccountNo(accountNo);
        return accountNo;
    }
//******************************************************************** New User ********************************************************************************************************************************************************************************************

    public void createAccount() {
        long accountNo = this.getRandomAccountNo();

        Random rnd = new Random();
        int userId = rnd.nextInt(999999);
        System.out.println("*******************************************************************************************************************************************");
        System.out.println("--------------------------------------------------- Welcome To I.D.B.C Bank ------------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root", "Harsh@12345");
            System.out.println(" Select Your Choice   \n 1. New User \n 2. Existing User ");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("--------------------------------------------------------- New User ----------------------------------------------------------------------");
                System.out.println();
                System.out.println(" Enter Your  Full Name ");
                String name = sc.next();
                sc.nextLine();
                System.out.println(" Enter Your Mobile Number ");
                long mobile = sc.nextLong();
                System.out.println(" Enter Your Age ");
                int age = sc.nextInt();
                sc.nextLine();
                if (age < 18) {
                    System.out.println(" Sorry You are Under Age ");
                    return;
                }
                System.out.println(" Do You Want To Deposit Some Amount ");
                System.out.println(" 1. yes \n 2. or Yes");
                int c = sc.nextInt();
                if (c == 1) {
                    System.out.println(" Please Enter Amount ");
                    long balance = sc.nextLong();
                    System.out.println(" Please enter account type You want to open \n 1. Saving Ac  \n 2. Pay Ac ");
                    String accountType = sc.next();
                    sc.nextLine();
                    String query = "insert into Banking values(" + accountNo + "," + balance + ",'" + accountType + "')";
                    Statement st = con.createStatement();
                    int rows = st.executeUpdate(query);
                    // System.out.println(" Amount Deposited Successfully ");
                    System.out.println();
                    String query1 = "insert into customers values(" + userId + ",'" + name + "'," + mobile + "," + age + "," + accountNo + ")";
                    Statement st1 = con.createStatement();
                    int rows1 = st.executeUpdate(query1);
                    System.out.println(" -*~*- Congratulation Account Created Successfully -*~*- ");
                }
            }


//*************************************************************** Existing User *******************************************************************************************************************************************************************************


            if (choice == 2) {
                System.out.println("------------------------------------------------------ Existing Customer ---------------------------------------------------------------------");
                System.out.println();
                System.out.println("----------------------------------------------------- Showing Customer Data ------------------------------------------------------------------");
                System.out.println(" Enter Your User ID ");
                String user = sc.next();
                sc.nextLine();
                String querry1 = "select * from customers where userId like " + user;
                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(querry1);
                if (rs1.next()) {
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(" WELCOME USER ----> " + rs1.getInt("userId") + "\t" + rs1.getString(2) + "\t" +
                            rs1.getLong(3) + "\t" + rs1.getInt(4));
                } else
                    System.out.println(" Invalid User ID Please Try again ");
            }
        } catch (Exception e) {
            System.out.println("Error---" + e);
        }
    }

//************************************************************** Deposit  And Withdraw *****************************************************************************************************************************************************************************************


    public void typeOfTransaction() {


        while (true) {
            System.out.println("**********************************************************************************************************************************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.println(" Please Enter Your Choice \n  1. Deposit  \n  2. Withdraw  \n  3. Check Available Balance \n  4. Fund Transfer \n  5. Exit \n  6. Statement  ");
            int choice = sc.nextInt();
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root", "Harsh@12345");
                if (choice == 1) {

                    System.out.println("****************************************************************************************************************************************************************************");
                    System.out.println(" Please Enter Your Account Number ");
                    long acc = sc.nextLong();
                    sc.nextLine();
                    System.out.println(" Please Enter The Amount You Want To Deposit ");
                    long amount = sc.nextLong();
                    sc.nextLine();
                    String accountType = "Deposit";
                    sc.nextLine();
                    String query = " update Banking  set balance = (balance + " + amount + ") where  accountNo =" + acc;
                    Statement st = con.createStatement();
                    int rows = st.executeUpdate(query);
                    Timestamp timestamp= new Timestamp(System.currentTimeMillis());
                    String query11 = "insert into Statement values(" + acc + ",'" + transId + "','" + accountType + "', " + amount + ",'"+ timestamp +"')";
                    Statement st11 = con.createStatement();
                    int rows11 = st11.executeUpdate(query11);
                    System.out.println(" -*- Amount Deposited Successfully -*- ");

                    System.out.println("******************************************************************************************************************************************************************************");
                }

//******************************************* Withdraw *********************************************************************************************************************************************************************************************************************

                if (choice == 2) {
                    System.out.println(" Please Enter Your Account Number ");
                    long acc1 = sc.nextLong();
                    sc.nextLine();
                    String query1 = "select * from Banking where  accountNo =" + acc1;
                    Statement st1 = con.createStatement();
                    ResultSet rows1 = st1.executeQuery(query1);
                    long balance = 0;
                    while (rows1.next()) {
                        balance = rows1.getLong("balance");

                    }
                    System.out.println("Available Balance is --> " + balance);
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(" Please Enter The Amount You Want To Transfer ");
                    long amount1 = sc.nextLong();
                    String accountType = "Withdraw";

                    if (balance < amount1) {
                        System.out.println(" Insufficient Balance in your account ");
                    } else {
                        String query2 = " update Banking  set balance = (balance - " + amount1 + ") where  accountNo =" + acc1;
                        Statement st = con.createStatement();
                        int rows = st.executeUpdate(query2);
                        Timestamp timestamp= new Timestamp(System.currentTimeMillis());
                        String query11 = "insert into Statement values(" + acc1 + ",'" + transId + "','" + accountType + "', " + amount1 + ",'"+ timestamp +"')";
                        Statement st11 = con.createStatement();
                        int rows11 = st11.executeUpdate(query11);
                        System.out.println(" -*- Amount Deducted Successfully -*- ");
                    }

                }
                if (choice == 3) {
                    System.out.println(" Please Enter Your Account Number ");
                    long acc1 = sc.nextLong();
                    sc.nextLine();
                    String query1 = "select * from Banking where  accountNo =" + acc1;
                    Statement st1 = con.createStatement();
                    ResultSet rows1 = st1.executeQuery(query1);
                    long balance = 0;
                    while (rows1.next()) {
                        balance = rows1.getLong("balance");

                    }
                    System.out.println("Available Balance is --> " + balance);
                    return;
                }
                if (choice == 4) {
                    this.fundTransfer();
                }
                if (choice == 5) {
                    break;
                }
                if (choice == 6) {
                    System.out.println(" Enter Your Transaction ID ");
                    int transid = sc.nextInt();
                    String queryy = "select * from statement where  transId =" + transid;
                    Statement st122 = con.createStatement();
                    ResultSet rows1 = st122.executeQuery(queryy);
                    ResultSet rs1 = st122.executeQuery(queryy);
                    if (rs1.next()) {
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println(" YOUR STATEMENT ------> " + rs1.getLong("accountNo") + "\t" + rs1.getInt(2) + "\t" +
                                rs1.getString(3) + "\t" + rs1.getLong(4) + "\t" + rs1.getTimestamp(5));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error---" + e);
            }
        }
    }

//******************************************** Fund Transfer *************************************************************************************************************************************************************************************************


        public void fundTransfer () {

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC", "root", "Harsh@12345");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("  <-*~*-> Welcome To I.D.B.C Bank Fund Transfer Services <-*~*->  ");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                Scanner sc = new Scanner(System.in);
                System.out.println(" Please Enter Your Account Number To check sufficient Balance For Fund Transfer  ");
                long acc1 = sc.nextLong();
                sc.nextLine();
                String query1 = "select * from Banking where  accountNo =" + acc1;
                Statement st1 = con.createStatement();
                ResultSet rows1 = st1.executeQuery(query1);
                long balance = 0;
                while (rows1.next()) {
                    balance = rows1.getLong("balance");

                }
                System.out.println("Available Balance is --> " + balance);
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(" Please Enter Account Number To Which you want to Transfer ");
                long acc = sc.nextLong();
                System.out.println(" -*- Please Enter The Amount You Want To Withdraw -*- ");
                long amount1 = sc.nextLong();


                if (balance < amount1) {
                    System.out.println(" Insufficient Balance in your account ");
                } else {
                    String query2 = " update Banking  set balance = (balance + " + amount1 + ") where  accountNo =" + acc;
                    Statement st = con.createStatement();
                    int rows = st.executeUpdate(query2);
                    String query3 = " update Banking  set balance = (balance - " + amount1 + ") where  accountNo =" + acc1;
                    Statement st3 = con.createStatement();
                    int rows3 = st3.executeUpdate(query3);
                    System.out.println(" Please enter  \n 1. fundTransfer ");
                    String choice = " fundTransfer ";

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        String query11 = "insert into Statement values(" + acc1 + ",'" + transId + "','" + choice+ "', " + amount1 + ",'" + timestamp + "')";
                        Statement st11 = con.createStatement();
                        int rows11 = st11.executeUpdate(query11);
                        System.out.println(" -*- Fund Transferred Successfully -*- ");
                    }

            } catch (Exception e) {
                System.out.println("Error---" + e);
            }
        }

}






