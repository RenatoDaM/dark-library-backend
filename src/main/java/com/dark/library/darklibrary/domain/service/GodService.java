package com.dark.library.darklibrary.domain.service;

import com.dark.library.darklibrary.domain.dao.GodDAO;
import com.dark.library.darklibrary.domain.dao.GodTypeDAO;
import com.dark.library.darklibrary.api.exception.BookConflictException;
import com.dark.library.darklibrary.api.exception.BookNotFoundException;
import com.dark.library.darklibrary.domain.model.GodModel;
import com.dark.library.darklibrary.domain.model.GodTypeModel;
import com.dark.library.darklibrary.api.request.GodRequest;
import com.dark.library.darklibrary.api.request.GodTypeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GodService {
    Logger log = LoggerFactory.getLogger(GodService.class);
    @Autowired
    GodDAO godDAO;
    @Autowired
    GodTypeDAO godTypeDAO;

    // =============== GOD =============== //
    public void createGod(GodRequest godRequest) throws BookConflictException {
        GodModel godModel = godRequest.convertToEntity(new GodModel());
        if (!existsByGodTypeId(godModel.getGodType())) {
            log.error("Operation FAILED. A God Type with ID: {} not found", godModel.getGodType());
            throw new BookConflictException("Operation FAILED. A God Type with ID: " + godModel.getGodType() + " was not found.");
        }

        if (existsByGodName(godModel)) {
            log.error("Operation FAILED. A God with name: {} has already been registered", godModel.getGodName());
            throw new BookConflictException("Operation FAILED. A God with name: " + godModel.getGodName() + "has already been registered.");
        }
        godDAO.createGod(godModel);
    }

    public void updateGod(GodRequest godRequest) throws BookNotFoundException, BookConflictException {
        GodModel godModel = godRequest.convertToEntity(new GodModel());
        if (!existsByGodId(godModel.getGodId())) {
            log.error("Operation FAILED. A God with id: {} not found", godModel.getGodId());
            throw new BookNotFoundException("Operation FAILED. A God with id: " + godModel.getGodId() + " was not found");
        }
        if (existsByGodName(godModel)) {
            log.error("Operation FAILED. A God with name: {} has already been registered", godModel.getGodName());
            throw new BookConflictException("Operation FAILED. A God with name: "+ godModel.getGodName() +" has already been registered");
        }
        godDAO.updateGod(godModel);
    }

    public void deleteGod(Integer godId) throws BookNotFoundException {
        if (!existsByGodId(godId)) {
            log.error("Operation FAILED. A God with id: {} not found", godId);
            throw new BookNotFoundException("Operation FAILED. A God with id: " + godId + " was not found");
        }
        godDAO.deleteGod(godId);
    }

    public GodModel readByIdGod(Integer godId) throws BookNotFoundException {
        if (!existsByGodId(godId)) {
            log.error("Operation FAILED. A God with id: {} not found", godId);
            throw new BookNotFoundException("Operation FAILED. A God with id: " + godId + " was not found");
        }
        return godDAO.readByIdGod(godId);
    }

    public List<GodModel> readAllGod() {
        return godDAO.readAllGod();
    }

    public boolean existsByGodName(GodModel godModel) {
        return godDAO.readAllGod().stream()
                .anyMatch(e -> e.getGodName().equalsIgnoreCase(godModel.getGodName()));
    }

    public boolean existsByGodId(Integer godId) {
        return godDAO.readAllGod().stream()
                .anyMatch(e -> e.getGodId() == godId);
    }

    // ============ GOD TYPE ============= //
    public void createGodType(GodTypeRequest godTypeRequest) throws BookConflictException {
        GodTypeModel godTypeModel = godTypeRequest.convertToEntity(new GodTypeModel());
        if (existsByGodTypeName(godTypeModel)) {
            log.error("Operation FAILED. Type of god: {} has already been registered", godTypeModel.getGodTypeName());
            throw new BookConflictException("Operation FAILED. Type of god: "+  godTypeModel.getGodTypeName() +"has already been registered");
        }
        godTypeDAO.save(godTypeModel);
    }

    public void updateGodType(GodTypeRequest godTypeRequest) throws BookNotFoundException, BookConflictException {
        GodTypeModel godTypeModel = godTypeRequest.convertToEntity(new GodTypeModel());
        if (!existsByGodTypeId(godTypeModel.getGodTypeId())) {
            log.error("Operation FAILED. A God Type with id: {} was not found", godTypeModel.getGodTypeId());
            throw new BookNotFoundException("Operation FAILED. A God Type with id: " + godTypeModel.getGodTypeId() + " was not found");
        }
        if (existsByGodTypeName(godTypeModel)) {
            log.error("Operation FAILED. A God with name: {} has already been registered", godTypeModel.getGodTypeName());
            throw new BookConflictException("Operation FAILED. A God with name: "+ godTypeModel.getGodTypeName() +" has already been registered");
        }
        godTypeDAO.update(godTypeModel);
    }

    public void deleteGodType(Integer godTypeId) throws BookNotFoundException {
        if (!existsByGodTypeId(godTypeId)) {
            log.error("Operation FAILED. A God Type with id: {} was not found", godTypeId);
            throw new BookNotFoundException("Operation FAILED. A God Type with id: " + godTypeId + " was not found");
        }
        godTypeDAO.delete(godTypeId);
    }

    public GodTypeModel readByIdGodType(Integer godTypeId) throws BookNotFoundException {
        if (!existsByGodTypeId(godTypeId)) {
            log.error("Operation FAILED. A God Type with id: {} not found", godTypeId);
            throw new BookNotFoundException("Operation FAILED. A God Type with id: " + godTypeId + " was not found");
        }
        return godTypeDAO.readById(godTypeId);
    }

    public List<GodTypeModel> readAllGodType() {
        return godTypeDAO.readAll();
    }

    public boolean existsByGodTypeName(GodTypeModel godTypeModel) {
        return godTypeDAO.readAll().stream()
                .anyMatch(e -> godTypeModel.getGodTypeName().equalsIgnoreCase(e.getGodTypeName()));
    }

    public boolean existsByGodTypeId(Integer godTypeId) {
        return godTypeDAO.readAll().stream()
                .anyMatch(e -> e.getGodTypeId() == godTypeId);
    }
}
