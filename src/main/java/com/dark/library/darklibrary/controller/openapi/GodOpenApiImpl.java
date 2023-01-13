package com.dark.library.darklibrary.controller.openapi;

import com.dark.library.darklibrary.exception.BookConflictException;
import com.dark.library.darklibrary.exception.BookNotFoundException;
import com.dark.library.darklibrary.model.GodModel;
import com.dark.library.darklibrary.model.GodTypeModel;
import com.dark.library.darklibrary.request.GodRequest;
import com.dark.library.darklibrary.request.GodTypeRequest;
import com.dark.library.darklibrary.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface GodOpenApiImpl {
    @Operation(summary = "Create a new GOD")
    @ApiResponses(value = {
            @ApiResponse(description = "Operation FAILED. A God Type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "God created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodRequest.class))),
            @ApiResponse(description = "Operation FAILED. A God with this name has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Object> createGod(@RequestBody GodRequest godRequest) throws BookConflictException;

    @Operation(summary = "Create a new GOD")
    @ApiResponses(value = {
            @ApiResponse(description = "God updated", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodRequest.class))),
            @ApiResponse(description = "Operation FAILED. A God with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Operation FAILED. A God with this name has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Object> updateGod(@RequestBody GodRequest godRequest) throws BookNotFoundException, BookConflictException;

    @Operation(summary = "DELETE a god by ID")
    @ApiResponses(value = {
            @ApiResponse(description = "God deleted", responseCode = "200", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "No god was found with this ID", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Object> deleteGod(@PathVariable Integer godId) throws BookNotFoundException;

    @Operation(summary = "GET one god by ID")
    @ApiResponses(value = {
            @ApiResponse(description = "No god was found with this ID", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodModel.class)))
    })
    public ResponseEntity<Object> readByIdGod(@RequestParam Integer godId) throws BookNotFoundException;

    @Operation(summary = "GET all gods")
    @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodModel.class)))

    public ResponseEntity<Object> readAllGod();

    // ============== GOD TYPE ============== //

    @Operation(summary = "CREATE a new type of god")
    @ApiResponses(value = {
            @ApiResponse(description = "God type created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodTypeRequest.class))),
            @ApiResponse(description = "Operation FAILED. This book type has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class)) )
    })
    public ResponseEntity<Object> createGodType(@RequestBody GodTypeRequest godTypeRequest) throws BookConflictException;


    @Operation(summary = "UPDATE a existent type of god")
    @ApiResponses(value = {
            @ApiResponse(description = "God type updated", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodTypeRequest.class))),
            @ApiResponse(description = "Operation FAILED. This god type has already been registered", responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class)) ),
            @ApiResponse(description = "Operation FAILED. A god type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class)) )
    })
    public ResponseEntity<Object> updateGodType(@RequestBody GodTypeRequest godTypeRequest) throws BookNotFoundException, BookConflictException;

    @Operation(summary = "DELETE a type of god")
    @ApiResponses(value = {
            @ApiResponse(description = "God type deleted", responseCode = "200", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Operation FAILED. A book type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Object> deleteGodType(@PathVariable Integer godTypeId) throws BookNotFoundException;


    @Operation(summary = "GET a god type by ID")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodTypeModel.class))),
            @ApiResponse(description = "Operation FAILED. A god type with this ID was not found", responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Object> readByIdGodType(@RequestParam Integer godTypeId) throws BookNotFoundException;


    @Operation(summary = "GET all god types")
    @ApiResponses(value = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GodTypeModel.class)))
    })
    public ResponseEntity<Object> readAllGodType();
    }
