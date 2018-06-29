package com.mycompany.myapp

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

@CompileStatic
class ReadConfigService implements GrailsConfigurationAware {

    String value1
    String value2

    @Override
    @CompileDynamic
    void setConfiguration(Config co) {
        value1 = co.getProperty('myApp.value')
        value2 = co.myApp.value
    }

    def serviceMethod() {

        log.info "value1 = $value1"
        log.info "value2 = $value2"

        assert value1 == value2

        log.info 'service method called'
    }
}