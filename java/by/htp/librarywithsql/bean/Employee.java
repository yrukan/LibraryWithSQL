package by.htp.librarywithsql.bean;

import java.util.Date;

public class Employee extends Entity{
	private String name;
	private String email;
	private Date dob;

	public Employee() {
		super();
	}	

	public Employee(String name, String email, Date dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", email=" + email + ", dob=" + dob + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	

}
