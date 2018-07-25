package org.cendra.geoobject.populate.download;

import java.io.File;

import org.cendra.geoobject.populate.PopulateProperties;

public class Download {

	private HTMLDocumentUtil htmlDocumentUtil;
	private PopulateProperties populateProperties;

	public Download(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super();
		this.htmlDocumentUtil = htmlDocumentUtil;
		this.populateProperties = populateProperties;
	}

	public HTMLDocumentUtil getHtmlDocumentUtil() {
		return htmlDocumentUtil;
	}

	public PopulateProperties getPopulateProperties() {
		return populateProperties;
	}

	public File getFileFullPath(String path, boolean mkdirs) {
		
		String pathHome = populateProperties.getPathHome();

		path = path.replace("/", File.separatorChar + "");

		File file = new File(pathHome + File.separatorChar + path);
		if (mkdirs) {
			file.mkdirs();
		}

		return file;

	}

	public static String replaceSeparatorChar(String path) {
		return path.replace("/", File.separatorChar + "");
	}

}
