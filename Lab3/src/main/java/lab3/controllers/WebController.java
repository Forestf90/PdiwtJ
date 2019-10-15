package lab3.controllers;

import lab3.entities.Student;
import lab3.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController implements WebMvcConfigurer {

    @Autowired
    DBService dbService;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/result").setViewName("result");
    }

    @GetMapping("/")
    public String showForm(Student student) {
        return "form";
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String showStudents(Model model) {

        List<Student> list = dbService.getAllStudents();
        model.addAttribute("list", list);

        return "students";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        dbService.saveStudent(student);

        return "redirect:/result";
    }
}
