package com.dark.library.darklibrary.domain.dao;

import com.dark.library.darklibrary.domain.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookDAO {
    @Autowired
    private NamedParameterJdbcTemplate namedParameter;


    public void save(BookModel bookModel) {
        String sql = "INSERT INTO book (book_id, book_name, book_type, book_god) VALUES (:bookId, :bookName,  :bookType, :bookGod)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("bookId", bookModel.getBookId());
        mapSqlParameterSource.addValue("bookName", bookModel.getBookName());
        mapSqlParameterSource.addValue("bookType", bookModel.getBookType());
        mapSqlParameterSource.addValue("bookGod", bookModel.getBookGod());
        namedParameter.update(sql, mapSqlParameterSource);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM book WHERE book_id = :bookId";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("bookId", id);
        namedParameter.update(sql, mapSqlParameterSource);
    }

    public void update(BookModel bookModel) {
        String sql = "UPDATE book SET book_name = :bookName, book_type = :bookType, book_god = :bookGod WHERE book_id = :bookId";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("bookId", bookModel.getBookId())
                .addValue("bookName", bookModel.getBookName())
                .addValue("bookType", bookModel.getBookType())
                .addValue("bookGod", bookModel.getBookGod());
        namedParameter.update(sql, mapSqlParameterSource);
    }

    public BookModel readById(Integer id) {
        String sql = "SELECT b.book_id as bookId, b.book_name as bookName, t.book_type_name as bookType, g.god_name as bookGod FROM book as b JOIN book_type as t ON book_type = book_type_id JOIN god as g ON book_god = god_id WHERE book_id = :bookId";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("bookId", id);
        return namedParameter.queryForObject(sql, mapSqlParameterSource, new BeanPropertyRowMapper<>(BookModel.class));
    }

    public List<BookModel> readAll() {
        String sql = "SELECT b.book_id as bookId, b.book_name as bookName, t.book_type_name as bookType, g.god_name as bookGod FROM book as b JOIN book_type as t ON book_type = book_type_id JOIN god as g ON book_god = god_id";
        List<BookModel> resultList = namedParameter.query(sql, new BeanPropertyRowMapper<>(BookModel.class));
        return resultList;
    }

}
