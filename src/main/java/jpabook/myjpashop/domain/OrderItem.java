package jpabook.myjpashop.domain;


import jpabook.myjpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;//주문 가격

    private int count;//주문 수량

    /**
     * 비지니스 로직
     */

    //주문취소 시 재고 증가
    public void cancel() {
        item.addStockQuantity(this.count);
    }

    public int getTotal() {
        return getItem().getPrice();
    }

    /**
     * 생성 메서드
     */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        orderItem.getItem().removeQuantity(count);//주문수량만큼 재고 줄여줌.
        return orderItem;
    }

    /**
     * 조회 로직
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
