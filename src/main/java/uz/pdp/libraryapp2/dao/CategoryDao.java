package uz.pdp.libraryapp2.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.libraryapp2.dto.CategoryDto;
import uz.pdp.libraryapp2.model.Categories;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDao {


    private final JdbcTemplate jdbcTemplate;


    public List<Categories> getAllCategoriesFromDb() {


        String sql = "select * from categories";

        return jdbcTemplate.query(sql, (resultSet, row) ->
                Categories.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .build()

        );
    }

    public void addNewCategory(Categories category) {


        String sql = "insert into categories (name, description)\n" +
                "values ('" + category.getName() + "' , '" + category.getDescription() + "');";
        jdbcTemplate.update(sql);

    }

    public void editCategory(Categories category) throws IllegalAccessException {

        try {
            String sql =
                    "UPDATE categories SET name = '" + category.getName() + "',description  = '" + category.getDescription() + "'" +
                            " " +"WHERE id =" + category.getId() + ";";
            jdbcTemplate.update(sql);

        } catch (Exception e) {
            throw new IllegalAccessException("Something went wrong...");
        }
    }

    public void deleteCategoryById(Integer id) throws IllegalAccessException {

        try {
            String sql = "delete FROM categories where id = " + id + ";";
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalAccessException("Something went wrong...");
        }

    }

    public Categories getCategoryById(Integer id) {

        String sql = "select * from categories where id = " + id;
        return  jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Categories.class));

    }

    public List<CategoryDto> read() {
        String sql = "select c.id, c.name from categories c;";
        return jdbcTemplate.query(sql, (rs, row) -> {
                    return CategoryDto.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .build();
                }
        );
    }
}
