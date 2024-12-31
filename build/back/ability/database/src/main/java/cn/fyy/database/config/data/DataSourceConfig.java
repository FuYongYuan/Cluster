package cn.fyy.database.config.data;

import cn.fyy.database.config.data.routing.DataSourceRouting;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.jakarta.StatViewServlet;
import com.alibaba.druid.support.jakarta.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Druid配置
 *
 * @author fuyy
 */
@Slf4j
@Configuration
// 启用 DruidStatProperties 配置
@EnableConfigurationProperties(DruidStatProperties.class)
public class DataSourceConfig {
    //------------------------------------------------------------------------------------------------------------------写

    /**
     * 写数据源
     */
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.write")
    public DruidDataSource writeDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        // 配置防御SQL注入攻击策略
        List<Filter> filtersList = new ArrayList<>();
        filtersList.add(writeStatFilter());
        filtersList.add(writeWallFilter());
        druidDataSource.setProxyFilters(filtersList);
        return druidDataSource;
    }

    /**
     * 统计配置，当前只配置了慢sql检查
     */
    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.write.filter.stat")
    public StatFilter writeStatFilter() {
        return new StatFilter();
    }

    /**
     * 防护墙配置-启动、类型等
     */
    @Primary
    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.write.filter.wall")
    public WallFilter writeWallFilter() {
        WallFilter filter = new WallFilter();
        filter.setConfig(writeWallConfig());
        return filter;
    }

    /**
     * 防护墙配置-细节配置
     */
    @Primary
    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.write.filter.wall.config")
    public WallConfig writeWallConfig() {
        return new WallConfig();
    }

    //------------------------------------------------------------------------------------------------------------------读

    /**
     * 读数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.read")
    public DruidDataSource readDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        // 配置防御SQL注入攻击策略
        List<Filter> filtersList = new ArrayList<>();
        filtersList.add(readStatFilter());
        filtersList.add(readWallFilter());
        druidDataSource.setProxyFilters(filtersList);
        return druidDataSource;
    }

    /**
     * 统计配置，当前只配置了慢sql检查
     */
    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.read.filter.stat")
    public StatFilter readStatFilter() {
        return new StatFilter();
    }

    /**
     * 防护墙配置-启动、类型等
     */
    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.read.filter.wall")
    public WallFilter readWallFilter() {
        WallFilter filter = new WallFilter();
        filter.setConfig(readWallConfig());
        return filter;
    }

    /**
     * 防护墙配置-细节配置
     */
    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.read.filter.wall.config")
    public WallConfig readWallConfig() {
        return new WallConfig();
    }

    //------------------------------------------------------------------------------------------------------------------路由配置

    /**
     * 路由配置
     */
    @Bean
    public DataSourceRouting dataSourceRouting() {
        DruidDataSource writeDataSource = writeDataSource();
        DruidDataSource readDataSource = readDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceRouting.DataSourceType.WriteDataSource, writeDataSource);
        targetDataSources.put(DataSourceRouting.DataSourceType.ReadDataSource, readDataSource);

        DataSourceRouting dataSource = new DataSourceRouting();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(writeDataSource);
        return dataSource;
    }

    //------------------------------------------------------------------------------------------------------------------监控页面设置--不注入的话通过配置文件使用配置中心无法直接生效

    /**
     * Druid监控页面Servlet注册
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(DruidStatProperties properties) {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(statViewServlet, properties.getStatViewServlet().getUrlPattern());
        servletRegistrationBean.addInitParameter("loginUsername", properties.getStatViewServlet().getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", properties.getStatViewServlet().getLoginPassword());
        return servletRegistrationBean;
    }

    /**
     * Druid Web监控过滤器注册
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(DruidStatProperties properties) {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(webStatFilter);
        bean.setUrlPatterns(List.of(properties.getWebStatFilter().getUrlPattern()));
        bean.addInitParameter("exclusions", properties.getWebStatFilter().getExclusions());
        return bean;
    }
}