package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookRequestDto bookRequestDto){
        try {
            int authorId=bookRequestDto.getAuthorId();
            Author author=authorRepository.findById(authorId).get();

            Book book=new Book();
            book.setGenre(bookRequestDto.getGenre());
            book.setIssued(false);
            book.setName(bookRequestDto.getName());
            book.setPages(bookRequestDto.getPages());

            book.setAuthor(author);
            List<Book> currentBooksWritten=author.getBooksWritten();
            currentBooksWritten.add(book);
            author.setBooksWritten(currentBooksWritten);

            authorRepository.save(author);
        }catch (Exception e){

        }


        return "Book added Successfully";
    }
}
