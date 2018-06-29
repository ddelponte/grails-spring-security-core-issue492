# grails-spring-security-core-issue492
Grails 3.3.6 app which demonstrates a bug in secondary config loading where environmental variables are not properly evaluated

This repo is intended to duplicate the bug reported in issue #[492](https://github.com/grails-plugins/grails-spring-security-core/issues/492)

Grails 3.3.6

Spring Security Core Plugin 3.2.2

I have a simple `DefaultSecondaryConfig.groovy` whose contents don't seem to matter to reproduce this bug. I also have a `SecondaryConfigLoader.groovy` bean defined in `src/main/groovy` and injected in `resources.groovy`, to simulate a plugin lifecycle action for demonstration purposes.

`ReadConfigService.groovy` is a grails service that reads configuration using the map-navigation syntax `co.myApp.value`, as well as the preferred `getProperty` syntax `co.getProperty('myApp.value')`. Its service method asserts that both read values are equal. 

`application.yml` defines a property `myApp.value: '${MY_APP_VALUE:foo}'` that should evaluate to `foo` when `MY_APP_VALUE` is not defined (the default case in the sample application).

I call the service method inside `BootStrap.groovy`, so the application only runs if the assertion is true.

Expected Behavior
===
Both property values are equal, regardless of where `SpringSecurityUtils.loadSecondaryConfig` is called in the application lifecycle. The application runs without any error.

Actual Behavior
===
The assertion fails inside the service method:
```
org.codehaus.groovy.runtime.powerassert.PowerAssertionError: assert value1 == value2
       |         |
       |         foo
       ${MY_APP_VALUE:foo}
```

This can be tracked down to calling `SpringSecurityUtils.loadSecondaryConfig` inside `doWithApplicationContext`. Commenting out that line makes the assertion pass.

Workaround
===
Use the less-efficient map-navigation syntax for parsing configuration