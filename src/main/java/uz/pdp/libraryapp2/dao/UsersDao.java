package uz.pdp.libraryapp2.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.libraryapp2.model.Users;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersDao {

    private final JdbcTemplate jdbcTemplate;

    public void addNewUsers(Users users) {

        String sql = "INSERT INTO public.users (id, full_name, phone_number, is_admin, password)\n" +
                "VALUES (DEFAULT, '" + users.getFullName() + "', '" + users.getPhoneNumber() + "', DEFAULT, '" + users.getPassword() + "');";

        jdbcTemplate.update(sql);
    }

    public List<Users> getAllUsersFromDb() {
        String sql = "select * from public.users";

        return jdbcTemplate.query(sql, (resultSet, row) ->
                Users.builder()
                        .id(resultSet.getInt(1))
                        .fullName(resultSet.getString(2))
                        .phoneNumber(resultSet.getString(3))
                        .build()

        );
    }

    public Users getUsersById(Integer id) {
        String sql = "select * from public.users where id= " + id;
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Users.class));
    }

    public void editUsers(Users users) throws IllegalAccessException {
        try {
            String sql="UPDATE public.users \n" +
                    "SET full_name    = '"+users.getFullName()+"',\n" +
                    "    phone_number = ' "+users.getPhoneNumber()+"',\n" +
                    "    is_admin     = "+users.isAdmin()+",\n" +
                    "    password     = '"+users.getPassword()+"'\n" +
                    "WHERE id = "+users.getId()+";";

            jdbcTemplate.update(sql);
        } catch (Exception e) {
            throw new IllegalAccessException("Something went wrong...");
        }
    }

    public void deleteUsers(Integer id) throws IllegalAccessException {
        try {
            String sql = "delete FROM public.users where id =" + id;
            jdbcTemplate.update(sql);
        }catch (Exception e) {
            e.printStackTrace();
            throw new IllegalAccessException("Something went wrong...");
        }
    }

    public Users readById(int id) {
        String sql = "select * from public.users where id = " + id ;
        return jdbcTemplate.query(sql, rs -> {
            rs.next();
            return Users.builder()
                    .id(rs.getInt(1))
                    .fullName(rs.getString(2))
                    .phoneNumber(rs.getString(3))
                    .isAdmin(rs.getBoolean(4))
                    .password(rs.getString(5))
                    .build();
        });
    }

}
