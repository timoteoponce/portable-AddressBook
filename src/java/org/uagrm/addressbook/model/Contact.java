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
	
	public boolean hasAddresses(){
		return !addresses.isEmpty();
	}

	public Set<VirtualAddress> getVirtualAddresses() {
		return virtualAddresses;
	}
	
	public boolean hasVirtualAddresses(){
		return !virtualAddresses.isEmpty();
	}

	public Set<Phone> getPhones() {
		return phones;
	}
	
	public boolean hasPhones(){
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