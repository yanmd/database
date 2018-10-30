package dao;

import entity.*;
import org.apache.ibatis.annotations.*;
import vo.OrderVO;

import java.time.LocalDateTime;
import java.util.List;

public interface MainDao {
    @Insert("insert into users(name,telephone) values(#{name},#{telephone})")
    void addUser(User user);

    @Select("select * from users where id=#{uid}")
    User findUserById(@Param("uid") int uid);

    @Insert("insert into plans(name, description, cost, calls, messages, localData, nationalData) values(#{name}, #{description}, #{cost}, #{calls}, #{messages}, #{localData}, #{nationalData})")
    void addPlan(Plan plan);

    @Select("select * from plans where id=#{pid}")
    Plan findPlanById(@Param("pid") int pid);

    @Select("select * from plans")
    List<Plan> findPlans();

    @Insert("insert into orders(uid, pid, orderTime, startTime) values (#{uid}, #{pid}, #{orderTime}, #{startTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addOrder(Order order);

    @Update("update orders set endTime=#{endTime} where id=#{oid}")
    void cancelOrder(@Param("oid") int oid, @Param("endTime") LocalDateTime endTime);

    @Select("select * from orders where uid = #{uid} order by orderTime")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "pid", property = "plan", one = @One(select = "dao.MainDao.findPlanById")),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "startTime", property = "startTime"),
            @Result(column = "endTime", property = "endTime")
    })
    List<OrderVO> findOrdersByUserId(@Param("uid") int uid);

    @Select("select * from plans p where p.id in (select pid from orders o where o.uid = #{uid}\n" +
            "               and startTime < #{endTime} and (endTime is null or endTime >= #{endTime}))")
    List<Plan> findActivePlans(@Param("uid") int uid, @Param("endTime") LocalDateTime endTime);

    @Insert("insert into `usage` values(#{uid}, #{num}, #{useTime}, #{type})")
    void addUsage(Usage usage);

    @Select("select * from `usage` where uid=#{uid} and type = #{type} and useTime >=#{startTime} and useTime < #{endTime} ")
    List<Usage> findUsages(@Param("uid") int uid, @Param("type") Type type, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
