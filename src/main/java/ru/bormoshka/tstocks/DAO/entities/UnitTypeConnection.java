/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.bormoshka.tstocks.DAO.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author 45
 */
@Entity
@Table(name = "UnitTypeConnection")
public class UnitTypeConnection extends AbstractEntity implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "parent_id", unique = false, nullable = false)
	private Long parentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "child_id", nullable = false, referencedColumnName = "id")
	private UnitType connectedUnitType;

	@Column(name = "min_quantity", nullable = false)
	private Integer minQuantity;
	
	@Column(name = "max_quantity", nullable = false)
	private Integer maxQuantity;

	@Column(name = "is_required", nullable = false)
	private Integer isRequired;

	public UnitTypeConnection() {
	}

	public UnitTypeConnection(Long id, Long parentId, UnitType connectedUnitType, Integer minQuantity, Integer maxQuantity, Integer isRequired) {
		this.id = id;
		this.parentId = parentId;
		this.connectedUnitType = connectedUnitType;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
		this.isRequired = isRequired;
	}

	public UnitType getConnectedUnitType() {
		return connectedUnitType;
	}

	public void setConnectedUnitType(UnitType connectedUnitType) {
		this.connectedUnitType = connectedUnitType;
	}

	public Integer getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(Integer minQuantity) {
		this.minQuantity = minQuantity;
	}

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
