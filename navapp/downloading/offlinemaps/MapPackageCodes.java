package com.com.navapp.downloading.offlinemaps;

import java.util.HashMap;

public class MapPackageCodes
{
  private static final MapPackageCodes ourInstance = new MapPackageCodes();
  private final HashMap<String, String> countryCodesMap = new HashMap();
  
  private MapPackageCodes()
  {
    countryCodesMap.put("TTO", "Trinidad and Tobago");
    countryCodesMap.put("LTU", "Lithuania");
    countryCodesMap.put("LKA", "Sri Lanka");
    countryCodesMap.put("SOM", "Somalia");
    countryCodesMap.put("QAT", "Qatar");
    countryCodesMap.put("DOM", "Dominican Republic");
    countryCodesMap.put("LBN", "Lebanon");
    countryCodesMap.put("BHR", "Bahrain");
    countryCodesMap.put("ABW", "Aruba");
    countryCodesMap.put("ISR", "Israel");
    countryCodesMap.put("MEX", "Mexico");
    countryCodesMap.put("TCD", "Chad");
    countryCodesMap.put("MYS", "Malaysia");
    countryCodesMap.put("CRI", "Costa Rica");
    countryCodesMap.put("LVA", "Latvia");
    countryCodesMap.put("GIN", "Guinea");
    countryCodesMap.put("KEN", "Kenya");
    countryCodesMap.put("BEN", "Benin");
    countryCodesMap.put("GEO", "Georgia");
    countryCodesMap.put("AZE", "Azerbaijan");
    countryCodesMap.put("MKD", "Macedonia [FYROM]");
    countryCodesMap.put("NOR", "Norway");
    countryCodesMap.put("SVN", "Slovenia");
    countryCodesMap.put("AND", "Andorra");
    countryCodesMap.put("MDV", "Maldives");
    countryCodesMap.put("RWA", "Rwanda");
    countryCodesMap.put("HUN", "Hungary");
    countryCodesMap.put("IRQ", "Iraq");
    countryCodesMap.put("BIH", "Bosnia and Herzegovina");
    countryCodesMap.put("BGR", "Bulgaria");
    countryCodesMap.put("CMR", "Cameroon");
    countryCodesMap.put("BFA", "Burkina Faso");
    countryCodesMap.put("SLV", "El Salvador");
    countryCodesMap.put("AGO", "Angola");
    countryCodesMap.put("MYT", "Mayotte");
    countryCodesMap.put("SRB", "Serbia");
    countryCodesMap.put("NAM", "Namibia");
    countryCodesMap.put("EST", "Estonia");
    countryCodesMap.put("BEL", "Belgium");
    countryCodesMap.put("NIC", "Nicaragua");
    countryCodesMap.put("MOZ", "Mozambique");
    countryCodesMap.put("GMB", "Gambia");
    countryCodesMap.put("TUR", "Turkey");
    countryCodesMap.put("TZA", "Tanzania");
    countryCodesMap.put("PHL", "Philippines");
    countryCodesMap.put("CZE", "Czech Republic");
    countryCodesMap.put("PRT", "Portugal");
    countryCodesMap.put("IRL", "Ireland");
    countryCodesMap.put("ARM", "Armenia");
    countryCodesMap.put("CHL", "Chile");
    countryCodesMap.put("ARE", "United Arab Emirates");
    countryCodesMap.put("SWE", "Sweden");
    countryCodesMap.put("MUS", "Mauritius");
    countryCodesMap.put("SLE", "Sierra Leone");
    countryCodesMap.put("KNA", "Saint Kitts and Nevis");
    countryCodesMap.put("DZA", "Algeria");
    countryCodesMap.put("ALB", "Albania");
    countryCodesMap.put("EGY", "Egypt");
    countryCodesMap.put("BRA", "Brazil");
    countryCodesMap.put("VGB", "Virgin Islands");
    countryCodesMap.put("VAT", "Vatican City");
    countryCodesMap.put("STP", "Sao Tome and Principe");
    countryCodesMap.put("JOR", "Jordan");
    countryCodesMap.put("MDA", "Moldova");
    countryCodesMap.put("DEU", "Germany");
    countryCodesMap.put("SEN", "Senegal");
    countryCodesMap.put("COD", "Congo (Zaire)");
    countryCodesMap.put("CAN", "Canada");
    countryCodesMap.put("BHS", "Bahamas");
    countryCodesMap.put("SMR", "San Marino");
    countryCodesMap.put("GUY", "Guyana");
    countryCodesMap.put("NPL", "Nepal");
    countryCodesMap.put("RUS", "Russia");
    countryCodesMap.put("BGD", "Bangladesh");
    countryCodesMap.put("LIE", "Liechtenstein");
    countryCodesMap.put("CIV", "Ivory Coast");
    countryCodesMap.put("ARG", "Argentina");
    countryCodesMap.put("BLR", "Belarus");
    countryCodesMap.put("MAR", "Morocco");
    countryCodesMap.put("KWT", "Kuwait");
    countryCodesMap.put("ITA", "Italy");
    countryCodesMap.put("AUT", "Austria");
    countryCodesMap.put("MNE", "Montenegro");
    countryCodesMap.put("BOL", "Bolivia");
    countryCodesMap.put("GAB", "Gabon");
    countryCodesMap.put("LUX", "Luxembourg");
    countryCodesMap.put("PER", "Peru");
    countryCodesMap.put("GTM", "Guatemala");
    countryCodesMap.put("SAU", "Saudi Arabia");
    countryCodesMap.put("GBR", "United Kingdom");
    countryCodesMap.put("SUR", "Suriname");
    countryCodesMap.put("NER", "Niger");
    countryCodesMap.put("DJI", "Djibouti");
    countryCodesMap.put("MRT", "Mauritania");
    countryCodesMap.put("GNB", "Guinea-Bissau");
    countryCodesMap.put("THA", "Thailand");
    countryCodesMap.put("FRA", "France");
    countryCodesMap.put("COG", "Congo (Republic)");
    countryCodesMap.put("BDI", "Burundi");
    countryCodesMap.put("NLD", "Netherlands");
    countryCodesMap.put("POL", "Poland");
    countryCodesMap.put("FIN", "Finland");
    countryCodesMap.put("CHE", "Switzerland");
    countryCodesMap.put("COM", "Comoros");
    countryCodesMap.put("ROU", "Romania");
    countryCodesMap.put("UKR", "Ukraine");
    countryCodesMap.put("TGO", "Togo");
    countryCodesMap.put("VIR", "Virgin Islands");
    countryCodesMap.put("ECU", "Ecuador");
    countryCodesMap.put("VCT", "Saint Vincent and the Grenadines");
    countryCodesMap.put("BLZ", "Belize");
    countryCodesMap.put("OMN", "Oman");
    countryCodesMap.put("ISL", "Iceland");
    countryCodesMap.put("MLT", "Malta");
    countryCodesMap.put("CPV", "Cape Verde");
    countryCodesMap.put("GIB", "Gibraltar");
    countryCodesMap.put("SWZ", "Swaziland");
    countryCodesMap.put("MWI", "Malawi");
    countryCodesMap.put("KAZ", "Kazakhstan");
    countryCodesMap.put("GRC", "Greece");
    countryCodesMap.put("HKG", "Hong Kong and Macau");
    countryCodesMap.put("TWN", "Taiwan");
    countryCodesMap.put("BRN", "Brunei");
    countryCodesMap.put("UGA", "Uganda");
    countryCodesMap.put("DNK", "Denmark");
    countryCodesMap.put("SGP", "Singapore");
    countryCodesMap.put("BWA", "Botswana");
    countryCodesMap.put("PRY", "Paraguay");
    countryCodesMap.put("NGA", "Nigeria");
    countryCodesMap.put("MLI", "Mali");
    countryCodesMap.put("LSO", "Lesotho");
    countryCodesMap.put("ZMB", "Zambia");
    countryCodesMap.put("VNM", "Vietnam");
    countryCodesMap.put("YEM", "Yemen");
    countryCodesMap.put("JAM", "Jamaica");
    countryCodesMap.put("LBR", "Liberia");
    countryCodesMap.put("BMU", "Bermuda");
    countryCodesMap.put("SHN", "Saint Helena");
    countryCodesMap.put("CAF", "Central African Republic");
    countryCodesMap.put("SVK", "Slovakia");
    countryCodesMap.put("IDN", "Indonesia");
    countryCodesMap.put("IND", "India");
    countryCodesMap.put("CYP", "Cyprus");
    countryCodesMap.put("ZAF", "South Africa");
    countryCodesMap.put("MCO", "Monaco");
    countryCodesMap.put("GNQ", "Equatorial Guinea");
    countryCodesMap.put("CUB", "Cuba");
    countryCodesMap.put("ESP", "Spain");
    countryCodesMap.put("HND", "Honduras");
    countryCodesMap.put("GHA", "Ghana");
    countryCodesMap.put("MAC", "Hong Kong and Macau");
    countryCodesMap.put("ZWE", "Zimbabwe");
    countryCodesMap.put("VEN", "Venezuela");
    countryCodesMap.put("USA", "USA");
    countryCodesMap.put("LBY", "Libya");
    countryCodesMap.put("HRV", "Croatia");
    countryCodesMap.put("URY", "Uruguay");
    countryCodesMap.put("ETH", "Ethiopia");
    countryCodesMap.put("TUN", "Tunisia");
    countryCodesMap.put("ERI", "Eritrea");
    countryCodesMap.put("PAN", "Panama");
    countryCodesMap.put("GGY", "Guernsey");
    countryCodesMap.put("SYC", "Seychelles");
    countryCodesMap.put("COL", "Colombia");
    countryCodesMap.put("CYM", "Cayman Islands");
    countryCodesMap.put("MDG", "Madagascar");
  }
  
  public static MapPackageCodes getInstance()
  {
    return ourInstance;
  }
  
  public String getCountryEnglishName(String paramString)
  {
    paramString = (String)countryCodesMap.get(paramString);
    if (paramString != null) {
      return paramString;
    }
    return "";
  }
}
