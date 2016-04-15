package baseClasses;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * This class represents an order.
 *
 * @author Paulius
 *
 */
public class Order {

    // Fields
    private enum OrderStatus{IN_PROCESS, SHIPPED, DELIVERED}
    private OrderStatus orderStatus;
    private long orderId;
    private List<Product> orderList = new ArrayList<Product>();
    private Date orderDate;
    

    /**
     *  Empty constructor.
     */
    public Order() {
    }

    /**
     *  Order constructor.
     *
     *  @param orderId
     *  @param orderList
     *  @param orderDate
     *  @param orderStatus
     */
    public Order(long orderId, List<Product> orderList, Date orderDate, OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.orderId = orderId;
        this.orderList = orderList;
        this.orderDate = orderDate;
    }

    // Getters and setters for fields below.

    /**
     *  Method returning order status.
     *  @return OrderStatus
     */
    public OrderStatus getOrderStatus() { return orderStatus; }

    /**
     *  Method to set the order status.
     *  @param orderStatus
     */
    public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus = orderStatus; }

    /**
     *  Method returning order id.
     *  @return long
     */
    public long getOrderId() { return orderId; }

    /**
     * Method setting order id.
     * @param orderId
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Method returning list of products.
     * @return List<Product>
     */
    public List<Product> getOrderList() {
        return orderList;
    }

    /**
     *  Method to set the list of products.
     *  @param orderList
     */
    public void setOrderList(List<Product> orderList) { this.orderList = orderList; }

    /**
     *  Method returning the order date.
     *  @return Date
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     *  Method to set the date of the order.
     *  @param orderDate
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *  Method to get the product by its position in the list.
     *  @param n
     *  @return Product
     */
    public Product getItem(int n) { return orderList.get(n); }

    /**
     *  Method returning the string output of the order.
     *  @return String
     */
    @Override
    public String toString() {
        return orderId + " " + " " + orderDate + " " + "\n" + orderList;
    }

    /**
     *  Class that represents order iterator.
     */
    public class OrderIterator implements Iterator<Product> {

        private final List<Product> orderList;
        private int index;

        /**
         *  Order iterator.
         *  @param orderList
         */
        public OrderIterator(List<Product> orderList) {
            this.orderList = orderList;
            index = 0;
        }

        /**
         *  Method to return next product in the list.
         *  @return Product
         */
        @Override
        public Product next() {
            if(hasNext())
                return orderList.get(index++);
            else
                throw new NoSuchElementException("There are no products!");
        }

        /**
         *  Method to check whether there is next product in the list.
         *  @return boolean (true/false)
         */
        @Override
        public boolean hasNext() {
            return !(orderList.size() != index);
        }

        /**
         *  Method to remove a product from the list.
         */
        @Override
        public void remove() {
            if(index <= 0)
                throw new IllegalStateException("You can't delete element before first next() method call");
            orderList.remove(--index);
        }
    }
}
