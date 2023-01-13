package com.dark.library.darklibrary.dao;

import com.dark.library.darklibrary.model.GodModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GodDAO {

    @Autowired
    NamedParameterJdbcTemplate namedParameter;

    public void createGod(GodModel godModel) {
        String sql = "INSERT INTO god VALUES (:godId, :godName, :godType)";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godId", godModel.getGodId())
                .addValue("godName", godModel.getGodName())
                .addValue("godType", godModel.getGodType());
        namedParameter.update(sql, mapSql);
    }

    public void updateGod(GodModel godModel) {
        String sql = "UPDATE god SET god_name = :godName, god_type = :godType WHERE god_id = :godId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godId", godModel.getGodId())
                .addValue("godName", godModel.getGodName())
                .addValue("godType", godModel.getGodType());
        namedParameter.update(sql, mapSql);
    }

    public void deleteGod(Integer godId) {
        String sql = "DELETE FROM god WHERE god_id = :godId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godId", godId);
        namedParameter.update(sql, mapSql);

    }

    public GodModel readByIdGod(Integer godId) {
        String sql = "SELECT god_id as godId, god_name as godName, god_type as godType FROM god WHERE god_id = :godId";
        MapSqlParameterSource mapSql = new MapSqlParameterSource("godId", godId);
        return namedParameter.queryForObject(sql, mapSql, new BeanPropertyRowMapper<>(GodModel.class));
    }

    public List<GodModel> readAllGod() {
        String sql = "SELECT * FROM god";
        return namedParameter.query(sql, new BeanPropertyRowMapper<>(GodModel.class));

    }
}
