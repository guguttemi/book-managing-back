package dev.library.user.presentation;

import dev.library.admin.application.CreateBookByAdminService;
import dev.library.book.dto.BookTitleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateBookTest {

    @Autowired
    CreateBookByAdminService createBookByAdminService;

    @Test
    public void createBookTest(){
        BookTitleDTO dto = new BookTitleDTO();
        dto.setTitle("노인과 바다");
        String result = createBookByAdminService.createBook(dto);
        System.out.println(result);
    }

}
