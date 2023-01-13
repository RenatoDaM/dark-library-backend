package com.dark.library.darklibrary.model;

/**
 * This class BookModel don't represent any table on database. In the database
 * bookType and bookGod are Integers, foreign keys from respective tables.
 * I created BookRequest class just to POST method, because only this method need to know the foreign keys.
 *
 * Using JDBC Template we can have this type of flexibility.
 *
 * @Author Renato Davoli
 */

public class BookModel {

    private Integer bookId;
    private String bookName;
    private String bookType;
    private String bookGod;

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

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookGod() {
        return bookGod;
    }

    public void setBookGod(String bookGod) {
        this.bookGod = bookGod;
    }
}
