package sumeng.student.uap.common.utils;

/**
 * 通过从数据库中获得两个时间的差值，在本类中格式化后，返回天，时，分，秒
 */
public class Time {

    private Long day;
    private Long hour;
    private Long min;
    private Long s;

    public Time(Long time) {
        this.day = time / (24 * 60 * 60 * 1000);;
        this.hour =  (time / (60 * 60 * 1000) - day * 24);
        this.min =  ((time / (60 * 1000)) - day * 24 * 60 - hour * 60);
        this.s = (time / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getS() {
        return s;
    }

    public void setS(Long s) {
        this.s = s;
    }
}
