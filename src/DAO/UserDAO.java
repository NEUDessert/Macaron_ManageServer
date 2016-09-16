package DAO;

import Eneity.*;
import JsonHandlerEntity.BasicInfo;
import JsonHandlerEntity.CurrentStatus;
import JsonHandlerEntity.DevicedMessage;
import JsonHandlerEntity.Log;
import Utils.GetCurrentTime;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by killeryuan on 2016/9/10.
 */
public class UserDAO implements IUserDAO{



    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean login(String username, String password){
        Session session = sessionFactory.openSession();
        try {

            String hql = "from IntelUserInfoEntity where username = '" + username + "' and password = '" + password + "'";
            Query query = session.createQuery(hql);
            List<IntelUserInfoEntity> intelUserInfoEntityList = query.list();
            if (intelUserInfoEntityList.size() == 0) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String register(IntelUserInfoEntity userInfo) {
        System.out.println(userInfo.getEmail());
        Session session = sessionFactory.openSession();
        try {
            String hql = "from IntelUserInfoEntity where username = '" + userInfo.getUsername() + "'";
            Query query = session.createQuery(hql);
            List<IntelUserInfoEntity> list = query.list();
            if (list.size() == 0) {
                session.beginTransaction();
                session.save(userInfo);
                session.getTransaction().commit();
                session.close();
                return "0";
            }
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return "2";
        }
    }

    public BasicInfo getBasicInfo(String username){
        Session session = sessionFactory.openSession();
        try{
            String hql = "select  devnum  from IntelUserInfoEntity  where username = '"+username+"'";
            Query query = session.createQuery(hql);
            List<Integer> list = query.list();
            BasicInfo basicInfo = new BasicInfo();
            basicInfo.setTotal(list.get(0) + "");
            basicInfo.setOnline(list.get(0)+"");
            String offline = "select recetime from LastDataEntity where username = '"+username+"'";
            query = session.createQuery(offline);
            List<Long> lastNum = query.list();
            if (lastNum.size() == 0) {
                basicInfo.setOffline(list.get(0)+"");
                basicInfo.setOnline(0+"");
            }
            else {
                int noMessage = 0;
                int index = 0;
                long times = System.currentTimeMillis();
                if (lastNum.size() == list.get(0)) {
                    for (int i = 0; i < lastNum.size(); i++) {
                        if ((times - lastNum.get(i)) > 60 * 1000 * 3) {
                            System.out.println(times - lastNum.get(i));
                            index++;
                        }
                    }
                } else {
                    noMessage = list.get(0) - lastNum.size();
                    for (int i = 0; i < lastNum.size(); i++) {
                        if ((times - lastNum.get(i)) > 60 * 1000 * 3) {
                            System.out.println(times - lastNum.get(i));
                            index++;
                        }
                    }
                }
                basicInfo.setOffline(index + noMessage + "");
                basicInfo.setOnline((Integer.parseInt(basicInfo.getOnline()) - index-noMessage) + "");
            }
            hql = "select count(*) from IntelAlertEntity where username = '"+username+"'and ischeck = 0 ";
            query = session.createQuery(hql);
            List<Integer> list1 = query.list();
            basicInfo.setUnread(list1.get(0)+"");
            return basicInfo;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<CurrentStatus> getCurrentStatus(String username){
        Session session = sessionFactory.openSession();
        try {

//            String hql = "select data.deviceid, data.temperature, data.dampness, data.pm25, max(data.recetime) " +
//                    "from IntelDataEntity as data , IntelDevInfoEntity  as devInfo " +
//                    "where data.username = devInfo.username and data.deviceid = devInfo.deviceid and data.username = '" + username + "' " +
//                    "order by data.deviceid";
            String hql = "select  data.deviceid, data.temperature, data.dampness, data.pm25, data.recetime " +
                    "from LastDataEntity as data ,IntelDevInfoEntity as dev " +
                    "where data.username = '" + username + "' and data.deviceid = dev.deviceid " ;
            Query query = session.createQuery(hql);
            List<Object[]> list = query.list();
            if (list.size() == 0) {
                return null;
            }
            List<CurrentStatus> statusList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Object object[] = list.get(i);
                CurrentStatus currentStatus = new CurrentStatus();
                hql = "select location from IntelDevInfoEntity where username = '"+username+"' and deviceid = "+(Integer)object[0];
                query = session.createQuery(hql);
                List<String> locations = query.list();
                currentStatus.setDeviceName(locations.get(0));
                currentStatus.setCurrentTemperature((String) (object[1]));
                currentStatus.setCurrentHumidity((String) (object[2]));
                currentStatus.setCurrentAirRate((String) object[3]);
                hql = "select count(*) from IntelAlertEntity where username = '" + username + "' and deviceid = " + (int) object[0]+ " and ischeck = 0";
                query = session.createQuery(hql);
                List<Integer> li = query.list();
                currentStatus.setUnreadLogs("" + li.get(0));
                statusList.add(currentStatus);
            }
            return statusList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<DevicedMessage> getDevice(String username)throws ParseException{
        Session session = sessionFactory.openSession();
        try {

            String hql = "from IntelDevInfoEntity where username = '" + username + "'";
            Query query = session.createQuery(hql);
            List<IntelDevInfoEntity> devInfoEntities = query.list();
            List<DevicedMessage> devicedMessage = new ArrayList<>();
            if (devInfoEntities.size() == 0) {
                return null;
            } else {
                for (int i = 0; i < devInfoEntities.size(); i++) {
                    DevicedMessage devide = new DevicedMessage();
                    devide.setDeviceName(username + "-" + devInfoEntities.get(i).getDeviceid());
                    devide.setDeviceLocate(devInfoEntities.get(i).getLocation());
                    GetCurrentTime time = GetCurrentTime.singleGetCurrentTime();
                    String register = time.getTime(devInfoEntities.get(i).getRegisterTime());
                    devide.setRegisterTime(register);
                    devicedMessage.add(devide);
                }
                return devicedMessage;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Log> getLogs(String username){
        Session session = sessionFactory.openSession();
        try {

            String hql = "select alert.alertId, alert.alerttime, alert.deviceid, dev.location, alert.ischeck, alert.alerttype from IntelAlertEntity as alert ,IntelDevInfoEntity as dev where dev.username = '" + username + "' and dev.username = alert.username and dev.deviceid = alert.deviceid";
            Query query = session.createQuery(hql);
            List<Object[]> strings = query.list();
            List<Log> logs = new ArrayList<>();
            if (strings.size() == 0) {
                return null;
            } else {
                for (int i = 0; i < strings.size(); i++) {
                    Log log = new Log();
                    log.setAlertId((Integer)strings.get(i)[0]+"");
                    GetCurrentTime getTime = GetCurrentTime.singleGetCurrentTime();
                    String time = getTime.getTime((Long)strings.get(i)[1]);
                    log.setOccurTime((time));
                    log.setDeviceName(username + "-" + strings.get(i)[2]);
                    log.setDeviceLocate((String)strings.get(i)[3]);
                    log.setIsChecked((Integer)strings.get(i)[4]+"");
                    log.setMessage((Integer)strings.get(i)[5]+"");
                    logs.add(log);
                }
                return logs;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

    }

    public boolean logOperation(int method, int alert_id){
        Session session = sessionFactory.openSession();
        try {

            String hql = "update IntelAlertEntity set ischeck = " + method + " where alertId = " + alert_id;
            Query query = session.createQuery(hql);
            query.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean addDevice(String location, String username){
        Session session = sessionFactory.openSession();
        try {

            String hql = "select max(deviceid) from IntelDevInfoEntity where username = '" + username + "'";
            Query query = session.createQuery(hql);
            List<Integer> list = query.list();
            int index;
            if (list.get(0) == null) {
                index = 1;
            } else {
                index = list.get(0) + 1;
            }
            IntelDevInfoEntity intelDevInfoEntity = new IntelDevInfoEntity();
            intelDevInfoEntity.setUsername(username);
            intelDevInfoEntity.setDeviceid(index);
            intelDevInfoEntity.setLocation(location);
            intelDevInfoEntity.setRegisterTime(System.currentTimeMillis());
            session.beginTransaction();
            session.save(intelDevInfoEntity);
            session.getTransaction().commit();
            hql = "select devnum from IntelUserInfoEntity where username = '" + username + "'";
            query = session.createQuery(hql);
            List<Integer> devnum = query.list();
            index = devnum.get(0) + 1;
            hql = "update IntelUserInfoEntity set devnum = " + index + " where username = '" + username + "'";
            query = session.createQuery(hql);
            query.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean destroyDevice(String username, int deviceId){
        Session session = sessionFactory.openSession();
        try {

            String hql = "delete IntelDevInfoEntity where username = '" + username + "' and  deviceid = " + deviceId;
            Query query = session.createQuery(hql);
            query.executeUpdate();
            hql = "delete IntelDataEntity where username = '" + username + "' and  deviceid = " + deviceId;
            query = session.createQuery(hql);
            query.executeUpdate();
            hql = "delete LastDataEntity where username = '" + username + "' and  deviceid = " + deviceId;
            query = session.createQuery(hql);
            query.executeUpdate();
            hql = "delete IntelAlertEntity where username = '" + username + "' and  deviceid = " + deviceId;
            query = session.createQuery(hql);
            query.executeUpdate();

            hql = "select devnum from IntelUserInfoEntity where username = '" + username + "'";
            query = session.createQuery(hql);
            List<Integer> devnum = query.list();
            int index = devnum.get(0) - 1;
            hql = "update IntelUserInfoEntity set devnum = " + index + " where username = '" + username + "'";
            query = session.createQuery(hql);
            query.executeUpdate();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}



