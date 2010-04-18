package org.uagrm.addressbook.model;

import java.util.HashSet;
import java.util.Set;

import org.uagrm.addressbook.view.dialog.SelectableItem;

/**
 * @author Timo
 * @version 1.0
 * @created 16-ene-2010 0:59:50
 */
public class Contact implements SelectableItem {

	private Integer id;
	private String firstName;
	private String lastName;
	private final Set<Group> groups = new HashSet<Group>();
	private final Set<Address> addresses = new HashSet<Address>();
	private final Set<VirtualAddress> virtualAddresses = new HashSet<VirtualAddress>();
	private final Set<Phone> phones = new HashSet<Phone>();

	public Contact() {

	}

	public Contact(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setId(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public boolean hasAddresses() {
		return !addresses.isEmpty();
	}

	public Set<VirtualAddress> getVirtualAddresses() {
		return virtualAddresses;
	}

	public boolean hasVirtualAddresses() {
		return !virtualAddresses.isEmpty();
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public boolean hasPhones() {
		return !phones.isEmpty();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "[ " + id + " , " + firstName + " , " + lastName + " ]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		result = prime * result + ((virtualAddresses == null) ? 0 : virtualAddresses.hashCode());
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
		Contact other = (Contact) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phones == null) {
			if (other.phones != null)
				return false;
		} else if (!phones.equals(other.phones))
			return false;
		if (virtualAddresses == null) {
			if (other.virtualAddresses != null)
				return false;
		} else if (!virtualAddresses.equals(other.virtualAddresses))
			return false;
		return true;
	}

	@Override
	public boolean equals(SelectableItem item) {
		if (this == item)
			return true;
		if (item == null)
			return false;
		if (getClass() != item.getClass())
			return false;
		Contact other = (Contact) item;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}