package ru.bormoshka.tstocks.DAO.entities;
// Generated Nov 21, 2013 2:12:48 AM by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * UnitPropertyValue generated by hbm2java
 */
@Entity
@Table(name = "UnitPropertyValue")
@PrimaryKeyJoinColumn(name="user_nbr", referencedColumnName = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class UnitPropertyValue extends Property implements java.io.Serializable {

	@Column(name = "unit_id", unique = true, nullable = false)
	protected long unitId;
	
	@Column(name = "property_id", nullable = false)
	protected long propertyId;
	
	@Column(name = "value", length = 120)
	protected String value;

	public UnitPropertyValue() {
	}

	public UnitPropertyValue(long unitId, long propertyId) {
		this.unitId = unitId;
		this.propertyId = propertyId;
	}

	public UnitPropertyValue(long unitId, long propertyId, String value) {
		this.unitId = unitId;
		this.propertyId = propertyId;
		this.value = value;
	}

	
	public long getUnitId() {
		return this.unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}

	
	public long getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
