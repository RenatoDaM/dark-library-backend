package com.dark.library.darklibrary.request;

import com.dark.library.darklibrary.model.BookTypeModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookTypeRequest {

    private Integer bookTypeId;
    @NotNull
    @NotBlank
    private String bookTypeName;

    public Integer getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Integer bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public BookTypeModel convertToEntity(BookTypeModel bookTypeModel) {
        bookTypeModel.setBookTypeName(this.bookTypeName);
        bookTypeModel.setBookTypeId(this.bookTypeId);
        return bookTypeModel;
    }
}
