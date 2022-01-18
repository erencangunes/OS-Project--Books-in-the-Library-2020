package book;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {

    Lock[] lockBooks = new Lock[6];
    int [] books = new int[6];

    int  requestedBook;
    int [] returnedBook = new int[6];
    boolean [] isReturnedBook = new boolean[6];
    int random;

    ArrayList<Boolean> isAllStudentRead = new ArrayList<>(40);


    public  Library(){

        for (int i=0;i<6;i++) {
            lockBooks[i] = new ReentrantLock();
            returnedBook[i] = 0;
            books[i] = 1;
            isReturnedBook[i] = false;
        }
        for (int i = 0;i<40;i++)
            isAllStudentRead.add(i,false);

            requestedBook = -1;
            random = -1;



    }


}
