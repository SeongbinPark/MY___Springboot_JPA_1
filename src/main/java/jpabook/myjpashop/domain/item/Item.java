package jpabook.myjpashop.domain.item;


import jpabook.myjpashop.exception.NotEnoughQuantityException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    /**
     * 비지니스 로직
     */
    //재고 증가
    public void addStockQuantity(int count) {
        this.stockQuantity += count;
    }

    //재고 감소
    public void removeQuantity(int count) {
        int restStock = this.stockQuantity - count;
        if (restStock < 0) {
            throw new NotEnoughQuantityException("재고는 0보다 작을 수 없습니다.");
        }
        this.stockQuantity = restStock;
    }

    public void change(Book param) {
        this.price = param.getPrice();
        this.name = param.getName();
        this.stockQuantity = param.getStockQuantity();
    }


}
