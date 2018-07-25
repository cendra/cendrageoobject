package org.cendra.geoobject.populate;

import java.io.File;

public class PopulateProperties {

	private String pathHome = "D:/dev/salidas_pruebas/geoobject_data";

	public String getPathHome() {
		pathHome = replaceSeparatorChar(pathHome);
		return pathHome;
	}

	public void setPathHome(String pathHome) {
		pathHome = replaceSeparatorChar(pathHome);
		this.pathHome = pathHome;
	}
	
	public String getPathHomeDownloads() {		
		return getPathHome() + File.separatorChar + "downloads";
	}

	private String replaceSeparatorChar(String path) {
		return path.replace("/", File.separatorChar + "");
	}
	
	
	
	/////////////////////////////////////////////////////////

	public File getPathHomeFile() {
		return new File(getPathHome());
	}

	public File getPathDownloadsFile() {
		return new File(getPathHome() + File.separatorChar + "downloads");
	}

	public File getPathDownloadsGeonamesFile() {
		return new File(getPathDownloadsFile().getAbsolutePath()
				+ File.separatorChar + "geonames");
	}
	
	public File getPathDownloadsWikipediaFile() {
		return new File(getPathDownloadsFile().getAbsolutePath()
				+ File.separatorChar + "wikipedia");
	}

//	public File getPathJSONDBFile() {
//		return new File(getPathHome() + File.separatorChar + "json_db");
//	}

}
