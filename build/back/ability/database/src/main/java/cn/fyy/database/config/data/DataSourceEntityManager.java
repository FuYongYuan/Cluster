package cn.fyy.database.config.data;

import cn.fyy.database.config.data.routing.DataSourceRouting;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

/**
 * 数据源配置
 *
 * @author fuyy
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "platformTransactionManager",
        basePackages = {"${spring.jpa.repository-path}"} //设置Repository所在位置
)
public class DataSourceEntityManager {

    /**
     * JAP参数
     */
    @Resource
    private JpaProperties jpaProperties;

    /**
     * 数据源
     */
    @Resource
    private DataSourceRouting dataSourceRouting;

    /**
     * 数据模型目录
     */
    @Value("${spring.jpa.po-path}")
    private String poPath;

    /**
     * 构建实体工厂
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                // 数据源
                .dataSource(dataSourceRouting)
                // JPA配置
                .properties(jpaProperties.getProperties())
                // 设置实体类所在位置
                .packages(poPath)
                // 持久化单元名称
                .persistenceUnit("persistenceUnit")
                .build();
    }

    /**
     * 实体对象
     */
    @Bean
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return Objects.requireNonNull(this.entityManagerFactoryBean(builder).getObject()).createEntityManager();
    }

    /**
     * 事物
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(this.entityManagerFactoryBean(builder).getObject()));
    }

}
