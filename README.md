# LoadingSkeleton
[![Build Status](https://travis-ci.org/tehras/LoadingSkeleton.svg?branch=master)](https://travis-ci.org/tehras/LoadingSkeleton)[ ![Download](https://api.bintray.com/packages/tehras/maven/loading-skeleton/images/download.svg) ](https://bintray.com/tehras/maven/loading-skeleton/_latestVersion)
[![codecov](https://codecov.io/gh/tehras/LoadingSkeleton/branch/master/graph/badge.svg)](https://codecov.io/gh/tehras/LoadingSkeleton)


---
<h3>How it looks:</h3>
<br/>

![Demo GIF](/assets/demo_gif_small.gif "Demo GIF")

---

<h3>How To Use Skeleton Layout:</h3>
<br/>

1. Wrap your layout
```xml
<com.github.tehras.loadingskeleton.LoadingSkeleton
    android:id="@+id/skeleton_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    ...
        
</com.github.tehras.loadingskeleton.LoadingSkeleton>
```

2. Start and Stop
```kotlin
        //use as a normal layout
        val loadingSkeleton = findViewById(R.id.skeleton_layout) as LoadingSkeleton
        
        //start loading layout
        loadingSkeleton.start()
        
        //stop loading layout
        loadingSkeleton.stop()
```
3. Setting custom actions
  
<b>Java:</b>
```Java
  //Set your own Skeleton View Converter
   
  loadingSkeleton
          .skeletonViewConverter(new LoadingSkeletonViewConverter.Builder()
                  .color(R.color.colorPrimary) //set color
                  .shimmer(true)  //shimmer, true/false
                  .cornerRadius(16f) //corner radius
                  .gradient(true) //gradient, true/false
                  .addConvert(YourLoadingSkeletonViewConverter1()) //add your own custom conver
                  .addConvert(YourLoadingSkeletonViewConverter2()) //adding converter ovverides default ones
                  .build()) //build, and you're done
  ```
  
  <b>Kotlin:</b>
  ```Kotlin
  loadingSkeleton
          .skeletonViewConverter(LoadingSkeletonViewConverter.Builder()
                  .color(R.color.colorPrimary) //set color
                  .shimmer(true)  //shimmer, true/false
                  .gradient(true) //gradient, true/false
                  .cornerRadius(16.toFloat()) //corner radius
                  .addConvert(YourLoadingSkeletonViewConverter1()) //add your own custom conver
                  .addConvert(YourLoadingSkeletonViewConverter2()) //adding converter ovverides default ones
                  .build()) //build, and you're done
```
---
<h3>Creating your own converter:</h3>
<br/>

1. Extend ```LoadingSkeletonViewStreamer<T : View>```
2. Implement ```public void convert(Context c, T v, Options options)``` || ```fun convert(c: Context, v: T, options: Options)``` - Called when ```.start()``` is called for EVERY view
3. Implement ```public void revert(Context c, T v)``` || ```fun revert(c: Context, v: T)``` - Called when ```.stop``` is called for EVERY view
4. There are optional ```public void start(){}``` || ```fun start(){}``` and ```public void stop(){}``` ```fun stop(){}``` fields that are called only once at start and stop
<br/>
(Just take a look at the <b>DefaultTextViewStreamer</b> or <b>DefaultImageViewStreamer</b> for examples)

---

<h4>Gradle:</h4>

```Groovy
compile 'com.github.tehras:loading-skeleton:0.2.9'
```

<h4>Maven:</h4>

```Groovy
<dependency>
  <groupId>com.github.tehras</groupId>
  <artifactId>loading-skeleton</artifactId>
  <version>0.2.9</version>
  <type>pom</type>
</dependency>
```
