package entity;

import java.util.*;

import javax.persistence.*;

import enums.Status;
import lombok.*;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post extends BaseEntity {

	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "content")
	private String content;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();
	
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToMany
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<>();

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", status=" + status + ", getId()=" + getId() + "]";
	}

}
