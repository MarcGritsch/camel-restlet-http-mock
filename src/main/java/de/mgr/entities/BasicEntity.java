package de.mgr.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ENITIES")
@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 1, sequenceName = "ENTITY_ID")
public class BasicEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    
    @Basic(optional = true)
    @Column(name = "KEY", unique = false, nullable = true)
    private String key;
    
    public BasicEntity() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
    
    
  
}
