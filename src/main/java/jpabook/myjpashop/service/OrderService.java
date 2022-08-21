package jpabook.myjpashop.service;

import jpabook.myjpashop.domain.Delivery;
import jpabook.myjpashop.domain.Member;
import jpabook.myjpashop.domain.Order;
import jpabook.myjpashop.domain.OrderItem;
import jpabook.myjpashop.domain.item.Item;
import jpabook.myjpashop.repository.ItemRepository;
import jpabook.myjpashop.repository.MemberRepository;
import jpabook.myjpashop.repository.OrderRepository;
import jpabook.myjpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
//    private final MemberService memberService;
//    private final ItemService itemService;

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional//false
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.find(memberId);
        Item item = itemRepository.findOne(itemId);

        int price = item.getPrice();
        OrderItem orderItem = OrderItem.createOrderItem(item, price, count);//1차목표 orderItem 만들기

        Delivery delivery= new Delivery();
        delivery.setAddress(member.getAddress());//2차목표 delivery 만들기

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();

    }

    //검색 ( 이렇게 위임만 하는 기능은 없애는 것도 고려 )
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByCriteria(orderSearch);
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }


}
