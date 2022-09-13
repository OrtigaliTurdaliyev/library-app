package uz.pdp.libraryapp2.dao;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.libraryapp2.dto.AuthorDto;
import uz.pdp.libraryapp2.dto.BookDto;
import uz.pdp.libraryapp2.dto.CategoryDto;
import com.google.gson.reflect.TypeToken;
import uz.pdp.libraryapp2.model.Author;
import uz.pdp.libraryapp2.model.Books;
import uz.pdp.libraryapp2.model.Language;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BooksDao {

    private final JdbcTemplate jdbcTemplate;

//    public List<Books> getAllBooksFromDb() {
//
//
//        String sql = "select * from public.books";
//
//        return jdbcTemplate.query(sql, (resultSet, row) ->
//                Books.builder()
//                        .id(resultSet.getInt(1))
//                        .title(resultSet.getString(2))
//                        .isbn(resultSet.getString(3))
//                        .totalCount(resultSet.getString(4))
//                        .description(resultSet.getString(5))
//                        .language(Language.builder()
//                                .id(resultSet.getInt(6))
//                                .lang(resultSet.getString(7))
//                                .build()
//                        )
//                        .build()
//
//        );
//    }

    public void create(BookDto books) {
        String sql ="INSERT INTO public.books (id, title, isbn, description, language_id, total_count)\n" +
                "VALUES (DEFAULT, '"+books.getTitle()+"', '"+books.getIsbn()+"', '"+books.getDescription()+"', "+books.getLanguageId()+", "+books.getCount()+");";
        jdbcTemplate.update(sql);

        String bookIdSql = "select id from books where isbn = '" + books.getIsbn() + "';";
        Integer bookId = jdbcTemplate.query(bookIdSql, rs -> {
            rs.next();
            return rs.getInt(1);
        });

        for (Integer authorsId : books.getAuthorsIds()) {
            String bookAuthorsSql = "insert into books_authors(book_id, author_id) values (?,?);";
            jdbcTemplate.update(bookAuthorsSql, bookId, authorsId);
        }
        for (Integer categoriesId : books.getCategoriesIds()) {
            String bookCategoriesSql = "insert into books_categories(book_id, category_id) values (?,?);";
            jdbcTemplate.update(bookCategoriesSql, bookId, categoriesId);
        }
    }

    public List<BookDto> read() {
        String sql = "select b.id, b.title ,json_agg(json_build_object('authorId',a.id,'authorFullName', a.full_name)) as authors , l.lang, total_count from books b join books_authors ba on b.id = ba.book_id\n" +
                "                join authors a on a.id = ba.author_id join languages l on b.language_id = l.id group by b.id, l.id";
        return jdbcTemplate.query(sql, (rs, row) -> {
                    String categorySql = "select c.id, c.name from books_categories bc join categories c on c.id = bc.category_id join books b on b.id = bc.book_id where b.id=" + rs.getInt(1) + ";";
                    List<CategoryDto> categoryDtoList = jdbcTemplate.query(categorySql, (rs1, rowNum) -> {
                        return CategoryDto.builder()
                                .id(rs1.getInt(1))
                                .name(rs1.getString(2))
                                .build();
                    });
                    Array array = rs.getArray(3);
                    Type type = new TypeToken<List<AuthorDto>>() {
                    }.getType();
                    List<AuthorDto> authorDtoList = new Gson().fromJson(array.toString(), type);
                    return BookDto.builder()
                            .id(rs.getInt(1))
                            .title(rs.getString(2))
                            .authorDtoList(authorDtoList)
                            .categoryDtoList(categoryDtoList)
                            .count(rs.getInt(5))
                            .languageName(rs.getString(4))
                            .build();

                }
        );
    }

    public void update(BookDto bookDto) {
        String bookSql = "update books set title = ?,  total_count = ?, description = ?, language_id = ? where id = ?;";
        jdbcTemplate.update(bookSql, bookDto.getTitle(),  bookDto.getCount(), bookDto.getDescription(), bookDto.getLanguageId(), bookDto.getId());
        String categorySql = "delete from books_categories where book_id = ?;";
        jdbcTemplate.update(categorySql, bookDto.getId());
        String authorSql = "delete from books_authors where book_id = ?;";
        jdbcTemplate.update(authorSql, bookDto.getId());
        for (Integer authorsId : bookDto.getAuthorsIds()) {
            String bookAuthorsSql = "insert into books_authors(book_id, author_id) values (?,?);";
            jdbcTemplate.update(bookAuthorsSql, bookDto.getId(), authorsId);
        }
        for (Integer categoriesId : bookDto.getCategoriesIds()) {
            String bookCategoriesSql = "insert into books_categories(book_id, category_id) values (?,?);";
            jdbcTemplate.update(bookCategoriesSql, bookDto.getId(), categoriesId);
        }
    }

    public void delete(Integer id) {

        String categorySql = "delete from books_categories where book_id = ?;";
        jdbcTemplate.update(categorySql, id);
        String authorSql = "delete from books_authors where book_id = ?;";
        jdbcTemplate.update(authorSql, id);
        String bookSql = "delete from books where id = ?";
        jdbcTemplate.update(bookSql, id);
    }

    public BookDto readById(Integer id) {
        String bookSql = "select b.id, b.title ," +
                "json_agg(json_build_object('authorId',a.id,'authorFullName', a.full_name)) as authors," +
                " isbn,total_count, description, language_id from books b join books_authors ba on b.id = ba.book_id" +
                " join authors a on a.id = ba.author_id  where b.id = " + id + " group by b.id;";
        BookDto bookDto = jdbcTemplate.query(bookSql, rs -> {
            rs.next();
            Array array = rs.getArray(3);
            Type type = new TypeToken<List<AuthorDto>>() {
            }.getType();
            List<AuthorDto> authorDtoList = new Gson().fromJson(array.toString(), type);


            return BookDto.builder()
                    .id(rs.getInt(1))
                    .title(rs.getString(2))
                    .isbn(rs.getString(4))
                    .count(rs.getInt(5))
                    .description(rs.getString(6))
                    .languageId(rs.getInt(7))
                    .authorDtoList(authorDtoList)
                    .build();
        });


        String langSql = "select lang from languages where id = " + bookDto.getLanguageId() + ";";
        bookDto.setLanguageName(jdbcTemplate.query(langSql, rs -> {
            rs.next();
            return rs.getString(1);
        }));

        String categorySql = "select c.id, c.name from books_categories bc join categories c on c.id = bc.category_id join books b on b.id = bc.book_id where b.id=" + bookDto.getId() + ";";
        bookDto.setCategoryDtoList(jdbcTemplate.query(categorySql, (rs, rowNum) -> {
            return CategoryDto.builder()
                    .id(rs.getInt(1))
                    .name(rs.getString(2))
                    .build();
        }));

        return bookDto;
    }

    public boolean exist(String isbn) {
        String existSql = "select count(*) from books;";
        Integer count = jdbcTemplate.query(existSql,rs -> {
            rs.next();
            return rs.getInt(1);
        });
        if (count!=0){
            String sql = "select nullif(id, null) title from books where isbn = '" + isbn + "';";
            try {
                Integer query = jdbcTemplate.query(sql, rs -> {
                    rs.next();
                    return rs.getInt(1);
                });
                return true;
            } catch (DataAccessException e) {
                return false;
            }
        }
        return false;
    }

    public void addToAmount(BookDto bookDto) {
        String sql = "update books set total_count = total_count+? where id = ?";
        jdbcTemplate.update(sql,bookDto.getCount(),bookDto.getId());
    }


}
