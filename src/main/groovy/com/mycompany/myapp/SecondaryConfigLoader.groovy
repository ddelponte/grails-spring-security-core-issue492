package com.mycompany.myapp

import grails.core.GrailsApplicationLifeCycle
import grails.plugin.springsecurity.SpringSecurityUtils
import groovy.util.logging.Slf4j

/**
 * Loads a secondary config similar to how a plugin would
 */
@Slf4j
class SecondaryConfigLoader implements GrailsApplicationLifeCycle {
    @Override
    Closure doWithSpring() {
        return null
    }

    @Override
    void doWithDynamicMethods() {

    }

    @Override
    void doWithApplicationContext() {
//        loadSecurityConfig()
        log.info "Loaded secondary config"
    }

    @Override
    void onConfigChange(Map<String, Object> event) {

    }

    @Override
    void onStartup(Map<String, Object> event) {
        loadSecurityConfig()
    }

    @Override
    void onShutdown(Map<String, Object> event) {

    }

    private static void loadSecurityConfig() {
        SpringSecurityUtils.loadSecondaryConfig 'DefaultSecondaryConfig'
    }
}
