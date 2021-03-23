package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/user")
class UserController {
    


    @Autowired
    UserRepository repo;
    
    @GetMapping("/index")
    public String getAll(Model model) {

        model.addAttribute("user", repo.findAll());
        return "index";
        
    }
    
    @PostMapping("/save")
    public String create(Integer id, String username, String password) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);

        if (id != null) {
            userEntity.setId(id);
        }

        repo.save(userEntity);
        return "redirect:/user/index";

    }

    @GetMapping("update")
    public String update(Model model, Integer id) {

        model.addAttribute("userupdate", repo.findById(id).get());
        return "index";

    }

    @GetMapping("/delete")
    public String delete(Integer id) {

        repo.delete(repo.findById(id).get());
        return "redirect:/user/index";

    }


}
