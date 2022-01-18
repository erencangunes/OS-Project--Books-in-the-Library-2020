
package book;


public class Main {

    static Library library = new Library();

    public static void main(String [] args) throws InterruptedException {
        Student  [] students = new Student[40];
        Librarian  [] librarians = new Librarian[3];
        Thread [] threads = new Thread[43];



        for(int i =0;i<40;i++)
        {
            students[i] = new Student(i,"student"+String.valueOf(i),library);
            threads[i] = new Thread(students[i]);
        }
        int a = 0;
        for(int i=40;i<43;i++){
            librarians[a] = new Librarian(a,"Librarian"+String.valueOf(a),library);
            threads[i] = new Thread(librarians[a]);
            a++;
        }

        for(int i = 0;i<43;i++) {
            threads[i].start();
        }

    }

}
