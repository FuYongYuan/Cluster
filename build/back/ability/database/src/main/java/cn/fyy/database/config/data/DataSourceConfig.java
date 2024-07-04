package cn.fyy.database.config.data;

import cn.fyy.database.config.data.routing.DataSourceRouting;
import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
        filtersList.add(writeWallFilter());
        druidDataSource.setProxyFilters(filtersList);
        return druidDataSource;
    }

    @Primary
    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.write.filter.wall")
    public WallFilter writeWallFilter() {
        WallFilter filter = new WallFilter();
        filter.setConfig(writeWallConfig());
        return filter;
    }

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
        filtersList.add(readWallFilter());
        druidDataSource.setProxyFilters(filtersList);
        return druidDataSource;
    }

    @Bean(autowireCandidate = false)
    @ConfigurationProperties("spring.datasource.druid.read.filter.wall")
    public WallFilter readWallFilter() {
        WallFilter filter = new WallFilter();
        filter.setConfig(readWallConfig());
        return filter;
    }

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
}