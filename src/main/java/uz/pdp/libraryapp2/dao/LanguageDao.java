package uz.pdp.libraryapp2.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.libraryapp2.model.Language;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LanguageDao {


    private final JdbcTemplate jdbcTemplate;


    public List<Language> getAllLanguagesFromDb() {


        String sql = "select * from languages";

            return jdbcTemplate.query(sql, (resultSet, row) ->
                Language.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build()

        );
    }

    public void addNewLanguage(Language language) {


        String sql = "insert into languages (lang)\n" +
                "values ('" + language.getName() + "');";
        jdbcTemplate.update(sql);

    }

    public void editLanguage(Language language) throws IllegalAccessException {

        try {
            String sql =
                    "UPDATE public.languages SET lang = '" + language.getName() + "' WHERE id =" + language.getId() + ";";
             jdbcTemplate.update(sql);

        } catch (Exception e) {
            throw new IllegalAccessException("Something went wrong...");
        }
    }

    public void deleteLanguageById(Integer id) throws IllegalAccessException {

        try {
            String sql = "delete FROM languages where id = " + id + ";";
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalAccessException("Something went wrong...");
        }

    }

    public Language getLanguageById(Integer id) {

        String sql = "select * from languages where id = " + id;
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Language.class));
    }

    public List<Language> read() {
        String sql = "select l.id, l.lang from languages l;";
        return jdbcTemplate.query(sql, (rs, row) ->
                Language.builder()
                .id(rs.getInt(1))
                .name(rs.getString(2))
                .build()
        );
    }
}
