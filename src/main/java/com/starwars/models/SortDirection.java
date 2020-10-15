package com.starwars.models;

public enum SortDirection {

	ASC("asc"), DESC("desc");

	String name;

	SortDirection() {
		name = "asc";
	}

	SortDirection(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isDirection(String name) {
		return name != null && this.name.equals(name);
	}

	public static SortDirection getDirection(String name) {
		if (name == null || name.trim().equals(""))
			return ASC;
		name = name.toLowerCase();
		switch (name) {
		case "desc":
			return DESC;
		default:
			return ASC;
		}
	}

}
