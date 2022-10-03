package dev.library.book.domain;

import dev.library.book.dto.BookTitleDTO;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class Book {

    @EmbeddedId
    private BookId id;

    private String title;

    @Embedded
    private BookState bookState;

    public Book(BookId id, String title, BookState bookState) {
        this.id = id;
        this.title = title;
        this.bookState = bookState;
    }

    public void convertStateToOpposite() {
        if (this.getBookState().getBookStateEnum() == BookStateEnum.ABLE) {
            this.getBookState().setBookStateEnum(BookStateEnum.UNABLE);
        } else {
            this.getBookState().setBookStateEnum(BookStateEnum.ABLE);
        }

    @Builder
    private Book(BookId id,String title){
        this.title = title;
        this.id = id;
    }
    public static Book createBook(BookTitleDTO bookTitleDTO,BookId id){
        return Book.builder().title(bookTitleDTO.getTitle()).id(id).build();

    }

}
