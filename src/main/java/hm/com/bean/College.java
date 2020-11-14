package hm.com.bean;

public class College {
    private Integer id;

    private String name;

    private String location;

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