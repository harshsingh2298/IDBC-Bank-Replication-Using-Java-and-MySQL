package Harsh.Singh;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;
public class Impl {
    public static void main(String[] args) {
        Operation operation = new Operation();
        operation.createAccount();
        operation.typeOfTransaction();
        operation.fundTransfer();
    }
}
