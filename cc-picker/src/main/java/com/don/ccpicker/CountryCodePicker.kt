package com.don.ccpicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.don.ccpicker.model.CountryItem
import com.don.ccpicker.utils.AppUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerTextField(
    defaultCountryCode: String? = "",
    textBoxHint: String = "",
    onItemSelected: (country: CountryItem) -> Unit,
    countryNameTextSize: Float = 16f,
    countryCodeTextSize: Float = 16f,
    countryFlagSize: Float = 24f,
    isFlagShow: Boolean = true,
    title: String = "",
    titleModifier: Modifier = Modifier,
    titleStyle: TextStyle = LocalTextStyle.current,
    phoneNumber: MutableState<TextFieldValue>,
    textFieldColors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    shape: Shape = OutlinedTextFieldDefaults.shape,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
) {
    val sheetState = remember { SheetState(skipHiddenState = false, skipPartiallyExpanded = false) }
    val scaffoldState = rememberBottomSheetScaffoldState(sheetState)
    val scope = rememberCoroutineScope()
    var selectedCountry = remember { mutableStateOf<CountryItem?>(null) }

    val countryList = AppUtils.readJSONFromAsset(context = LocalContext.current)

    if (defaultCountryCode.isNullOrEmpty()) {
        selectedCountry.value = (countryList?.get(0))
    } else {
        for (country in countryList!!) {
            if (country.countryCode.equals(defaultCountryCode, true)) {
                selectedCountry.value = country
                break
            }
        }
    }

    BottomSheetScaffold(
        modifier = modifier,
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column() {
                Text(
                    "$title",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    style = titleStyle,
                    modifier = titleModifier,
                )
                LazyColumn(
                    modifier = Modifier.heightIn(min = 20.dp, max = 300.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    countryList?.let { country ->
                        items(country.size) {
                            Row(
                                Modifier.padding(top = 0.dp).clickable(
                                    onClick = {
                                        selectedCountry.value = country[it]
                                        onItemSelected(country[it])
                                        scope.launch {
                                            scaffoldState.bottomSheetState.hide()
                                        }
                                    },
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                if (isFlagShow) {
                                    Text(
                                        modifier = Modifier
                                            .padding(10.dp),
                                        text = "${country[it].countryFlag}",
                                        fontSize = countryFlagSize.sp,
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .padding(20.dp).weight(1f),
                                    text = "${country[it].countryName}",
                                    fontSize = countryNameTextSize.sp,
                                )

                                Text(
                                    "${country[it].countryPhoneCode}",
                                    fontSize = countryCodeTextSize.sp,
                                )
                            }
                            Divider(
                                color = Color.LightGray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(0.4.dp),
                            )
                        }
                    }
                }
            }
        },
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            OutlinedTextField(
                modifier = textFieldModifier,
                singleLine = true,
                shape = shape,
                colors = textFieldColors,
                leadingIcon = {
                    Row(
                        modifier = Modifier.clickable(onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(10.dp),
                            text = "${selectedCountry?.value?.countryFlag}",
                            fontSize = countryFlagSize.sp,
                        )
                        Text(
                            "${selectedCountry?.value?.countryPhoneCode}",
                            fontSize = countryCodeTextSize.sp,
                        )
                    }
                },
//                modifier = Modifier.fillMaxWidth().background(Color.Transparent),
                value = phoneNumber.value,
                onValueChange = { phoneNumber.value = it },
                label = { Text(text = textBoxHint) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
        }
    }
}
