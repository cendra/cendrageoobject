package org.cendra.geoobject.populate.download.wikipedia.continents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.continents.ContinentGeoname;
import org.cendra.geoobject.populate.download.geonames.continents.DownloadContinents;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;

public class DownloadWikiContinents extends Download {

	public static final String FILE_NAME = "continents";

	public static String FULL_PATH = "downloads/wikipedia/continents";

	private String urlStsartString = "https://en.wikipedia.org";

	public DownloadWikiContinents(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH));
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

		List<ContinentWikipedia> newContinents = new ArrayList<ContinentWikipedia>();

		for (ContinentGeoname continent : continents) {
			ContinentWikipedia continentWikipedia = parse(newContinentsFile,
					continent.getCode(), continent.getWikipediaURL());
			newContinents.add(continentWikipedia);
		}

		// -------------------------------------------------------------

		File json = new File(newContinentsFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		mapper.writeValue(json, newContinents);

		return json;
	}

	public ContinentWikipedia parse(File newContinentsFile, String code,
			String urlString) throws Exception {

		String f = urlString;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_wikipedia.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(
				urlString,
				true,
				newContinentsFile.getAbsolutePath()
						+ java.io.File.separatorChar + code.toLowerCase() + "_"
						+ f);

		String shortName = getShortNanme(doc);

		ContinentWikipedia continent = new ContinentWikipedia();
		continent.setCode(code);
		continent.setShortName(shortName);
		continent.setOrthographicProjectionURL(getOrthographicProjectionURL(
				continent, doc, newContinentsFile, urlString));

		String ext = continent.getOrthographicProjectionURL().split("/")[continent
				.getOrthographicProjectionURL().split("/").length - 1]
				.split("[.]")[1];

		String fileName = continent.getCode().toLowerCase()
				+ "_orthographic_projection_" + ext + "." + ext;

		this.getHtmlDocumentUtil().download(
				continent.getOrthographicProjectionURL(),
				newContinentsFile.getAbsolutePath() + File.separatorChar
						+ fileName);

		return continent;

	}

	private String getShortNanme(Document doc) {

		return this.getHtmlDocumentUtil()
				.getElementById(doc.getRootElement(), "firstHeading")
				.getValue();
	}

	private String getOrthographicProjectionURL(ContinentWikipedia continent,
			Document doc, File newContinentsFile, String urlString)
			throws Exception {

		Element e = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "mw-content-text");

		if (this.getHtmlDocumentUtil().isContainsElementByClassElementName(e,
				"infobox geography vcard", "table")) {
			e = this.getHtmlDocumentUtil().getFirstElementByClassElementName(e,
					"infobox geography vcard", "table");
		} else if (this.getHtmlDocumentUtil()
				.isContainsElementByClassElementName(e, "infobox", "table")) {
			e = this.getHtmlDocumentUtil().getFirstElementByClassElementName(e,
					"infobox", "table");
		} else if (this.getHtmlDocumentUtil()
				.isContainsElementByClassElementName(e, "infobox", "table")) {
			e = this.getHtmlDocumentUtil().getFirstElementByClassElementName(e,
					"infobox vcard", "table");
		}

		List<Element> elements = this.getHtmlDocumentUtil()
				.getElementsByClassElementName(e, "image", "a");

		for (Element item : elements) {

			// String title = item.getAttributeValue("title");

			String f = item.getAttributeValue("href");
			String[] ff = f.split("[/]");
			f = ff[ff.length - 1].toLowerCase().replace(":", "_");

			if (f.endsWith(".html")) {
				f = f.replaceAll(".html", "");
			}

			f += "_wikipedia.html";

			doc = this.getHtmlDocumentUtil().getDocument(
					urlStsartString + item.getAttributeValue("href"),
					true,
					newContinentsFile.getAbsolutePath()
							+ java.io.File.separatorChar
							+ continent.getCode().toLowerCase() + "_" + f);

			e = this.getHtmlDocumentUtil().getElementById(doc.getRootElement(),
					"file");

			e = this.getHtmlDocumentUtil().getFirstElementByElementName(e, "a");

			String url = urlString.split(":")[0] + ":"
					+ e.getAttributeValue("href");

			e = this.getHtmlDocumentUtil().getFirstElementByElementName(e,
					"img");
			String fileName = e.getAttributeValue("alt");

			if (fileName.contains("orthographic")) {
				return url;
			}

			return url;

		}

		return null;
	}

}
