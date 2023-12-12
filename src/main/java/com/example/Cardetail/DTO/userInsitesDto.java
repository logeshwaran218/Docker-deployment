package com.example.Cardetail.DTO;

public class userInsitesDto {
	private String carname;
	private String carmodel;
	private String username;
	private String userrole;
	public userInsitesDto() {
		super();
	}
	public userInsitesDto(String carname, String carmodel, String username, String userrole) {
		super();
		this.carname = carname;
		this.carmodel = carmodel;
		this.username = username;
		this.userrole = userrole;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarmodel() {
		return carmodel;
	}
	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

}
