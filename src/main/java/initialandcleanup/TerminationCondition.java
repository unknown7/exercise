package initialandcleanup;

import java.util.concurrent.TimeUnit;

public class TerminationCondition {
    public static void main(String[] args) throws InterruptedException {
        Book book = new Book(true);
        book.checkIn();
        new Book(true);
        System.err.println("waiting for gc..");
        TimeUnit.SECONDS.sleep(2);
        System.gc();
    }
}
class Book {
    private boolean checkedOut;
    public Book(boolean checkOut) {
        checkedOut = checkOut;
    }
    public void checkIn() {
        this.checkedOut = false;
    }
    @Override
    protected void finalize() throws Throwable {
        if (checkedOut)
            System.err.println("Error, Book checked out!");
    }
}