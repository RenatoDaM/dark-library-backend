package com.dark.library.darklibrary.api.controller;

import com.dark.library.darklibrary.api.request.GodRequest;
import com.dark.library.darklibrary.api.request.GodTypeRequest;
import com.dark.library.darklibrary.api.controller.openapi.GodOpenApiImpl;
import com.dark.library.darklibrary.api.response.ErrorResponse;
import com.dark.library.darklibrary.api.util.MediaType;
import com.dark.library.darklibrary.domain.exception.BookConflictException;
import com.dark.library.darklibrary.domain.exception.BookNotFoundException;
import com.dark.library.darklibrary.api.response.Response;
import com.dark.library.darklibrary.domain.service.GodService;
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
    @PostMapping(value = "/create/god", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> createGod(@RequestBody GodRequest godRequest) throws BookConflictException {
        godService.createGod(godRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(godRequest);
    }

    @PutMapping(value = "/alter/god", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> updateGod(@RequestBody GodRequest godRequest) throws BookNotFoundException, BookConflictException {
        godService.updateGod(godRequest);
        return ResponseEntity.status(HttpStatus.OK).body(godRequest);
    }

    @DeleteMapping("/delete/god/{godId}")
    public ResponseEntity<Object> deleteGod(@PathVariable Integer godId) throws BookNotFoundException {
        godService.deleteGod(godId);
        Response response = new Response(204, "God with ID: " + godId + " has returned to oblivion");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping(value = "/search/god", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> readByIdGod(@RequestParam Integer godId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readByIdGod(godId));
    }

    @GetMapping("/search/god/all")
    public ResponseEntity<Object> readAllGod() {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readAllGod());
    }


    // =========== GOD TYPE ========== //
    @PostMapping(value = "/create/god/type", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> createGodType(@RequestBody GodTypeRequest godTypeRequest) throws BookConflictException{
        godService.createGodType(godTypeRequest);
        Response response = new Response(201, "God " + godTypeRequest.getGodTypeName() + " created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/alter/god/type", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> updateGodType(@RequestBody GodTypeRequest godTypeRequest) throws BookNotFoundException, BookConflictException {
        godService.updateGodType(godTypeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(godTypeRequest);
    }

    @DeleteMapping("/delete/god/type/{godTypeId}")
    public ResponseEntity<Object> deleteGodType(@PathVariable Integer godTypeId) throws BookNotFoundException {
        godService.deleteGodType(godTypeId);
        ErrorResponse errorResponse = new ErrorResponse(204, "God type ID " + godTypeId + " deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }

    @GetMapping(value = "/search/god/type", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> readByIdGodType(@RequestParam Integer godTypeId) throws BookNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readByIdGodType(godTypeId));
    }

    @GetMapping(value = "/search/god/type/all", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ResponseEntity<Object> readAllGodType() {
        return ResponseEntity.status(HttpStatus.OK).body(godService.readAllGodType());
    }


}
