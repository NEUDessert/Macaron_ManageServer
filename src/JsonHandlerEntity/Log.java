package JsonHandlerEntity;

/**
 * Created by killeryuan on 2016/9/13.
 */
public class Log {
    String alertId;
    String occurTime;
    String deviceName;
    String  deviceLocate;
    String message;
    String  isChecked;

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceLocate() {
        return deviceLocate;
    }

    public void setDeviceLocate(String deviceLocate) {
        this.deviceLocate = deviceLocate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
