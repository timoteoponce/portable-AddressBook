package org.ponce.addressbook.model;

public class Phone implements Entity {
    private Integer id;
    private String housePhone;
    private String mobilePhone;
    private String workPhone;

    public Phone() {
	// TODO Auto-generated constructor stub
    }

    public Phone(Integer id, String housePhone, String mobilePhone,
	    String workPhone) {
	super();
	this.id = id;
	this.housePhone = housePhone;
	this.mobilePhone = mobilePhone;
	this.workPhone = workPhone;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getHousePhone() {
	return housePhone;
    }

    public void setHousePhone(String housePhone) {
	this.housePhone = housePhone;
    }

    public String getMobilePhone() {
	return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
    }

    public String getWorkPhone() {
	return workPhone;
    }

    public void setWorkPhone(String workPhone) {
	this.workPhone = workPhone;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Phone other = (Phone) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
