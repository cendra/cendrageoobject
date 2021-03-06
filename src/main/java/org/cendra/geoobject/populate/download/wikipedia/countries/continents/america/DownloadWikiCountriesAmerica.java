package org.cendra.geoobject.populate.download.wikipedia.countries.continents.america;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.jdom.Document;
import org.jdom.Element;

public class DownloadWikiCountriesAmerica extends Download {

	public static final String FILE_NAME = "division_politica_america.html";
	public static String FULL_PATH = "downloads/wikipedia/countries/continents/america";
	private String urlString = "https://es.wikipedia.org/wiki/Anexo:Divisi%C3%B3n_pol%C3%ADtica_de_Am%C3%A9rica";

	public DownloadWikiCountriesAmerica(HTMLDocumentUtil htmlDocumentUtil,
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

		File americaFile = this.getFileFullPath(FULL_PATH, true);

		String path = americaFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME;

		// -------------------------------------------------------------

		List<CountryWikipediaAmerica> countries = parse(path, americaFile);

		// -------------------------------------------------------------

		File json = new File(americaFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, countries);

		return json;
	}

	@SuppressWarnings("unchecked")
	public List<CountryWikipediaAmerica> parse(String path,
			java.io.File americaFile) throws Exception {

		List<CountryWikipediaAmerica> countries = new ArrayList<CountryWikipediaAmerica>();

		String f = urlString;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_wikipedia.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(urlString, true,
				path);

		Element e = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "mw-content-text");

		e = (Element) e.getChildren().get(0);

		List<Element> elements = e.getChildren();

		List<Element> tables = new ArrayList<Element>();

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getName().equalsIgnoreCase("table")) {
				tables.add(elements.get(i));
			}
		}

		e = tables.get(0);

		elements = e.getChildren();

		for (int i = 0; i < elements.size(); i++) {
			Element row = elements.get(i);

			if (i > 0) {
				CountryWikipediaAmerica country = getCountry(row, americaFile);
				countries.add(country);
			}

		}

		return countries;

	}

	private CountryWikipediaAmerica getCountry(Element row,
			java.io.File americaFile) throws Exception {

		@SuppressWarnings("unchecked")
		List<Element> columns = row.getChildren();

		Element column = columns.get(2);
		String iso3 = this.getHtmlDocumentUtil()
				.getFirstElementByElementName(column, "b").getValue();

		column = columns.get(8);

		Element a = this.getHtmlDocumentUtil().getFirstElementByElementName(
				column, "a");

		// https://commons.wikimedia.org/wiki/File:Antigua_and_Barbuda_in_its_region.svg

		// String url = "https:" + a.getAttributeValue("href");
		String url = "https://commons.wikimedia.org"
				+ a.getAttributeValue("href").replaceAll("Archivo", "File");

		// -------------------------------------------------------

		java.io.File countryFile = new java.io.File(
				americaFile.getAbsolutePath() + java.io.File.separatorChar
						+ iso3.toLowerCase());
		countryFile.mkdirs();

		String f = url;
		String[] ff = f.split("[/]");
		f = ff[ff.length - 1].toLowerCase().replace(":", "_");

		if (f.endsWith(".html")) {
			f = f.replaceAll(".html", "");
		}

		f += "_wikipedia.html";

		Document doc = this.getHtmlDocumentUtil().getDocument(
				url,
				true,
				countryFile.getAbsolutePath() + java.io.File.separatorChar
						+ iso3.toLowerCase() + "_" + f);

		Element e = this.getHtmlDocumentUtil().getElementById(
				doc.getRootElement(), "file");

		e = this.getHtmlDocumentUtil().getFirstElementByElementName(e, "a");

		url = null;

		if (e.getAttributeValue("href").startsWith(urlString.split(":")[0])) {
			url = e.getAttributeValue("href");
		} else {
			url = urlString.split(":")[0] + ":" + e.getAttributeValue("href");
		}

		e = this.getHtmlDocumentUtil().getFirstElementByElementName(e, "img");
		// String fileName = e.getAttributeValue("alt");

		// -------------------------------------------------------

		CountryWikipediaAmerica country = new CountryWikipediaAmerica();

		country.setIso3166Alpha3(iso3);
		country.setLocationURL(url);

		String ext = country.getLocationURL().split("/")[country
				.getLocationURL().split("/").length - 1].split("[.]")[1];

		String fileName = country.getIso3166Alpha3().toLowerCase()
				+ "_location_" + ext + "." + ext;

		country.setLocationPath(countryFile.getAbsolutePath()
				+ File.separatorChar + fileName);

		this.getHtmlDocumentUtil().download(country.getLocationURL(),
				country.getLocationPath());


		// this.getHtmlDocumentUtil().download(
		// country.getLocationURL(),
		// countryFile.getAbsolutePath() + java.io.File.separatorChar
		// + country.getIso3166Alpha3().toLowerCase()
		// + "_location_"
		// + url.split("/")[url.split("/").length - 1]);
		country.setRecognisedState(true);

		return country;

	}

}
