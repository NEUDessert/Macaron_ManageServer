package JsonHandlerEntity;

import java.util.ArrayList;

/**
 * Created by killeryuan on 2016/9/11.
 */
public class ListCurrentStatus {
    ArrayList<CurrentStatus> list = new ArrayList<>();

    public void add(CurrentStatus currentStatus){
        list.add(currentStatus);
    }

}
