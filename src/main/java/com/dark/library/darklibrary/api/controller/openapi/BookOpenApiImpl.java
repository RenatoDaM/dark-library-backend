package com.dark.library.darklibrary.api.controller.openapi;

import com.dark.library.darklibrary.domain.exception.BookNotFoundException;
import com.dark.library.darklibrary.api.request.BookRequest;
import com.dark.library.darklibrary.api.request.BookTypeRequest;
import com.dark.library.darklibrary.api.response.ErrorResponse;
import com.dark.library.darklibrary.domain.exception.BookConflictException;
import com.dark.library.darklibrary.domain.model.BookModel;
import com.dark.library.darklibrary.domain.model.BookTypeModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface BookOpenApiImpl {
    @Operation(summary = "CREATE a new book")
    @ApiResponses(value = {
            @ApiResponse(description = "Operation FAILED. God or Book Type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Operation FAILED. A book with this name has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Book created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookRequest.class)))
    })
    public ResponseEntity<Object> saveBook(@Valid @RequestBody BookRequest bookRequest) throws BookConflictException, BookNotFoundException;


    @Operation(summary = "UPDATE a book. This request gonna take the id from the object and update the book with same id in database")
    @ApiResponses(value = {
            @ApiResponse(description = "Book updated", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookRequest.class))),
            @ApiResponse(description = "Operation FAILED. A book with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Operation FAILED. A book with this name has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Object> updateBook(@Valid @RequestBody BookRequest bookRequest) throws BookNotFoundException, BookConflictException;


    @Operation(summary = "DELETE a book by ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Book deleted", responseCode = "200", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "No book was found with this ID", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Object> deleteBook(@PathVariable Integer bookId) throws BookNotFoundException;


    @Operation(summary = "GET one book by ID")
    @ApiResponses(value = {
            @ApiResponse(description = "No book was found with this ID", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookModel.class)))
    })
    public ResponseEntity<Object> readByIdBook(@RequestParam(required = true) Integer bookId) throws BookNotFoundException;


    @Operation(summary = "GET all books")
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookModel.class)))
    public ResponseEntity<Object> readAllBook();


    // ========== BookType Controller =========== //

    @Operation(summary = "CREATE a new type of book")
    @ApiResponses(value = {
            @ApiResponse(description = "Book type created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookTypeRequest.class))),
            @ApiResponse(description = "Operation FAILED. This book type has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class)) )
    })
    public ResponseEntity<Object> saveType(@RequestBody BookTypeRequest bookTypeRequest) throws BookConflictException;


    @Operation(summary = "UPDATE a existent type of book")
    @ApiResponses(value = {
            @ApiResponse(description = "Book type updated", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookTypeRequest.class))),
            @ApiResponse(description = "Operation FAILED. This book type has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class)) ),
            @ApiResponse(description = "Operation FAILED. A book type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class)) )
    })
    public ResponseEntity<Object> updateType(@RequestBody BookTypeRequest bookTypeRequest) throws BookConflictException, BookNotFoundException;


    @Operation(summary = "DELETE a type of book")
    @ApiResponses(value = {
            @ApiResponse(description = "Book type deleted", responseCode = "200", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Operation FAILED. A book type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Object> deleteType(@PathVariable (value = "bookTypeId" )Integer typeId) throws BookNotFoundException;


    @Operation(summary = "GET a book type by ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookTypeModel.class))),
            @ApiResponse(description = "Operation FAILED. A book type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Object> readByIdType(@RequestParam Integer typeId) throws BookNotFoundException;

    @Operation(summary = "GET all book types")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookTypeModel.class)))
    })
    public ResponseEntity<Object> readAllType();
}
