package uz.pdp.libraryapp2.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.libraryapp2.dto.AuthorDto;
import uz.pdp.libraryapp2.model.Author;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Author> getAllAuthorsFromDb() {


        String sql = "select * from authors";

        return jdbcTemplate.query(sql, (resultSet, row) ->
                Author.builder()
                        .id(resultSet.getInt(1))
                        .fullName(resultSet.getString(2))
                        .biography(resultSet.getString(3))
                        .build()

        );
    }

    public void addNewAuthor(Author author) {


        String sql = "insert into authors (full_name, biography)\n" +
                "values ('" + author.getFullName() + "' , '" + author.getBiography() + "');";
        jdbcTemplate.update(sql);

    }

    public void editAuthor(Author author) throws IllegalAccessException {

        try {
            String sql =
                    "UPDATE authors SET full_name = '" + author.getFullName() + "',biography  = '" + author.getBiography() + "' " +
                            "WHERE id =" + author.getId() + ";";
             jdbcTemplate.update(sql);

        } catch (Exception e) {
            throw new IllegalAccessException("Something went wrong...");
        }
    }

    public void deleteAuthorById(Integer id) throws IllegalAccessException {

        try {
            String sql = "delete FROM authors where id = " + id ;
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalAccessException("Something went wrong...");
        }

    }

    public Author getAuthorById(Integer id) {

        String sql = "select * from authors where id = " + id;
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Author.class));
    }

    public List<AuthorDto> read() {
        String sql = "select a.id, a.full_name from authors a;";
        return jdbcTemplate.query(sql, (rs, row) ->
                AuthorDto.builder().authorId(rs.getInt(1)).authorFullName(rs.getString(2)).build()
        );
    }
}
