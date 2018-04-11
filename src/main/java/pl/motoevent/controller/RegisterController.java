package pl.motoevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.motoevent.entity.User;
import pl.motoevent.entity.UserDetails;
import pl.motoevent.entity.UserRole;
import pl.motoevent.repository.UserDetailsRepository;
import pl.motoevent.repository.UserRepository;
import pl.motoevent.repository.UserRoleRepository;

import java.util.List;

@RestController
@RequestMapping("/registry")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

//    @PostMapping("/register")
//    public String addUser(@RequestBody User user) {
//        user.setPassword(encoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return "ok";
//    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
//        User newUser = user;
        System.out.println("/n *************** SAVING ************* /n");
        System.out.println(user);
        UserRole userRole = new UserRole();
        userRole.setRole("USER");
        user.setUserRole(userRole);
        userRoleRepository.save(userRole);
        UserDetails userDetails = new UserDetails();
//        userDetails.setEmail(user.getEmail());
        userDetailsRepository.save(userDetails);
//        String pass = user.getPassword();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println("/n*******/n user added /n******/n");
        return "user added";
    }

//    @GetMapping("/login?error")
//    public String sss() {
//        return "redirect:http://www.google.pl";
//    }


}