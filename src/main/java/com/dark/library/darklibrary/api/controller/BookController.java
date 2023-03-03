package com.dark.library.darklibrary.api.controller;

import com.dark.library.darklibrary.api.request.BookRequest;
import com.dark.library.darklibrary.api.controller.openapi.BookOpenApiImpl;
import com.dark.library.darklibrary.api.util.MediaType;
import com.dark.library.darklibrary.api.exception.BookConflictException;
import com.dark.library.darklibrary.api.exception.BookNotFoundException;
import com.dark.library.darklibrary.api.request.BookTypeRequest;
import com.dark.library.darklibrary.api.response.Response;
import com.dark.library.darklibrary.domain.service.BookService;
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

    @PostMapping(value = "/create/book", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> saveBook(@Valid @RequestBody BookRequest bookRequest) throws BookConflictException, BookNotFoundException {
        bookService.save(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRequest);
    }


    @PutMapping(value = "/alter/book", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
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
    @GetMapping(value = "/search/book", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> readByIdBook(@RequestParam(required = true) Integer bookId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readById(bookId));
    }
    @GetMapping(value = "/search/book/all", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> readAllBook() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readAll());
    }

    // ========== BookType Controller =========== //

    @PostMapping(value = "/create/book/type", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> saveType(@RequestBody BookTypeRequest bookTypeRequest) throws BookConflictException {
        bookService.saveType(bookTypeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookTypeRequest);
    }
    @PutMapping(value = "/alter/book/type", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
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
    @GetMapping(value = "/search/book/type", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML} )
    public ResponseEntity<Object> readByIdType(@RequestParam Integer typeId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readByIdType(typeId));
    }
    @GetMapping(value = "/search/book/type/all", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> readAllType() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.readAllType());
    }
}
