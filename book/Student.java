
package book;

import java.util.Random;


public class Student  implements Runnable{

    int id;
    String name;
    Library library;
    int [] limit = new int [6];
    Random r = new Random();
    int requestedBook;
    int timeForRead;

    public  Student(int id ,String name,Library library){

        this.id = id;
        this.name = name;
        this.library = library;
        for(int i = 0;i<6;i++)
            limit[i] = 1;

    }
    ///to read the book
    public void readBook(int book) throws InterruptedException {

        timeForRead = r.nextInt(2000);
        System.out.println(name + " Reading book " +book + " in "+ timeForRead + " milliseconds");
        Thread.sleep(timeForRead);

        deliverBook(book);
    }
    //To get the book status
    public void takeBook(int book) throws InterruptedException {
        library.lockBooks[book].lock();
        library.books[book] = 0;
        limit[book] = 0;
        library.isReturnedBook[book] = false;
        readBook(book);
    }

    //For the student to want to hand over the book
    public void deliverBook(int book){
        System.out.println(name + " delivered book "+ book);
        library.isReturnedBook[book] = true;
        library.returnedBook[book] = 1;
        library.lockBooks[book].unlock();

    }
    @Override
    public void run() {
        //For students to read all the books
        while (limit[0] == 1 || limit[1] == 1 || limit[2] == 1 ||
                limit[3] == 1 || limit[4] == 1 || limit[5] == 1 ){

            try {
                requestedBook = r.nextInt(6);
                library.lockBooks[requestedBook].lock();
                //If it has not read the book and the book is available and not in the process of being delivered
                if(limit[requestedBook] == 1 && library.books[requestedBook] == 1 && !library.isReturnedBook[requestedBook]) {
                    library.requestedBook = requestedBook;
                    takeBook(requestedBook);
                }
                library.lockBooks[requestedBook].unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        library.isAllStudentRead.set(id,true);
        System.out.println(name +" read all books");



    }
}
