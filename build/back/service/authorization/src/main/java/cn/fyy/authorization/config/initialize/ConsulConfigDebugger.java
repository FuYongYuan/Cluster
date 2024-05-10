package cn.fyy.authorization.config.initialize;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.consul.ConsulProperties;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsulConfigDebugger {

    @Resource
    private ConsulProperties consulProperties;
    @Resource
    private ConsulConfigProperties consulConfigProperties;
    @Resource
    private ConsulDiscoveryProperties consulDiscoveryProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("Consul Config ConsulProperties: {}", consulProperties.toString());
        log.info("Consul Config ConsulConfigProperties: {}", consulConfigProperties.toString());
        log.info("Consul Config ConsulDiscoveryProperties: {}", consulDiscoveryProperties.toString());
    }
}
