package com.dark.library.darklibrary.dao;

import com.dark.library.darklibrary.model.GodTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GodTypeDAO {
    @Autowired
    NamedParameterJdbcTemplate namedParameter;

    public void save(GodTypeModel godTypeModel) {
        String sql = "INSERT INTO god_type VALUES (:godTypeId, :godTypeName)";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godTypeId", godTypeModel.getGodTypeId())
                .addValue("godTypeName", godTypeModel.getGodTypeName());
        namedParameter.update(sql, mapSql);
    }

    public void update(GodTypeModel godTypeModel) {
        String sql = "UPDATE god_type SET god_type_name = :godTypeName WHERE god_type_id = :godTypeId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godTypeId", godTypeModel.getGodTypeId())
                .addValue("godTypeName", godTypeModel.getGodTypeName());
        namedParameter.update(sql, mapSql);
    }

    public void delete(Integer godTypeId) {
        String sql = "DELETE FROM god_type WHERE god_type_id = :godTypeId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godTypeId", godTypeId);
        namedParameter.update(sql, mapSql);
    }

    public GodTypeModel readById(Integer godTypeId) {
        String sql = "SELECT god_type_id as godTypeId, god_type_name as godTypeName FROM god_type WHERE god_type_id = :godTypeId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godTypeId", godTypeId);
        return namedParameter.queryForObject(sql, mapSql, new BeanPropertyRowMapper<>(GodTypeModel.class));
    }

    public List<GodTypeModel> readAll() {
        String sql = "SELECT * FROM god_type";
        return namedParameter.query(sql, new BeanPropertyRowMapper<>(GodTypeModel.class));
    }
}
