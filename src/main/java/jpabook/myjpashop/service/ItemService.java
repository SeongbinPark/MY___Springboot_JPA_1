package jpabook.myjpashop.service;

import jpabook.myjpashop.domain.item.Book;
import jpabook.myjpashop.domain.item.Item;
import jpabook.myjpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, Book param) {

        Item findItem = itemRepository.findOne(itemId);
        //em.find올 찾아온 객체는 영속상태이다 -> 리턴된 findItem도 영속상태객체를 가리킨다.
        findItem.change( param);

        //persist 안해도 됨
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
