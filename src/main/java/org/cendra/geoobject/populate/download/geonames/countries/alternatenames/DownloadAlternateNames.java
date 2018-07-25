package org.cendra.geoobject.populate.download.geonames.countries.alternatenames;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.UtilZip;
import org.codehaus.jackson.map.ObjectMapper;

public class DownloadAlternateNames extends Download {

	public static final String FILE_NAME = "alternateNames.zip";
	public static final String FILE_NAME_2 = "alternateNames.txt";
	public static String FULL_PATH = "downloads/geonames/alternatenames";

	private String link = "http://download.geonames.org/export/dump/alternateNames.zip";

	public DownloadAlternateNames(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public static File getFullPathFileDownload(String pathHome) {
		return new File(pathHome + File.separatorChar
				+ replaceSeparatorChar(FULL_PATH) + File.separatorChar
				+ FILE_NAME + ".json");
	}

	public File download() throws Exception {

		File alternateNamesFile = this.getFileFullPath(FULL_PATH, true);

		String path = alternateNamesFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME;

		// this.getHtmlDocumentUtil().download(link,
		// path);

		// ----------------------------------------------------------------

		UtilZip utilZip = new UtilZip();

		utilZip.unZipFiles(new File(path), alternateNamesFile.getAbsolutePath());

		path = alternateNamesFile.getAbsolutePath() + File.separatorChar
				+ FILE_NAME_2;

		// ----------------------------------------------------------------

		// ///////////////////////////////////////////////////////////////////////

		@SuppressWarnings("unused")
		int c = 0;

		FileReader fileReader = null;

		try {
			fileReader = new FileReader(path);
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while ((line = bufferedReader.readLine()) != null) {

				String alternateNameId = null;
				String geonameId = null;
				String isolanguage = null;
				String alternateName = null;
				String isPreferredName = null;
				String isShortName = null;
				String isColloquial = null;
				String isHistoric = null;

				String[] values = line.split("\t");

				alternateNameId = values[0].trim();
				geonameId = values[1].trim();

				if (values.length >= 3) {

					isolanguage = values[2].trim();

					if (values.length >= 4) {
						alternateName = values[3].trim();

						if (values.length >= 5) {
							isPreferredName = values[4].trim();

							if (values.length >= 6) {
								isShortName = values[5].trim();

								if (values.length >= 7) {
									isColloquial = values[6].trim();

									if (values.length >= 8) {
										isHistoric = values[7].trim();
									}
								}
							}
						}
					}
				}

				if (isolanguage != null && isolanguage.isEmpty() == false) {
					if (isolanguage.contains("-")) {
						isolanguage = isolanguage.split("-")[0];
					}
				}

				AlternateNameFull alternateNameFull = new AlternateNameFull();

				alternateNameFull.setAlternateNameId(alternateNameId);
				alternateNameFull.setGeonameId(Long.valueOf(geonameId));
				alternateNameFull.setAlternateName(alternateName);
				alternateNameFull.setLanguageCode(isolanguage);
				alternateNameFull.setIsPreferredName(("1"
						.equalsIgnoreCase(isPreferredName)));
				alternateNameFull.setIsShortName(("1"
						.equalsIgnoreCase(isShortName)));
				alternateNameFull.setIsColloquial(("1"
						.equalsIgnoreCase(isColloquial)));
				alternateNameFull.setIsHistoric(("1"
						.equalsIgnoreCase(isHistoric)));
				

				String alternateNamePath = FULL_PATH + File.separatorChar + geonameId;
				
				File alternateNameFile = this.getFileFullPath(alternateNamePath, true);
				
				File json = new File(alternateNameFile.getAbsolutePath()
						+ File.separatorChar + alternateNameId + ".json");

				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(json, alternateNameFull);

				c++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Excepcion leyendo " + path + ": " + e);
		} finally {
			if (fileReader != null) {
				fileReader.close();
			}

		}
		// /////////////////////////////////////////////////////////////////////////

		

		return alternateNamesFile;
	}

}
