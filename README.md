# Jetpack Compose Country Code Picker

Jetpack Compose Country Code Picker

<h1>Android Jetpack compose based Country picker</h1>

[![](https://jitpack.io/v/ayeshdon/CountryCodePickerLibs.svg)](https://jitpack.io/#ayeshdon/CountryCodePickerLibs)



Supported Languages:

* English

<h3>Screenshot</h3>
<img src="https://github.com/ayeshdon/CountryCodePickerLibs/blob/master/screenshot/Screenshot_20231025_095146.png" width="230">
<img src="https://github.com/ayeshdon/CountryCodePickerLibs/blob/master/screenshot/Screenshot_20231025_095218.png" width="230">

<img src="https://github.com/ayeshdon/CountryCodePickerLibs/blob/master/screenshot/Screenshot_20231025_095234.png" width="230">
<img src="https://github.com/ayeshdon/CountryCodePickerLibs/blob/master/screenshot/Screenshot_20231025_095250.png" width="230">

<h3> Example </h3>

```kotlin
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

```  

## Gradle
<p>Add it in your root build.gradle at the end of repositories:</p>

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```
## Step 2. 
<p>Add the dependency</p>

```
dependencies {
	        implementation 'com.github.ayeshdon:CountryCodePickerLibs:<version_number>'
	}

```




## License

[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

    Copyright (C) 2023 Codewith-fun

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

