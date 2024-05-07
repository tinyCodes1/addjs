# addjs [![](https://jitpack.io/v/tinyCodes1/addjs.svg)](https://jitpack.io/#tinyCodes1/addjs)
---
@tinyCodes1/addjs is simple android module to enhance functionality of android webview.
  
  
## Usage (Gradle kotlin DSL):
---

1. Add maven("https://jitpack.io") to settings.gradle.kts,
   so it looks something like this :

```
dependencyResolutionManagement {
   repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
   repositories {
      mavenCentral()
      maven("https://jitpack.io")
   }
}
```

2. Add dependencies in build.gradle.kts (Module:app) eg.

```
dependencies {
   implementation("com.github.tinyCodes1:addjs:v1.8")
}
```

3. Add as interface in android webview

```java
webviewName.addJavascriptInterface(WebInterface(this), "Android");
```

4. Functions will be added to webpage javascript. Now functions can be used as Android prefix. eg.

```java
Android.show("Hello world");
```
  
  
## List of available functions:
---

- show ... display toast message
- selectDir ... prompt user to select folder
- listFiles ... returns list of files
- getFile ... returns file object with parameters name, type, uri-string, base64data-string
- openWith ... open file with compatible app
- saveData ...save string
- getData ... returns saved string
  
