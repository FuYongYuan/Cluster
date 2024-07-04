package cn.fyy.database.config.data.aspect;

import cn.fyy.database.config.data.routing.DataSourceRouting;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源切换 切面设置
 *
 * @author fuyy
 */
@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    /**
     * JAP参数
     */
    @Resource
    private JpaProperties jpaProperties;

    /**
     * 执行前判断选择
     */
    @Before("(@annotation(cn.fyy.database.config.data.annotation.WriteDataSource)" +
            "&& !@annotation(cn.fyy.database.config.data.annotation.ReadDataSource)" +
            "||  execution(* cn.fyy.*.service..*.insert*(..))" +
            "||  execution(* cn.fyy.*.service..*.update*(..))" +
            "||  execution(* cn.fyy.*.service..*.save*(..))" +
            "||  execution(* cn.fyy.*.service..*.delete*(..))" +
            ")")
    public void setWriteDataSourceType() {
        DataSourceRouting.writeDataSource();
        if (jpaProperties.isShowSql()) {
            log.info("<<<<<<<<<<<<======== 数据源连接 切换至写 ========>>>>>>>>>>>>");
        }
    }

    /**
     * 执行前判断选择
     */
    @Before("(@annotation(cn.fyy.database.config.data.annotation.ReadDataSource)" +
            "&& !@annotation(cn.fyy.database.config.data.annotation.WriteDataSource)" +
            "||  execution(* cn.fyy.*.service..*.query*(..))" +
            "||  execution(* cn.fyy.*.service..*.get*(..))" +
            "||  execution(* cn.fyy.*.service..*.find*(..))" +
            "||  execution(* cn.fyy.*.service..*.count*(..))" +
            ")")
    public void setReadDataSourceType() {
        DataSourceRouting.readDataSource();
        if (jpaProperties.isShowSql()) {
            log.info("<<<<<<<<<<<<======== 数据源连接 切换至读 ========>>>>>>>>>>>>");
        }
    }

    /**
     * 执行后执行关闭操作
     */
    @After("(" +
            "execution(* cn.fyy.*.service..*.query*(..))" +
            "|| execution(* cn.fyy.*.service..*.get*(..))" +
            "|| execution(* cn.fyy.*.service..*.find*(..))" +
            "|| execution(* cn.fyy.*.service..*.count*(..))" +
            "|| execution(* cn.fyy.*.service..*.insert*(..))" +
            "|| execution(* cn.fyy.*.service..*.update*(..))" +
            "|| execution(* cn.fyy.*.service..*.save*(..))" +
            "|| execution(* cn.fyy.*.service..*.delete*(..))" +
            "|| @annotation(cn.fyy.database.config.data.annotation.ReadDataSource)" +
            "|| @annotation(cn.fyy.database.config.data.annotation.WriteDataSource)" +
            ")")
    public void clean() {
        DataSourceRouting.cleanAll();
        if (jpaProperties.isShowSql()) {
            log.info("<<<<<<<<<<<<======== 数据源连接 关闭 ========>>>>>>>>>>>>");
        }
    }
}