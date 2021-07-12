package jpabook.jpashop.domain;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class OrderItem extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="ORDER_ITEM_ID")
    private long id;

//    @Column(name="ORDER_ID")
//    private Long orderId;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order; // 객체

//    @Column(name="ITEM_ID")
//    private Long itemId;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item; // 객체

    private int orderPrice;
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
