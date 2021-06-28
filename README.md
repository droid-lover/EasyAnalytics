# EasyAnalytics!
[![](https://jitpack.io/v/myJarvis/EasyAnalytics.svg)](https://jitpack.io/#myJarvis/EasyAnalytics)

an easy to use android library to let humna know how much internet-data their app is consuming. We can identify this as we want based on :
-  any Screen or Module.
-  any event.

EasyAnalytics library gives us the power to easily add logging events anywhere we want to check here how much Internet is consumed.

<br/>
To demo our EasyAnalytics lib we are using our old Code repo<a href="https://github.com/myJarvis/DaggerHiltApp"> News Sample App using HILT</a>

![Alt Text](https://github.com/myJarvis/EasyAnalytics/blob/master/images/analytics.gif)


# Gradle
Add this into your dependencies block.

```
implementation 'com.github.myJarvis:EasyAnalytics:0.0.1'
```

## Wiki
### General usage

### Step1- register ```EasyAnanlytics``` inside your Application class.
```
@HiltAndroidApp
class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        //initialize EasyAnalytics
        EasyAnalytics.register(this)
    }
  }
```

### Step2- To get information about Internet data consumed in any screen inside your application.

Call ```EasyAnanlytics``` method like this -
```
EasyAnalytics.getAppUsageInfo(context,"getNews_API_call_success")
```

### Params
- first parameter is just a context
- and second parameter is The screen name or any event name which happened when we want to track the internet consumption.
