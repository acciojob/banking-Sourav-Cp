package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        StringBuilder sb = new StringBuilder();
        int rem = sum;

        if(9*digits > sum)
        {
            throw new Exception("Account Number can not be generated");
        }
        else
        {
            while(digits > 0 && rem > 0)
            {
                if(rem >= 9)
                {
                    sb.append("9");
                    rem = rem - 9;
                }
                else
                {
                    sb.append(Integer.toString(rem));
                    rem = 0;
                }
                digits--;
            }
            while(digits > 0)
            {
                sb.append("0");
                digits--;
            }
        }

        return sb.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
       balance = balance + amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance

        if(balance - amount >= minBalance)
        {
            balance = balance - amount;
        }

        else throw new Exception("Insufficient Balance");
    }

}