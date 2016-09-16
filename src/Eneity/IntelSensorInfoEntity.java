package Eneity;

import javax.persistence.*;

/**
 * Created by killeryuan on 2016/9/15.
 */
@Entity
@Table(name = "sensor_info", schema = "", catalog = "macaron")
public class IntelSensorInfoEntity {
    private int sensId;
    private String username;
    private int deviceid;
    private int sensorid;
    private int type;
    private String location;

    @Id
    @Column(name = "sens_id")
    public int getSensId() {
        return sensId;
    }

    public void setSensId(int sensId) {
        this.sensId = sensId;
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
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntelSensorInfoEntity that = (IntelSensorInfoEntity) o;

        if (sensId != that.sensId) return false;
        if (deviceid != that.deviceid) return false;
        if (sensorid != that.sensorid) return false;
        if (type != that.type) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sensId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + deviceid;
        result = 31 * result + sensorid;
        result = 31 * result + type;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
