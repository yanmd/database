package service;

import entity.Plan;
import entity.Type;
import entity.User;
import vo.Bill;
import vo.Expense;
import vo.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

public interface MainService {
    /**
     * 新增用户
     *
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 新增套餐
     *
     * @param plan 套餐
     */
    void addPlan(Plan plan);

    /**
     * @return 所有套餐
     */
    List<Plan> getPlans();

    /**
     * @param uid 用户id
     * @return 用户的订购记录
     */
    List<OrderVO> getOrders(int uid);

    /**
     * 订购套餐
     *
     * @param uid         用户id
     * @param pid         套餐id
     * @param nowTime     现在时间
     * @param immediately 是否立即订购
     * @return 订单id
     */
    int orderPlan(int uid, int pid, LocalDateTime nowTime, boolean immediately);

    /**
     * 取消订单
     *
     * @param oid         订单id
     * @param nowTime     现在时间
     * @param immediately 是否立即退订
     */
    void cancelPlan(int oid, LocalDateTime nowTime, boolean immediately);

    /**
     * 获取用户资费信息
     *
     * @param uid  用户id
     * @param now  现在时间
     * @param type 数据类型
     * @return 资费账单
     */
    Expense getExpense(int uid, LocalDateTime now, Type type);

    /**
     * 获取用户月账单
     *
     * @param uid   用户id
     * @param year  年
     * @param month 月
     * @return 月账单
     */
    Bill getBill(int uid, int year, int month);

    /**
     * 打电话
     *
     * @param uid       用户id
     * @param startTime 开始时间
     * @param minutes   时长
     */
    void call(int uid, LocalDateTime startTime, int minutes);

    /**
     * 发信息
     *
     * @param uid       用户id
     * @param startTime 开始时间
     */
    void sendMessage(int uid, LocalDateTime startTime);

    /**
     * 使用流量
     *
     * @param uid       用户id
     * @param startTime 开始时间
     * @param num       使用量
     * @param local     是否本地
     */
    void useData(int uid, LocalDateTime startTime, int num, boolean local);
}
