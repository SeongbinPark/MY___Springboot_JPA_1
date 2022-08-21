package jpabook.myjpashop.controller;


import jpabook.myjpashop.domain.item.Book;
import jpabook.myjpashop.domain.item.Item;
import jpabook.myjpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createItem(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Item item = Book.createBook(form);

        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);
        BookForm form = BookForm.createBook(item);

        model.addAttribute("form", form);
        return "/items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute BookForm form) {
        Book book = Book.createBook(form);
        itemService.saveItem(book);
        return "redirect:/";
    }
}
