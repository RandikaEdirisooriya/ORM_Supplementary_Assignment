package lk.ijse;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Entity.Author;
import lk.ijse.Entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Author authorOne = new Author();
        authorOne.setId("A001");
        authorOne.setName("Martin Wickramasinghe");

        Book bookOne = new Book();
        bookOne.setId("B001");
        bookOne.setTitle("Madolduwa");
        bookOne.setPublicationYear(2009);
        bookOne.setPrice(2000.00);
        bookOne.setAuthor(authorOne);

        Book bookTwo = new Book();
        bookTwo.setId("B002");
        bookTwo.setTitle("Gamperaliya ");
        bookTwo.setPublicationYear(2011);
        bookTwo.setPrice(2000.00);
        bookTwo.setAuthor(authorOne);

        List<Book> books = new ArrayList<>();
        books.add(bookOne);
        books.add(bookTwo);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Save author and books
       /* session.save(authorOne);
        for (Book book : books) {
            session.save(book);
        }*/

        // 01. HQL to get books published after 2010
        String hql = "FROM Book b WHERE b.publicationYear > 2010";
        Query<Book> query = session.createQuery(hql, Book.class);
        List<Book> bookList = query.getResultList();
        System.out.println("Books published after 2010: " + bookList);

        // 02. Update prices of books for a specific author
        String hqlUpdate = "UPDATE Book b SET b.price = b.price * 1.1 WHERE b.author = :author";
        Query queryUpdate = session.createQuery(hqlUpdate);
        queryUpdate.setParameter("author", authorOne);
        int rowCount = queryUpdate.executeUpdate();
        System.out.println("Number of books updated: " + rowCount);

        // 03. Delete author and associated books
       // deleteAuthorAndBooks(authorOne);





//04 find the average of book price
        Query queryAVG = session.createQuery("SELECT AVG(b.price) FROM Book b");
        Double averagePrice = (Double) queryAVG.uniqueResult();
        System.out.println("Average Price of All Books: " + averagePrice);



        //05 finding the book count of ech writter write
        Query queryAuthor = session.createQuery("SELECT a, COUNT(b) FROM Author a JOIN a.books b GROUP BY a.id");
        List<Object[]> result = queryAuthor.list();

        for (Object[] row : result) {
            Author author = (Author) row[0];
            Long bookCount = (Long) row[1];

            System.out.println("Author: " + author.getName() + ", Books Written: " + bookCount);
        }








        transaction.commit();
        session.close();
    }

    private static void deleteAuthorAndBooks(Author author) {


   /*thama naaa*/
    }
}