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
@Table(name = "Text")
public class Text extends AbstractEntity implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "location_id", nullable = false, length = 45)
	private String location = "";

	@Column(name = "text", columnDefinition = "TEXT")
	private String text = "";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id", nullable = false, referencedColumnName = "id")
	private Page page;

	public Text() {
	}

	public Text(String text) {
		this.text = text;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return location;
	}

	public void setName(String name) {
		this.location = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
