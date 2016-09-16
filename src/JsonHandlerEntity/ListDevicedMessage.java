package JsonHandlerEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by killeryuan on 2016/9/13.
 */
public class ListDevicedMessage {
    private List<DevicedMessage> devicedMessages = new ArrayList<>();

    public void setDevicedMessage(DevicedMessage devicedMessage){
        devicedMessages.add(devicedMessage);
    }
}
