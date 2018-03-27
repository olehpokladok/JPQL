package entity;

import javax.persistence.*;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
}
