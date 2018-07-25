package org.cendra.geoobject.populate.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class HTMLDocumentUtil {

	public Document getDocument(String urlString, boolean downloadAllHtml,
			String toPathFile) throws Exception {

		if (toPathFile != null && toPathFile.isEmpty() == false) {
			if (downloadAllHtml) {
				download(urlString, toPathFile);
			} else if (new File(toPathFile).exists() == false) {
				download(urlString, toPathFile);
			}
		}

		// -------------------------------------------------------------------

		URL url = null;
		URLConnection connection = null;
		InputStream isInHtml = null;
		BufferedInputStream buffer = null;

		try {

			System.out.println("\n[..] Leyendo página web \"" + urlString
					+ "\". ");

			if (toPathFile != null && toPathFile.isEmpty() == false) {

				if (new File(toPathFile).exists() == false) {

					url = new URL(urlString);
					connection = url.openConnection();
					isInHtml = connection.getInputStream();
				} else {
					isInHtml = new FileInputStream(new File(toPathFile));
				}

			} else {
				url = new URL(urlString);
				connection = url.openConnection();
				isInHtml = connection.getInputStream();
			}

			buffer = new BufferedInputStream(isInHtml);

			SAXBuilder saxBuilder = new SAXBuilder(
					"org.ccil.cowan.tagsoup.Parser", false);
			Document jdomDocument = saxBuilder.build(buffer);

			System.out.println("\n[OK] Página web \"" + urlString
					+ "\" leída.\n");

			return jdomDocument;

		} catch (Exception e) {
			throw e;
		} finally {

			if (buffer != null) {
				isInHtml.close();
			}
		}
	}

	public Element getElementById(Element element, String id) {

		id = id.trim();

		// if (element.getAttribute("id") != null &&
		// element.getAttribute("id").getValue().equalsIgnoreCase(id)) {
		// return element;
		// }

		@SuppressWarnings("unchecked")
		List<Element> elements = element.getChildren();

		for (Element e : elements) {

			// System.out.println(e + " " + e.getChildren());

			if (e.getAttribute("id") != null
					&& e.getAttribute("id").getValue().equalsIgnoreCase(id)) {
				return e;
			}

			Element er = getElementById(e, id);
			if (er != null) {
				return er;
			}
		}

		return null;
	}

	public Element getFirstElementByClassElementName(Element element,
			String clazz, String elementName) {

		clazz = clazz.trim();
		elementName = elementName.trim();

		@SuppressWarnings("unchecked")
		List<Element> elements = element.getChildren();

		for (Element e : elements) {

			if (e.getName().equalsIgnoreCase(elementName)
					&& e.getAttribute("class") != null
					&& e.getAttribute("class").getValue()
							.equalsIgnoreCase(clazz)) {
				return e;
			}

			Element er = getFirstElementByClassElementName(e, clazz,
					elementName);
			if (er != null) {
				return er;
			}
		}

		return null;
	}

	public boolean isContainsElementByClassElementName(Element element,
			String clazz, String elementName) { // 666

		clazz = clazz.trim();
		elementName = elementName.trim();

		@SuppressWarnings("unchecked")
		List<Element> elements = element.getChildren();

		for (Element e : elements) {

			if (e.getName().equalsIgnoreCase(elementName)
					&& e.getAttribute("class") != null
					&& e.getAttribute("class").getValue()
							.equalsIgnoreCase(clazz)) {
				return true;
			}

			boolean b = isContainsElementByClassElementName(e, clazz,
					elementName);
			if (b) {
				return b;
			}
		}

		return false;
	}

	public Element getFirstElementByElementName(Element element,
			String elementName) { // 777

		elementName = elementName.trim();

		@SuppressWarnings("unchecked")
		List<Element> elements = element.getChildren();

		for (Element e : elements) {

			if (e.getName() != null
					&& e.getName().equalsIgnoreCase(elementName)) {

				return e;
			} else {
				Element er = getFirstElementByElementName(e, elementName);
				if (er != null) {
					return er;
				}
			}

		}

		return null;
	}

	public List<Element> getElementsByClassElementName(Element element,
			String clazz, String elementName) {

		List<Element> elementsReturn = new ArrayList<Element>();

		clazz = clazz.trim();
		elementName = elementName.trim();

		@SuppressWarnings("unchecked")
		List<Element> elements = element.getChildren();

		for (Element e : elements) {

			if (e.getName().equalsIgnoreCase(elementName)
					&& e.getAttribute("class") != null
					&& e.getAttribute("class").getValue()
							.equalsIgnoreCase(clazz)) {

				elementsReturn.add(e);
			}

			List<Element> er = getElementsByClassElementName(e, clazz,
					elementName);
			for (Element item : er) {
				elementsReturn.add(item);
			}
		}

		return elementsReturn;

	}

	public List<Element> getElementsByElementName(Element element,
			String elementName) {

		List<Element> elementsReturn = new ArrayList<Element>();

		elementName = elementName.trim();

		@SuppressWarnings("unchecked")
		List<Element> elements = element.getChildren();

		for (Element e : elements) {

			if (e.getName().equalsIgnoreCase(elementName)) {

				elementsReturn.add(e);
			}

			List<Element> er = getElementsByElementName(e, elementName);
			for (Element item : er) {
				elementsReturn.add(item);
			}
		}

		return elementsReturn;

	}

	public String download(String uri, String toPathFile) throws Exception {

		InputStream is = null;
		FileOutputStream fos = null;

		try {
			System.out.println("\n[..] Descargando \"" + uri + "\" en \""
					+ toPathFile + "\".");

			// Url con la foto
			URL url = new URL(uri);

			// establecemos conexion
			URLConnection urlCon = url.openConnection();

			// Sacamos por pantalla el tipo de fichero
			// System.out.println(urlCon.getContentType());

			// toPathFile += "." +
			// mimeTypes2.getProperty(urlCon.getContentType());

			// Se obtiene el inputStream de la foto web y se abre el fichero
			// local.
			is = urlCon.getInputStream();
			fos = new FileOutputStream(toPathFile);

			// Lectura de la foto de la web y escritura en fichero local
			byte[] array = new byte[1000]; // buffer temporal de lectura.
			int leido = is.read(array);
			while (leido > 0) {
				fos.write(array, 0, leido);
				leido = is.read(array);
			}

			// cierre de conexion y fichero.
			is.close();
			fos.close();

			System.out.println("\n[OK] \"" + uri + "\" descargado en \""
					+ toPathFile + "\".");

			return toPathFile;

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			throw e;
		} finally {

			if (fos != null) {
				fos.close();
			}

			if (is != null) {
				is.close();
			}

		}

	}

}
