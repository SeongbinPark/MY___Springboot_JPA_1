package jpabook.myjpashop.repository;


import jpabook.myjpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//주문내역 확인 시 이용.
public class OrderSearch {

    private String memberName;//회원이름
    private OrderStatus orderStatus;//주문 상태 [ORDER, CANCEL]
    // 이 두가지 조건 중 파라미터 조건이 있으면 WHERE로 검색


}
