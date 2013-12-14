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

@Entity
@Table(name = "Page")
public class Page implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;

	@Column(name = "name", length = 45)
	protected String name;

	@Column(name = "title", length = 45)
	protected String title;

	@Column(name = "description", length = 512)
	protected String description;

	@Column(name = "keywords", length = 512)
	protected String keywords;

	@Column(name = "link_name", length = 45)
	protected String linkName;

	@Column(name = "view", length = 45)
	protected String view;

	@Column(name = "uri", unique = true, nullable = false, length = 45)
	protected String uri;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
	private Category category;

	public Page() {

	}

	public Page(String name, String title, String description, String keywords, String linkName, String view) {
		this.name = name;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.linkName = linkName;
		this.view = view;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
