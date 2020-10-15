package com.starwars.models;

public enum SortField {

	NAME("name"), GENDER("gender"), HEIGHT("height");

	String name;

	SortField() {
		name = "name";
	}

	SortField(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isDirection(String name) {
		return name != null && this.name.equals(name);
	}

	public static SortField getField(String name) {
		if (name == null || name.trim().equals(""))
			return NAME;
		name = name.toLowerCase();
		switch (name) {
		case "gender":
			return GENDER;
		case "height":
			return HEIGHT;
		default:
			return NAME;
		}
	}

}
