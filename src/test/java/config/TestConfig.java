package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties"
})
public interface TestConfig extends Config {

    @Key("browser.name")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browser.version")
    @DefaultValue("138.0")
    String getBrowserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("selenoidCredentials")
    String getSelenoidCredentials();

    @Key("selenoidUrl")
    String getSelenoidUrl();

    @Key("demoqaLogin")
    String getDemoqaLogin();

    @Key("demoqaPassword")
    String getDemoqaPassword();

}

