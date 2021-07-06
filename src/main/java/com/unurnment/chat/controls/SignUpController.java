package com.unurnment.chat.controls;

import com.unurnment.chat.model.Role;
import com.unurnment.chat.model.User;
import com.unurnment.chat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class SignUpController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String createUser(User user, Map<String,Object> model){
      User other =  userRepo.findByUsername(user.getUsername());

      if (other != null){
          model.put("massage","Пользователь существует");
          return "registration";
      }

      user.setRoles(Collections.singleton(Role.USER));

      userRepo.save(user);
      return "redirect:/login";
    }
}
