package jpabook.myjpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.concurrent.atomic.DoubleAdder;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delevery_id")
    private Long id;

    private DeliveryStatus deliveryStatus;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;


}
