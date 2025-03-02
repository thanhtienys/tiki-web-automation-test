package driver;

import java.time.Duration;

public class Config {

    public static Duration TIME_WAIT_DISPLAY_ADS = Duration.ofSeconds(15);

    public static Duration TIMEOUT_GET_ELEMENT = Duration.ofSeconds(5);
    public static Duration TIME_CLICK_ABLE = Duration.ofSeconds(5);
    public static Duration TIME_VISIBILITY = Duration.ofSeconds(5);
    public static Duration TIME_INVISIBILITY = Duration.ofSeconds(5);


    public enum BrowserName {
        CHROME,
        FIREFOX,
        SAFARI
    }
}
