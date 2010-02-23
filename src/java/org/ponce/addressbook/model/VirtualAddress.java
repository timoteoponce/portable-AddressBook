package org.ponce.addressbook.model;

public class VirtualAddress implements Entity {
    private Integer id;
    private String identifier;
    private String website;
    private Protocol protocol;

    public VirtualAddress() {
	// TODO Auto-generated constructor stub
    }

    public VirtualAddress(Integer id, String identifier, String website,
	    Protocol protocol) {
	super();
	this.id = id;
	this.identifier = identifier;
	this.website = website;
	this.protocol = protocol;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public Protocol getProtocol() {
	return protocol;
    }

    public void setProtocol(Protocol protocol) {
	this.protocol = protocol;
    }

    public String getWebsite() {
	return website;
    }

    public void setWebsite(String website) {
	this.website = website;
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
	VirtualAddress other = (VirtualAddress) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
