package org.cendra.geoobject.populate.download.geonames.countries;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;

import au.com.bytecode.opencsv.CSVReader;

public class DownloadCountriesInfo extends Download {

	public static final String FILE_NAME = "countryInfo.txt";
	public static String FULL_PATH = "downloads/geonames/countries";

	private String urlString = "http://download.geonames.org/export/dump/countryInfo.txt";

	public DownloadCountriesInfo(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File countriesFile = this.getFileFullPath(FULL_PATH, true);

		String path = countriesFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME;

		List<CountryInfoGeoname> countries = new ArrayList<CountryInfoGeoname>();

		// -------------------------------------------------------------

		this.getHtmlDocumentUtil().download(urlString, path);

		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(path), '\t');

		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {

			if (nextLine[0].startsWith("#") == false) {
				CountryInfoGeoname countryInfo = new CountryInfoGeoname();
				countryInfo.setIso(nextLine[0]);
				countryInfo.setIso3(nextLine[1]);
				countryInfo.setIsoNumeric(nextLine[2]);
				countryInfo.setFips(nextLine[3]);
				countryInfo.setCountry(nextLine[4]);
				countryInfo.setCapital(nextLine[5]);
				countryInfo.setArea(Double.valueOf(nextLine[6]));
				countryInfo.setPopulation(Long.valueOf(nextLine[7]));
				countryInfo.setContinent(nextLine[8]);
				countryInfo.setTld(nextLine[9]);
				countryInfo.setCurrencyCode(nextLine[10]);
				countryInfo.setCurrencyName(nextLine[11]);
				countryInfo.setPhone(nextLine[12]);
				countryInfo.setPostalCodeFormat(nextLine[13]);
				countryInfo.setPostalCodeRegex(nextLine[14]);

				String[] languages = nextLine[15].trim().split(",");
				for (String language : languages) {
					if (language != null && language.trim().length() != 0) {
						countryInfo.addLanguage(language);
					}
				}

				countryInfo.setGeonameId(Long.valueOf(nextLine[16]));

				String[] neighbours = nextLine[17].trim().split(",");
				for (String neighbour : neighbours) {
					if (neighbour != null && neighbour.trim().length() != 0) {
						countryInfo.addNeighbour(neighbour);
					}
				}

				countryInfo.setEquivalentFipsCode(nextLine[18]);

				countries.add(countryInfo);

			}

		}

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, countries);

		return json;
	}

}
