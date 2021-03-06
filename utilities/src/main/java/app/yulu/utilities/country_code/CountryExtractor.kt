package app.yulu.utilities.country_code

import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.yulu.utilities.R
import app.yulu.utilities.background_task.AsyncT
import app.yulu.utilities.background_task.DoBackground
import app.yulu.utilities.single_live.SingleLiveEvent

/**
 *  Created by saurabhtripathi on 22/06/20
 */
open class CountryExtractor {

    companion object{
        fun instance() = CountryExtractor()
    }

    private val countryCodeDataModelArrayList: ArrayList<CountryCodeDataModel> = ArrayList()

    private val countryCodeDataModel: ArrayList<CountryCodeDataModel>
            = ArrayList()
    private val countryCodeDataModelLiveData : SingleLiveEvent<ArrayList<CountryCodeDataModel>>
            = SingleLiveEvent()
    private val filterCountryCodeDataModelLiveData : SingleLiveEvent<ArrayList<CountryCodeDataModel>>
            = SingleLiveEvent()

    private val currentAndAllCountryCodeDataModelLiveData : SingleLiveEvent<ArrayList<CountryCodeDataModel>>
            = SingleLiveEvent()

    private val currentCountryCodeDataModelLiveData: SingleLiveEvent<CountryCodeDataModel>
            = SingleLiveEvent()

    val filterCountryCodeDataModelLiveEvent : MutableLiveData<ArrayList<CountryCodeDataModel>> = MutableLiveData()



    private fun populateCountryData(){

        AsyncT(object : DoBackground{

            override fun onStart() {
                super.onStart()
                countryCodeDataModel.clear()
            }

            override fun onBackground() {
                super.onBackground()
                countryCodeDataModel.addAll(populateCountryDataRaw())
            }

            override fun onFinished() {
                super.onFinished()
                countryCodeDataModelLiveData.postValue(countryCodeDataModel)
            }

        }).execute()
    }

    private fun populateCountryDataFilter(context: Context, filterList: ArrayList<String>){

        val filterCountryCodeData : ArrayList<CountryCodeDataModel> = ArrayList()

        AsyncT(object : DoBackground{


            override fun onStart() {
                super.onStart()
                filterCountryCodeData.clear()
            }

            override fun onBackground() {
                super.onBackground()

                val countryCodeListData = populateCountryDataRaw()


                filterList.forEach {filterList->
                   filterCountryCodeData.addAll(countryCodeListData.filter { it.codeName == filterList})
                }

                val tm =
                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val countryCodeValue = tm.networkCountryIso



                val indexSearchedElement = filterCountryCodeData.indexOfFirst { countryCodeDataModel -> countryCodeDataModel.codeName == countryCodeValue }

                val toSearch = filterCountryCodeData[indexSearchedElement].also { it.isSelected = true }

                Log.i("APPDATA", toSearch.isSelected.toString())

                filterCountryCodeData.removeAt(indexSearchedElement)

                filterCountryCodeData.add(0, toSearch)


            }

            override fun onFinished() {
                filterCountryCodeDataModelLiveData.postValue(filterCountryCodeData)
            }
        }).execute()
    }

