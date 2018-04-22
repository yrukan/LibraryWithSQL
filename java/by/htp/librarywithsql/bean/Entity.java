package by.htp.librarywithsql.bean;

public abstract class Entity {
	protected int id;

	public Entity() {

	}

	public Entity(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + "]";
	}

}
