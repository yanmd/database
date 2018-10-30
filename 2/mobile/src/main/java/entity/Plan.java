package entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Plan {
    private Integer id;

    private String name;

    private String description;

    private BigDecimal cost;

    private Integer calls;

    private Integer messages;

    private Integer localData;

    private Integer nationalData;

    public Plan(Integer id, String name, String description, BigDecimal cost, Integer calls, Integer messages, Integer localData, Integer nationalData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.calls = calls;
        this.messages = messages;
        this.localData = localData;
        this.nationalData = nationalData;
    }

    public Integer getTypeNum(Type type) {
        switch (type) {
            case CALL:
                return calls;
            case MESSAGE:
                return messages;
            case LOCAL_DATA:
                return localData;
            case NATIONAL_DATA:
                return nationalData;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        String newLine = System.lineSeparator();

        return newLine +
                "套餐名：" + name + newLine +
//                "套餐描述：" + description + newLine +
                "套餐费用：" + cost + newLine;
    }
}
