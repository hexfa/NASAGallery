# NASAGallery
## [![N|Solid][logo]][hexfa] _Nasa App Gallery_
-----------------------------
* - * - *  * - * - * 
-----------------------------
## Detail
This is a piece of app gallery software for displaying photos with details through the "nasa.gov" site API.

## Lets Start

Clone this repository and import it into your **Android IDE**
```bash
https://github.com/hexfa/NASAGallery.git
```

## Resources
- #### Documents
  **`For looking at the code document of this project, you could generate it from the`** [Dokka section][dokka].
  
  **`Also, you could see the last documentation of the project on this link`** [Documentation][doc]

- #### API
  ```bash
  https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2021-01-01&end_date=2021-03-12
  ```

- #### Structure
  **_MVVM_**

## Configuration
    
- ### Dokka
  - _I recommend Html document type_
  - _You have to use the JAVA JDK 18 and set it in your JAVA_HOME environment. after putting it in the system needs a restart_

    - ###### Dokka terminal commands
      | Type | Syntax |
      | ------ | ------ |
      | Html | ./gradlew.bat dokkaHtml |
      | Java doc | ./gradlew.bat dokkaJavadoc |
      | Gfm | ./gradlew.bat dokkaGfm |

    - ###### Output Location
      `app -> build -> dokka`

### Keystore
- Location of the keystore is in **root** directory(**nasa.jks**).
- Alias= nasa
- Key password= nasa2022 
- Key store password= nasa2022

### Publish App
- Don't forget to update **versionCode** and **versionName** before uploading **.aab** file on Store.

- [APK link][link]

## Demo
  ### Night
  <table>
    <tr>
  <th><img src="https://hexfa.com/my-git-doc/nasa/images/1.jpg" width="200" height="400" /></th>
  <th><img src="https://hexfa.com/my-git-doc/nasa/images/2.jpg" width="200" height="400" /></th>
    </tr>
  </table>
  
  ### Day
  <table>
    <tr>
  <th><img src="https://hexfa.com/my-git-doc/nasa/images/3.jpg" width="200" height="400" /></th>
  <th><img src="https://hexfa.com/my-git-doc/nasa/images/4.jpg" width="200" height="400" /></th>
    </tr>
  </table>

## \\|/ `Technologies` \\|/
- Data Binding
- Retrofit
- RX2
- Hilt
- Navigation Component
- Live Data
- Glide
- Dokka
- Web view


[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen.)

   [logo]: <https://upload.wikimedia.org/wikipedia/commons/5/57/Iconoir_github-outline.svg> 
   [hexfa]: <https://github.com/hexfa>
   [link]: <https://hexfa.com/my-git-doc/nasa/apk/nasa.apk>
   [dokka]: <#dokka>
   [doc]: <https://hexfa.com/my-git-doc/nasa/doc>
