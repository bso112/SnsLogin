<h1 align="center">SnsLogin</h1>

<p align="center">
<img src="https://img.shields.io/badge/-Android-FA7343?style=flat&logo=Android"/>
<img alt="License" src="https://img.shields.io/badge/License-MIT-blue.svg"/>
<img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/>
</p>

# Before we start
**You need to set up all setting for each OAuth provider you use.**
For example, if you want to use google OAuth, you must create Firebase app and set all setting.
> *For now, google login & twitter login uses Firebase Authentication.*


# How to use
1. Initialize SnsLogin with OAuth providers you want.

```kotlin
class SnsLoginApp : Application() {

    override fun onCreate() {
        super.onCreate()
        SnsLogin
            .withGoogle(this)
            .withTwitter(this)
            .withKakao(this, BuildConfig.KAKAO_APP_KEY)
            .withNaver(this, clientId = "", clientSecret = "", clientName = "")
    }
}
```

Don't forget to specify **android:name** on <application> tag in your AndoroidManifest.xml.

2. Use **{AuthProvider}Login()** function to login.

```kotlin
 SnsLogin.kakaoLogin(this, onSuccess = {
            onSuccess(it)
        }, onFailure = {
            onFailure(it.message ?: "")
            SnsLogin.kakaoLogout()
        })
````


## License

```
This library is licensed under the MIT License:

Copyright (c) 2021 Petfriends

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


