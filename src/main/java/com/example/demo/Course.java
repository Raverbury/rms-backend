package com.example.demo;

public class Course {

	private int id;
	public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  private String name;
	
	public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Course(int id, String name) {
    super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return "Course [id: " + id + ", name: " + name + "]";
	}
}
