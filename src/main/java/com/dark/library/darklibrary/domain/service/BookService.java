package com.dark.library.darklibrary.domain.service;

import com.dark.library.darklibrary.api.controller.BookController;
import com.dark.library.darklibrary.domain.dao.BookDAO;
import com.dark.library.darklibrary.domain.dao.BookTypeDAO;
import com.dark.library.darklibrary.domain.exception.BookConflictException;
import com.dark.library.darklibrary.domain.exception.BookNotFoundException;
import com.dark.library.darklibrary.domain.model.BookModel;
import com.dark.library.darklibrary.domain.model.BookTypeModel;
import com.dark.library.darklibrary.api.request.BookRequest;
import com.dark.library.darklibrary.api.request.BookTypeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {
    Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookTypeDAO bookTypeDAO;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    GodService godService;

    public void save(BookRequest bookRequest) throws BookConflictException, BookNotFoundException {

        if (!existsByBookTypeId(bookRequest.getBookType())) {
            log.error("Operation FAILED. A Book Type with ID: {} not found", bookRequest.getBookType());
            throw new BookNotFoundException("Book Type with ID: "+ bookRequest.getBookType() +" not found");
        }

        if (!godService.existsByGodTypeId(bookRequest.getBookGod())) {
            log.error("Operation FAILED. A God with ID: {} not found", bookRequest.getBookGod());
            throw new BookNotFoundException("God with ID: "+ bookRequest.getBookGod() +" not found");
        }

        BookModel bookModel = bookRequest.convertToEntity(new BookModel());

        if (existsByBookName(bookModel)) {
            log.error("Operation FAILED. A book with name: {} has already been registered", bookModel.getBookName());
            throw new BookConflictException("Operation FAILED. A book with name: " + bookModel.getBookName() + " has already been registered");
        }

         bookDAO.save(bookModel);
    }

    public void update(BookRequest bookRequest) throws BookNotFoundException, BookConflictException {
        BookModel bookModel = bookRequest.convertToEntity(new BookModel());
        if (!existsByBookId(bookModel.getBookId())) {
            log.error("Update FAILED. Book with id: {} not found", bookModel.getBookId());
            throw new BookNotFoundException("Update FAILED. Book with id: "+ bookModel.getBookId() +" was not found");
        }
        if (existsByBookName(bookModel)) {
            log.error("Operation FAILED. A book with name: {} has already been registered", bookModel.getBookName());
            throw new BookConflictException("Operation FAILED. A book with name: " + bookModel.getBookName() + " has already been registered");
        }
        bookDAO.update(bookModel);
    }

    public void delete(Integer bookId) throws BookNotFoundException {
        if (!existsByBookId(bookId)) {
            log.error("Operation FAILED. No book with id {} found", bookId);
            throw new BookNotFoundException("Operation FAILED. No book with id "+ bookId +" found");
        }
        bookDAO.delete(bookId);
    }

    public BookModel readById(Integer bookId) throws BookNotFoundException {
        if (!existsByBookId(bookId)) {
            log.error("Operation FAILED. Book with id: {} not found.", bookId);
            throw new BookNotFoundException("Operation FAILED. Book with id: " + bookId + " was not Found");
        }
        return bookDAO.readById(bookId);
    }

    public List<BookModel> readAll() {
        return bookDAO.readAll();
    }


    public boolean existsByBookName (BookModel bookModel) {
        return bookDAO.readAll().stream()
                .anyMatch(e -> e.getBookName().equalsIgnoreCase(bookModel.getBookName()));
    }

    public boolean existsByBookId(Integer bookId) {
        return bookDAO.readAll().stream().anyMatch(e -> e.getBookId().equals(bookId));
    }

    // ============ TYPE ============ //

    public void saveType(BookTypeRequest bookTypeRequest) throws BookConflictException {
        BookTypeModel bookTypeModel = bookTypeRequest.convertToEntity(new BookTypeModel());
        if(!existsByBookType(bookTypeModel)) {
            log.error("Operation FAILED. A book type with name: {} has already been registered", bookTypeModel.getBookTypeName());
            throw new BookConflictException("Operation FAILED. This book type: " + bookTypeModel.getBookTypeName() + " has already been registered");
        }
        bookTypeDAO.save(bookTypeModel);
    }

    public void updateType(BookTypeRequest bookTypeRequest) throws BookNotFoundException, BookConflictException {
        BookTypeModel bookTypeModel = bookTypeRequest.convertToEntity(new BookTypeModel());
        if (!existsByBookTypeId(bookTypeModel.getBookTypeId())) {
            log.error("Update FAILED. Book type with id: {} not found", bookTypeModel.getBookTypeId());
            throw new BookNotFoundException("Update FAILED. Book  type with id: "+ bookTypeModel.getBookTypeId() +" was not found");
        }
        if (existsByBookType(bookTypeModel)) {
            log.error("Operation FAILED. A book type with name: {} has already been registered", bookTypeModel.getBookTypeName());
            throw new BookConflictException("Operation FAILED. This book type: " + bookTypeModel.getBookTypeName() + " has already been registered");
        }
        bookTypeDAO.update(bookTypeModel);
    }

    public void deleteType(Integer bookTypeId) throws BookNotFoundException {
        if (!existsByBookTypeId(bookTypeId)) {
            log.error("Operation FAILED. Book type with id: {} not found.", bookTypeId);
            throw new BookNotFoundException("Operation FAILED. Book type with id: " + bookTypeId + " was not Found");
        }
        bookTypeDAO.delete(bookTypeId);
    }

    public BookTypeModel readByIdType(Integer bookTypeId) throws BookNotFoundException {
        if (!existsByBookTypeId(bookTypeId)) {
            log.error("Operation FAILED. Book with id: {} not found.", bookTypeId);
            throw new BookNotFoundException("Operation FAILED. Book type with id:"+ bookTypeId +" was not Found");
        }
        return bookTypeDAO.readById(bookTypeId);
    }

    public List<BookTypeModel> readAllType() {
        return bookTypeDAO.readAll();
    }

    public boolean existsByBookType (BookTypeModel bookTypeModel) {
        return bookTypeDAO.readAll().stream()
                .anyMatch(e -> e.getBookTypeName().equals(bookTypeModel.getBookTypeName()));
    }

    public boolean existsByBookTypeId(Integer bookTypeId) {
        return bookTypeDAO.readAll().stream()
                .anyMatch(e -> e.getBookTypeId().equals(bookTypeId));
    }
}
