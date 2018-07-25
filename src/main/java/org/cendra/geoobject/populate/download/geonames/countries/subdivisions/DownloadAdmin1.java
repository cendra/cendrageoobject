package org.cendra.geoobject.populate.download.geonames.countries.subdivisions;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;

import au.com.bytecode.opencsv.CSVReader;

public class DownloadAdmin1 extends Download {

	public static final String FILE_NAME = "admin1CodesASCII.txt";
//	public static String FULL_PATH = "downloads/geonames/countries/subdivisions";
	public static String FULL_PATH = "downloads/geonames/subdivisions";

	private String link = "http://download.geonames.org/export/dump/admin1CodesASCII.txt";

	public DownloadAdmin1(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File admin1File = this.getFileFullPath(FULL_PATH, true);

		String path = admin1File.getAbsolutePath() + File.separatorChar
				+ FILE_NAME;

		this.getHtmlDocumentUtil().download(link, path);

		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(path), '\t');

		List<Admin1> subdivisions = new ArrayList<Admin1>();

		@SuppressWarnings("unused")
		int c = 0;

		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {

			if (nextLine[0].startsWith("#") == false) {
				Admin1 admin1 = new Admin1();
				
				String iso3166Alpha2Fips = nextLine[0];				
				admin1.setIso3166Alpha2Fips(iso3166Alpha2Fips);				
				
				String fips = iso3166Alpha2Fips.split("[.]")[1];
				admin1.setFips(fips);
				
				String iso3166Alpha2 = iso3166Alpha2Fips.split("[.]")[0];
				admin1.setIso3166Alpha2(iso3166Alpha2);
				
				String name = nextLine[1];
				admin1.setName(name.trim());
				
				String nameB = nextLine[2];
				admin1.setNameB(nameB.trim());
				
				String geonameId = nextLine[3];
				admin1.setGeonameId(Long.valueOf(geonameId.trim()));
				
				subdivisions.add(admin1);
				
			}

			c++;
		}

		File json = new File(admin1File.getAbsolutePath() + File.separatorChar
				+ FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, subdivisions);

		return json;
	}

}
