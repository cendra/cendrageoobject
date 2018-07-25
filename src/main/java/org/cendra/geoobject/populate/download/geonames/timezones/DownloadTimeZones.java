package org.cendra.geoobject.populate.download.geonames.timezones;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.codehaus.jackson.map.ObjectMapper;

import au.com.bytecode.opencsv.CSVReader;

public class DownloadTimeZones extends Download {

	public static final String FILE_NAME = "timeZones.txt";
	public static String FULL_PATH = "downloads/geonames/timezones";
	private String link = "http://download.geonames.org/export/dump/timeZones.txt";

	public DownloadTimeZones(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File timeZonesFile = this.getFileFullPath(FULL_PATH, true);

		String path = timeZonesFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME;

		this.getHtmlDocumentUtil().download(link, path);

		List<TimeZoneGeoname> timezones = new ArrayList<TimeZoneGeoname>();

		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(path), '\t');

		String gmtOffset = null; // 2
		String dstOffset = null; // 3

		int c = 0;

		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {

			if (c == 0) {
				gmtOffset = nextLine[2].trim().replace("GMT offset", "").trim();
				dstOffset = nextLine[3].trim().replace("DST offset", "").trim();
			}

			if (c > 0) {
				String countryCode = nextLine[0].trim(); // 0
				String timeZoneId = nextLine[1].trim(); // 1
				String rawOffsetIndependantOfDST = nextLine[4].trim(); // 4

				TimeZoneGeoname timeZone = new TimeZoneGeoname();
				timeZone.setCountryCode(countryCode);
				timeZone.setTimeZoneId(timeZoneId);
				timeZone.setDstOffset(dstOffset);
				timeZone.setGmtOffset(gmtOffset);
				timeZone.setRawOffsetIndependantOfDST(rawOffsetIndependantOfDST);

				timezones.add(timeZone);
			}

			c++;

		}

		File json = new File(timeZonesFile.getAbsolutePath()
				+ File.separatorChar + FILE_NAME + ".json");

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(json, timezones);

		return json;
	}

}
