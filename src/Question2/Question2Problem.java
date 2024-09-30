package Question2;

//Q2
//Detect potential functional and execution issues:
//void transfer(Account from, Account to, int amount) {
//    synchronized(from){
//        synchronized(to) {
//            from.debit(amount);
//            to.credit(amount);
//        }
//    }
//    Expected answers:
//    1.	Validate amount >= 0
//    2.	Check for overdraft
//    3.	Deadlock


class Account {
    int amount;
    Account to;
    Account from;

    public int getBalance() {
        return amount;
    }

    public void debit(int amt) {
        //return this.amount-amt;
    }

    public void credit(int amount) {
    }
}

public class Question2Problem {

    void transfer(Account to,Account from,int amount){

        //1.	Validate amount >= 0
        if(amount < 0){
            throw new IllegalArgumentException("Amount Cannot be Negative");
        }

        //    2.	Check for overdraft
        if(from.getBalance() < amount){
            throw new IllegalStateException("There Should be Sufficient Balance to Withdraw");
        }

        //    3.	Deadlock
        /* The synchronized blocks on both from and to accounts can lead to a deadlock
        if two threads try to transfer money between
        the same two accounts but in reverse order.*/

        /*
        To avoid deadlock is to always lock the accounts in a consistent order
        * */
        Account first = (from.hashCode() > to.hashCode()) ? from : to;
        Account second = (from.hashCode() < to.hashCode()) ? to:from;

        synchronized (first){
            synchronized (second){
                from.debit(amount);
                to.credit(amount);

            }
        }

    }
}
