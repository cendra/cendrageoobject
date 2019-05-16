package org.geo.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.geo.Proerties;
import org.geo.model.country.Country;

public class CountryDAO {

	public List<Country> find() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		List<Country> countries = new ArrayList<Country>();

		File folder = new File(Proerties.pathFolderCountries);

		File[] files = folder.listFiles();

		for (File fileJSON : files) {

			if (fileJSON.isDirectory() == false
					&& fileJSON.getName().endsWith(".json")) {

				Country country = mapper.readValue(fileJSON.getAbsoluteFile(),
						Country.class);
				countries.add(country);

			}
		}

		return countries;
	}

}
