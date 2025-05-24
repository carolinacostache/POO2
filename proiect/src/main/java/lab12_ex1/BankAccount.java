package main.java.lab12_ex1;

public class BankAccount {

    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", balance " + balance);
    }

    public synchronized void withdrawl(int amount) {
        if (amount <= balance){
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrawl " + amount + ", balance " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " withdrawl failed. not enough money in the account.");
        }
    }

    public int getBalance(){
        return balance;
    }
}
