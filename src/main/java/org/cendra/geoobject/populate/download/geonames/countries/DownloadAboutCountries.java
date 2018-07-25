package org.cendra.geoobject.populate.download.geonames.countries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.GeonameAbout;
import org.cendra.geoobject.populate.download.geonames.countries.alternatenames.NameAbout;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DownloadAboutCountries extends Download {

	public static final String FILE_NAME = "countries_about";
	public static String FULL_PATH = "downloads/geonames/countries";

	public DownloadAboutCountries(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File newCountriesFile = this.getFileFullPath(FULL_PATH, true);

		ObjectMapper mapper = new ObjectMapper();

		List<CountryInfoGeoname> countries = mapper.readValue(
				DownloadCountriesInfo.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryInfoGeoname.class));

		List<GeonameAbout> newContinents = new ArrayList<GeonameAbout>();

		for (CountryInfoGeoname country : countries) {

			String path = newCountriesFile.getAbsolutePath()
					+ File.separatorChar + country.getIso().toLowerCase() + "_"
					+ country.getGeonameId() + "_about.rdf.xml";
			String link = "http://sws.geonames.org/" + country.getGeonameId()
					+ "/about.rdf";

			this.getHtmlDocumentUtil().download(link, path);

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(path);

			GeonameAbout countryGeonameAbout = new GeonameAbout();
			countryGeonameAbout.setCode(country.getIso());

			try {

				Document document = (Document) builder.build(xmlFile);

				Element rootNode = document.getRootElement();

				@SuppressWarnings("rawtypes")
				List list = ((Element) rootNode.getChildren().get(0))
						.getChildren();

				for (int i = 0; i < list.size(); i++) {
					Element element = (Element) list.get(i);
					if (element.getName().equals(NameAbout.NAME)) {
						NameAbout name = new NameAbout();
						name.setName(element.getValue());
						name.setLanguageCode(null);
						name.setType(NameAbout.NAME);
						countryGeonameAbout.addName(name);
					} else if (element.getName().equals(
							NameAbout.ALTERNATE_NAME)) {
						NameAbout name = new NameAbout();
						name.setName(element.getValue());

						if (element.getAttributes().size() > 0) {
							name.setLanguageCode(((org.jdom.Attribute) element
									.getAttributes().get(0)).getValue());
						}

						name.setType(NameAbout.ALTERNATE_NAME);
						countryGeonameAbout.addName(name);
					} else if (element.getName()
							.equals(NameAbout.OFFICIAL_NAME)) {
						NameAbout name = new NameAbout();
						name.setName(element.getValue());
						if (element.getAttributes().size() > 0) {
							name.setLanguageCode(((org.jdom.Attribute) element
									.getAttributes().get(0)).getValue());
						}
						name.setType(NameAbout.OFFICIAL_NAME);
						countryGeonameAbout.addName(name);
					} else if (element.getName().equals(NameAbout.SHORT_NAME)) {
						NameAbout name = new NameAbout();
						name.setName(element.getValue());
						if (element.getAttributes().size() > 0) {
							name.setLanguageCode(((org.jdom.Attribute) element
									.getAttributes().get(0)).getValue());
						}
						name.setType(NameAbout.SHORT_NAME);
						countryGeonameAbout.addName(name);
					} else if (element.getName().equals("lat")) {
						countryGeonameAbout.setLat(Double.valueOf(element
								.getValue().toString()));
					} else if (element.getName().equals("long")) {
						countryGeonameAbout.setLng(Double.valueOf(element
								.getValue().toString()));
					} else if (element.getName().equals("seeAlso")) {
						countryGeonameAbout
								.setDbpediaURL(((org.jdom.Attribute) element
										.getAttributes().get(0)).getValue());
					} else if (element.getName().toLowerCase().endsWith("name")
							&& element.getName().equals(NameAbout.NAME) == false
							&& element.getName().equals(NameAbout.ALTERNATE_NAME) == false
							&& element.getName().equals(NameAbout.OFFICIAL_NAME) == false
							&& element.getName().equals(NameAbout.SHORT_NAME) == false) {
						System.out
								.println(" ......................................  " + element.getValue());
						throw new RuntimeException(element.getValue());
					}

				}

			} catch (IOException io) {
				io.printStackTrace();
			} catch (JDOMException jdomex) {
				jdomex.printStackTrace();
			}

			newContinents.add(countryGeonameAbout);

		}

		// -------------------------------------------------------------

		File json = new File(newCountriesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newContinents);

		return newCountriesFile;
	}
}
