package org.cendra.geoobject.populate.download.geonames.continents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;

public class DownloadContinents extends Download {

	public static final String FILE_NAME = "continents";

	public static String FULL_PATH = "downloads/geonames/continents";

	public DownloadContinents(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File continentsFile = this.getFileFullPath(FULL_PATH, true);

		List<ContinentGeoname> continents = new ArrayList<ContinentGeoname>();

		ContinentGeoname continent = new ContinentGeoname();
		continent.setCode("AF");
		continent.setShortName("Africa");
		continent.setGeonameId(6255146L);
		continent.setWikipediaURL("https://en.wikipedia.org/wiki/Africa");
		continents.add(continent);

		continent = new ContinentGeoname();
		continent.setCode("AS");
		continent.setShortName("Asia");
		continent.setGeonameId(6255147L);
		continent.setWikipediaURL("https://en.wikipedia.org/wiki/Asia");
		continents.add(continent);

		continent = new ContinentGeoname();
		continent.setCode("EU");
		continent.setShortName("Europe");
		continent.setGeonameId(6255148L);
		continent.setWikipediaURL("https://en.wikipedia.org/wiki/Europe");
		continents.add(continent);

		continent = new ContinentGeoname();
		continent.setCode("NA");
		continent.setShortName("North America");
		continent.setGeonameId(6255149L);
		continent
				.setWikipediaURL("https://en.wikipedia.org/wiki/North_America");
		continents.add(continent);

		continent = new ContinentGeoname();
		continent.setCode("OC");
		continent.setShortName("Oceania");
		continent.setGeonameId(6255151L);
		continent.setWikipediaURL("https://en.wikipedia.org/wiki/Oceania");
		continents.add(continent);

		continent = new ContinentGeoname();
		continent.setCode("SA");
		continent.setShortName("South America");
		continent.setGeonameId(6255150L);
		continent
				.setWikipediaURL("https://en.wikipedia.org/wiki/South_America");
		continents.add(continent);

		continent = new ContinentGeoname();
		continent.setCode("AN");
		continent.setShortName("Antarctica");
		continent.setGeonameId(6255152L);
		continent.setWikipediaURL("https://en.wikipedia.org/wiki/Antarctica");
		continents.add(continent);

		File json = new File(continentsFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, continents);

		return json;
	}

}
