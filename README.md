[![Release](https://jitpack.io/v/evilthreads669966/easyshells.svg)](https://jitpack.io/#evilthreads669966/easyshells)&nbsp;&nbsp;[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=plastic)](https://android-arsenal.com/api?level=26)
# Easy Shells
### Execute system commands with responses
#### I had this written awhile ago and didn't want to throw it away. I'll probably work on turning this into more of a reverse shell library as time goes on with networking features.
### User Instructions
1. Add the maven repository to your project's build.gradle file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add the dependency to your app's build.gradle file
```gradle
dependencies {
    implementation 'com.github.evilthreads669966:easyshells:beta-1.1'
}
```
3. Use the evade ktx function inside of any android context.
```kotlin
val response = async { shell("uptime") }
Toast.makeText(this@MainActivity, response.await(), Toast.LENGTH_LONG).show()
```
## License
```
Copyright 2019 Chris Basinger

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
limitations under the License.
```
