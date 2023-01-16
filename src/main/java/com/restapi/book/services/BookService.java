package com.restapi.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.restapi.book.dao.BookRepository;
import com.restapi.book.entities.Book;

@Component
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	//private static List<Book> list=new ArrayList<>();
	
//	static {
//		list.add(new Book(12,"Java complete reference","XYZ"));
//		list.add(new Book(36,"Head First to java","ABC"));
//		list.add(new Book(18,"Think in java","LMN"));
//	
//		}
	//get all books handler
	public List<Book>getAllBooks(){
	List<Book>list=	(List<Book>) this.bookRepository.findAll();
		return list;
	}
	//get single book handler
	public Book getBookById(int id) {
		Book book=null;
		try {
			this.bookRepository.findById(id);
		
			//book=list.stream().filter(e->e.getId()==id).findFirst().get();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	//new book handler
	public Book addBook(Book b) {
		//list.add(b);
		Book result=bookRepository.save(b);
		return result;
	}
	
	//deleted book handler
	public void deleteBook(int bookId) {
		
		//list=list.stream().filter(book->book.getId()!=bookId).collect(Collectors.toList());
		bookRepository.deleteById(bookId);		
		
	}
    //update the book
	public void updateBook(Book book,int bookId) {
		book.setId(bookId);
		bookRepository.save(book);
		
		
//		list=list.stream().map(b->{
//			if (b.getId()==bookId)
//			{
//				b.setTitle(book.getTitle());
//				b.setAuthor(b.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
}
}
