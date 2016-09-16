package Eneity;

import javax.persistence.*;

/**
 * Created by killeryuan on 2016/9/15.
 */
@Entity
@Table(name = "dev_info", schema = "", catalog = "macaron")
public class IntelDevInfoEntity {
    private int devId;
    private String username;
    private int deviceid;
    private String location;
    private long registerTime;

    @Id
    @Column(name = "dev_id")
    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
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
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "registerTime")
    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntelDevInfoEntity that = (IntelDevInfoEntity) o;

        if (devId != that.devId) return false;
        if (deviceid != that.deviceid) return false;
        if (registerTime != that.registerTime) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = devId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + deviceid;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (int) (registerTime ^ (registerTime >>> 32));
        return result;
    }
}
