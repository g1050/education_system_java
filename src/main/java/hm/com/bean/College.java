package hm.com.bean;

import org.hibernate.validator.constraints.Length;
import org.junit.runners.Parameterized;

import javax.validation.constraints.Pattern;

public class College {
    private Integer id;

    //可以验证中文
    @Length(min = 1,max=30,message = "学院名字长度30字符以内")
    private String name;

    private String location;

    @Pattern(regexp = "正则" )
    private Integer majorNum;

    private String president;

    private String phone;

    public College() {
		super();
		// TODO Auto-generated constructor stub
	}

	public College(String name, String location, Integer majorNum, String president, String phone) {
		super();
		this.name = name;
		this.location = location;
		this.majorNum = majorNum;
		this.president = president;
		this.phone = phone;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(Integer majorNum) {
        this.majorNum = majorNum;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president == null ? null : president.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}