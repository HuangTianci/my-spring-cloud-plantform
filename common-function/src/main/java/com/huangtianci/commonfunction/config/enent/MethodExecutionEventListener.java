package com.huangtianci.commonfunction.config.enent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author Huang Tianci
 * @date 2017/11/22
 */
@Service
public class MethodExecutionEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof MethodExecutionEvent) {
            //TODO
        }
    }
}
