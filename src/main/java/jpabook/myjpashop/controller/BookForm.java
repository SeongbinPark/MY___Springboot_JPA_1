package jpabook.myjpashop.controller;


import jpabook.myjpashop.domain.item.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm{

    private Long id;//상품수정 할 때 필요함.

    private String name;
    private int price;
    private int stockQuantity;
    //여기 까지 상품의 공통속성

    private String author;
    private String isbn;

    public static BookForm createBook(Book item) {
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        return form;
    }

}
