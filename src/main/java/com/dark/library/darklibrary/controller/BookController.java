package com.dark.library.darklibrary.controller;

import com.dark.library.darklibrary.controller.openapi.BookOpenApiImpl;
import com.dark.library.darklibrary.request.BookRequest;
import com.dark.library.darklibrary.exception.BookConflictException;
import com.dark.library.darklibrary.exception.BookNotFoundException;
import com.dark.library.darklibrary.model.BookTypeModel;
import com.dark.library.darklibrary.request.BookTypeRequest;
import com.dark.library.darklibrary.response.Response;
import com.dark.library.darklibrary.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/dark-library")
@RestController
@CrossOrigin(origins = "*")
@Validated
public class BookController implements BookOpenApiImpl {
    @Autowired
    BookService bookService;

    @PostMapping("/create/book")
    public ResponseEntity<Object> saveBook(@Valid @RequestBody BookRequest bookRequest) throws BookConflictException, BookNotFoundException {
        bookService.save(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRequest);
    }


    @PutMapping("/alter/book")
    public ResponseEntity<Object> updateBook(@Valid @RequestBody BookRequest bookRequest) throws BookNotFoundException, BookConflictException {
        bookService.update(bookRequest);
        return ResponseEntity.status(HttpStatus.OK).body(bookRequest);
    }
    @DeleteMapping("/delete/book/{bookId}")
    public ResponseEntity<Object> deleteBook(@PathVariable Integer bookId) throws BookNotFoundException {
        bookService.delete(bookId);
        Response response = new Response(204, "Book with ID: " + bookId + " Deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
    @GetMapping("/search/book")
    public ResponseEntity<Object> readByIdBook(@RequestParam(required = true) Integer bookId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readById(bookId));
    }
    @GetMapping("/search/book/all")
    public ResponseEntity<Object> readAllBook() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readAll());
    }

    // ========== BookType Controller =========== //

    @PostMapping("/create/book/type")
    public ResponseEntity<Object> saveType(@RequestBody BookTypeRequest bookTypeRequest) throws BookConflictException {
        bookService.saveType(bookTypeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookTypeRequest);
    }
    @PutMapping("/alter/book/type")
    public ResponseEntity<Object> updateType(@RequestBody BookTypeRequest bookTypeRequest) throws BookConflictException, BookNotFoundException {
        bookService.updateType(bookTypeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(bookTypeRequest);
    }

    @DeleteMapping("/delete/book/type/{bookTypeId}")
    public ResponseEntity<Object> deleteType(@PathVariable (value = "bookTypeId" )Integer typeId) throws BookNotFoundException {
        bookService.deleteType(typeId);
        Response response = new Response(204, "Type ID: " + typeId + " deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
    @GetMapping("/search/book/type")
    public ResponseEntity<Object> readByIdType(@RequestParam Integer typeId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readByIdType(typeId));
    }
    @GetMapping("/search/book/type/all")
    public ResponseEntity<Object> readAllType() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readAllType());
    }
}
