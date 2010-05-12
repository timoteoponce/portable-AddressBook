package org.uagrm.addressbook.model;

/**
 * @author Timoteo Ponce
 * 
 */
public class VirtualAddress implements Entity {
	private Integer id;
	private String identifier;
	private String website;
	private Service service;

	public VirtualAddress() {

	}

	public VirtualAddress(Integer id, String identifier, String website, Service service) {
		super();
		this.id = id;
		this.identifier = identifier;
		this.website = website;
		this.service = service;
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
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
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VirtualAddress [id=");
		builder.append(id);
		builder.append(", identifier=");
		builder.append(identifier);
		builder.append(", service=");
		builder.append(service);
		builder.append(", website=");
		builder.append(website);
		builder.append("]");
		return builder.toString();
	}

}
