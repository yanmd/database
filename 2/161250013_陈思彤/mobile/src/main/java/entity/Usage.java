package entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Usage {
    private int uid;

    private int num;

    private LocalDateTime useTime;

    private Type type;

    public Usage(int uid, int num, LocalDateTime useTime, Type type) {
        this.uid = uid;
        this.num = num;
        this.useTime = useTime;
        this.type = type;
    }

    public Usage() {
    }
}
