package jpabook.myjpashop.controller;


import jpabook.myjpashop.domain.Member;
import jpabook.myjpashop.domain.Order;
import jpabook.myjpashop.domain.item.Item;
import jpabook.myjpashop.repository.OrderSearch;
import jpabook.myjpashop.service.ItemService;
import jpabook.myjpashop.service.MemberService;
import jpabook.myjpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findmembers();
        List<Item> items = itemService.findItems();
        //member, item 목록 중 고르기 때문에 모두 넘겨줌.

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam Long memberId,
                        @RequestParam Long itemId,
                        @RequestParam int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/";
    }


    @GetMapping("/orders")
    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
//      @ModelAttribute 때문에 이게 추가됨. model.addAttribute("orderSearch", orderSearch);
        model.addAttribute("orders", orders);
        return "/order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }


}
