package uz.pdp.libraryapp2.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.libraryapp2.dao.LanguageDao;
import uz.pdp.libraryapp2.dao.UsersDao;
import uz.pdp.libraryapp2.model.Users;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    public final UsersDao usersDao;


    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", usersDao.getAllUsersFromDb());

        return "/users/view-users";
    }

    @GetMapping("/get-form")
    public String getLanguageForm() {
        return "/users/add-users-form";
    }

    @SneakyThrows
    @PostMapping
    public String addNewUsers(Users users) {
        try {
            usersDao.addNewUsers(users);
            return "redirect:/users";

        }catch (Exception e) {
            e.printStackTrace();
            throw new IllegalAccessException("Something went wrong...");
        }
    }

    @GetMapping("/by/{id}")
    public String getUsersById(@PathVariable Integer id ,Model model){
        Users usersById=usersDao.getUsersById(id);
        model.addAttribute("users",usersById);
        return "/users/show-users";
    }

    @PostMapping("/edit/{id}")
    public String editeUsersById(@PathVariable("id") Integer id ,Model model){
        Users usersById=usersDao.getUsersById(id);
        model.addAttribute("users", usersById);
        return "/users/edit-users-form";

    }

    @GetMapping("/{id}")
    public String readById(@PathVariable("id") int id, Model model) {
        Users user = usersDao.readById(id);
        model.addAttribute("user", user);
        return "/users/users-by-id";
    }

    @PostMapping("/edit")
    public String editUsers(Users users){
        try {
            usersDao.editUsers(users);
            return "redirect:/users";
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUsersById(@PathVariable Integer id) {
        try {
            usersDao.deleteUsers(id);
            return "redirect:/users";
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping({ "/edit"})
    public String getForm(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Users user = usersDao.readById(id);
            model.addAttribute("user", user);
        }
        return "/users/user-form";
    }



}
