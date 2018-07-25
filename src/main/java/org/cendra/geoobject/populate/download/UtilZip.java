package org.cendra.geoobject.populate.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class UtilZip {
	
	/**
	 * Funciones: compresión varios archivos en un archivo zip
	 * 
	 * @param srcfile:
	 *            Archivo de código fuente de la lista
	 * @param zipfile:
	 *            Archivo
	 */
	public static void zipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			// ZipOutputStream: completar el archivo o carpeta comprimir
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("Comprimido.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public void unZipFiles(File zipfile, String pathFolderDest) throws ZipException, IOException {

		new File(pathFolderDest).mkdirs();

		ZipFile zf = new ZipFile(zipfile);
		for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zf.getInputStream(entry);
			OutputStream out = new FileOutputStream(pathFolderDest + File.separatorChar + zipEntryName);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}

		zf.close();

	}


}
