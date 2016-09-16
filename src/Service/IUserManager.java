package Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import Eneity.*;
/**
 * Created by killeryuan on 2016/9/10.
 */
public interface IUserManager {
    public boolean login(String usernmae, String password);
    public String register(IntelUserInfoEntity userInfo);
    public String  getBasicInfo(String username)throws JsonProcessingException;
    public String  getCurrentStatus(String username) throws JsonProcessingException;
    public String getDevice(String username)throws ParseException , JsonProcessingException ;
    public String getLogs(String username)throws ParseException,JsonProcessingException;
    public boolean logOperation(String  method, String  alert_id);
    public boolean addDevice(String location, String username);
    public boolean destroyDevice(String username, String deviceName);
}
