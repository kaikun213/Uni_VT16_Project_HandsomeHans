/**
 * 
 */
package baseClasses;

import java.util.List;
import java.util.Date;

/**
 * This class represents an order.
 *
 * @author Paulius
 *
 */
public class Order {

    private long orderId;
    private List<Product> orderList;
    private enum orderStatus {New,Shippable,Delivered};
    private orderStatus status;
    private Date orderDate;

    public Order() {
    }

    public Order(long orderId, List<Product> orderList, orderStatus status, Date orderDate) {
        this.orderId = orderId;
        this.orderList = orderList;
        this.status = status;
        this.orderDate = orderDate;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Product> orderList) {
        this.orderList = orderList;
    }

    public orderStatus getOrderStatus() {
        return status;
    }

    public void setOrderStatus(orderStatus orderStatus) {
        this.status = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id = " + orderId +
                ", status = '" + status + '\'' +
                ", orderDate = " + orderDate +
                '}';
    }
}
