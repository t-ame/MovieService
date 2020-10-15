package com.starwars.services;

import java.util.List;

import com.google.gson.JsonObject;
import com.starwars.models.Character;
import com.starwars.utils.AppException;

public interface CharacterService {

	List<Character> getCharacters(int movieId)
			throws AppException;

	JsonObject getCharacters(int movieId, String filterGender, String sortField, String sortDirection)
			throws AppException;
}
