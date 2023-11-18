<h1 align="center">SnsLogin</h1>

<p align="center">
<img src="https://img.shields.io/badge/-Android-FA7343?style=flat&logo=Android"/>
<img alt="License" src="https://img.shields.io/badge/License-MIT-blue.svg"/>
<img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/>
<img alt="Version" src="https://jitpack.io/v/bsw112/SnsLogin.svg"/>
</p>

<p align="center">
Make Android OAuth easier to use
</p>

# Feature
- Google login
- Twitter login
- Kakao login
- Naver login

# Before we start
**You need to set up all setting for each OAuth provider you use.**
For example, if you want to use google OAuth, you must create Firebase app and set all setting.
> *For now, google login & twitter login uses Firebase Authentication.*

# Install

1. Add below in your project level build.gradle
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
			//for kakao
			maven { url 'https://devrepo.kakao.com/nexus/content/groups/public/' }
		}
	}
````

Or in your setting.gradle

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url "https://jitpack.io" }
	//for kakao
	maven { url 'https://devrepo.kakao.com/nexus/content/groups/public/' }
    }
}

```

2. Add below in your app level build.gralde
```gradle
	dependencies {
	    implementation com.github.bso112.SnsLogin:kakao:x.y.z
	    implementation com.github.bso112.SnsLogin:naver:x.y.z
	    implementation com.github.bso112.SnsLogin:google:x.y.z
	    implementation com.github.bso112.SnsLogin:twitter:x.y.z
	}
```

<img alt="Version" src="https://jitpack.io/v/bsw112/SnsLogin.svg"/>

# How to use

### Google Login
```gradle

// project level build.gradle
plugins {
    id "com.google.gms.google-services" version "x.y.z" apply false
}

// app level build.gralde
plugins {
    id 'com.google.gms.google-services'
}
```

```kotlin
class MainActivity : AppCompatActivity() {

    private val googleLoginLauncher = registerForGoogleLoginResult(
        onSuccess = {
            //you can get firebaseUserData from $it
            /**
             * data class FirebaseUserData(
             *     val idToken : String,
             *     val email : String,
             *     val displayName: String,
             *     val phoneNumber: String,
             *     val photoUrl : String,
             *     val isEmailVerified : Boolean,
             *     val isAnonymous : Boolean,
             *     val providerId : String,
             *     val uid: String
             * )
             */
            Toast.makeText(this, "success $it", Toast.LENGTH_SHORT).show()
            Log.d("mylog", "success: $it")
        },
        onFailure = {
            Toast.makeText(this, "failure $it", Toast.LENGTH_SHORT).show()
            Log.d("mylog", "failure: $it")
        },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleLoginLauncher.launch(getString(R.string.default_web_client_id))
    }

}
```

## License

```
This library is licensed under the MIT License:

Copyright (c) 2022 bsw112

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
```


