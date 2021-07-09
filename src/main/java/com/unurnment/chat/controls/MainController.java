package com.unurnment.chat.controls;

import com.unurnment.chat.model.Massage;
import com.unurnment.chat.model.User;
import com.unurnment.chat.repository.MassageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/main")
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MassageRepo massageRepo;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "") String filter,Model model){
       Iterable<Massage> massages;
        if (filter != null && !filter.isEmpty()){
            massages = massageRepo.findByTagOrTextContaining(filter,filter);
        }else {
            massages = massageRepo.findAll();
        }
        model.addAttribute("massages", massages);
        model.addAttribute("filter",filter);
        return "main";
    }

    @PostMapping
    public String sand(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag,Model model, @RequestParam("file") MultipartFile file) throws IOException {
        Massage massage = new Massage(text,tag,user);
        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String result = uuid + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath+"/"+result));
            massage.setFileName(result);
        }
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("GMT+03"));
        massage.setCurrent_date(df.format(date));
        massageRepo.save(massage);
        Iterable<Massage> massages = massageRepo.findAll();
        model.addAttribute("massages", massages);
        return "main";
    }

    @GetMapping("/{massage.id}/edit")
    public String editPage (@PathVariable(value = "massage.id") Long id, Model model){
       Optional<Massage> massage = massageRepo.findById(id);
        ArrayList<Massage> massageArrayList = new ArrayList<>();
        massage.ifPresent(massageArrayList :: add);
        model.addAttribute("massages",massageArrayList);
        return "edit";
    }

    @PostMapping("/{massage.id}/edit")
    public String edit(@PathVariable(value = "massage.id") Long id,@RequestParam String text,@RequestParam String tag,Model model) throws IOException {
        Massage massage = massageRepo.findById(id).orElseThrow();
        massage.setText(text);
        massage.setTag(tag);
        massageRepo.save(massage);
        return "redirect:/main";
    }
    @GetMapping("/{massage.id}/delete")
    public String delete(@PathVariable(value = "massage.id") Long id,Model model){
        Massage massage = massageRepo.findById(id).orElseThrow();
        massageRepo.delete(massage);
        return "redirect:/main";
    }
}