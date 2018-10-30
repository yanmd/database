import com.github.javafaker.Faker;
import entity.Type;
import entity.User;
import service.MainService;
import service.impl.MainServiceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

public class Main {
    private static MainService mainService = new MainServiceImpl();

    public static void main(String[] args) {
//        init();

        //1. 查询所有套餐
        runTask(() -> print("1. 查询所有套餐", mainService.getPlans()));

        //2.1 对用户1进行套餐查询（包括历史记录）
        runTask(() -> print("2.1 查询用户1套餐历史记录", mainService.getOrders(1)));

        //2.2 对用户2进行套餐查询（包括历史记录）
        runTask(() -> print("2.2 查询用户2套餐历史记录", mainService.getOrders(2)));

        //3. 对所有用户进行月账单查询
        runTask(() -> print("3.1 查询用户1的10月账单", mainService.getBill(1, 2018, 10)));
        runTask(() -> print("3.2 查询用户2的10月账单", mainService.getBill(2, 2018, 10)));
        runTask(() -> print("3.3 查询用户3的10月账单", mainService.getBill(3, 2018, 10)));

        //4. 对所有用户在通话情况下的资费生成
        runTask(() -> print("4.1 查询用户1在通话情况下的资费生成", mainService.getExpense(1, LocalDateTime.now(), Type.CALL)));
        runTask(() -> print("4.2 查询用户2在通话情况下的资费生成", mainService.getExpense(2, LocalDateTime.now(), Type.CALL)));
        runTask(() -> print("4.3 查询用户3在通话情况下的资费生成", mainService.getExpense(3, LocalDateTime.now(), Type.CALL)));

        //5. 对所有用户在使用本地流量情况下的资费生成
        runTask(() -> print("5.1 查询用户1在使用本地流量情况下的资费生成", mainService.getExpense(1, LocalDateTime.now(), Type.LOCAL_DATA)));
        runTask(() -> print("5.2 查询用户2在使用本地流量情况下的资费生成", mainService.getExpense(2, LocalDateTime.now(), Type.LOCAL_DATA)));
        runTask(() -> print("5.3 查询用户3在使用本地流量情况下的资费生成", mainService.getExpense(3, LocalDateTime.now(), Type.LOCAL_DATA)));

        //6. 订购套餐
        runTask(() -> mainService.orderPlan(1, 3, LocalDateTime.now(), true));
        print("6.1 用户1立即订购本地流量套餐（通过历史记录和月账单查看）", mainService.getOrders(1), mainService.getBill(1, 2018, 10));

        //7. 立即退订
        runTask(() -> mainService.cancelPlan(3, LocalDateTime.now(), true));
        print("7 用户1立即退订套餐5（通过历史记录和月账单查看）", mainService.getOrders(1), mainService.getBill(1, 2018, 10));

        //8. 下月退订
        runTask(() -> mainService.cancelPlan(1, LocalDateTime.now(), false));
        print("8 用户1下月退订套餐1（通过历史记录查看结果）", mainService.getOrders(1));

    }

    private static void init() {
        LocalDateTime now = LocalDateTime.now();

        Faker faker = new Faker(Locale.CHINA);

        for (int i = 0; i < 10; i++)
            mainService.addUser(new User(i, faker.name().fullName(), faker.phoneNumber().cellPhone()));

        mainService.orderPlan(1, 1, now, true);
        mainService.orderPlan(1, 3, now.plusMinutes(1), false);
        mainService.orderPlan(1, 5, now.plusMinutes(2), true);

        mainService.useData(1, now.plusMinutes(3), 5 * 1024, true);
        mainService.call(1, now.plusMinutes(10), 13);

        mainService.orderPlan(2, 1, now, true);
        int oid = mainService.orderPlan(2, 2, now.plusMinutes(1), true);
        mainService.cancelPlan(oid, now.plusMinutes(1).plusSeconds(5), false);
        mainService.call(2, now.plusMinutes(2), 3);

        mainService.call(3, now, 7);
        mainService.useData(3, now.plusMinutes(8), 64, true);
        mainService.useData(3, now.plusMinutes(10), 124, false);
    }

    private static void runTask(Runnable task) {
        LocalDateTime start = LocalDateTime.now();
        task.run();
        LocalDateTime end = LocalDateTime.now();

        System.out.println("开始时间：" + start);
        System.out.println("结束时间：" + end);
        System.out.println("运行：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println();
    }

    @SafeVarargs
    private static <T> void print(String operation, T... list) {
        System.out.println(operation);
        for (T t : list) {
            System.out.println(t);
        }
    }
}
