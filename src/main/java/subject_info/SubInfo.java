package subject_info;

public class SubInfo {
	
	private Integer sub_code;
    private String sub_name;
	private String sub_type;
	private String pro_name;
    private Integer grade;
	private Integer stu_num;
	private Integer major_stu_num;
	private Integer minor_stu_num;
    private Integer general_stu_num;
    private String place;
	private String time;
    private Integer rating;


	public SubInfo(Integer sub_code, String sub_name, String sub_type, String pro_name, Integer grade, Integer stu_num, Integer major_stu_num, Integer minor_stu_num, Integer general_stu_num, String place, String time, Integer rating) {
		this.sub_code = sub_code;
		this.sub_name = sub_name;
		this.sub_type = sub_type;
		this.pro_name = pro_name;
		this.grade = grade;
		this.stu_num = stu_num;
		this.major_stu_num = major_stu_num;
		this.minor_stu_num = minor_stu_num;
		this.general_stu_num = general_stu_num;
		this.place = place;
		this.time = time;
		this.rating = rating;
	}

	public Integer getSub_code() {
		return sub_code;
	}

	public void setSub_code(Integer sub_code) {
		this.sub_code = sub_code;
	}

	public String getSub_name() {
		return sub_name;
	}

	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}

	public String getSub_type() {
		return sub_type;
	}

	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getStu_num() {
		return stu_num;
	}

	public void setStu_num(Integer stu_num) {
		this.stu_num = stu_num;
	}

	public Integer getMajor_stu_num() {
		return major_stu_num;
	}

	public void setMajor_stu_num(Integer major_stu_num) {
		this.major_stu_num = major_stu_num;
	}

	public Integer getMinor_stu_num() {
		return minor_stu_num;
	}

	public void setMinor_stu_num(Integer minor_stu_num) {
		this.minor_stu_num = minor_stu_num;
	}

	public Integer getGeneral_stu_num() {
		return general_stu_num;
	}

	public void setGeneral_stu_num(Integer general_stu_num) {
		this.general_stu_num = general_stu_num;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}