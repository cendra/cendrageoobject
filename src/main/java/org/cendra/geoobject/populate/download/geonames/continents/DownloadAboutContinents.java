package org.cendra.geoobject.populate.download.geonames.continents;

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

public class DownloadAboutContinents extends Download {

	public static final String FILE_NAME = "continents_about";
	public static String FULL_PATH = "downloads/geonames/continents";

	public DownloadAboutContinents(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File newContinentsFile = this.getFileFullPath(FULL_PATH, true);

		ObjectMapper mapper = new ObjectMapper();

		List<ContinentGeoname> continents = mapper.readValue(DownloadContinents
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome()), mapper.getTypeFactory()
				.constructCollectionType(List.class, ContinentGeoname.class));

		List<GeonameAbout> newContinents = new ArrayList<GeonameAbout>();

		for (ContinentGeoname continent : continents) {

			String path = newContinentsFile.getAbsolutePath()
					+ File.separatorChar + continent.getCode().toLowerCase() + "_"
					+ continent.getGeonameId() + "_about.rdf.xml";
			String link = "http://sws.geonames.org/" + continent.getGeonameId()
					+ "/about.rdf";

			this.getHtmlDocumentUtil().download(link, path);

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(path);

			GeonameAbout continentGeonameAbout = new GeonameAbout();
			continentGeonameAbout.setCode(continent.getCode());

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
						continentGeonameAbout.addName(name);
					} else if (element.getName().equals(
							NameAbout.ALTERNATE_NAME)) {
						NameAbout name = new NameAbout();
						name.setName(element.getValue());
						if (element.getAttributes().size() > 0) {
							name.setLanguageCode(((org.jdom.Attribute) element
									.getAttributes().get(0)).getValue());
						}
						continentGeonameAbout.addName(name);
					} else if (element.getName()
							.equals(NameAbout.OFFICIAL_NAME)) {
						NameAbout name = new NameAbout();
						name.setName(element.getValue());
						if (element.getAttributes().size() > 0) {
							name.setLanguageCode(((org.jdom.Attribute) element
									.getAttributes().get(0)).getValue());
						}
						name.setType(NameAbout.OFFICIAL_NAME);
						continentGeonameAbout.addName(name);
					} else if (element.getName().equals(NameAbout.SHORT_NAME)) {
						NameAbout name = new NameAbout();
						name.setName(element.getValue());
						if (element.getAttributes().size() > 0) {
							name.setLanguageCode(((org.jdom.Attribute) element
									.getAttributes().get(0)).getValue());
						}
						name.setType(NameAbout.SHORT_NAME);
						continentGeonameAbout.addName(name);
					} else if (element.getName().equals("lat")) {
						continentGeonameAbout.setLat(Double.valueOf(element
								.getValue().toString()));
					} else if (element.getName().equals("long")) {
						continentGeonameAbout.setLng(Double.valueOf(element
								.getValue().toString()));
					} else if (element.getName().equals("seeAlso")) {
						continentGeonameAbout
								.setDbpediaURL(((org.jdom.Attribute) element
										.getAttributes().get(0)).getValue());
					} else if (element.getName().toLowerCase().endsWith("name")
							&& element.getName().equals(NameAbout.NAME) == false
							&& element.getName().equals(
									NameAbout.ALTERNATE_NAME) == false
							&& element.getName()
									.equals(NameAbout.OFFICIAL_NAME) == false
							&& element.getName().equals(NameAbout.SHORT_NAME) == false) {
						System.out
								.println(" ......................................  "
										+ element.getValue());
						throw new RuntimeException(element.getValue());
					}

				}

			} catch (IOException io) {
				io.printStackTrace();
			} catch (JDOMException jdomex) {
				jdomex.printStackTrace();
			}

			newContinents.add(continentGeonameAbout);

		}

		// -------------------------------------------------------------

		File json = new File(newContinentsFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newContinents);

		return newContinentsFile;
	}

}
