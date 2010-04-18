package org.uagrm.addressbook.model.dto;


public class EntityStatus<T> {
	private final T entity;

	private final StatusType status;

	private EntityStatus(T entity, StatusType status) {
		super();
		this.entity = entity;
		this.status = status;
	}

	public T getEntity() {
		return entity;
	}

	public StatusType getStatus() {
		return status;
	}

	public static <T> EntityStatus<T> create(T entity, StatusType status) {
		return new EntityStatus<T>(entity, status);
	}

}
