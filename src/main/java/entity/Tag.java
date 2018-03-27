package entity;

import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "tag")
@Getter @Setter
@NoArgsConstructor
public class Tag extends BaseEntity {
	
	@Column(name = "tagname")
	private String tagname;
	
	@ManyToMany(mappedBy = "tags")
	private List<Post> posts = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Tag [tagname=" + tagname + ", getId()=" + getId() + "]";
	}
	
	
	
}
