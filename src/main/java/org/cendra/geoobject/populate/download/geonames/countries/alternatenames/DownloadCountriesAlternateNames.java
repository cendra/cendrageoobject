package org.cendra.geoobject.populate.download.geonames.countries.alternatenames;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.countries.CountryFormGeoname;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountriesFormGeonames;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;

public class DownloadCountriesAlternateNames extends Download {

	public static final String FILE_NAME = "countries_alternatenames";

	public static String FULL_PATH = "downloads/geonames/countries/alternatenames";

	public DownloadCountriesAlternateNames(HTMLDocumentUtil htmlDocumentUtil,
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

		ObjectMapper mapper = new ObjectMapper();

		List<CountryFormGeoname> countries = mapper.readValue(
				DownloadCountriesFormGeonames.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryFormGeoname.class));

		List<CountryAlternateNamesGeoname> newCountries = new ArrayList<CountryAlternateNamesGeoname>();

		for (CountryFormGeoname country : countries) {
			CountryAlternateNamesGeoname countryAlternateNamesGeoname = parse(
					countriesFile, country.getIso3166Alpha2(),
					country.getOtherCountryNamesURL());
			newCountries.add(countryAlternateNamesGeoname);
		}

		// -------------------------------------------------------------

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newCountries);

		return json;
	}

	@SuppressWarnings("unchecked")
	public CountryAlternateNamesGeoname parse(File countriesFile,
			String iso3166Alpha2, String otherCountryNamesURL) throws Exception {

		String f = otherCountryNamesURL;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_geonames.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(
				otherCountryNamesURL,
				true,
				countriesFile.getAbsolutePath() + java.io.File.separatorChar
						+ iso3166Alpha2.toLowerCase() + "_" + f);

		Element table = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "altnametable");
		List<Element> rows = table.getChildren();

		CountryAlternateNamesGeoname countryAlternateNamesGeoname = new CountryAlternateNamesGeoname();
		countryAlternateNamesGeoname.setIso3166Alpha2(iso3166Alpha2);

		for (int i = 0; i < rows.size(); i++) {
			if (i > 0) {
				Element row = rows.get(i);

				String alternateName = ((Element) row.getChildren().get(0))
						.getValue();
				String languajeCode = ((Element) row.getChildren().get(2))
						.getValue();

				if (languajeCode.contains("-")) {
					languajeCode = languajeCode.split("-")[0];
				}

				AlternateName alternateNameObject = new AlternateName();
				alternateNameObject.setAlternateName(alternateName);
				alternateNameObject.setLanguageCode(languajeCode);

//				if (alternateName != null && alternateName.isEmpty() == false
//						&& alternateName.trim().startsWith("http") == false) {

					countryAlternateNamesGeoname
							.addAlternateName(alternateNameObject);
//				}

			}

		}

		return countryAlternateNamesGeoname;

	}

//	private Languaje getLanguaje(Country country, String code) throws Exception {
//
//		if ("macrolanguage".equalsIgnoreCase(code.trim())) {
//			Languaje languaje = new Languaje();
//			languaje.setMacrolanguage(true);
//			languaje.setIso639_3("@@@");
//			languaje.setIso639_2("@@");
//			languaje.setIso639_1("@");
//			return languaje;
//		}
//
//		if ("abbr".equalsIgnoreCase(code.trim())) {
//			return null;
//		}
//		if ("post".equalsIgnoreCase(code.trim())) {
//			return null;
//		}
//		if ("iata".equalsIgnoreCase(code.trim())) {
//			return null;
//		}
//
//		for (Languaje languaje : languajes) {
//
//			if (languaje.getIso639_3().equalsIgnoreCase(code.trim())) {
//				return languaje;
//			}
//			if (languaje.getIso639_2().equalsIgnoreCase(code.trim())) {
//				return languaje;
//			}
//			if (languaje.getIso639_1().equalsIgnoreCase(code.trim())) {
//				return languaje;
//			}
//		}
//
//		LogItem logItem = new LogItem();
//		logItem.setType(logItem.TYPE_WARNING);
//		logItem.setTitle("Agregando un lenguaje");
//		logItem.setSubject("Contry " + country.getIso2() + ", Languaje " + code
//				+ ".");
//		logItem.setMesg("No se encontro el lenguaje especificado en la lista.");
//
//		if (code.trim().equalsIgnoreCase("link") == false) {
//			logs.add(logItem);
//		}
//
//		System.err.println(logItem);
//		// throw new Exception("Lenguaje " + code + " no encontrado.");
//		return null;
//	}

}
