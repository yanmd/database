package service.impl;

import dao.MainDao;
import entity.*;
import org.apache.ibatis.session.SqlSession;
import service.MainService;
import util.BasicUtil;
import util.DateUtil;
import util.MybatisUtil;
import vo.Bill;
import vo.Expense;
import vo.OrderVO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainServiceImpl implements MainService {
    @Override
    public List<OrderVO> getOrders(int uid) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            return mainDao.findOrdersByUserId(uid);
        }
    }

    @Override
    public void addUser(User user) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            mainDao.addUser(user);
        }
    }

    @Override
    public void addPlan(Plan plan) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            mainDao.addPlan(plan);
        }
    }

    @Override
    public List<Plan> getPlans() {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            return mainDao.findPlans();
        }
    }

    @Override
    public int orderPlan(int uid, int pid, LocalDateTime now, boolean immediately) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            Order order = new Order(uid, pid, now, immediately ? now : DateUtil.getNextMonthFirstDayZero(now));
            mainDao.addOrder(order);
            return order.getId();
        }
    }

    @Override
    public void cancelPlan(int oid, LocalDateTime now, boolean immediately) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            mainDao.cancelOrder(oid, immediately ? now : DateUtil.getNextMonthFirstDayZero(now));
        }
    }

    @Override
    public Expense getExpense(int uid, LocalDateTime now, Type type) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            LocalDateTime startTime = DateUtil.getThisMonthFirstDayZero(now);
            LocalDateTime endTime = DateUtil.getNextMonthFirstDayZero(now);

            if (type == Type.CALL || type == Type.MESSAGE || type == Type.NATIONAL_DATA) {

                long useTotal = mainDao.findUsages(uid, type, startTime, endTime).stream().mapToLong(Usage::getNum).sum();

                long planTotal = mainDao.findActivePlans(uid, endTime).stream().mapToLong(x -> x.getTypeNum(type)).sum();

                BigDecimal basicCost = BigDecimal.valueOf(0, 2);

                if (useTotal > planTotal) {
                    long difference = useTotal - planTotal;

                    switch (type) {
                        case CALL:
                            basicCost = BasicUtil.calculateBasicCallCost(difference);
                            break;
                        case MESSAGE:
                            basicCost = BasicUtil.calculateBasicMessageCost(difference);
                            break;
                        case NATIONAL_DATA:
                            basicCost = BasicUtil.calculateBasicNationalDataCost(difference);
                            break;
                    }
                }

                return new Expense(now.getYear(), now.getMonthValue(), type, useTotal, planTotal, basicCost);
            } else {
                long useLocalTotal = mainDao.findUsages(uid, type, startTime, endTime).stream().mapToLong(Usage::getNum).sum();

                long useNationalTotal = mainDao.findUsages(uid, Type.NATIONAL_DATA, startTime, endTime).stream().mapToInt(Usage::getNum).sum();

                List<Plan> activePlan = mainDao.findActivePlans(uid, endTime);

                long planNationalTotal = activePlan.stream().mapToLong(x -> x.getTypeNum(Type.NATIONAL_DATA)).sum();

                long planLocalTotal = activePlan.stream().mapToLong(x -> x.getTypeNum(Type.LOCAL_DATA)).sum();

                long nationRemain = (planNationalTotal > useNationalTotal) ? planNationalTotal - useNationalTotal : 0;

                BigDecimal basicCost = useLocalTotal > planLocalTotal + nationRemain ? BasicUtil.calculateBasicLocalDataCost(useLocalTotal - planLocalTotal - nationRemain) : BigDecimal.valueOf(0, 2);

                return new Expense(now.getYear(), now.getMonthValue(), Type.LOCAL_DATA, useLocalTotal, planLocalTotal + nationRemain, basicCost);
            }
        }
    }

    @Override
    public Bill getBill(int uid, int year, int month) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            Bill bill = new Bill();

            bill.setYear(year);
            bill.setMonth(month);

            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            bill.setTelephone(mainDao.findUserById(uid).getTelephone());

            List<Plan> activePlans = mainDao.findActivePlans(uid, DateUtil.getNextMonthFirstDayZero(year, month));

            bill.setPlanCosts(activePlans
                    .stream()
                    .collect(Collectors.toMap(Plan::getName, Plan::getCost)));

            int planCalls = activePlans.stream().mapToInt(Plan::getCalls).sum(),
                    planMessages = activePlans.stream().mapToInt(Plan::getMessages).sum(),
                    planLocalData = activePlans.stream().mapToInt(Plan::getLocalData).sum(),
                    planNationalData = activePlans.stream().mapToInt(Plan::getNationalData).sum();

            LocalDateTime startTime = DateUtil.getThisMonthFirstDayZero(year, month);
            LocalDateTime endTime = DateUtil.getNextMonthFirstDayZero(year, month);

            long usedCalls = mainDao.findUsages(uid, Type.CALL, startTime, endTime).stream().mapToLong(Usage::getNum).sum();
            long usedMessages = mainDao.findUsages(uid, Type.MESSAGE, startTime, endTime).stream().mapToLong(Usage::getNum).sum();
            long usedLocalData = mainDao.findUsages(uid, Type.LOCAL_DATA, startTime, endTime).stream().mapToLong(Usage::getNum).sum();
            long usedNationalData = mainDao.findUsages(uid, Type.NATIONAL_DATA, startTime, endTime).stream().mapToLong(Usage::getNum).sum();

            Map<String, BigDecimal> basicCosts = new HashMap<>();

            if (usedCalls > planCalls)
                basicCosts.put("通话费用", BasicUtil.calculateBasicCallCost(usedCalls - planCalls));
            if (usedMessages > planMessages)
                basicCosts.put("短信费用", BasicUtil.calculateBasicMessageCost(usedMessages - planMessages));

            long remainData = 0;

            if (usedNationalData > planNationalData)
                basicCosts.put("国内流量费用", BasicUtil.calculateBasicNationalDataCost(usedNationalData - planNationalData));
            else remainData = planNationalData - usedNationalData;

            if (usedLocalData > planLocalData + remainData)
                basicCosts.put("本地流量费用", BasicUtil.calculateBasicLocalDataCost(usedLocalData - planLocalData - remainData));

            bill.setBasicCosts(basicCosts);
            return bill;
        }
    }

    @Override
    public void call(int uid, LocalDateTime startTime, int minutes) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            mainDao.addUsage(new Usage(uid, minutes, startTime, Type.CALL));
        }
    }

    @Override
    public void sendMessage(int uid, LocalDateTime startTime) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            mainDao.addUsage(new Usage(uid, 1, startTime, Type.MESSAGE));
        }
    }

    @Override
    public void useData(int uid, LocalDateTime startTime, int num, boolean local) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            MainDao mainDao = sqlSession.getMapper(MainDao.class);

            mainDao.addUsage(new Usage(uid, num, startTime, local ? Type.LOCAL_DATA : Type.NATIONAL_DATA));
        }
    }
}
