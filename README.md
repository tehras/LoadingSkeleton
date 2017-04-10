# LoadingSkeleton
[![Build Status](https://travis-ci.org/tehras/LoadingSkeleton.svg?branch=master)](https://travis-ci.org/tehras/LoadingSkeleton)[ ![Download](https://api.bintray.com/packages/tehras/maven/loading-skeleton/images/download.svg) ](https://bintray.com/tehras/maven/loading-skeleton/_latestVersion)

---
<h4>How it looks:</h4>
<br/>

![Demo GIF](/assets/demo_gif_small.gif "Demo GIF")

---

<h4>How To Use Skeleton Layout:</h4>
<br/>

1. Wrap your layout
```
<com.github.tehras.loadingskeleton.LoadingSkeleton
    android:id="@+id/skeleton_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    ...
        
</com.github.tehras.loadingskeleton.LoadingSkeleton>
```

2. Start and Stop
```
        //use as a normal layout
        val loadingSkeleton = findViewById(R.id.skeleton_layout) as LoadingSkeleton
        
        //start loading layout
        loadingSkeleton.start()
        
        //stop loading layout
        loadingSkeleton.stop()
```
3. Setting custom actions
```
  //Set your own Skeleton View Converter
  loadingSkeleton
          .skeletonViewConverter(LoadingSkeletonViewConverter.Builder()
                  .color(R.color.colorPrimary) //set color
                  .shimmer(true)  //shimmer, true/false
                  .gradient(true) //gradient, true/false
                  .addConvert(YourLoadingSkeletonViewConverter1()) //add your own custom conver
                  .addConvert(YourLoadingSkeletonViewConverter2()) //adding converter ovverides default ones
                  .build()) //build, and you're done
```
---
<h4>Creating your own converter:</h4>
<br/>

1. Extend ```LoadingSkeletonViewStreamer<T : View>```
2. Implement ```fun convert(c: Context, v: T, options: Options)``` - Called when ```.start()``` is called for EVERY view
3. Implement ```fun revert(c: Context, v: T)``` - Called when ```.stop``` is called for EVERY view
4. There are optional ```fun start(){}``` and ```fun stop(){}``` fields that are called only once at start and stop
<br/>
(Just take a look at the ```DefaultTextViewStreamer``` or ```DefaultImageViewStreamer``` for examples)

---

<h5>Gradle:</h5>

```
compile 'com.github.tehras:loading-skeleton:0.2.0'
```

<h5>Maven:</h5>

```
<dependency>
  <groupId>com.github.tehras</groupId>
  <artifactId>loading-skeleton</artifactId>
  <version>0.2.0</version>
  <type>pom</type>
</dependency>
```
