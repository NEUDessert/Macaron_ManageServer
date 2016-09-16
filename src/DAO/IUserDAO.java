package DAO;

import JsonHandlerEntity.BasicInfo;
import JsonHandlerEntity.CurrentStatus;
import JsonHandlerEntity.DevicedMessage;
import JsonHandlerEntity.Log;

import java.text.ParseException;
import java.util.List;
import Eneity.*;
/**
 * Created by killeryuan on 2016/9/10.
 */
public interface IUserDAO {

    public boolean login(String username, String password);
    public String register(IntelUserInfoEntity userInfo);
    public BasicInfo getBasicInfo(String username);
    public List<CurrentStatus> getCurrentStatus(String username);
    public List<DevicedMessage> getDevice(String username)throws ParseException;
    public List<Log> getLogs(String username);
    public boolean logOperation(int method, int alert_id);
    public boolean addDevice(String location, String username);
    public boolean destroyDevice(String username, int deviceId);
}
