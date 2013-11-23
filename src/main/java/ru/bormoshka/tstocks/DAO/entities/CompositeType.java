package ru.bormoshka.tstocks.DAO.entities;
// Generated Nov 23, 2013 3:05:27 PM by Hibernate Tools 3.6.0

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * CompositeType generated by hbm2java
 */
@Entity
@Table(name = "CompositeType")
public class CompositeType extends AbstractEntity implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
	private Category category;
	
	@Column(name = "name", nullable = false, length = 120)
	private String name;
	@Column(name = "description", length = 65535)
	private String description;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "CompositeType_has_UnitType",
			joinColumns = @JoinColumn(name = "composite_type_id"),
			inverseJoinColumns = @JoinColumn(name = "unit_type_id"))
	@OrderBy(value = "name")
	private Set<UnitType> unitTypes;

	public CompositeType() {
	}

	public CompositeType(Category category, String name) {
		this.category = category;
		this.name = name;
	}

	public CompositeType(Category category, String name, String description) {
		this.category = category;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<UnitType> getUnitTypes() {
		return unitTypes;
	}

	public void setUnitTypes(Set<UnitType> unitTypes) {
		this.unitTypes = unitTypes;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}