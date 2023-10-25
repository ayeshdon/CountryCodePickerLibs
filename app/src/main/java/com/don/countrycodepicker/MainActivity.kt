package com.don.countrycodepicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.don.ccpicker.CountryPickerTextField
import com.don.countrycodepicker.ui.theme.CountryCodePickerLibsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryCodePickerLibsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val phoneNumber = remember { mutableStateOf(TextFieldValue("")) }
                    Box() {
                        CountryPickerTextField(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                            onItemSelected = {
                                Log.e("MAIN", "COUNTRY NAME ${it.countryName}")
                                Log.e("MAIN", "COUNTRY phoneNumber ${phoneNumber.value.text}")
                            },
                            isFlagShow = true,
                            title = "Select Country",
                            titleModifier = Modifier.fillMaxWidth(),
                            titleStyle = MaterialTheme.typography.headlineLarge,
                            phoneNumber = phoneNumber,
                            textBoxHint = "Enter Phone Number",
                            shape = RoundedCornerShape(8.dp),
                            textFieldModifier = Modifier.fillMaxWidth()
                                .background(Color.Transparent),
                        )
                    }
                }
            }
        }
    }
}
