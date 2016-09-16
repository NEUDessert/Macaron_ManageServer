package JsonHandlerEntity;

/**
 * Created by killeryuan on 2016/9/13.
 */
public class DevicedMessage {
    String deviceName;
    String registerTime;
    String deviceLocate;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getDeviceLocate() {
        return deviceLocate;
    }

    public void setDeviceLocate(String deviceLocate) {
        this.deviceLocate = deviceLocate;
    }
}
