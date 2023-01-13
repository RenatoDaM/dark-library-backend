package com.dark.library.darklibrary.request;

import com.dark.library.darklibrary.model.BookModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequest {
    @Schema(description = "The database auto generate ID, you can leave this empty if you want.")
    Integer bookId;

    @Schema(description = "book's name.", example = "Necronomicon")
    @NotNull
    @NotBlank
    String bookName;

    @Schema(description = "Book type ID, we use this to JOIN tables on database.", example = "1")
    @NotNull
    Integer bookType;

    @Schema(description = "God ID, we use this to JOIN tables on database.", example = "1")

    @NotNull
    Integer bookGod;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Integer getBookGod() {
        return bookGod;
    }

    public void setBookGod(Integer bookGod) {
        this.bookGod = bookGod;
    }

    public BookModel convertToEntity(BookModel bookModel) {
        bookModel.setBookId(this.bookId);
        bookModel.setBookName(this.bookName);
        bookModel.setBookGod(String.valueOf(this.bookGod));
        bookModel.setBookType(String.valueOf(this.bookType));
        return bookModel;
    }
}
