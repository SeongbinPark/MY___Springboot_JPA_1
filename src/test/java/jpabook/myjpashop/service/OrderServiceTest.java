package jpabook.myjpashop.service;

import jpabook.myjpashop.domain.Address;
import jpabook.myjpashop.domain.Member;
import jpabook.myjpashop.domain.Order;
import jpabook.myjpashop.domain.OrderStatus;
import jpabook.myjpashop.domain.item.Book;
import jpabook.myjpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("상품 주문")
    public void orderingItem() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("이상한 나라의 엘리스", 12000, 12);

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류, 수가 정확해야한다.", 1, getOrder.getOrderItems().size());
//        assertEquals("주문가격은 가격 * 수량 이다. ", 12000*12, getOrder.getOrder);
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1번");
        member.setAddress(new Address("seoul", "mainstreet", "123-1232"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book=new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

}