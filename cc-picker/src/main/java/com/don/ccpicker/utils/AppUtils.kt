package com.don.ccpicker.utils

import android.content.Context
import com.don.ccpicker.model.CountryItem
import org.json.JSONArray
import java.io.InputStream

object AppUtils {
    @JvmStatic
    fun readJSONFromAsset(context: Context): List<CountryItem>? {
        var json: String?
        val countryList: ArrayList<CountryItem> = ArrayList()
        val inputStream: InputStream = context.assets.open("country_list.json")
        json = inputStream.bufferedReader().use { it.readText() }

        val jsonArray = JSONArray(json)
        for (i in 0 until jsonArray.length()) {
            var jonOgj = jsonArray.getJSONObject(i)
            countryList.add(
                CountryItem(
                    countryName = jonOgj.getString("name"),
                    countryFlag = jonOgj.getString("flag"),
                    countryCode = jonOgj.getString("code"),
                    countryPhoneCode = jonOgj.getString("dial_code"),

                ),
            )
        }
        return countryList
    }
}
