package JsonHandlerEntity;

/**
 * Created by killeryuan on 2016/9/11.
 */
public class CurrentStatus {
    String currentTemperature;
    String currentHumidity;
    String currentAirRate;
    String unreadLogs;
    String deviceName;


    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(String currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public String getCurrentAirRate() {
        return currentAirRate;
    }

    public void setCurrentAirRate(String currentAirRate) {
        this.currentAirRate = currentAirRate;
    }

    public String getUnreadLogs() {
        return unreadLogs;
    }

    public void setUnreadLogs(String unreadLogs) {
        this.unreadLogs = unreadLogs;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
