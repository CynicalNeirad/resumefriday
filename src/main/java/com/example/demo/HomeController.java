package com.example.demo;
import com.sun.jndi.cosnaming.IiopUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ResumeBook page;

    @RequestMapping("/")
    public String listCourses(Model model) {
        model.addAttribute("resume", page.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String resumeform(Model model) {
        model.addAttribute("resume", new Resume());
        return "resumeform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "resumeform";
        }
        page.save(resume);
        return "redirect:/";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("resume", page.findOne(id));
        return "resumeform";
    }
    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        page.delete(id);
        return "redirect:/";
    }

}
