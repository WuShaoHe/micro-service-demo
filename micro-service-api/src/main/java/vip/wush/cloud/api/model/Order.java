package vip.wush.cloud.api.model;

import java.io.Serializable;

/**
 * @ClassName: Order
 * @Description: TODO
 * @Author: wush
 * @Date: 2020/4/25 15:15
 */

public class Order implements Serializable {

    private static final long serialVersionUID = -697753539628771343L;

    private Long id;

    private String number;

    private int status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", status=" + status +
                '}';
    }
}
