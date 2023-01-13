package com.dark.library.darklibrary.controller;

import com.dark.library.darklibrary.controller.openapi.GodOpenApiImpl;
import com.dark.library.darklibrary.request.GodRequest;
import com.dark.library.darklibrary.request.GodTypeRequest;
import com.dark.library.darklibrary.response.ErrorResponse;
import com.dark.library.darklibrary.exception.BookConflictException;
import com.dark.library.darklibrary.exception.BookNotFoundException;
import com.dark.library.darklibrary.model.GodModel;
import com.dark.library.darklibrary.model.GodTypeModel;
import com.dark.library.darklibrary.response.Response;
import com.dark.library.darklibrary.service.GodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/dark-library")
@RestController
@CrossOrigin(origins = "*")
@RestControllerAdvice
public class GodController implements GodOpenApiImpl {
    @Autowired
    GodService godService;

    // NECESSÁRIO USAR UM RESPONSE PRA USAR CONSTRAINTS

    // ============ GOD ============== //
    @PostMapping("/create/god")
    public ResponseEntity<Object> createGod(@RequestBody GodRequest godRequest) throws BookConflictException {
        godService.createGod(godRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(godRequest);
    }

    @PutMapping("/alter/god")
    public ResponseEntity<Object> updateGod(@RequestBody GodRequest godRequest) throws BookNotFoundException, BookConflictException {
        godService.updateGod(godRequest);
        return ResponseEntity.status(HttpStatus.OK).body(godRequest);
    }

    @DeleteMapping("/delete/god/{godId}")
    public ResponseEntity<Object> deleteGod(@PathVariable Integer godId) throws BookNotFoundException {
        godService.deleteGod(godId);
        Response response = new Response(200, "God with ID: " + godId + " has returned to oblivion");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/search/god")
    public ResponseEntity<Object> readByIdGod(@RequestParam Integer godId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readByIdGod(godId));
    }

    @GetMapping("/search/god/all")
    public ResponseEntity<Object> readAllGod() {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readAllGod());
    }


    // =========== GOD TYPE ========== //
    @PostMapping("/create/god/type")
    public ResponseEntity<Object> createGodType(@RequestBody GodTypeRequest godTypeRequest) throws BookConflictException{
        godService.createGodType(godTypeRequest);
        Response response = new Response(201, "God " + godTypeRequest.getGodTypeName() + " created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/alter/god/type")
    public ResponseEntity<Object> updateGodType(@RequestBody GodTypeRequest godTypeRequest) throws BookNotFoundException, BookConflictException {
        godService.updateGodType(godTypeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(godTypeRequest);
    }

    @DeleteMapping("/delete/god/type/{godTypeId}")
    public ResponseEntity<Object> deleteGodType(@PathVariable Integer godTypeId) throws BookNotFoundException {
        godService.deleteGodType(godTypeId);
        ErrorResponse errorResponse = new ErrorResponse(200, "God type ID " + godTypeId + " deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
    }

    @GetMapping("/search/god/type")
    public ResponseEntity<Object> readByIdGodType(@RequestParam Integer godTypeId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readByIdGodType(godTypeId));
    }

    @GetMapping("/search/god/type/all")
    public ResponseEntity<Object> readAllGodType() {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readAllGodType());
    }


}
