package org.cendra.geoobject.populate.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.cendra.geoobject.populate.PopulateProperties;
import org.cendra.geoobject.populate.download.Download;
import org.cendra.geoobject.populate.download.HTMLDocumentUtil;
import org.cendra.geoobject.populate.download.geonames.continents.ContinentGeoname;
import org.cendra.geoobject.populate.download.geonames.continents.DownloadContinents;
import org.cendra.geoobject.populate.download.geonames.countries.CountryFormGeoname;
import org.cendra.geoobject.populate.download.geonames.countries.CountryGeoname;
import org.cendra.geoobject.populate.download.geonames.countries.CountryInfoGeoname;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountries;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountriesFormGeonames;
import org.cendra.geoobject.populate.download.geonames.countries.DownloadCountriesInfo;
import org.cendra.geoobject.populate.download.wikipedia.continents.ContinentWikipedia;
import org.cendra.geoobject.populate.download.wikipedia.continents.DownloadWikiContinents;
import org.cendra.geoobject.populate.download.wikipedia.countries.CountryFormWikipedia;
import org.cendra.geoobject.populate.download.wikipedia.countries.CountryWikipedia;
import org.cendra.geoobject.populate.download.wikipedia.countries.CountryWikipediaISO2;
import org.cendra.geoobject.populate.download.wikipedia.countries.DownloadCountriesFormWikipedia;
import org.cendra.geoobject.populate.download.wikipedia.countries.DownloadWikiCountries;
import org.cendra.geoobject.populate.download.wikipedia.countries.DownloadWikiCountriesISO2;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.africa.CountryWikipediaAfrica;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.africa.DownloadWikiCountriesAfrica;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.america.CountryWikipediaAmerica;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.america.DownloadWikiCountriesAmerica;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.asia.CountryWikipediaAsia;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.asia.DownloadWikiCountriesAsia;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.europa.CountryWikipediaEuropa;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.europa.DownloadWikiCountriesEurpoa;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.oceania.CountryWikipediaOceania;
import org.cendra.geoobject.populate.download.wikipedia.countries.continents.oceania.DownloadWikiCountriesOceania;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class BuildModel extends Download {

	public BuildModel(HTMLDocumentUtil htmlDocumentUtil,
			PopulateProperties populateProperties) {
		super(htmlDocumentUtil, populateProperties);
	}

	public void build() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		// ==============================================================

		List<ContinentGeoname> continentsGeoNames = mapper.readValue(
				DownloadContinents.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						ContinentGeoname.class));

		List<ContinentWikipedia> continentsWikipedia = mapper.readValue(
				DownloadWikiContinents.getFullPathFileDownload(this
						.getPopulateProperties().getPathHome()),
				mapper.getTypeFactory().constructCollectionType(List.class,
						ContinentWikipedia.class));

		// ----------------------------------------------------------------

		for (ContinentGeoname continentGeoname : continentsGeoNames) {
			boolean b = false;
			for (ContinentWikipedia continentWikipedia : continentsWikipedia) {
				if (continentGeoname.getCode().equalsIgnoreCase(
						continentWikipedia.getCode())) {
					b = true;
				}
			}

			if (b == false) {
				throw new Exception("El continente de Geoname "
						+ continentGeoname.getCode()
						+ ", no se encuentra en la lista de Wikipedia.\n"
						+ DownloadContinents.getFullPathFileDownload(this
								.getPopulateProperties().getPathHome())
						+ "\n"
						+ DownloadWikiContinents.getFullPathFileDownload(this
								.getPopulateProperties().getPathHome()));
			}
		}

		// ----------------------------------------------------------------

		for (ContinentWikipedia continentWikipedia : continentsWikipedia) {

			boolean b = false;
			for (ContinentGeoname continentGeoname : continentsGeoNames) {
				if (continentGeoname.getCode().equalsIgnoreCase(
						continentWikipedia.getCode())) {
					b = true;
				}
			}

			if (b == false) {
				throw new Exception("El continente de Wikipedia "
						+ continentWikipedia.getCode()
						+ ", no se encuentra en la lista de Geoname.\n"
						+ DownloadContinents.getFullPathFileDownload(this
								.getPopulateProperties().getPathHome())
						+ "\n"
						+ DownloadWikiContinents.getFullPathFileDownload(this
								.getPopulateProperties().getPathHome()));
			}
		}

		// ----------------------------------------------------------------

		String path = "model/continents";
		File continentsFile = this.getFileFullPath(path, true);

		List<Continent> continents = new ArrayList<Continent>();

		for (ContinentGeoname continentGeoname : continentsGeoNames) {

			Continent continent = new Continent();
			continent.setGeonameId(Integer.valueOf(continentGeoname
					.getGeonameId().toString()));
			continent.setCode(continentGeoname.getCode());
			continent.setShortName(continentGeoname.getShortName());
			continent.setWikipediaURL(continentGeoname.getWikipediaURL());
			for (ContinentWikipedia continentWikipedia : continentsWikipedia) {
				if (continentGeoname.getCode().equalsIgnoreCase(
						continentWikipedia.getCode())) {
					continent.setOrthographicProjectionURL(continentWikipedia
							.getOrthographicProjectionURL());

					String ext = continent.getOrthographicProjectionURL()
							.split("/")[continent
							.getOrthographicProjectionURL().split("/").length - 1]
							.split("[.]")[1];

					String fileName = continent.getCode().toLowerCase()
							+ "_orthographic_projection_" + ext + "." + ext;

					continent.setOrthographicProjection(fileName);

					File source = new File(DownloadWikiContinents
							.getPathFileDownload(
									this.getPopulateProperties().getPathHome())
							.getAbsolutePath()
							+ File.separatorChar + fileName);

					File dest = new File(continentsFile.getAbsolutePath()
							+ File.separatorChar + fileName);

					copyFileUsingStream(source, dest);

					break;
				}
			}

			continents.add(continent);

		}

		// ==============================================================
		buildCountry(mapper, continentsFile, continents);
		// ==============================================================

	}

	private void buildCountry(ObjectMapper mapper, File continentsFile,
			List<Continent> continents) throws Exception {

		String path = "model/countries";
		File countriesFile = this.getFileFullPath(path, true);

		File countriesInfoGeonamesFile = DownloadCountriesInfo
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome());

		List<CountryInfoGeoname> countriesInfoGeonames = mapper.readValue(
				countriesInfoGeonamesFile,
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryInfoGeoname.class));

		File countriesGeonameFile = DownloadCountries
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome());

		List<CountryGeoname> countriesGeonames = mapper.readValue(
				countriesGeonameFile,
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryGeoname.class));

		File wikiCountriesFile = DownloadWikiCountries
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome());

		List<CountryWikipedia> countriesWikipedia = mapper.readValue(
				wikiCountriesFile,
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryWikipedia.class));

		File wikiCountriesISO2File = DownloadWikiCountriesISO2
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome());

		List<CountryWikipediaISO2> countriesWikipediaISO2 = mapper.readValue(
				wikiCountriesISO2File,
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryWikipediaISO2.class));

		// File wikiCountriesEuropaFile = DownloadWikiCountriesEurpoa
		// .getFullPathFileDownload(this.getPopulateProperties()
		// .getPathHome());

		// List<CountryWikipediaEuropa> countriesWikipediaEuropa = mapper
		// .readValue(
		// wikiCountriesEuropaFile,
		// mapper.getTypeFactory().constructCollectionType(
		// List.class, CountryWikipediaEuropa.class));

		// File wikiCountriesAfricaFile = DownloadWikiCountriesAfrica
		// .getFullPathFileDownload(this.getPopulateProperties()
		// .getPathHome());

		// List<CountryWikipediaAfrica> countriesWikipediaAfrica = mapper
		// .readValue(
		// wikiCountriesAfricaFile,
		// mapper.getTypeFactory().constructCollectionType(
		// List.class, CountryWikipediaAfrica.class));

		// File wikiCountriesAmericaFile = DownloadWikiCountriesAmerica
		// .getFullPathFileDownload(this.getPopulateProperties()
		// .getPathHome());

		// List<CountryWikipediaAmerica> countriesWikipediaAmerica = mapper
		// .readValue(
		// wikiCountriesAmericaFile,
		// mapper.getTypeFactory().constructCollectionType(
		// List.class, CountryWikipediaAmerica.class));

		// File wikiCountriesAsiaFile = DownloadWikiCountriesAsia
		// .getFullPathFileDownload(this.getPopulateProperties()
		// .getPathHome());

		// List<CountryWikipediaAsia> countriesWikipediaAsia = mapper.readValue(
		// wikiCountriesAsiaFile,
		// mapper.getTypeFactory().constructCollectionType(List.class,
		// CountryWikipediaAsia.class));

		// File wikiCountriesOceaniaFile = DownloadWikiCountriesOceania
		// .getFullPathFileDownload(this.getPopulateProperties()
		// .getPathHome());

		// List<CountryWikipediaOceania> countriesWikipediaOceania = mapper
		// .readValue(
		// wikiCountriesOceaniaFile,
		// mapper.getTypeFactory().constructCollectionType(
		// List.class, CountryWikipediaOceania.class));

		File countriesFormGeonamesFile = DownloadCountriesFormGeonames
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome());

		List<CountryFormGeoname> countriesFormGeoname = mapper.readValue(
				countriesFormGeonamesFile,
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryFormGeoname.class));

		File countriesFormWikipediaFile = DownloadCountriesFormWikipedia
				.getFullPathFileDownload(this.getPopulateProperties()
						.getPathHome());

		List<CountryFormWikipedia> countriesFormWikipedia = mapper.readValue(
				countriesFormWikipediaFile,
				mapper.getTypeFactory().constructCollectionType(List.class,
						CountryFormWikipedia.class));

		List<Country> countries = new ArrayList<Country>();

		// ----------------------------------------------------------------

		checkCountryA(countriesInfoGeonamesFile, countriesGeonameFile,
				countriesInfoGeonames, countriesGeonames);

		// ----------------------------------------------------------------

		File countriesResultFile = buildCountryStep1(mapper, countries,
				continentsFile, continents, countriesFile, countriesGeonames,
				countriesInfoGeonames);

		// ----------------------------------------------------------------

		checkCountryB(countriesResultFile, wikiCountriesFile,
				countriesWikipedia, countries);

		// ----------------------------------------------------------------

		buildCountryStep2(mapper, countriesFile, countriesResultFile,
				wikiCountriesFile, countriesWikipedia, countries);

		// ----------------------------------------------------------------

		checkCountryC(countriesResultFile, wikiCountriesISO2File,
				countriesWikipediaISO2, countries);

		// ----------------------------------------------------------------

		buildCountryStep3(mapper, countriesFile, countriesResultFile,
				wikiCountriesFile, countriesWikipediaISO2, countries);

		// ----------------------------------------------------------------

		// checkCountryE(countriesResultFile, wikiCountriesAfricaFile,
		// countriesWikipediaAfrica, countries);
		//
		// checkCountryF(countriesResultFile, wikiCountriesAmericaFile,
		// countriesWikipediaAmerica, countries);
		//
		// checkCountryG(countriesResultFile, wikiCountriesAsiaFile,
		// countriesWikipediaAsia, countries);
		//
		// checkCountryD(countriesResultFile, wikiCountriesEuropaFile,
		// countriesWikipediaEuropa, countries);
		//
		// checkCountryH(countriesResultFile, wikiCountriesOceaniaFile,
		// countriesWikipediaOceania, countries);

		// ----------------------------------------------------------------

		// buildCountryStep4(mapper, countriesFile, countriesResultFile,
		// wikiCountriesAfricaFile, countriesWikipediaAfrica, countries);
		//
		// buildCountryStep5(mapper, countriesFile, countriesResultFile,
		// wikiCountriesAmericaFile, countriesWikipediaAmerica, countries);
		//
		// buildCountryStep6(mapper, countriesFile, countriesResultFile,
		// wikiCountriesAsiaFile, countriesWikipediaAsia, countries);
		//
		// buildCountryStep7(mapper, countriesFile, countriesResultFile,
		// wikiCountriesEuropaFile, countriesWikipediaEuropa, countries);
		//
		// buildCountryStep8(mapper, countriesFile, countriesResultFile,
		// wikiCountriesOceaniaFile, countriesWikipediaOceania, countries);

		// ----------------------------------------------------------------

		buildCountryStep9(mapper, countriesFile, countriesResultFile,
				countriesFormGeonamesFile, countriesFormGeoname, countries);

		// ----------------------------------------------------------------

		buildCountryStep10(mapper, countriesFile, countriesResultFile,
				countriesFormWikipediaFile, countriesFormWikipedia, countries);

		// ----------------------------------------------------------------

	}

	private File buildCountryStep1(ObjectMapper mapper,
			List<Country> countries, File continentsFile,
			List<Continent> continents, File countriesFile,
			List<CountryGeoname> countriesGeonames,
			List<CountryInfoGeoname> countriesInfoGeonames)
			throws JsonGenerationException, JsonMappingException, IOException {

		for (CountryGeoname countryGeoname : countriesGeonames) {

			Country countryContinent = new Country();
			countryContinent.getIso31661().setAlpha2(
					countryGeoname.getIso3166Alpha2());
			for (Continent continent : continents) {
				if (countryGeoname.getContinent().trim()
						.equalsIgnoreCase(continent.getCode().trim())) {
					continent.addCountry(countryContinent);
				}
			}

			for (Continent continent : continents) {
				File json = new File(continentsFile.getAbsolutePath()
						+ File.separatorChar
						+ continent.getCode().toLowerCase() + ".json");

				mapper.writeValue(json, continent);
			}

			Country country = new Country();

			ContinentCode continent = new ContinentCode();
			continent.setCode(countryGeoname.getContinent());
			country.setContinent(continent);

			country.getIso31661().setAlpha2(countryGeoname.getIso3166Alpha2());
			country.getIso31661().setAlpha3(countryGeoname.getIso3166Alpha3());
			country.getIso31661()
					.setNumeric(countryGeoname.getIso3166Numeric());
			country.getFips().setFips(countryGeoname.getFips());

			country.setName(countryGeoname.getCountry());
			country.setCapital(countryGeoname.getCapital());

			country.getExternalLinks().setGeonamesURL(
					countryGeoname.getGeonamesURL());

			for (CountryInfoGeoname countryInfoGeoname : countriesInfoGeonames) {
				if (countryInfoGeoname
						.getIso()
						.trim()
						.equalsIgnoreCase(
								country.getIso31661().getAlpha2().trim())) {

					if (country
							.getName()
							.trim()
							.equalsIgnoreCase(
									countryInfoGeoname.getCountry().trim()) == false) {
						country.setName2(countryInfoGeoname.getCountry());
					}
					country.setGeonameId(Integer.valueOf(countryInfoGeoname
							.getGeonameId().toString()));
					country.getFips().setEquivalentFipsCode(
							countryInfoGeoname.getEquivalentFipsCode());
					country.setArea(countryInfoGeoname.getArea());
					country.setPopulation(countryInfoGeoname.getPopulation());
					country.setTld(countryInfoGeoname.getTld());
					country.setPhone(countryInfoGeoname.getPhone());
					PostalCode postalCode = new PostalCode();
					postalCode.setFormat(countryInfoGeoname
							.getPostalCodeFormat());
					postalCode
							.setRegex(countryInfoGeoname.getPostalCodeRegex());
					country.setPostalCode(postalCode);
					Currency currency = new Currency();
					currency.setCode(countryInfoGeoname.getCurrencyCode());
					currency.setName(countryInfoGeoname.getCurrencyName());
					country.setCurrency(currency);
					for (String countrycISO : countryInfoGeoname
							.getNeighbours()) {
						CountryNeighbour countryNeighbour = new CountryNeighbour();
						countryNeighbour.getIso31661().setAlpha2(countrycISO);
						country.addNeighbour(countryNeighbour);
					}
					country.setLanguages(countryInfoGeoname.getLanguages());

					break;
				}
			}

			countries.add(country);

			File json = new File(countriesFile.getAbsolutePath()
					+ File.separatorChar
					+ country.getIso31661().getAlpha2().toLowerCase() + ".json");

			mapper.writeValue(json, country);

		}

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + "countries.json");

		mapper.writeValue(json, countries);

		return json;

	}

	private void checkCountryA(File countriesInfoGeonamesFile,
			File countriesGeonameFile,
			List<CountryInfoGeoname> countriesInfoGeonames,
			List<CountryGeoname> countriesGeonames) throws Exception {

		for (CountryInfoGeoname countryInfoGeoname : countriesInfoGeonames) {

			String msg = "\nEl país de Geoname " + countryInfoGeoname.getIso()
					+ ", es diferente en la otra lista de Geonames.\n"
					+ countriesGeonameFile + "\n" + countriesInfoGeonamesFile
					+ "\n";

			boolean b = false;

			for (CountryGeoname countryGeoname : countriesGeonames) {

				if (countryInfoGeoname
						.getIso()
						.trim()
						.equalsIgnoreCase(
								countryGeoname.getIso3166Alpha2().trim())) {
					checkCountryA(countryInfoGeoname, countryGeoname,
							countriesGeonameFile, countriesInfoGeonamesFile,
							msg);
					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}
		}

		for (CountryGeoname countryGeoname : countriesGeonames) {

			String msg = "\nEl país de Geoname "
					+ countryGeoname.getIso3166Alpha2()
					+ ", es diferente en la otra lista de Geonames.\n"
					+ countriesGeonameFile + "\n" + countriesInfoGeonamesFile
					+ "\n";

			boolean b = false;

			for (CountryInfoGeoname countryInfoGeoname : countriesInfoGeonames) {

				if (countryInfoGeoname
						.getIso()
						.trim()
						.equalsIgnoreCase(
								countryGeoname.getIso3166Alpha2().trim())) {
					checkCountryA(countryInfoGeoname, countryGeoname,
							countriesGeonameFile, countriesInfoGeonamesFile,
							msg);
					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}
		}

	}

	private void checkCountryA(CountryInfoGeoname countryInfoGeoname,
			CountryGeoname countryGeoname, File countriesGeonameFile,
			File fileCountriesInfo, String msg) throws Exception {

		if (this.checkEquals(countryGeoname.getIso3166Alpha3(),
				countryInfoGeoname.getIso3()) == false) {

			msg += "\nISO3 : " + countryGeoname.getIso3166Alpha3() + " != "
					+ countryInfoGeoname.getIso3() + "\n";

			throw new Exception(msg);
		}

		if (this.checkEquals(countryGeoname.getIso3166Numeric(),
				countryInfoGeoname.getIsoNumeric()) == false) {

			msg += "\nISO Numeric : " + countryGeoname.getIso3166Numeric()
					+ " != " + countryInfoGeoname.getIsoNumeric() + "\n";

			throw new Exception(msg);
		}

		if (this.checkEquals(countryGeoname.getFips(),
				countryInfoGeoname.getFips()) == false) {

			msg += "\nFips : " + countryGeoname.getFips() + " != "
					+ countryInfoGeoname.getFips() + "\n";

			throw new Exception(msg);
		}

		if (this.checkEquals(countryGeoname.getCountry(),
				countryInfoGeoname.getCountry()) == false) {

			msg += "\n[WARNING] Name : " + countryGeoname.getCountry() + " != "
					+ countryInfoGeoname.getCountry() + "\n";

			System.err.println(msg);
			// throw new Exception(msg);
		}

		if (this.checkEquals(countryGeoname.getContinent(),
				countryInfoGeoname.getContinent()) == false) {

			msg += "\n[WARNING] Continent : " + countryGeoname.getContinent()
					+ " != " + countryInfoGeoname.getContinent() + "\n";

			System.err.println(msg);
			// throw new Exception(msg);
		}

		if (this.checkEquals(countryGeoname.getCapital(),
				countryInfoGeoname.getCapital()) == false) {

			// msg += "\nCapital : " + countryGeoname.getCapital() + " != "
			// + countryInfoGeoname.getCapital() + "\n";
			//
			// throw new Exception(msg);

			msg += "\n[WARNING] Capital : " + countryGeoname.getCapital()
					+ " != " + countryInfoGeoname.getCapital() + "\n";

			System.err.println(msg);
		}

	}

	private void checkCountryB(File countriesResultFile,
			File wikiCountriesFile, List<CountryWikipedia> countriesWikipedia,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			String msg = "\nEl país de Geoname "
					+ country.getIso31661().getAlpha2()
					+ ", es diferente en la otra lista de Geonames.\n"
					+ countriesResultFile + "\n" + wikiCountriesFile + "\n";

			boolean b = false;

			for (CountryWikipedia countryWikipedia : countriesWikipedia) {

				if (country
						.getIso31661()
						.getAlpha2()
						.trim()
						.equalsIgnoreCase(
								countryWikipedia.getIso3166Alpha2().trim())) {
					checkCountryB(country, countryWikipedia,
							countriesResultFile, wikiCountriesFile, msg);
					b = true;
				}

			}

			if (b == false) {
				msg += "\n[WARNING] Name : " + country.getName() + "\n";
				System.err.println(msg);
				// throw new Exception(msg);
			}
		}

		for (CountryWikipedia countryWikipedia : countriesWikipedia) {

			String msg = "\nEl país de Geoname "
					+ countryWikipedia.getIso3166Alpha2()
					+ ", es diferente en la otra lista de Geonames.\n"
					+ countriesResultFile + "\n" + wikiCountriesFile + "\n";

			boolean b = false;

			for (Country country : countries) {

				if (country
						.getIso31661()
						.getAlpha2()
						.trim()
						.equalsIgnoreCase(
								countryWikipedia.getIso3166Alpha2().trim())) {
					checkCountryB(country, countryWikipedia,
							countriesResultFile, wikiCountriesFile, msg);
					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}
		}

	}

	private void checkCountryB(Country country,
			CountryWikipedia countryWikipedia, File countriesResultFile,
			File wikiCountriesFile, String msg) throws Exception {

		if (this.checkEquals(country.getIso31661().getAlpha3(),
				countryWikipedia.getIso3166Alpha3()) == false) {

			msg += "\nISO3 : " + country.getIso31661().getAlpha3() + " != "
					+ countryWikipedia.getIso3166Alpha3() + "\n";

			throw new Exception(msg);
		}

		if (this.checkEquals(country.getIso31661().getNumeric(),
				countryWikipedia.getIso3166Numeric()) == false) {

			msg += "\nISO Numeric : " + country.getIso31661().getNumeric()
					+ " != " + countryWikipedia.getIso3166Numeric() + "\n";

			throw new Exception(msg);
		}

	}

	private void buildCountryStep2(ObjectMapper mapper, File countriesFile,
			File countriesResultFile, File wikiCountriesFile,
			List<CountryWikipedia> countriesWikipedia, List<Country> countries)
			throws Exception {

		for (Country country : countries) {

			for (CountryWikipedia countryWikipedia : countriesWikipedia) {
				if (country
						.getIso31661()
						.getAlpha2()
						.trim()
						.equalsIgnoreCase(
								countryWikipedia.getIso3166Alpha2().trim())) {

					country.getIso31661().setIndependent(
							countryWikipedia.getIndependent());
					country.getExternalLinks().setWikipediaURL(
							countryWikipedia.getWikipediaURL());
					country.getExternalLinks().setWikipediaISO31662URL(
							countryWikipedia.getWikipediaISO31662URL());

					File json = new File(countriesFile.getAbsolutePath()
							+ File.separatorChar
							+ country.getIso31661().getAlpha2().toLowerCase()
							+ ".json");

					mapper.writeValue(json, country);

					break;

				}
			}
		}

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + "countries.json");

		mapper.writeValue(json, countries);

	}

	private void checkCountryC(File countriesResultFile,
			File wikiCountriesISO2File,
			List<CountryWikipediaISO2> countriesWikipediaISO2,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			String msg = "\nEl país de Geoname "
					+ country.getIso31661().getAlpha2()
					+ ", es diferente en la otra lista de Geonames.\n"
					+ countriesResultFile + "\n" + wikiCountriesISO2File + "\n";

			boolean b = false;

			for (CountryWikipediaISO2 countryWikipediaISO2 : countriesWikipediaISO2) {

				if (country
						.getIso31661()
						.getAlpha2()
						.trim()
						.equalsIgnoreCase(
								countryWikipediaISO2.getIso3166Alpha2().trim())) {
					b = true;
				}

			}

			if (b == false) {
				msg += "\n[WARNING] Name : " + country.getName() + "\n";
				// System.err.println(msg);
				throw new Exception(msg);
			}
		}

		// for (CountryWikipediaISO2 countryWikipediaISO2 :
		// countriesWikipediaISO2) {
		//
		// String msg = "\nEl país de Geoname "
		// + countryWikipediaISO2.getIso3166Alpha2()
		// + ", es diferente en la otra lista de Geonames.\n"
		// + countriesResultFile + "\n" + wikiCountriesISO2File + "\n";
		//
		// boolean b = false;
		//
		// for (Country country : countries) {
		//
		// if (country
		// .getIso()
		// .trim()
		// .equalsIgnoreCase(
		// countryWikipediaISO2.getIso3166Alpha2().trim())) {
		// // checkCountryB(country, countryWikipediaISO2,
		// // countriesResultFile, wikiCountriesISO2File, msg);
		// b = true;
		// }
		//
		// }
		//
		// if (b == false) {
		// msg += "\n[WARNING] Name : " +
		// countryWikipediaISO2.getIso3166Alpha2State() + "\n";
		// System.err.println(msg);
		// // throw new Exception(msg);
		// }
		// }

	}

	private void buildCountryStep3(ObjectMapper mapper, File countriesFile,
			File countriesResultFile, File wikiCountriesFile,
			List<CountryWikipediaISO2> countriesWikipediaISO2,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			for (CountryWikipediaISO2 countryWikipediaISO2 : countriesWikipediaISO2) {
				if (country
						.getIso31661()
						.getAlpha2()
						.trim()
						.equalsIgnoreCase(
								countryWikipediaISO2.getIso3166Alpha2().trim())) {

					ISO3166Alpha2State iso3166Alpha2State = new ISO3166Alpha2State();

					iso3166Alpha2State.setName(countryWikipediaISO2
							.getIso3166Alpha2State());
					if (iso3166Alpha2State.getName().equals(
							CountryWikipediaISO2.DELETED)) {
						iso3166Alpha2State.setCode(1);
					} else if (iso3166Alpha2State.getName().equals(
							CountryWikipediaISO2.EXCEPTIONALLY)) {
						iso3166Alpha2State.setCode(2);
					} else if (iso3166Alpha2State.getName().equals(
							CountryWikipediaISO2.INDETERMINATELY)) {
						iso3166Alpha2State.setCode(3);
					} else if (iso3166Alpha2State.getName().equals(
							CountryWikipediaISO2.OFfICIALLY)) {
						iso3166Alpha2State.setCode(4);
					} else if (iso3166Alpha2State.getName().equals(
							CountryWikipediaISO2.TRANSITIONALLY)) {
						iso3166Alpha2State.setCode(5);
					} else if (iso3166Alpha2State.getName().equals(
							CountryWikipediaISO2.UNASSIGNED)) {
						iso3166Alpha2State.setCode(6);
					} else if (iso3166Alpha2State.getName().equals(
							CountryWikipediaISO2.USER_ASSIGNED)) {
						iso3166Alpha2State.setCode(7);
					} else {
						throw new Exception(iso3166Alpha2State.getName());
					}

					country.getIso31661().setIso3166Alpha2State(
							iso3166Alpha2State);

					File json = new File(countriesFile.getAbsolutePath()
							+ File.separatorChar
							+ country.getIso31661().getAlpha2().toLowerCase()
							+ ".json");

					mapper.writeValue(json, country);

					break;

				}
			}
		}

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + "countries.json");

		mapper.writeValue(json, countries);

	}

	private void checkCountryD(File countriesResultFile,
			File wikiCountriesEurpoaFile,
			List<CountryWikipediaEuropa> countriesWikipediaEuropa,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			String msg = "\nEl país de Geoname "
					+ country.getIso31661().getAlpha3()
					+ ", es diferente en la otra lista de Wikipedia.\n"
					+ countriesResultFile + "\n" + wikiCountriesEurpoaFile
					+ "\n";

			boolean b = false;

			if (country.getContinent().getCode().trim().equalsIgnoreCase("EU")) {

				for (CountryWikipediaEuropa countryWikipediaEuropa : countriesWikipediaEuropa) {

					if (country.getContinent().getCode().trim()
							.equalsIgnoreCase("EU")
							&& country
									.getIso31661()
									.getAlpha3()
									.trim()
									.equalsIgnoreCase(
											countryWikipediaEuropa
													.getIso3166Alpha3().trim())) {

						if (country
								.getIso31661()
								.getIndependent()
								.equals(countryWikipediaEuropa
										.getRecognisedState()) == false) {

							msg += "\n[WARNING] Name : "
									+ country.getName()
									+ " - "
									+ country.getIso31661().getIndependent()
									+ " != "
									+ countryWikipediaEuropa
											.getRecognisedState() + "\n";
							System.err.println(msg);
							// throw new Exception(msg);
						}

						b = true;
					}

				}

				if (b == false) {
					msg += "\n[WARNING] Name : " + country.getName() + "\n";
					System.err.println(msg);
					// throw new Exception(msg);
				}

			}

		}

		for (CountryWikipediaEuropa countryWikipediaEuropa : countriesWikipediaEuropa) {

			String msg = "\nEl país de Wikipedia "
					+ countryWikipediaEuropa.getIso3166Alpha3()
					+ ", es diferente en la otra lista de Geoname.\n"
					+ countriesResultFile + "\n" + wikiCountriesEurpoaFile
					+ "\n";

			boolean b = false;

			for (Country country : countries) {

				if (country
						.getIso31661()
						.getAlpha3()
						.trim()
						.equalsIgnoreCase(
								countryWikipediaEuropa.getIso3166Alpha3()
										.trim())) {

					if (country
							.getIso31661()
							.getIndependent()
							.equals(countryWikipediaEuropa.getRecognisedState()) == false) {

						msg += "\n[WARNING] Name : " + country.getName()
								+ " - "
								+ country.getIso31661().getIndependent()
								+ " != "
								+ countryWikipediaEuropa.getRecognisedState()
								+ "\n";
						System.err.println(msg);
						// throw new Exception(msg);
					}

					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}

		}

	}

	private void checkCountryE(File countriesResultFile,
			File wikiCountriesAfricaFile,
			List<CountryWikipediaAfrica> countriesWikipediaAfrica,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			String msg = "\nEl país de Geoname "
					+ country.getIso31661().getAlpha3()
					+ ", es diferente en la otra lista de Wikipedia.\n"
					+ countriesResultFile + "\n" + wikiCountriesAfricaFile
					+ "\n";

			if (country.getContinent().getCode().trim().equalsIgnoreCase("AF")) {

				boolean b = false;

				for (CountryWikipediaAfrica countryWikipediaAfrica : countriesWikipediaAfrica) {

					if (country
							.getIso31661()
							.getAlpha3()
							.trim()
							.equalsIgnoreCase(
									countryWikipediaAfrica.getIso3166Alpha3()
											.trim())) {

						if (country
								.getIso31661()
								.getIndependent()
								.equals(countryWikipediaAfrica
										.getRecognisedState()) == false) {

							msg += "\n[WARNING] Name : "
									+ country.getName()
									+ " - "
									+ country.getIso31661().getIndependent()
									+ " != "
									+ countryWikipediaAfrica
											.getRecognisedState() + "\n";
							System.err.println(msg);
							// throw new Exception(msg);
						}

						b = true;
					}

				}

				if (b == false) {
					msg += "\n[WARNING] Name : " + country.getName() + "\n";
					System.err.println(msg);
					// throw new Exception(msg);
				}

			}

		}

		for (CountryWikipediaAfrica countryWikipediaAfrica : countriesWikipediaAfrica) {

			String msg = "\nEl país de Wikipedia "
					+ countryWikipediaAfrica.getIso3166Alpha3()
					+ ", es diferente en la otra lista de Geoname.\n"
					+ countriesResultFile + "\n" + wikiCountriesAfricaFile
					+ "\n";

			boolean b = false;

			for (Country country : countries) {

				if (country
						.getIso31661()
						.getAlpha3()
						.trim()
						.equalsIgnoreCase(
								countryWikipediaAfrica.getIso3166Alpha3()
										.trim())) {

					if (country
							.getIso31661()
							.getIndependent()
							.equals(countryWikipediaAfrica.getRecognisedState()) == false) {

						msg += "\n[WARNING] Name : " + country.getName()
								+ " - "
								+ country.getIso31661().getIndependent()
								+ " != "
								+ countryWikipediaAfrica.getRecognisedState()
								+ "\n";
						System.err.println(msg);
						// throw new Exception(msg);
					}

					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}

		}

	}

	private void checkCountryF(File countriesResultFile,
			File wikiCountriesAmericaFile,
			List<CountryWikipediaAmerica> countriesWikipediaAmerica,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			String msg = "\nEl país de Geoname "
					+ country.getIso31661().getAlpha3()
					+ ", es diferente en la otra lista de Wikipedia.\n"
					+ countriesResultFile + "\n" + wikiCountriesAmericaFile
					+ "\n";

			if (country.getContinent().getCode().trim().equalsIgnoreCase("NA")
					|| country.getContinent().getCode().trim()
							.equalsIgnoreCase("SA")) {

				boolean b = false;

				for (CountryWikipediaAmerica countryWikipediaAmerica : countriesWikipediaAmerica) {

					if (country
							.getIso31661()
							.getAlpha3()
							.trim()
							.equalsIgnoreCase(
									countryWikipediaAmerica.getIso3166Alpha3()
											.trim())) {

						if (country
								.getIso31661()
								.getIndependent()
								.equals(countryWikipediaAmerica
										.getRecognisedState()) == false) {

							msg += "\n[WARNING] Name : "
									+ country.getName()
									+ " - "
									+ country.getIso31661().getIndependent()
									+ " != "
									+ countryWikipediaAmerica
											.getRecognisedState() + "\n";
							System.err.println(msg);
							// throw new Exception(msg);
						}

						b = true;
					}

				}

				if (b == false) {
					msg += "\n[WARNING] Name : " + country.getName() + "\n";
					System.err.println(msg);
					// throw new Exception(msg);
				}

			}

		}

		for (CountryWikipediaAmerica countryWikipediaAmerica : countriesWikipediaAmerica) {

			String msg = "\nEl país de Wikipedia "
					+ countryWikipediaAmerica.getIso3166Alpha3()
					+ ", es diferente en la otra lista de Geoname.\n"
					+ countriesResultFile + "\n" + wikiCountriesAmericaFile
					+ "\n";

			boolean b = false;

			for (Country country : countries) {

				if (country
						.getIso31661()
						.getAlpha3()
						.trim()
						.equalsIgnoreCase(
								countryWikipediaAmerica.getIso3166Alpha3()
										.trim())) {

					if (country
							.getIso31661()
							.getIndependent()
							.equals(countryWikipediaAmerica
									.getRecognisedState()) == false) {

						msg += "\n[WARNING] Name : " + country.getName()
								+ " - "
								+ country.getIso31661().getIndependent()
								+ " != "
								+ countryWikipediaAmerica.getRecognisedState()
								+ "\n";
						System.err.println(msg);
						// throw new Exception(msg);
					}

					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}

		}

	}

	private void checkCountryG(File countriesResultFile,
			File wikiCountriesAsiaFile,
			List<CountryWikipediaAsia> countriesWikipediaAsia,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			String msg = "\nEl país de Geoname "
					+ country.getIso31661().getAlpha3()
					+ ", es diferente en la otra lista de Wikipedia.\n"
					+ countriesResultFile + "\n" + wikiCountriesAsiaFile + "\n";

			if (country.getContinent().getCode().trim().equalsIgnoreCase("AS")) {

				boolean b = false;

				for (CountryWikipediaAsia countryWikipediaAsia : countriesWikipediaAsia) {

					if (country
							.getIso31661()
							.getAlpha3()
							.trim()
							.equalsIgnoreCase(
									countryWikipediaAsia.getIso3166Alpha3()
											.trim())) {

						if (country
								.getIso31661()
								.getIndependent()
								.equals(countryWikipediaAsia
										.getRecognisedState()) == false) {

							msg += "\n[WARNING] Name : " + country.getName()
									+ " - "
									+ country.getIso31661().getIndependent()
									+ " != "
									+ countryWikipediaAsia.getRecognisedState()
									+ "\n";
							System.err.println(msg);
							// throw new Exception(msg);
						}

						b = true;
					}

				}

				if (b == false) {
					msg += "\n[WARNING] Name : " + country.getName() + "\n";
					System.err.println(msg);
					// throw new Exception(msg);
				}

			}

		}

		for (CountryWikipediaAsia countryWikipediaAsia : countriesWikipediaAsia) {

			String msg = "\nEl país de Wikipedia "
					+ countryWikipediaAsia.getIso3166Alpha3()
					+ ", es diferente en la otra lista de Geoname.\n"
					+ countriesResultFile + "\n" + wikiCountriesAsiaFile + "\n";

			boolean b = false;

			for (Country country : countries) {

				if (country
						.getIso31661()
						.getAlpha3()
						.trim()
						.equalsIgnoreCase(
								countryWikipediaAsia.getIso3166Alpha3().trim())) {

					if (country.getIso31661().getIndependent()
							.equals(countryWikipediaAsia.getRecognisedState()) == false) {

						msg += "\n[WARNING] Name : " + country.getName()
								+ " - "
								+ country.getIso31661().getIndependent()
								+ " != "
								+ countryWikipediaAsia.getRecognisedState()
								+ "\n";
						System.err.println(msg);
						// throw new Exception(msg);
					}

					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}

		}

	}

	private void checkCountryH(File countriesResultFile,
			File wikiCountriesOceaniaFile,
			List<CountryWikipediaOceania> countriesWikipediaOceania,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			String msg = "\nEl país de Geoname "
					+ country.getIso31661().getAlpha3()
					+ ", es diferente en la otra lista de Wikipedia.\n"
					+ countriesResultFile + "\n" + wikiCountriesOceaniaFile
					+ "\n";

			if (country.getContinent().getCode().trim().equalsIgnoreCase("OC")) {

				boolean b = false;

				for (CountryWikipediaOceania countryWikipediaOceania : countriesWikipediaOceania) {

					if (country
							.getIso31661()
							.getAlpha3()
							.trim()
							.equalsIgnoreCase(
									countryWikipediaOceania.getIso3166Alpha3()
											.trim())) {

						if (country
								.getIso31661()
								.getIndependent()
								.equals(countryWikipediaOceania
										.getRecognisedState()) == false) {

							msg += "\n[WARNING] Name : "
									+ country.getName()
									+ " - "
									+ country.getIso31661().getIndependent()
									+ " != "
									+ countryWikipediaOceania
											.getRecognisedState() + "\n";
							System.err.println(msg);
							// throw new Exception(msg);
						}

						b = true;
					}

				}

				if (b == false) {
					msg += "\n[WARNING] Name : " + country.getName() + "\n";
					System.err.println(msg);
					// throw new Exception(msg);
				}

			}

		}

		for (CountryWikipediaOceania countryWikipediaOceania : countriesWikipediaOceania) {

			String msg = "\nEl país de Wikipedia "
					+ countryWikipediaOceania.getIso3166Alpha3()
					+ ", es diferente en la otra lista de Geoname.\n"
					+ countriesResultFile + "\n" + wikiCountriesOceaniaFile
					+ "\n";

			boolean b = false;

			for (Country country : countries) {

				if (country
						.getIso31661()
						.getAlpha3()
						.trim()
						.equalsIgnoreCase(
								countryWikipediaOceania.getIso3166Alpha3()
										.trim())) {

					if (country
							.getIso31661()
							.getIndependent()
							.equals(countryWikipediaOceania
									.getRecognisedState()) == false) {

						msg += "\n[WARNING] Name : " + country.getName()
								+ " - "
								+ country.getIso31661().getIndependent()
								+ " != "
								+ countryWikipediaOceania.getRecognisedState()
								+ "\n";
						System.err.println(msg);
						// throw new Exception(msg);
					}

					b = true;
				}

			}

			if (b == false) {
				throw new Exception(msg);
			}

		}

	}

	// private void buildCountryStep4(ObjectMapper mapper, File countriesFile,
	// File countriesResultFile, File wikiCountriesFile,
	// List<CountryWikipediaAfrica> countriesWikipedia,
	// List<Country> countries) throws Exception {
	//
	// for (Country country : countries) {
	//
	// for (CountryWikipediaAfrica countryWikipedia : countriesWikipedia) {
	// if (country
	// .getIso31661()
	// .getAlpha3()
	// .trim()
	// .equalsIgnoreCase(
	// countryWikipedia.getIso3166Alpha3().trim())) {
	//
	// country.setRecognisedState(countryWikipedia
	// .getRecognisedState());
	// country.getExternalLinks().setWikipediaLocationURL(
	// countryWikipedia.getLocationURL());
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar
	// + country.getIso31661().getAlpha2().toLowerCase()
	// + ".json");
	//
	// mapper.writeValue(json, country);
	//
	// String ext = country.getExternalLinks().getWikipediaLocationURL()
	// .split("/")[country.getExternalLinks()
	// .getWikipediaLocationURL().split("/").length - 1]
	// .split("[.]")[1];
	//
	// String fileName = country.getIso31661().getAlpha2()
	// .toLowerCase()
	// + "_location_" + ext + "." + ext;
	//
	// File source = new File(countryWikipedia.getLocationPath());
	//
	// File dest = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + fileName);
	//
	// copyFileUsingStream(source, dest);
	//
	// country.getFiles().setLocation(fileName);
	//
	// break;
	//
	// }
	// }
	// }
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + "countries.json");
	//
	// mapper.writeValue(json, countries);
	//
	// }

	// private void buildCountryStep5(ObjectMapper mapper, File countriesFile,
	// File countriesResultFile, File wikiCountriesFile,
	// List<CountryWikipediaAmerica> countriesWikipedia,
	// List<Country> countries) throws Exception {
	//
	// for (Country country : countries) {
	//
	// for (CountryWikipediaAmerica countryWikipedia : countriesWikipedia) {
	// if (country
	// .getIso31661()
	// .getAlpha3()
	// .trim()
	// .equalsIgnoreCase(
	// countryWikipedia.getIso3166Alpha3().trim())) {
	//
	// country.setRecognisedState(countryWikipedia
	// .getRecognisedState());
	// country.getExternalLinks().setWikipediaLocationURL(
	// countryWikipedia.getLocationURL());
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar
	// + country.getIso31661().getAlpha2().toLowerCase()
	// + ".json");
	//
	// mapper.writeValue(json, country);
	//
	// String ext = country.getExternalLinks().getWikipediaLocationURL()
	// .split("/")[country.getExternalLinks()
	// .getWikipediaLocationURL().split("/").length - 1]
	// .split("[.]")[1];
	//
	// String fileName = country.getIso31661().getAlpha2()
	// .toLowerCase()
	// + "_location_" + ext + "." + ext;
	//
	// File source = new File(countryWikipedia.getLocationPath());
	//
	// File dest = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + fileName);
	//
	// copyFileUsingStream(source, dest);
	//
	// country.getFiles().setLocation(fileName);
	//
	// break;
	//
	// }
	// }
	// }
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + "countries.json");
	//
	// mapper.writeValue(json, countries);
	//
	// }

	// private void buildCountryStep6(ObjectMapper mapper, File countriesFile,
	// File countriesResultFile, File wikiCountriesFile,
	// List<CountryWikipediaAsia> countriesWikipedia,
	// List<Country> countries) throws Exception {
	//
	// for (Country country : countries) {
	//
	// for (CountryWikipediaAsia countryWikipedia : countriesWikipedia) {
	// if (country
	// .getIso31661()
	// .getAlpha3()
	// .trim()
	// .equalsIgnoreCase(
	// countryWikipedia.getIso3166Alpha3().trim())) {
	//
	// country.setRecognisedState(countryWikipedia
	// .getRecognisedState());
	// country.getExternalLinks().setWikipediaLocationURL(
	// countryWikipedia.getLocationURL());
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar
	// + country.getIso31661().getAlpha2().toLowerCase()
	// + ".json");
	//
	// mapper.writeValue(json, country);
	//
	// String ext = country.getExternalLinks().getWikipediaLocationURL()
	// .split("/")[country.getExternalLinks()
	// .getWikipediaLocationURL().split("/").length - 1]
	// .split("[.]")[1];
	//
	// String fileName = country.getIso31661().getAlpha2()
	// .toLowerCase()
	// + "_location_" + ext + "." + ext;
	//
	// File source = new File(countryWikipedia.getLocationPath());
	//
	// File dest = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + fileName);
	//
	// copyFileUsingStream(source, dest);
	//
	// country.getFiles().setLocation(fileName);
	//
	// break;
	//
	// }
	// }
	// }
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + "countries.json");
	//
	// mapper.writeValue(json, countries);
	//
	// }

	// private void buildCountryStep7(ObjectMapper mapper, File countriesFile,
	// File countriesResultFile, File wikiCountriesFile,
	// List<CountryWikipediaEuropa> countriesWikipedia,
	// List<Country> countries) throws Exception {
	//
	// for (Country country : countries) {
	//
	// for (CountryWikipediaEuropa countryWikipedia : countriesWikipedia) {
	// if (country
	// .getIso31661()
	// .getAlpha3()
	// .trim()
	// .equalsIgnoreCase(
	// countryWikipedia.getIso3166Alpha3().trim())) {
	//
	// country.setRecognisedState(countryWikipedia
	// .getRecognisedState());
	// country.getExternalLinks().setWikipediaLocationURL(
	// countryWikipedia.getLocationURL());
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar
	// + country.getIso31661().getAlpha2().toLowerCase()
	// + ".json");
	//
	// mapper.writeValue(json, country);
	//
	// String ext = country.getExternalLinks().getWikipediaLocationURL()
	// .split("/")[country.getExternalLinks()
	// .getWikipediaLocationURL().split("/").length - 1]
	// .split("[.]")[1];
	//
	// String fileName = country.getIso31661().getAlpha2()
	// .toLowerCase()
	// + "_location_" + ext + "." + ext;
	//
	// File source = new File(countryWikipedia.getLocationPath());
	//
	// File dest = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + fileName);
	//
	// copyFileUsingStream(source, dest);
	//
	// country.getFiles().setLocation(fileName);
	//
	// break;
	//
	// }
	// }
	// }
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + "countries.json");
	//
	// mapper.writeValue(json, countries);
	//
	// }

	// private void buildCountryStep8(ObjectMapper mapper, File countriesFile,
	// File countriesResultFile, File wikiCountriesFile,
	// List<CountryWikipediaOceania> countriesWikipedia,
	// List<Country> countries) throws Exception {
	//
	// for (Country country : countries) {
	//
	// for (CountryWikipediaOceania countryWikipedia : countriesWikipedia) {
	// if (country
	// .getIso31661()
	// .getAlpha3()
	// .trim()
	// .equalsIgnoreCase(
	// countryWikipedia.getIso3166Alpha3().trim())) {
	//
	// country.setRecognisedState(countryWikipedia
	// .getRecognisedState());
	// country.getExternalLinks().setWikipediaLocationURL(
	// countryWikipedia.getLocationURL());
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar
	// + country.getIso31661().getAlpha2().toLowerCase()
	// + ".json");
	//
	// mapper.writeValue(json, country);
	//
	// File source = new File(countryWikipedia.getLocationPath());
	//
	// String ext = country.getExternalLinks().getWikipediaLocationURL()
	// .split("/")[country.getExternalLinks()
	// .getWikipediaLocationURL().split("/").length - 1]
	// .split("[.]")[1];
	//
	// String fileName = country.getIso31661().getAlpha2()
	// .toLowerCase()
	// + "_location_" + ext + "." + ext;
	//
	// File dest = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + fileName);
	//
	// copyFileUsingStream(source, dest);
	//
	// country.getFiles().setLocation(fileName);
	//
	// break;
	//
	// }
	// }
	// }
	//
	// File json = new File(countriesFile.getAbsolutePath()
	// + File.separatorChar + "countries.json");
	//
	// mapper.writeValue(json, countries);
	//
	// }

	private void buildCountryStep9(ObjectMapper mapper, File countriesFile,
			File countriesResultFile, File countriesFormGeonamesFile,
			List<CountryFormGeoname> countriesFormGeoname,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			for (CountryFormGeoname countryFormGeoname : countriesFormGeoname) {
				if (country
						.getIso31661()
						.getAlpha2()
						.trim()
						.equalsIgnoreCase(
								countryFormGeoname.getIso3166Alpha2().trim())) {

					country.getExternalLinks().setGeonamesEarthURL(
							countryFormGeoname.getGeonamesMapURL());
					country.getExternalLinks().setGeonamesFlagURL(
							countryFormGeoname.getFlagURL());
					country.getExternalLinks().setGeonamesMapURL(
							countryFormGeoname.getMapURL());
					if (country.getExternalLinks().getWikipediaURL() == null) {
						country.getExternalLinks().setWikipediaURL(
								countryFormGeoname.getWikipediaURL());
					} else if (countryFormGeoname.getWikipediaURL() != null
							&& country
									.getExternalLinks()
									.getWikipediaURL()
									.equalsIgnoreCase(
											countryFormGeoname
													.getWikipediaURL()) == false) {
						country.getExternalLinks().setWikipediaURLB(
								countryFormGeoname.getWikipediaURL());
					}

					// if(country.getLanguages().size() !=
					// countryFormGeoname.getLanguages().size()){
					// System.out.println("");
					// System.out.println(country);
					// System.out.println(country.getLanguages());
					// System.out.println(countryFormGeoname.getLanguages());
					// System.out.println("");
					// }

					for (String item : countryFormGeoname.getLanguages()) {
						boolean b = false;
						for (String item2 : country.getLanguages()) {
							if (item.equalsIgnoreCase(item2)) {
								b = true;
							}
						}
						if (b == false) {
							country.addLanguage(item);
						}
					}

					// -----------------------

					if (countryFormGeoname.getMapPath() != null) {
						String ext = country.getExternalLinks()
								.getGeonamesMapURL().split("/")[country
								.getExternalLinks().getGeonamesMapURL()
								.split("/").length - 1].split("[.]")[1];

						String fileName = country.getIso31661().getAlpha2()
								.toLowerCase()
								+ "_national_map_" + ext + "." + ext;

						File source = new File(countryFormGeoname.getMapPath());

						File dest = new File(countriesFile.getAbsolutePath()
								+ File.separatorChar + fileName);

						// System.out.println("source " + source);
						// System.out.println("dest " + dest);
						copyFileUsingStream(source, dest);

						country.getFiles().setGeonamesMap(fileName);

					}

					// -----------------------

					String ext = country.getExternalLinks()
							.getGeonamesFlagURL().split("/")[country
							.getExternalLinks().getGeonamesFlagURL().split("/").length - 1]
							.split("[.]")[1];

					String fileName = country.getIso31661().getAlpha2()
							.toLowerCase()
							+ "_national_flag_" + ext + "." + ext;

					File source = new File(countryFormGeoname.getFlagPath());

					File dest = new File(countriesFile.getAbsolutePath()
							+ File.separatorChar + fileName);

					copyFileUsingStream(source, dest);

					country.getFiles().setGeonamesFlag(fileName);

					// -----------------------

					File json = new File(countriesFile.getAbsolutePath()
							+ File.separatorChar
							+ country.getIso31661().getAlpha2().toLowerCase()
							+ ".json");

					mapper.writeValue(json, country);

					break;

				}
			}
		}

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + "countries.json");

		mapper.writeValue(json, countries);

	}

	private void buildCountryStep10(ObjectMapper mapper, File countriesFile,
			File countriesResultFile, File countriesFormGeonamesFile,
			List<CountryFormWikipedia> countriesFormWikipedia,
			List<Country> countries) throws Exception {

		for (Country country : countries) {

			for (CountryFormWikipedia countryFormWikipedia : countriesFormWikipedia) {
				if (country
						.getIso31661()
						.getAlpha2()
						.trim()
						.equalsIgnoreCase(
								countryFormWikipedia.getIso3166Alpha2().trim())) {

					country.getExternalLinks().setWikipediaFlagURL(
							countryFormWikipedia.getFlagURL());
					country.getExternalLinks().setWikipediaCoatOfArmsURL(
							countryFormWikipedia.getCoatOfArmsURL());
					country.getExternalLinks()
							.setWikipediaOrthographicProjectionURL(
									countryFormWikipedia
											.getOrthographicProjectionURL());

					// -----------------------
					if (countryFormWikipedia.getFlagPath() != null) {
						String ext = country.getExternalLinks()
								.getWikipediaFlagURL().split("/")[country
								.getExternalLinks().getWikipediaFlagURL()
								.split("/").length - 1].split("[.]")[1];

						String fileName = country.getIso31661().getAlpha2()
								.toLowerCase()
								+ "_national_flag_" + ext + "." + ext;

						File source = new File(
								countryFormWikipedia.getFlagPath());

						File dest = new File(countriesFile.getAbsolutePath()
								+ File.separatorChar + fileName);

						// System.out.println("source " + source);
						// System.out.println("dest " + dest);
						copyFileUsingStream(source, dest);

						country.getFiles().setWikipediaFlag(fileName);

					}
					// -----------------------
					if (countryFormWikipedia.getCoatOfArmsPath() != null) {
						String ext = country.getExternalLinks()
								.getWikipediaCoatOfArmsURL().split("/")[country
								.getExternalLinks().getWikipediaCoatOfArmsURL()
								.split("/").length - 1].split("[.]")[1];

						String fileName = country.getIso31661().getAlpha2()
								.toLowerCase()
								+ "_national_coat_" + ext + "." + ext;

						File source = new File(
								countryFormWikipedia.getCoatOfArmsPath());

						File dest = new File(countriesFile.getAbsolutePath()
								+ File.separatorChar + fileName);

						// System.out.println("source " + source);
						// System.out.println("dest " + dest);
						copyFileUsingStream(source, dest);

						country.getFiles().setWikipediaCoatOfArms(fileName);

					}
					// -----------------------
					
					if (countryFormWikipedia.getOrthographicProjectionPath() != null) {
						String ext = country.getExternalLinks()
								.getWikipediaOrthographicProjectionURL().split("/")[country
								.getExternalLinks().getWikipediaOrthographicProjectionURL()
								.split("/").length - 1].split("[.]")[1];

						String fileName = country.getIso31661().getAlpha2()
								.toLowerCase()
								+ "_orthographic_projection_" + ext + "." + ext;

						File source = new File(
								countryFormWikipedia.getOrthographicProjectionPath());

						File dest = new File(countriesFile.getAbsolutePath()
								+ File.separatorChar + fileName);

						// System.out.println("source " + source);
						// System.out.println("dest " + dest);
						copyFileUsingStream(source, dest);

						country.getFiles().setWikipediaOrthographicProjection(fileName);

					}
					// -----------------------

					File json = new File(countriesFile.getAbsolutePath()
							+ File.separatorChar
							+ country.getIso31661().getAlpha2().toLowerCase()
							+ ".json");

					mapper.writeValue(json, country);

					break;

				} 
			}
		}

		File json = new File(countriesFile.getAbsolutePath()
				+ File.separatorChar + "countries.json");

		mapper.writeValue(json, countries);

	}

	private boolean checkEquals(String v1, String v2) {
		if (v1 == null && v2 != null) {
			return false;
		}
		if (v1 != null && v2 == null) {
			return false;
		}
		if (v1 == null && v2 == null) {
			return true;
		}
		return v1.trim().equalsIgnoreCase(v2.trim());
	}

	private static void copyFileUsingStream(File source, File dest)
			throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

}
