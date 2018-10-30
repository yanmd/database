package vo;

import entity.Plan;
import lombok.Data;

import java.time.LocalDateTime;

//套餐查询
@Data
public class OrderVO {
    private Integer id;

    private Plan plan;

    private LocalDateTime orderTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Override
    public String toString() {
        String newLine = System.lineSeparator();

        return plan.toString() +
                "订购时间：" + orderTime + newLine +
                "生效时间：" + startTime + newLine +
                "到期时间：" + (endTime == null ? "" : endTime) + newLine;
    }
}
