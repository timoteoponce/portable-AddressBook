package org.ponce.addressbook.model;

/**
 * @author Timoteo Ponce
 * 
 */
public class ReferenceLink {

	private Integer sourceId;
	private Integer targetId;
	private String sourceColumn;
	private String targetColumn;
	private String tableName;

	public ReferenceLink(Integer sourceId, Integer targetId,
			String sourceColumn, String targetColumn, String tableName) {
		super();
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.sourceColumn = sourceColumn;
		this.targetColumn = targetColumn;
		this.tableName = tableName;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public String getSourceColumn() {
		return sourceColumn;
	}

	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}

	public String getTargetColumn() {
		return targetColumn;
	}

	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "RefenceLink [sourceColumn=" + sourceColumn + ", sourceId="
				+ sourceId + ", tableName=" + tableName + ", targetColumn="
				+ targetColumn + ", targetId=" + targetId + "]";
	}

}
