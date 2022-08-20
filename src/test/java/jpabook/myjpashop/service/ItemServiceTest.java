package jpabook.myjpashop.service;

import jpabook.myjpashop.domain.item.Book;
import jpabook.myjpashop.domain.item.Item;
import jpabook.myjpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired
    ItemRepository itemRepository;


    @Test
    @DisplayName("상품 저장")
    public void saveItem() throws Exception {
        //given
        Item item=new Book();
        //when
        itemService.saveItem(item);
        Item findItem = itemRepository.findOne(1L);
        //then
        assertEquals(findItem, item);
    }
}