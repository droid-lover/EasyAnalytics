# EasyAnalytics!
[![](https://jitpack.io/v/myJarvis/EasyAnalytics.svg)](https://jitpack.io/#myJarvis/EasyAnalytics)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-EasyAnalytics-green.svg?style=flat )]( https://android-arsenal.com/details/1/8245 )


an easy to use android library to let developers know how much internet-data their app is consuming. We can identify this as we want based on :
-  any Screen or Module.
-  any event.

EasyAnalytics library gives us the power to easily add logging events anywhere we want to check here how much Internet is consumed.

<br/>
To demo our EasyAnalytics lib we are using our old Code repo<a href="https://github.com/droid-lover/DaggerHiltApp"> News Sample App using HILT</a>

![Alt Text](https://github.com/myJarvis/EasyAnalytics/blob/master/images/analytics.gif)

<br/>

# Gradle
Add this into your dependencies block.

```
implementation 'com.github.myJarvis:EasyAnalytics:0.0.3'
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

### Step3- Want to see the captured internet data consumptions by ```EasyAnalytics``` in your app itself?

Call ```EasyAnanlytics``` method like this -
```
EasyAnalytics.showReports(context)
```
----------------------------------------------------------------------------------------------------------------------------------------------------------------
### and that's it ```EasyAnalytics``` will open a beautiful Screen loaded with all analytics logged events for you, in your app itself without any hassle 😁 
----------------------------------------------------------------------------------------------------------------------------------------------------------------


## Demo (inital release)-
![demovideo](https://github.com/myJarvis/EasyAnalytics/blob/master/images/demo.gif)
## Demo (release v2)-
<img src="https://github.com/myJarvis/EasyAnalytics/blob/master/images/v2release.gif" width="400" height="600" />

### Upcoming features
 - an exportable summarize report and more.
 
 
<div align="center">

### Bingo, thats all EasyAnalytics is ready to help you 😁, If you like this,</br></br>Show some ❤️ by starring⭐ this repo!

</div>

## About the author
### <a href="https://iamsachinrajput.medium.com/"> Sachin Rajput</a>
  Android Developer with 5 years of experiecne in building useful apps which serves a purpose to help people. <br/>
  In :heart: with <img src="https://github.com/myJarvis/EasyAnalytics/blob/master/images/android.png" alt="Android" width=20  height=30> </br> Open source contributor.
  
  Lets be friends over here :) 
  
[![Linkedin Badge](https://img.shields.io/badge/-LinkedIn-0e76a8?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/sachin-rajput-998b48105/)
[![Website Badge](https://img.shields.io/badge/Medium-3b5998?style=flat-square&logo=google-chrome&logoColor=white)](https://droid-lover.medium.com/)
[![Stackoverflow Badge](https://img.shields.io/badge/-Stackoverflow-FFA500?style=flat-square&logo=Stackoverflow&logoColor=orange)](https://stackoverflow.com/users/7193506/sachin)


