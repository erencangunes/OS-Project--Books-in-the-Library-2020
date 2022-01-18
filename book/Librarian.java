
package book;

import java.util.Random;

public class Librarian implements Runnable {

    int id;
    String name;
    Library library;
    Random r = new Random();
    int random;

    public Librarian(int id, String name, Library library) {
        this.id = id;
        this.name = name;
        this.library = library;
        this.random = -1;
    }

    ///For the librarian to put the book on the shelf
    public void receiveBook() throws InterruptedException {
        while (true) {
            Thread.sleep(r.nextInt(2000));
            random = r.nextInt(6);
            library.lockBooks[random].lock();
            //If a book is to be returned
            if (library.returnedBook[random] == 1 && library.isReturnedBook[random] ) {
                    library.books[random] = 1;
                    library.isReturnedBook[random] = false;
                System.out.println(name + " received book " + random );

            }
            library.lockBooks[random].unlock();
            ///If all the students have read all the books and got their return, the librarian is done. 
           if(!library.isAllStudentRead.contains(false) && library.books[0] == 1 &&
                   library.books[1] == 1 && library.books[2] == 1 && library.books[3] == 1 && library.books[5] == 1) {
               System.out.println(name+" 's work is done");
               break;
           }
        }
    }

    @Override
    public void run() {


        try {
            receiveBook();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
