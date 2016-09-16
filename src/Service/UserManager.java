package Service;

import DAO.IUserDAO;
import JsonHandlerEntity.BasicInfo;
import JsonHandlerEntity.Log;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.util.List;
import java.util.StringTokenizer;

import Eneity.*;
import org.hibernate.Session;

/**
 * Created by killeryuan on 2016/9/10.
 */
public class UserManager implements IUserManager{

    String alertType[] = {"燃气报警","烟雾报警","防盗报警","","","",""};

    private IUserDAO userDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private ObjectMapper objectMapper;

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean login(String usernmae, String password){
        return userDAO.login(usernmae,password);
    }

    public String register(IntelUserInfoEntity userInfo) {
        return userDAO.register(userInfo);
    }

    public String  getBasicInfo(String username)throws JsonProcessingException{
        BasicInfo basicInfo = userDAO.getBasicInfo(username);
         objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(basicInfo);
    }

    public String getCurrentStatus(String username) throws JsonProcessingException{
        String json;
        if((json=objectMapper.writeValueAsString(userDAO.getCurrentStatus(username))).equals("null")) {
            return "{\"error\":\"1\"}";
        }
        return json;
    }
    public String getDevice(String username)throws ParseException, JsonProcessingException {
        String json;
        if((json=objectMapper.writeValueAsString(userDAO.getDevice(username))).equals("null"))
            return "{\"error\":\"1\"}";
        return json;
    }
    public String getLogs(String username)throws ParseException,JsonProcessingException{
        try {
            List<Log> logs;

            if ((logs = userDAO.getLogs(username)) == null) {
                return "{\"error\":\"1\"}";
            }
            for (int i = 0; i < logs.size(); i++) {
                logs.get(i).setMessage(alertType[Integer.parseInt(logs.get(i).getMessage())]);
            }
            return objectMapper.writeValueAsString(logs);
        }catch (Exception e){
            e.printStackTrace();
            return "{\"error\":\"1\"}";
        }
    }

    public boolean logOperation(String  method, String  alert_id){
        try {
            int method_int = Integer.parseInt(method);
            int alert_id_int = Integer.parseInt(alert_id);
            return userDAO.logOperation(method_int, alert_id_int);
        }catch (Exception e){
            return false;
        }
    }

    public boolean addDevice(String location, String username){
        return userDAO.addDevice(location, username);
    }
    public boolean destroyDevice(String username, String deviceName){
        StringTokenizer stringTokenizer = new StringTokenizer(deviceName, "-");
        String getId[] = new String[2];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()){
            getId[i] = stringTokenizer.nextToken();
            i++;
        }
        int deviceId = Integer.parseInt(getId[1]);
        return userDAO.destroyDevice(username, deviceId);
    }
}
