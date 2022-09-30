package dev.library.admin.persentation;

import dev.library.admin.application.*;
import dev.library.admin.dto.AdminDTO;
import dev.library.admin.dto.AdminIdDTO;
import dev.library.book.dto.BookTitleDTO;
import dev.library.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("admin")
public class AdminController {

    @Autowired
    CreateAdminService createAdminService;

    @Autowired
    LoginAdminService loginAdminService;

    @Autowired
    CreateUserByAdminServiceImpl createUserService;

    @Autowired
    CreateBookByAdminService createBookByAdminService;

    @PostMapping("/login_admin")
    public AdminIdDTO loginAdmin(@RequestBody @Valid AdminDTO adminDTO, Errors errors, HttpSession session){

        if (errors.hasErrors()){
            for(FieldError e : errors.getFieldErrors()){
                System.out.println(e.getDefaultMessage());
            }
            return null;
        } else {

            AdminIdDTO result = loginAdminService.loginAdmin(adminDTO);

            if (result != null){
                session.setAttribute("adminId",result.getAdminId());
                return result;
            } else {
                return null;
            }
        }

    }

    public AdminIdDTO signUpAdmin(@RequestBody @Valid AdminDTO adminDTO, Errors errors){

        if (errors.hasErrors()){
            for(FieldError e : errors.getFieldErrors()){
                System.out.println(e.getDefaultMessage());
            }
            return null;
        } else {
            return createAdminService.createAdmin(adminDTO);
        }

    }

    @PostMapping("/createUser")
    public String registerUser(@RequestBody User.Request request) {
        return createUserService.saveUser(request);
    }

    @PostMapping("/createBook")
    public String registerBook(@RequestBody BookTitleDTO bookTitleDTO){
        return createBookByAdminService.createBook(bookTitleDTO);
    }

}
