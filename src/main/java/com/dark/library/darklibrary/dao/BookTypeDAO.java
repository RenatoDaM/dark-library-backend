package com.dark.library.darklibrary.dao;

import com.dark.library.darklibrary.model.BookTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class BookTypeDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameter;

    public void save(BookTypeModel bookTypeModel) {
        String sql = "INSERT INTO book_type (book_type_id, book_type_name) VALUES (:bookTypeId, :bookTypeName)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("bookTypeName", bookTypeModel.getBookTypeName());
        parameterSource.addValue("bookTypeId", bookTypeModel.getBookTypeId());
        namedParameter.update(sql, parameterSource);
    }

    public void update(BookTypeModel bookTypeModel) {
        String sql = "UPDATE book_type SET book_type_name = :bookTypeName WHERE book_type_id = :bookTypeId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("bookTypeName", bookTypeModel.getBookTypeName())
                .addValue("bookTypeId", bookTypeModel.getBookTypeId());
        namedParameter.update(sql, mapSql);
    }

    public void delete(Integer bookTypeId) {
        String sql = "DELETE FROM book_type WHERE book_type_id = :bookTypeId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("bookTypeId", bookTypeId);
        namedParameter.update(sql, mapSql);
    }

    public BookTypeModel readById(Integer bookTypeId) {
        String sql = "SELECT book_type_id as bookTypeId, book_type_name as bookTypeName FROM book_type WHERE book_type_id = :bookTypeId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("bookTypeId", bookTypeId);
        return namedParameter.queryForObject(sql, mapSql, new BeanPropertyRowMapper<>(BookTypeModel.class));
    }

    public List<BookTypeModel> readAll() {
        String sql = "SELECT * FROM book_type";
        return namedParameter.query(sql, new BeanPropertyRowMapper<>(BookTypeModel.class));
    }




}
