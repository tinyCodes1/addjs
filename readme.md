# addjs [![](https://jitpack.io/v/tinyCodes1/addjs.svg)](https://jitpack.io/#tinyCodes1/addjs)
---

@tinyCodes1/addjs is simple android module to enhance functionality of android webview.

## Usage (Gradle Kotlin DSL):
---

1. Add maven("https://jitpack.io") to settings.gradle.kts,
   so code block looks something like this :

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
   implementation("com.github.tinyCodes1:addjs:v1.11")
}
```

3. Add as interface in android webview

```java
webViewName.addJavascriptInterface(WebInterface(this),"Android");
```

4. Functions will be added to webpage javascript. Now functions can be used as Android prefix in js/ts file. eg.

```java
Android.show("Hello world");
```

5. If you are using typescript you may like to add namespace declaration. eg.

```ts 
declare namespace Android {
    const listFiles : ( uriString : string , extension?: string , recursive?: boolean , returnKey?: string ) => string ;
    const selectDirectory :(key: string) => void ;
    const getFile:(uriStr: string) => string ;
    const saveData:(key: string, value: string) => void ;
    const openWith:(uriString: string) => void ;
    const show:(toast: string) => void ;
    const getData:(key: string) => string ;
}
```

## List of available functions:
---

- show ... display toast message
- selectDir ... prompt user to select folder
- listFiles ... to get list of files from uri
- getFile ... to get file object with parameters name, type, uri-string, base64data-string
- openWith ... open file with compatible app
- saveData ...save string
- getData ... to get saved string

further details of functions can be found in typescript namespace in previous section.

## Sample app:
---

You may find sample app here.  
**PDF Search:**  [Google Play](https://play.google.com/store/apps/details?id=com.tinycode.pdfsearch)
