/**
 * 
 */
package baseClasses;

import java.util.List;
import java.util.Date;

/**
 * @author Paulius
 *
 */
public class Order {

    private long orderId;
    private List<Product> orderList;
    private enum orderStatus;
    private Date orderDate;

    public Order() {
    }

    public Order(long orderId, List<Product> orderList, enum status, Date orderDate) {
        this.orderId = orderId;
        this.orderList = orderList;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Product> orderList) {
        this.orderList = orderList;
    }

    public enum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(enum orderStatus) {
        this.setOrderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{"
                "id = " + id +
                ", status = '" + status + '\'' +
                ", userId = " + userId +
                ", orderDate = " + orderDate +
                '}';
    }
}
