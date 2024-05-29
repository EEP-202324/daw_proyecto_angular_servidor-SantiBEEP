package com.ejemplo.universidad;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Universidad{
	
	@Id
	@GeneratedValue
	 private Long id;
	 
	 private String name;
	 
	 private String ciudad;
	 
	 private String image;

	public Universidad() {
		super();
	}

	public Universidad(Long id, String name, String ciudad, String image) {
		super();
		this.id = id;
		this.name = name;
		this.ciudad = ciudad;
		this.image = image;
	}

	public Long getId() {
		return id;
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

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Universidad [id=" + id + ", name=" + name + ", ciudad=" + ciudad + ", image=" + image + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, id, image, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Universidad other = (Universidad) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(id, other.id)
				&& Objects.equals(image, other.image) && Objects.equals(name, other.name);
	}
	 
	
}
