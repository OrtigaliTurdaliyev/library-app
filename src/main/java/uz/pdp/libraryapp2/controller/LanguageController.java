package uz.pdp.libraryapp2.controller;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.libraryapp2.dao.LanguageDao;
import uz.pdp.libraryapp2.model.Language;

@Controller
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageDao languageDao;


    @GetMapping
    public String getAllLanguages(Model model) {

        model.addAttribute("tillar", languageDao.getAllLanguagesFromDb());

        return "/languages/view-languages";
    }

    @GetMapping("/get-form")
    public String getLanguageForm() {
        return "/languages/add-language-form";
    }

    @SneakyThrows
    @PostMapping
    public String addNewLanguage(Language language) {
        try {
            languageDao.addNewLanguage(language);
            return "redirect:/languages";

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalAccessException("Something went wrong...");
        }


    }

    @GetMapping("/edit/{id}")
    public String getFormForEditLanguage(@PathVariable Integer id, Model model) {

        Language languageById = languageDao.getLanguageById(id);
        model.addAttribute("til", languageById);
        return "/languages/edit-language-form";

    }

    @PostMapping("/edit")
    public String editLanguageById(Language language) {
        try {
            languageDao.editLanguage(language);
            return "redirect:/languages";
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLanguageById(@PathVariable Integer id) {
        try {
            languageDao.deleteLanguageById(id);
            return "redirect:/languages";
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
