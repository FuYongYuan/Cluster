package cn.fyy.common.config.loadbalancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.client.loadbalancer.BlockingLoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.DeferringLoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRestClientBuilderBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡配置
 *
 * @author fyy
 */
@Configuration
public class LoadBalancerConfig {
    // 延迟拦截器-因不是静态会报警告所以配置，后续版本如有更新可以删除
    @Bean
    public static DeferringLoadBalancerInterceptor deferringLoadBalancerInterceptor(
            ObjectProvider<BlockingLoadBalancerInterceptor> loadBalancerInterceptorObjectProvider) {
        return new DeferringLoadBalancerInterceptor(loadBalancerInterceptorObjectProvider);
    }

    // 延迟拦截器-因不是静态会报警告所以配置，后续版本如有更新可以删除
    @Bean
    @ConditionalOnBean(DeferringLoadBalancerInterceptor.class)
    static LoadBalancerRestClientBuilderBeanPostProcessor lbRestClientPostProcessor(
            DeferringLoadBalancerInterceptor loadBalancerInterceptor, ApplicationContext context) {
        return new LoadBalancerRestClientBuilderBeanPostProcessor(loadBalancerInterceptor, context);
    }
}