package Eneity;

import javax.persistence.*;

/**
 * Created by killeryuan on 2016/9/15.
 */
@Entity
@Table(name = "last_data", schema = "", catalog = "macaron")
public class LastDataEntity {
    private int dataId;

    @Id
    @Column(name = "data_id")
    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    private String username;

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private int deviceid;

    @Basic
    @Column(name = "deviceid")
    public int getDeviceid() {
        return deviceid;
    }



    private String temperature;





    private String dampness;

//    @Basic
//    @Column(name = "deviceid")
//    public int getDeviceid() {
//        return deviceid;
//    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    @Basic
    @Column(name = "temperature")
    public String getTemperature() {
        return temperature;
    }    private String pm25;

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Basic
    @Column(name = "dampness")
    public String getDampness() {
        return dampness;
    }

    public void setDampness(String dampness) {
        this.dampness = dampness;
    }    private String pictureurl;

    @Basic
    @Column(name = "pm25")
    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    @Basic
    @Column(name = "pictureurl")
    public String getPictureurl() {
        return pictureurl;
    }    private int battery;

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    @Basic
    @Column(name = "battery")
    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }    private long recetime;

    @Basic
    @Column(name = "recetime")
    public long getRecetime() {
        return recetime;
    }

    public void setRecetime(long recetime) {
        this.recetime = recetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LastDataEntity that = (LastDataEntity) o;

        if (dataId != that.dataId) return false;
        if (deviceid != that.deviceid) return false;
        if (battery != that.battery) return false;
        if (recetime != that.recetime) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (temperature != null ? !temperature.equals(that.temperature) : that.temperature != null) return false;
        if (dampness != null ? !dampness.equals(that.dampness) : that.dampness != null) return false;
        if (pm25 != null ? !pm25.equals(that.pm25) : that.pm25 != null) return false;
        if (pictureurl != null ? !pictureurl.equals(that.pictureurl) : that.pictureurl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + deviceid;
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        result = 31 * result + (dampness != null ? dampness.hashCode() : 0);
        result = 31 * result + (pm25 != null ? pm25.hashCode() : 0);
        result = 31 * result + (pictureurl != null ? pictureurl.hashCode() : 0);
        result = 31 * result + battery;
        result = 31 * result + (int) (recetime ^ (recetime >>> 32));
        return result;
    }
}
