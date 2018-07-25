package org.cendra.geoobject;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.DownloadAbout;
import org.cendra.geoobject.populate.download.geonames.continents.DownloadContinents;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountries;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountriesFormGeonames;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountriesInfo;
import org.cendra.geoobject.populate.download.geonames.countries.alternatenames.DownloadCountriesAlternateNames;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.DownloadAdmin1;
import org.cendra.geoobject.populate.download.geonames.countries.subdivisions.DownloadCountriesSubdivisionGeonames;
import org.cendra.geoobject.populate.download.geonames.languajes.DownloadLanguages;
import org.cendra.geoobject.populate.download.geonames.timezones.DownloadTimeZones;
import org.cendra.geoobject.populate.download.wikipedia.continents.DownloadWikiContinents;
import org.cendra.geoobject.populate.download.wikipedia.countries.DownloadCountriesFormWikipedia;
import org.cendra.geoobject.populate.download.wikipedia.countries.DownloadWikiCountries;
import org.cendra.geoobject.populate.download.wikipedia.countries.DownloadWikiCountriesISO2;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.africa.DownloadWikiCountriesAfrica;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.america.DownloadWikiCountriesAmerica;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.asia.DownloadWikiCountriesAsia;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.europa.DownloadWikiCountriesEurpoa;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.oceania.DownloadWikiCountriesOceania;
import org.cendra.geoobject.populate.model.BuildModel;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			PopulateProperties prop = new PopulateProperties();

			HTMLDocumentUtil htmlDocumentUtil = new HTMLDocumentUtil();

			// ///////////////////////////////////////////////////////////////////////////////
			// FASE 1

			DownloadLanguages populateLanguages = new DownloadLanguages(
					htmlDocumentUtil, prop);
			// populateLanguages.download();

			DownloadTimeZones downloadTimeZones = new DownloadTimeZones(
					htmlDocumentUtil, prop);
			// downloadTimeZones.download();

			// Listados !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			DownloadContinents downloadContinents = new DownloadContinents(
					htmlDocumentUtil, prop);
			// downloadContinents.download();

			DownloadCountriesInfo downloadCountriesInfo = new DownloadCountriesInfo(
					htmlDocumentUtil, prop);
			// downloadCountriesInfo.download();

			DownloadCountries downloadCountries = new DownloadCountries(
					htmlDocumentUtil, prop);
			// downloadCountries.download();

			DownloadWikiCountries downloadWikiCountries = new DownloadWikiCountries(
					htmlDocumentUtil, prop);
			// downloadWikiCountries.download();

			DownloadWikiCountriesISO2 downloadWikiCountriesISO2 = new DownloadWikiCountriesISO2(
					htmlDocumentUtil, prop);
			// downloadWikiCountriesISO2.download();

			DownloadAdmin1 downloadAdmin1 = new DownloadAdmin1(
					htmlDocumentUtil, prop);
			// downloadAdmin1.download();

			// ----------------------------------------------------------------
//			DownloadWikiCountriesEurpoa downloadWikiCountriesEurpoa = new DownloadWikiCountriesEurpoa(
//					new HTMLDocumentUtil(), prop);
			// downloadWikiCountriesEurpoa.download();

//			DownloadWikiCountriesAsia downloadWikiCountriesAsia = new DownloadWikiCountriesAsia(
//					new HTMLDocumentUtil(), prop);
			// downloadWikiCountriesAsia.download();

//			DownloadWikiCountriesAmerica downloadWikiCountriesAmerica = new DownloadWikiCountriesAmerica(
//					new HTMLDocumentUtil(), prop);
			// downloadWikiCountriesAmerica.download();

//			DownloadWikiCountriesAfrica downloadWikiCountriesAfrica = new DownloadWikiCountriesAfrica(
//					new HTMLDocumentUtil(), prop);
			// downloadWikiCountriesAfrica.download();

//			DownloadWikiCountriesOceania downloadWikiCountriesOceania = new DownloadWikiCountriesOceania(
//					new HTMLDocumentUtil(), prop);
			// downloadWikiCountriesOceania.download();

			// ----------------------------------------------------------------

			// ///////////////////////////////////////////////////////////////////////////////
			// FASE 2

			DownloadCountriesFormGeonames downloadCountriesFormGeonames = new DownloadCountriesFormGeonames(
					htmlDocumentUtil, prop);
			// downloadCountriesFormGeonames.download();

			DownloadCountriesAlternateNames downloadCountriesAlternateNames = new DownloadCountriesAlternateNames(
					new HTMLDocumentUtil(), prop);
			// downloadCountriesAlternateNames.download();

			DownloadCountriesSubdivisionGeonames downloadCountriesSubdivisionGeonames = new DownloadCountriesSubdivisionGeonames(
					htmlDocumentUtil, prop);
			// downloadCountriesSubdivisionGeonames.download();

			// ----------------------------------------------------------------

			// DownloadAboutContinents downloadAboutContinents = new
			// DownloadAboutContinents(
			// htmlDocumentUtil, prop);
			// downloadAboutContinents.download();

			// DownloadAboutCountries downloadAboutCountries = new
			// DownloadAboutCountries(
			// htmlDocumentUtil, prop);
			// downloadAboutCountries.download();

			DownloadAbout downloadAbout = new DownloadAbout(htmlDocumentUtil,
					prop);
			downloadAbout.download(false);

			// ----------------------------------------------------------------
			// WIIPEDIA

			DownloadWikiContinents downloadWikiContinents = new DownloadWikiContinents(
					new HTMLDocumentUtil(), prop);
			// downloadWikiContinents.download();

			DownloadCountriesFormWikipedia downloadCountriesFormWikipedia = new DownloadCountriesFormWikipedia(
					new HTMLDocumentUtil(), prop);
//			downloadCountriesFormWikipedia.download();
			// downloadCountriesFormWikipedia.check();

//			System.exit(0);

			// DownloadCountriesSubdivisionWikipedia
			// downloadCountriesSubdivisionWikipedia = new
			// DownloadCountriesSubdivisionWikipedia(
			// new HTMLDocumentUtil(), prop);
			// downloadCountriesSubdivisionWikipedia.download();
			// !!!!!!!!!!!!!!!!!!

			// ///////////////////////////////////////////////////////////////////////////////
			// FASE 3

			// DownloadAlternateNames downloadAlternateNames = new
			// DownloadAlternateNames(
			// new HTMLDocumentUtil(), prop);
			// downloadAlternateNames.download();

			// ///////////////////////////////////////////////////////////////////////////////
			// FASE 4

			BuildModel BuildModel = new BuildModel(new HTMLDocumentUtil(), prop);
			BuildModel.build();

			// System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
