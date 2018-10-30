package vo;

import entity.Type;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Expense {
    private int year;

    private int month;

    private Type type;

    private long useTotal;

    private long planTotal;

    private BigDecimal basicCost;

    public Expense(int year, int month, Type type, long useTotal, long planTotal, BigDecimal basicCost) {
        this.year = year;
        this.month = month;
        this.type = type;
        this.useTotal = useTotal;
        this.planTotal = planTotal;
        this.basicCost = basicCost;
    }

    public String toString() {
        String newLine = System.lineSeparator();

        return year + " 年 " + month + " 月 " + newLine +
                "资费账单（单位：元）" + newLine +
                "套餐量：" + planTotal + newLine +
                "使用量：" + useTotal + newLine +
                "基础费用：" + basicCost + newLine;
    }
}
