package Eneity;

import javax.persistence.*;

/**
 * Created by killeryuan on 2016/9/15.
 */
@Entity
@Table(name = "alert", schema = "", catalog = "macaron")
public class IntelAlertEntity {
    private int alertId;
    private String username;
    private int deviceid;
    private int sensorid;
    private int alerttype;
    private long alerttime;
    private int ischeck;

    @Id
    @Column(name = "alert_id")
    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "deviceid")
    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    @Basic
    @Column(name = "sensorid")
    public int getSensorid() {
        return sensorid;
    }

    public void setSensorid(int sensorid) {
        this.sensorid = sensorid;
    }

    @Basic
    @Column(name = "alerttype")
    public int getAlerttype() {
        return alerttype;
    }

    public void setAlerttype(int alerttype) {
        this.alerttype = alerttype;
    }

    @Basic
    @Column(name = "alerttime")
    public long getAlerttime() {
        return alerttime;
    }

    public void setAlerttime(long alerttime) {
        this.alerttime = alerttime;
    }

    @Basic
    @Column(name = "ischeck")
    public int getIscheck() {
        return ischeck;
    }

    public void setIscheck(int ischeck) {
        this.ischeck = ischeck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntelAlertEntity that = (IntelAlertEntity) o;

        if (alertId != that.alertId) return false;
        if (deviceid != that.deviceid) return false;
        if (sensorid != that.sensorid) return false;
        if (alerttype != that.alerttype) return false;
        if (alerttime != that.alerttime) return false;
        if (ischeck != that.ischeck) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = alertId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + deviceid;
        result = 31 * result + sensorid;
        result = 31 * result + alerttype;
        result = 31 * result + ischeck;
        return result;
    }
}