    private fun populateCountryDataRaw(): ArrayList<CountryCodeDataModel>{

        if(countryCodeDataModelArrayList.size == 241){
            return countryCodeDataModelArrayList
        }

        else{

            countryCodeDataModelArrayList.clear()

            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ad",
                    376,
                    "Andorra",
                    app.yulu.utilities.R.drawable.flag_andorra))

            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ae",
                    971,
                    "United Arab Emirates (UAE)",
                    app.yulu.utilities.R.drawable.flag_uae
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "af",
                    93,
                    "Afghanistan",
                    app.yulu.utilities.R.drawable.flag_afghanistan
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ag",
                    1,
                    "Antigua and Barbuda",
                    app.yulu.utilities.R.drawable.flag_antigua_and_barbuda
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ai",
                1,
                "Anguilla",
                app.yulu.utilities.R.drawable.flag_anguilla))

            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "al",
                    355,
                    "Albania",
                    app.yulu.utilities.R.drawable.flag_albania
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "am",
                    374,
                    "Armenia",
                    app.yulu.utilities.R.drawable.flag_armenia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ao",
                    244,
                    "Angola",
                    app.yulu.utilities.R.drawable.flag_angola
                )
            )

            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "aq",
                    672,
                    "Antarctica",
                    1
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ar",
                    54,
                    "Argentina",
                    1
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "as",
                    1,
                    "American Samoa",
                    app.yulu.utilities.R.drawable.flag_american_samoa
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "at",
                    43,
                    "Austria",
                    app.yulu.utilities.R.drawable.flag_austria
                )
            )

            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "au",
                    61,
                    "Australia",
                    app.yulu.utilities.R.drawable.flag_australia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "aw",
                    297,
                    "Aruba",
                    app.yulu.utilities.R.drawable.flag_aruba
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ax",
                    358,
                    "Åland Islands",
                    app.yulu.utilities.R.drawable.flag_aland
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "az",
                    994,
                    "Azerbaijan",
                    app.yulu.utilities.R.drawable.flag_azerbaijan
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ba",
                    387,
                    "Bosnia And Herzegovina",
                    app.yulu.utilities.R.drawable.flag_bosnia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bb",
                    1,
                    "Barbados",
                    app.yulu.utilities.R.drawable.flag_barbados
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bd",
                    880,
                    "Bangladesh",
                    app.yulu.utilities.R.drawable.flag_bangladesh
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "be",
                    32,
                    "Belgium",
                    app.yulu.utilities.R.drawable.flag_belgium
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bf",
                    226,
                    "Burkina Faso",
                    app.yulu.utilities.R.drawable.flag_burkina_faso
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bg",
                    359,
                    "Bulgaria",
                    app.yulu.utilities.R.drawable.flag_bulgaria
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bh",
                    973,
                    "Bahrain",
                    app.yulu.utilities.R.drawable.flag_bahrain
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bi",
                    257,
                    "Burundi",
                    app.yulu.utilities.R.drawable.flag_burundi
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bj",
                    229,
                    "Benin",
                    app.yulu.utilities.R.drawable.flag_benin
                )
            )

            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bl",
                    590,
                    "Saint Barthélemy",
                    app.yulu.utilities.R.drawable.flag_saint_barthelemy
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bm",
                    1,
                    "Bermuda",
                    app.yulu.utilities.R.drawable.flag_bermuda
                )
            )

            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bn",
                    673,
                    "Brunei Darussalam",
                    app.yulu.utilities.R.drawable.flag_brunei
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bo",
                    591,
                    "Bolivia, Plurinational State Of",
                    app.yulu.utilities.R.drawable.flag_bolivia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "br",
                    55,
                    "Brazil",
                    app.yulu.utilities.R.drawable.flag_brazil
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bs",
                    1,
                    "Bahamas",
                    app.yulu.utilities.R.drawable.flag_bahamas
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bt",
                    975,
                    "Bhutan",
                    app.yulu.utilities.R.drawable.flag_bhutan
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "bw",
                    267,
                    "Botswana",
                    app.yulu.utilities.R.drawable.flag_botswana
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("by", 375, "Belarus", app.yulu.utilities.R.drawable.flag_belarus))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("bz", 501, "Belize", app.yulu.utilities.R.drawable.flag_belize))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ca", 1, "Canada", app.yulu.utilities.R.drawable.flag_canada))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cc",
                    61,
                    "Cocos (keeling) Islands",
                    app.yulu.utilities.R.drawable.flag_cocos
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cd",
                    243,
                    "Congo, The Democratic Republic Of The",
                    app.yulu.utilities.R.drawable.flag_democratic_republic_of_the_congo
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cf",
                    236,
                    "Central African Republic",
                    app.yulu.utilities.R.drawable.flag_central_african_republic
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("cg", 242, "Congo", app.yulu.utilities.R.drawable.flag_republic_of_the_congo))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ch",
                    41,
                    "Switzerland",
                    app.yulu.utilities.R.drawable.flag_swaziland
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ci",
                    225,
                    "Côte D'ivoire",
                    app.yulu.utilities.R.drawable.flag_cote_divoire
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ck",
                    682,
                    "Cook Islands",
                    app.yulu.utilities.R.drawable.flag_cook_islands
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("cl", 56, "Chile", app.yulu.utilities.R.drawable.flag_chile))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cm",
                    237,
                    "Cameroon",
                    app.yulu.utilities.R.drawable.flag_cameroon
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("cn", 86, "China", app.yulu.utilities.R.drawable.flag_china))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("co", 57, "Colombia", app.yulu.utilities.R.drawable.flag_colombia))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cr",
                    506,
                    "Costa Rica",
                    app.yulu.utilities.R.drawable.flag_costa_rica
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("cu", 53, "Cuba", app.yulu.utilities.R.drawable.flag_cuba))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cv",
                    238,
                    "Cape Verde",
                    app.yulu.utilities.R.drawable.flag_cape_verde
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("cw", 599, "Curaçao", app.yulu.utilities.R.drawable.flag_curacao))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cx",
                    61,
                    "Christmas Island",
                    app.yulu.utilities.R.drawable.flag_christmas_island
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("cy", 357, "Cyprus", app.yulu.utilities.R.drawable.flag_cyprus))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "cz",
                    420,
                    "Czech Republic",
                    app.yulu.utilities.R.drawable.flag_czech_republic
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("de", 49, "Germany", app.yulu.utilities.R.drawable.flag_germany))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "dj",
                    253,
                    "Djibouti",
                    app.yulu.utilities.R.drawable.flag_djibouti
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("dk", 45, "Denmark", app.yulu.utilities.R.drawable.flag_denmark))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("dm", 1, "Dominica", app.yulu.utilities.R.drawable.flag_dominica))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "do",
                    1,
                    "Dominican Republic",
                    app.yulu.utilities.R.drawable.flag_dominican_republic
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("dz", 213, "Algeria", app.yulu.utilities.R.drawable.flag_algeria))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ec", 593, "Ecuador", app.yulu.utilities.R.drawable.flag_ecuador))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ee", 372, "Estonia", app.yulu.utilities.R.drawable.flag_estonia))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("eg", 20, "Egypt", app.yulu.utilities.R.drawable.flag_egypt))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("er", 291, "Eritrea", app.yulu.utilities.R.drawable.flag_eritrea))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("es", 34, "Spain", app.yulu.utilities.R.drawable.flag_spain))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "et",
                    251,
                    "Ethiopia",
                    app.yulu.utilities.R.drawable.flag_ethiopia
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("fi", 358, "Finland", app.yulu.utilities.R.drawable.flag_finland))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("fj", 679, "Fiji", app.yulu.utilities.R.drawable.flag_fiji))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "fk",
                    500,
                    "Falkland Islands (malvinas)",
                    app.yulu.utilities.R.drawable.flag_falkland_islands
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "fm",
                    691,
                    "Micronesia, Federated States Of",
                    app.yulu.utilities.R.drawable.flag_micronesia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "fo",
                    298,
                    "Faroe Islands",
                    app.yulu.utilities.R.drawable.flag_faroe_islands
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("fr", 33, "France", app.yulu.utilities.R.drawable.flag_france))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ga", 241, "Gabon", app.yulu.utilities.R.drawable.flag_gabon))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gb",
                    44,
                    "United Kingdom",
                    app.yulu.utilities.R.drawable.flag_united_kingdom
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("gd", 1, "Grenada", app.yulu.utilities.R.drawable.flag_grenada))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ge", 995, "Georgia", app.yulu.utilities.R.drawable.flag_georgia))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gf",
                    594,
                    "French Guyana",
                    app.yulu.utilities.R.drawable.flag_guyana
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("gh", 233, "Ghana", app.yulu.utilities.R.drawable.flag_ghana))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gi",
                    350,
                    "Gibraltar",
                    app.yulu.utilities.R.drawable.flag_gibraltar
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gl",
                    299,
                    "Greenland",
                    app.yulu.utilities.R.drawable.flag_greenland
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("gm", 220, "Gambia", app.yulu.utilities.R.drawable.flag_gambia))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("gn", 224, "Guinea", app.yulu.utilities.R.drawable.flag_guinea))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gp",
                    450,
                    "Guadeloupe",
                    app.yulu.utilities.R.drawable.flag_guadeloupe
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gq",
                    240,
                    "Equatorial Guinea",
                    app.yulu.utilities.R.drawable.flag_equatorial_guinea
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("gr", 30, "Greece", app.yulu.utilities.R.drawable.flag_greece))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gt",
                    502,
                    "Guatemala",
                    app.yulu.utilities.R.drawable.flag_guatemala
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("gu", 1, "Guam", app.yulu.utilities.R.drawable.flag_guam))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "gw",
                    245,
                    "Guinea-bissau",
                    app.yulu.utilities.R.drawable.flag_guinea_bissau
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("gy", 592, "Guyana", app.yulu.utilities.R.drawable.flag_guyana))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "hk",
                    852,
                    "Hong Kong",
                    app.yulu.utilities.R.drawable.flag_hong_kong
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "hn",
                    504,
                    "Honduras",
                    app.yulu.utilities.R.drawable.flag_honduras
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("hr", 385, "Croatia", app.yulu.utilities.R.drawable.flag_croatia))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ht", 509, "Haiti", app.yulu.utilities.R.drawable.flag_haiti))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("hu", 36, "Hungary", app.yulu.utilities.R.drawable.flag_hungary))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "id",
                    62,
                    "Indonesia",
                    app.yulu.utilities.R.drawable.flag_indonesia
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ie", 353, "Ireland", app.yulu.utilities.R.drawable.flag_ireland))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("il", 972, "Israel", app.yulu.utilities.R.drawable.flag_israel))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "im",
                    44,
                    "Isle Of Man",
                    app.yulu.utilities.R.drawable.flag_isleof_man
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("is", 354, "Iceland", app.yulu.utilities.R.drawable.flag_iceland))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("in", 91, "India", app.yulu.utilities.R.drawable.flag_india))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "io",
                    246,
                    "British Indian Ocean Territory",
                    app.yulu.utilities.R.drawable.flag_british_indian_ocean_territory
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("iq", 964, "Iraq", app.yulu.utilities.R.drawable.flag_iraq))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ir",
                    98,
                    "Iran, Islamic Republic Of",
                    app.yulu.utilities.R.drawable.flag_iran
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("it", 39, "Italy", app.yulu.utilities.R.drawable.flag_italy))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("je", 44, "Jersey ", app.yulu.utilities.R.drawable.flag_jersey))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("jm", 1, "Jamaica", app.yulu.utilities.R.drawable.flag_jamaica))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("jo", 962, "Jordan", app.yulu.utilities.R.drawable.flag_jordan))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("jp", 81, "Japan", app.yulu.utilities.R.drawable.flag_japan))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ke", 254, "Kenya", app.yulu.utilities.R.drawable.flag_kenya))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "kg",
                    996,
                    "Kyrgyzstan",
                    app.yulu.utilities.R.drawable.flag_kyrgyzstan
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "kh",
                    855,
                    "Cambodia",
                    app.yulu.utilities.R.drawable.flag_cambodia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ki",
                    686,
                    "Kiribati",
                    app.yulu.utilities.R.drawable.flag_kiribati
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("km", 269, "Comoros", app.yulu.utilities.R.drawable.flag_comoros))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "kn",
                    1,
                    "Saint Kitts and Nevis",
                    app.yulu.utilities.R.drawable.flag_saint_kitts_and_nevis
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "kp",
                    850,
                    "North Korea",
                    app.yulu.utilities.R.drawable.flag_north_korea
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "kr",
                    82,
                    "South Korea",
                    app.yulu.utilities.R.drawable.flag_south_korea
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("kw", 965, "Kuwait", app.yulu.utilities.R.drawable.flag_kuwait))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ky",
                    1,
                    "Cayman Islands",
                    app.yulu.utilities.R.drawable.flag_cayman_islands
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "kz",
                    7,
                    "Kazakhstan",
                    app.yulu.utilities.R.drawable.flag_kazakhstan
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "la",
                    856,
                    "Lao People's Democratic Republic",
                    app.yulu.utilities.R.drawable.flag_laos
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("lb", 961, "Lebanon", app.yulu.utilities.R.drawable.flag_lebanon))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "lc",
                    1,
                    "Saint Lucia",
                    app.yulu.utilities.R.drawable.flag_saint_lucia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "li",
                    423,
                    "Liechtenstein",
                    app.yulu.utilities.R.drawable.flag_liechtenstein
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "lk",
                    94,
                    "Sri Lanka",
                    app.yulu.utilities.R.drawable.flag_sri_lanka
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("lr", 231, "Liberia", app.yulu.utilities.R.drawable.flag_liberia))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ls", 266, "Lesotho", app.yulu.utilities.R.drawable.flag_lesotho))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "lt",
                    370,
                    "Lithuania",
                    app.yulu.utilities.R.drawable.flag_lithuania
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "lu",
                    352,
                    "Luxembourg",
                    app.yulu.utilities.R.drawable.flag_luxembourg
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("lv", 371, "Latvia", app.yulu.utilities.R.drawable.flag_latvia))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ly", 218, "Libya", app.yulu.utilities.R.drawable.flag_libya))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ma", 212, "Morocco", app.yulu.utilities.R.drawable.flag_morocco))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("mc", 377, "Monaco", app.yulu.utilities.R.drawable.flag_monaco))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "md",
                    373,
                    "Moldova, Republic Of",
                    app.yulu.utilities.R.drawable.flag_moldova
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "me",
                    382,
                    "Montenegro",
                    app.yulu.utilities.R.drawable.flag_of_montenegro
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mf",
                    590,
                    "Saint Martin",
                    app.yulu.utilities.R.drawable.flag_saint_martin
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mg",
                    261,
                    "Madagascar",
                    app.yulu.utilities.R.drawable.flag_madagascar
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mh",
                    692,
                    "Marshall Islands",
                    app.yulu.utilities.R.drawable.flag_marshall_islands
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mk",
                    389,
                    "Macedonia (FYROM)",
                    app.yulu.utilities.R.drawable.flag_macedonia
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ml", 223, "Mali", app.yulu.utilities.R.drawable.flag_mali))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("mm", 95, "Myanmar", app.yulu.utilities.R.drawable.flag_myanmar))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mn",
                    976,
                    "Mongolia",
                    app.yulu.utilities.R.drawable.flag_mongolia
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("mo", 853, "Macau", app.yulu.utilities.R.drawable.flag_macao))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mp",
                    1,
                    "Northern Mariana Islands",
                    app.yulu.utilities.R.drawable.flag_northern_mariana_islands
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mq",
                    596,
                    "Martinique",
                    app.yulu.utilities.R.drawable.flag_martinique
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mr",
                    222,
                    "Mauritania",
                    app.yulu.utilities.R.drawable.flag_mauritania
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ms",
                    1,
                    "Montserrat",
                    app.yulu.utilities.R.drawable.flag_montserrat
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("mt", 356, "Malta", app.yulu.utilities.R.drawable.flag_malta))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mu",
                    230,
                    "Mauritius",
                    app.yulu.utilities.R.drawable.flag_mauritius
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mv",
                    960,
                    "Maldives",
                    app.yulu.utilities.R.drawable.flag_maldives
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("mw", 265, "Malawi", app.yulu.utilities.R.drawable.flag_malawi))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("mx", 52, "Mexico", app.yulu.utilities.R.drawable.flag_mexico))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("my", 60, "Malaysia", app.yulu.utilities.R.drawable.flag_malaysia))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "mz",
                    258,
                    "Mozambique",
                    app.yulu.utilities.R.drawable.flag_mozambique
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("na", 264, "Namibia", 1))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "nc",
                    687,
                    "New Caledonia",
                    app.yulu.utilities.R.drawable.flag_new_caledonia
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ne", 227, "Niger", app.yulu.utilities.R.drawable.flag_niger))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "nf",
                    672,
                    "Norfolk Islands",
                    app.yulu.utilities.R.drawable.flag_norfolk_island
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ng", 234, "Nigeria", app.yulu.utilities.R.drawable.flag_nigeria))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ni",
                    505,
                    "Nicaragua",
                    app.yulu.utilities.R.drawable.flag_nicaragua
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "nl",
                    31,
                    "Netherlands",
                    app.yulu.utilities.R.drawable.flag_netherlands
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("no", 47, "Norway", app.yulu.utilities.R.drawable.flag_norway))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("np", 977, "Nepal", app.yulu.utilities.R.drawable.flag_nepal))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("nr", 674, "Nauru", app.yulu.utilities.R.drawable.flag_nauru))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("nu", 683, "Niue", app.yulu.utilities.R.drawable.flag_niue))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "nz",
                    64,
                    "New Zealand",
                    app.yulu.utilities.R.drawable.flag_new_zealand
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("om", 968, "Oman", app.yulu.utilities.R.drawable.flag_oman))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("pa", 507, "Panama", app.yulu.utilities.R.drawable.flag_panama))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("pe", 51, "Peru", app.yulu.utilities.R.drawable.flag_peru))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "pf",
                    689,
                    "French Polynesia",
                    app.yulu.utilities.R.drawable.flag_french_polynesia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "pg",
                    675,
                    "Papua New Guinea",
                    app.yulu.utilities.R.drawable.flag_papua_new_guinea
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ph",
                    63,
                    "Philippines",
                    app.yulu.utilities.R.drawable.flag_philippines
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("pk", 92, "Pakistan", app.yulu.utilities.R.drawable.flag_pakistan))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("pl", 48, "Poland", app.yulu.utilities.R.drawable.flag_poland))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "pm",
                    508,
                    "Saint Pierre And Miquelon",
                    app.yulu.utilities.R.drawable.flag_saint_pierre
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "pn",
                    870,
                    "Pitcairn Islands",
                    app.yulu.utilities.R.drawable.flag_pitcairn_islands
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "pr",
                    1,
                    "Puerto Rico",
                    app.yulu.utilities.R.drawable.flag_puerto_rico
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ps",
                    970,
                    "Palestine",
                    app.yulu.utilities.R.drawable.flag_palestine
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "pt",
                    351,
                    "Portugal",
                    app.yulu.utilities.R.drawable.flag_portugal
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("pw", 680, "Palau", app.yulu.utilities.R.drawable.flag_palau))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "py",
                    595,
                    "Paraguay",
                    app.yulu.utilities.R.drawable.flag_paraguay
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("qa", 974, "Qatar", app.yulu.utilities.R.drawable.flag_qatar))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("re", 262, "Réunion", app.yulu.utilities.R.drawable.flag_martinique)) //  no exact flag found
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ro", 40, "Romania", app.yulu.utilities.R.drawable.flag_romania))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("rs", 381, "Serbia",app.yulu.utilities.R.drawable.flag_serbia ))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ru",
                    7,
                    "Russian Federation",
                    app.yulu.utilities.R.drawable.flag_russian_federation
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("rw", 250, "Rwanda", app.yulu.utilities.R.drawable.flag_rwanda))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sa",
                    966,
                    "Saudi Arabia",
                    app.yulu.utilities.R.drawable.flag_saudi_arabia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sb",
                    677,
                    "Solomon Islands",
                    app.yulu.utilities.R.drawable.flag_soloman_islands
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sc",
                    248,
                    "Seychelles",
                    app.yulu.utilities.R.drawable.flag_seychelles
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("sd", 249, "Sudan", app.yulu.utilities.R.drawable.flag_sudan))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("se", 46, "Sweden", app.yulu.utilities.R.drawable.flag_sweden))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sg",
                    65,
                    "Singapore",
                    app.yulu.utilities.R.drawable.flag_singapore
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sh",
                    290,
                    "Saint Helena, Ascension And Tristan Da Cunha",
                    app.yulu.utilities.R.drawable.flag_saint_helena
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "si",
                    386,
                    "Slovenia",
                    app.yulu.utilities.R.drawable.flag_slovenia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sk",
                    421,
                    "Slovakia",
                    app.yulu.utilities.R.drawable.flag_slovakia
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sl",
                    232,
                    "Sierra Leone",
                    app.yulu.utilities.R.drawable.flag_sierra_leone
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sm",
                    378,
                    "San Marino",
                    app.yulu.utilities.R.drawable.flag_san_marino
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("sn", 221, "Senegal", app.yulu.utilities.R.drawable.flag_senegal))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("so", 252, "Somalia", app.yulu.utilities.R.drawable.flag_somalia))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sr",
                    597,
                    "Suriname",
                    app.yulu.utilities.R.drawable.flag_suriname
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ss",
                    211,
                    "South Sudan",
                    app.yulu.utilities.R.drawable.flag_south_sudan
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "st",
                    239,
                    "Sao Tome And Principe",
                    app.yulu.utilities.R.drawable.flag_sao_tome_and_principe
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sv",
                    503,
                    "El Salvador",
                    app.yulu.utilities.R.drawable.flag_el_salvador
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sx",
                    1,
                    "Sint Maarten",
                    app.yulu.utilities.R.drawable.flag_sint_maarten
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sy",
                    963,
                    "Syrian Arab Republic",
                    app.yulu.utilities.R.drawable.flag_syria
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "sz",
                    268,
                    "Swaziland",
                    app.yulu.utilities.R.drawable.flag_swaziland
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "tc",
                    1,
                    "Turks and Caicos Islands",
                    app.yulu.utilities.R.drawable.flag_turks_and_caicos_islands
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("td", 235, "Chad", app.yulu.utilities.R.drawable.flag_chad))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("tg", 228, "Togo", app.yulu.utilities.R.drawable.flag_togo))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("th", 66, "Thailand", app.yulu.utilities.R.drawable.flag_thailand))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "tj",
                    992,
                    "Tajikistan",
                    app.yulu.utilities.R.drawable.flag_tajikistan
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("tk", 690, "Tokelau", app.yulu.utilities.R.drawable.flag_tokelau))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "tl",
                    670,
                    "Timor-leste",
                    app.yulu.utilities.R.drawable.flag_timor_leste
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "tm",
                    993,
                    "Turkmenistan",
                    app.yulu.utilities.R.drawable.flag_turkmenistan
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("tn", 216, "Tunisia", app.yulu.utilities.R.drawable.flag_tunisia))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("to", 676, "Tonga", app.yulu.utilities.R.drawable.flag_tonga))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("tr", 90, "Turkey", app.yulu.utilities.R.drawable.flag_turkey))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "tt",
                    1,
                    "Trinidad &amp; Tobago",
                    app.yulu.utilities.R.drawable.flag_trinidad_and_tobago
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("tv", 688, "Tuvalu", app.yulu.utilities.R.drawable.flag_tuvalu))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("tw", 886, "Taiwan", app.yulu.utilities.R.drawable.flag_taiwan))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "tz",
                    255,
                    "Tanzania, United Republic Of",
                    app.yulu.utilities.R.drawable.flag_tanzania
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ua", 380, "Ukraine", app.yulu.utilities.R.drawable.flag_ukraine))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ug", 256, "Uganda", app.yulu.utilities.R.drawable.flag_uganda))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "us",
                    1,
                    "United States",
                    app.yulu.utilities.R.drawable.flag_united_kingdom
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("uy", 598, "Uruguay", app.yulu.utilities.R.drawable.flag_uruguay))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "uz",
                    998,
                    "Uzbekistan",
                    app.yulu.utilities.R.drawable.flag_uzbekistan
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "va",
                    379,
                    "Holy See (vatican City State)",
                    app.yulu.utilities.R.drawable.flag_vatican_city
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "vc",
                    1,
                    "Saint Vincent &amp; The Grenadines",
                    app.yulu.utilities.R.drawable.flag_saint_vicent_and_the_grenadines
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "ve",
                    58,
                    "Venezuela, Bolivarian Republic Of",
                    app.yulu.utilities.R.drawable.flag_venezuela
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "vg",
                    1,
                    "British Virgin Islands",
                    app.yulu.utilities.R.drawable.flag_british_virgin_islands
                )
            )
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "vi",
                    1,
                    "US Virgin Islands",
                    app.yulu.utilities.R.drawable.flag_us_virgin_islands
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("vn", 84, "Vietnam", app.yulu.utilities.R.drawable.flag_vietnam))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("vu", 678, "Vanuatu", app.yulu.utilities.R.drawable.flag_vanuatu))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "wf",
                    681,
                    "Wallis And Futuna",
                    app.yulu.utilities.R.drawable.flag_wallis_and_futuna
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ws", 685, "Samoa", app.yulu.utilities.R.drawable.flag_samoa))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("xk", 383, "Kosovo", app.yulu.utilities.R.drawable.flag_kosovo))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("ye", 967, "Yemen", app.yulu.utilities.R.drawable.flag_yemen))
            countryCodeDataModelArrayList.add(CountryCodeDataModel("yt", 262, "Mayotte", app.yulu.utilities.R.drawable.flag_martinique)) // no exact flag found
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "za",
                    27,
                    "South Africa",
                    app.yulu.utilities.R.drawable.flag_south_africa
                )
            )
            countryCodeDataModelArrayList.add(CountryCodeDataModel("zm", 260, "Zambia", app.yulu.utilities.R.drawable.flag_zambia))
            countryCodeDataModelArrayList.add(
                CountryCodeDataModel(
                    "zw",
                    263,
                    "Zimbabwe",
                    app.yulu.utilities.R.drawable.flag_zimbabwe
                )
            )
        }

        return countryCodeDataModelArrayList
    }

    private fun populateCurrentAndAllCountryData(context: Context){

        val currentAndAllCountryCodeData : ArrayList<CountryCodeDataModel> = ArrayList()

        AsyncT(object : DoBackground{

            override fun onStart() {
                super.onStart()
                currentAndAllCountryCodeData.clear()
            }


            override fun onBackground() {
                super.onBackground()

                val tm =
                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val countryCodeValue = tm.networkCountryIso

                val data = populateCountryDataRaw()

                currentAndAllCountryCodeData.addAll(data)

                val indexSearchedElement = currentAndAllCountryCodeData.indexOfFirst { countryCodeDataModel -> countryCodeDataModel.codeName == countryCodeValue }

                val toSearch = currentAndAllCountryCodeData[indexSearchedElement].also { it.isSelected = true }

                currentAndAllCountryCodeData.removeAt(indexSearchedElement)

                currentAndAllCountryCodeData.add(0, toSearch)


            }

            override fun onFinished() {
                currentAndAllCountryCodeDataModelLiveData.postValue(currentAndAllCountryCodeData)
            }
        }).execute()
    }

    private fun populateCurrentCountryData(context: Context){

        var currentCountryCodeData : CountryCodeDataModel? = null

        AsyncT(object : DoBackground{

            override fun onBackground() {
                super.onBackground()

                val tm =
                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val countryCodeValue = tm.networkCountryIso

                val data = populateCountryDataRaw()



                currentCountryCodeData = data.last { countryCodeDataModel -> countryCodeDataModel.codeName == countryCodeValue }
            }

            override fun onFinished() {
                currentCountryCodeDataModelLiveData.postValue(currentCountryCodeData)
            }
        }).execute()
    }

    fun getFilteredCountryData() = filterCountryCodeDataModelLiveEvent

    fun getCountryData(): SingleLiveEvent<ArrayList<CountryCodeDataModel>>
            = countryCodeDataModelLiveData.also {
        populateCountryData()
    }

    fun getFilterByCountryCode(context: Context, filterList: ArrayList<String>): SingleLiveEvent<ArrayList<CountryCodeDataModel>>
            = filterCountryCodeDataModelLiveData.also {
        populateCountryDataFilter(context, filterList)
    }

    fun getCurrentAndAllCountryData(context: Context) : SingleLiveEvent<ArrayList<CountryCodeDataModel>>
            = currentAndAllCountryCodeDataModelLiveData.also {
        populateCurrentAndAllCountryData(context)
    }

    fun getCurrentCountryData(context: Context) : SingleLiveEvent<CountryCodeDataModel> = currentCountryCodeDataModelLiveData.also {
        populateCurrentCountryData(context)
    }

    val filterCountryCodeData : ArrayList<CountryCodeDataModel> = ArrayList()

    val searchedCountryCodeData : ArrayList<CountryCodeDataModel> = ArrayList()

    fun setFilteredData(filterByCountryCode: ArrayList<String>?, context: Context, searchText: String?){
        if(searchText.isNullOrEmpty()){

            AsyncT(object : DoBackground{

                override fun onStart() {
                    super.onStart()
                    filterCountryCodeData.clear()
                }

                override fun onBackground() {
                    super.onBackground()

                    val countryCodeListData = populateCountryDataRaw()

                    if(!filterByCountryCode.isNullOrEmpty()){
                        filterByCountryCode.forEach {filterList->
                            filterCountryCodeData.addAll(countryCodeListData.filter { it.codeName == filterList})
                        }
                    }else{
                        filterCountryCodeData.addAll(countryCodeListData)
                    }

                    val tm =
                        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    val countryCodeValue = tm.networkCountryIso

                    val indexSearchedElement = filterCountryCodeData.indexOfFirst { countryCodeDataModel -> countryCodeDataModel.codeName == countryCodeValue }

                    val toSearch = filterCountryCodeData[indexSearchedElement].also { it.isSelected = true }

                    filterCountryCodeData.removeAt(indexSearchedElement)

                    filterCountryCodeData.add(0, toSearch)

                }

                override fun onFinished() {
                    filterCountryCodeDataModelLiveEvent.postValue(filterCountryCodeData)
                }
            }).execute()

        }
        else{
            if(filterCountryCodeData.isNotEmpty()){

                AsyncT(object : DoBackground{

                    override fun onStart() {
                        super.onStart()
                        searchedCountryCodeData.clear()
                    }

                    override fun onBackground() {
                        super.onBackground()

                        searchedCountryCodeData.addAll(filterCountryCodeData.filter { it.countryName.contains(searchText, true)
                                || it.phoneCodeWithPlus.contains(searchText, true)
                                || it.codeName.contains(searchText, true)
                        })

                    }

                    override fun onFinished() {
                        super.onFinished()

                        filterCountryCodeDataModelLiveEvent.postValue(searchedCountryCodeData)
                    }
                }).execute()

            }else{

                AsyncT(object : DoBackground{

                    override fun onStart() {
                        super.onStart()
                        filterCountryCodeData.clear()
                        searchedCountryCodeData.clear()
                    }

                    override fun onBackground() {
                        super.onBackground()

                        val countryCodeListData = populateCountryDataRaw()

                        if(!filterByCountryCode.isNullOrEmpty()){
                            filterByCountryCode.forEach {filterList->
                                filterCountryCodeData.addAll(countryCodeListData.filter { it.codeName == filterList})
                            }
                        }else{
                            filterCountryCodeData.addAll(countryCodeListData)
                        }

                        val tm =
                            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                        val countryCodeValue = tm.networkCountryIso

                        val indexSearchedElement = filterCountryCodeData.indexOfFirst { countryCodeDataModel -> countryCodeDataModel.codeName == countryCodeValue }

                        val toSearch = filterCountryCodeData[indexSearchedElement].also { it.isSelected = true }

                        filterCountryCodeData.removeAt(indexSearchedElement)

                        filterCountryCodeData.add(0, toSearch)

                        searchedCountryCodeData.addAll(filterCountryCodeData.filter { it.countryName.contains(searchText, true)
                                || it.phoneCodeWithPlus.contains(searchText, true)
                                || it.codeName.contains(searchText, true)
                        })

                    }

                    override fun onFinished() {
                        filterCountryCodeDataModelLiveEvent.postValue(searchedCountryCodeData)
                    }
                }).execute()
            }
        }
    }
}

