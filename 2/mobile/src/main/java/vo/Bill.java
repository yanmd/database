package vo;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;

//账单
@Data
public class Bill {
    private String telephone;

    private int year, month;

    private Map<String, BigDecimal> planCosts;

    private Map<String, BigDecimal> basicCosts;

    private BigDecimal getTotalCost() {
        BigDecimal total = BigDecimal.valueOf(0);

        Collection<BigDecimal> planCostValues = planCosts.values();
        Collection<BigDecimal> basicCostValues = basicCosts.values();

        for (BigDecimal cost : planCostValues)
            total = total.add(cost);

        for (BigDecimal cost : basicCostValues)
            total = total.add(cost);

        return total.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        String newLine = System.lineSeparator();

        return year + " 年 " + month + " 月 " + newLine +
                "您好！" + telephone + newLine +
                "本月账单（单位：元）" + newLine +
                "合计：" + getTotalCost() + newLine +
                "套餐费用：" + newLine +
                (planCosts.isEmpty() ? "无" :
                        planCosts.entrySet()
                                .stream()
                                .map(x -> x.getKey() + "——" + x.getValue())
                                .reduce((x, y) -> (x + newLine + y)).orElse("")) + newLine +
                "基础费用：" + newLine +
                (basicCosts.isEmpty() ? "无" :
                        basicCosts.entrySet()
                                .stream()
                                .map(x -> x.getKey() + "——" + x.getValue())
                                .reduce((x, y) -> (x + newLine + y)).orElse("")) + newLine;
    }
}
