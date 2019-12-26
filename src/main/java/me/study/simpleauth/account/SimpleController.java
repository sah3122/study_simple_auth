package me.study.simpleauth.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SimpleController {

    private final SimpleAccountService simpleAccountService;

    @PostMapping
    @ResponseBody
    public SimpleAccount account(@RequestBody SimpleAccountDto simpleAccountDto) {
        SimpleAccount account = simpleAccountService.createAccount(simpleAccountDto);
        return account;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal SimpleAccountUser simpleAccountUser) {
        if (simpleAccountUser == null) {
            model.addAttribute("message", "not login");
        } else
            model.addAttribute("message", simpleAccountUser.getAccount().toString());

        return "index";
    }

}
