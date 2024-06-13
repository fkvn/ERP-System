package com.tedkvn.erp.config;

import com.tedkvn.erp.util.DbUtil;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


@Configuration
public abstract class AbstractInitSequenceBuild implements ApplicationListener<ApplicationReadyEvent> {

    private boolean initSequenceCalled = false;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (event instanceof ApplicationReadyEvent && !initSequenceCalled) {
            // Call DbUtil.initSequence(specificClass) here
            DbUtil.initSequence(getSpecificClass()); // Assuming DbUtil has this method
            initSequenceCalled = true;
        }
    }

    protected abstract Class<?> getSpecificClass();
}
