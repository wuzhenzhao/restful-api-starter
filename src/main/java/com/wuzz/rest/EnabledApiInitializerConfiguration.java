package com.wuzz.rest;

import com.wuzz.rest.processor.RestfulApiProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/22
 * Time: 18:03
 * Description 描述:
 */
@Configuration
public class EnabledApiInitializerConfiguration implements SmartLifecycle, Ordered {

    //外部配置
    @Autowired
    private ApiProperties restfulApiProperties;

    @Autowired
    private RestfulApiProcessor RestfulApiProcessor;


    private boolean running;

    private int order = 1;

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void start() {

        new Thread(() -> {

            System.out.println("开始API生成线程，基础包：" + restfulApiProperties.getScanPackage());
            try {
                RestfulApiProcessor.doScannerControl(restfulApiProperties.getScanPackage());
                RestfulApiProcessor.parseControlApi();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }).start();

    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public int getOrder() {
        return this.order;
    }
}
