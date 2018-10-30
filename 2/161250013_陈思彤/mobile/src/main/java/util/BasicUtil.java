package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class BasicUtil {
    private static BigDecimal CALL = BigDecimal.valueOf(0.5);

    private static BigDecimal MESSAGE = BigDecimal.valueOf(0.1);

    private static BigDecimal LOCAL_DATA = BigDecimal.valueOf(2);

    private static BigDecimal NATIONAL_DATA = BigDecimal.valueOf(5);

    public static BigDecimal calculateBasicCallCost(long difference) {
        return CALL.multiply(BigDecimal.valueOf(difference).abs()).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateBasicMessageCost(long difference) {
        return MESSAGE.multiply(BigDecimal.valueOf(difference).abs()).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateBasicLocalDataCost(long difference) {
        return LOCAL_DATA.multiply(BigDecimal.valueOf(difference).abs()).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateBasicNationalDataCost(long difference) {
        return NATIONAL_DATA.multiply(BigDecimal.valueOf(difference).abs()).setScale(2, RoundingMode.HALF_UP);
    }
}
