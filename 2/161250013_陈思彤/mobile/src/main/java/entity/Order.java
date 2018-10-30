package entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id;

    private Integer uid;

    private Integer pid;

    private LocalDateTime orderTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Order(Integer id, Integer uid, Integer pid, LocalDateTime orderTime, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.uid = uid;
        this.pid = pid;
        this.orderTime = orderTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Order(Integer uid, Integer pid, LocalDateTime orderTime, LocalDateTime startTime) {
        this.uid = uid;
        this.pid = pid;
        this.orderTime = orderTime;
        this.startTime = startTime;
    }
}
