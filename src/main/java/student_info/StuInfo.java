package student_info;

import java.util.Date;

public class StuInfo {

    public int stu_code;
    public String stu_name;
    public String id;
    public String pw;
    public Date birthday;
    public String major;
    public String minor;
    public String mobile;
    public String email;
    public String addr;
    public double avg_grade;
    public Date enterdate;
    public String semester;


    public StuInfo(int stu_code, String stu_name, String id, String pw, Date birthday, String major, String minor, String mobile, String email, String addr, double avg_grade, Date enterdate, String semester) {
        this.stu_code = stu_code;
        this.stu_name = stu_name;
        this.id = id;
        this.pw = pw;
        this.birthday = birthday;
        this.major = major;
        this.minor = minor;
        this.mobile = mobile;
        this.email = email;
        this.addr = addr;
        this.avg_grade = avg_grade;
        this.enterdate = enterdate;
        this.semester = semester;
    }

    public int getStu_code() {
        return stu_code;
    }

    public void setStu_code(int stu_code) {
        this.stu_code = stu_code;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getAvg_grade() {
        return avg_grade;
    }

    public void setAvg_grade(double avg_grade) {
        this.avg_grade = avg_grade;
    }

    public Date getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(Date enterdate) {
        this.enterdate = enterdate;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
