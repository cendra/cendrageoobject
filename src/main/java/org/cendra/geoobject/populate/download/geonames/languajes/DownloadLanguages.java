package org.cendra.geoobject.populate.download.geonames.languajes;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;

import au.com.bytecode.opencsv.CSVReader;

public class DownloadLanguages extends Download {

	public static final String FILE_NAME = "iso-languagecodes.txt";
	public static String FULL_PATH = "downloads/geonames/languajes";

	private String geonameISOLanguageCodesLink = "http://download.geonames.org/export/dump/iso-languagecodes.txt";

	public DownloadLanguages(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File languagesFile = this.getFileFullPath(FULL_PATH, true);

		String path = languagesFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME;

		this.getHtmlDocumentUtil().download(geonameISOLanguageCodesLink, path);

		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(path), '\t');

		List<LanguageGeoname> languages = new ArrayList<LanguageGeoname>();

		int c = 0;

		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {

			if (nextLine[0].startsWith("#") == false && c > 0) {

				String ISO_639_3 = nextLine[0];
				String ISO_639_2 = nextLine[1];
				String ISO_639_1 = nextLine[2];
				String languageName = nextLine[3];

				LanguageGeoname languaje = new LanguageGeoname();
				languaje.setIso639_3(ISO_639_3);
				languaje.setIso639_2(ISO_639_2);
				languaje.setIso639_1(ISO_639_1);
				languaje.setLanguageName(languageName);

				languages.add(languaje);
			}

			c++;
		}

		File json = new File(languagesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, languages);

		return json;
	}

}
