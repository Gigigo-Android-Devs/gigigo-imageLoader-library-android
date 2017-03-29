# imageLoader
[![Build Status](https://travis-ci.org/Gigigo-Android-Devs/gigigo-imageLoader-library-android.svg?branch=master)](https://travis-ci.org/Gigigo-Android-Devs/gigigo-imageLoader-library-android.svg?branch=master)
[![](https://jitpack.io/v/Gigigo-Android-Devs/gigigo-imageLoader-library-android.svg)](https://jitpack.io/#Gigigo-Android-Devs/gigigo-imageLoader-library-android)

# Library wrapper to load images using Glide and Picasso libraries

You can using this library like an Api Fluent. The syntax is equal to Glide library.

### Example of use

To load Glide instance
```java
  imageLoader = new GlideImageLoaderImp(this);
```

To load Picasso instance
```java
imageLoader = new PicassoImageLoaderImp(this);
```

You can use the library (Picasso or Glide) with the same syntax and don't have to change your code.
```java
imageLoader.load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
```

With callback.

```
 imageLoader.load(url).into(new ImageLoaderCallback() {
          @Override public void onSuccess(Bitmap bitmap) {
          }
          @Override public void onError(Drawable errorDrawable) {
          }
          @Override public void onLoading() {
          }
        });
```


You can use also transformations (Glide-Transformations, GPUImage-Transformations).

```
java
imageLoader.load(url)
            .transform(new GrayscaleTransformation(MainActivity.this))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.errorimage)
            .into(imageView);
```


And load Videos as Gif Images (Needs to handle storage permissions)

```
  String filePath = "/storage/emulated/0/Pictures/Best20SOJA.mp4";
    Glide
        .with( context )
        .load( Uri.fromFile( new File( filePath ) ) )
        .into( imageView );

```

You can use this library importing the glide or picasso dependency in your build.gradle file

```gradle

compile 'com.github.gigigo-Android-Devs:gigigo-imageLoader-library-android:imageloader-glide:2.0RC'
compile 'com.github.gigigo-Android-Devs:gigigo-imageLoader-library-android:imageloader-picasso:2.0RC'
compile 'com.github.gigigo-Android-Devs:gigigo-imageLoader-library-android:imageloader-glide-blur:2.0RC'
compile 'com.github.gigigo-Android-Devs:gigigo-imageLoader-library-android:imageloader-glide-transformations:2.0RC'

```
### TODO
-

License
----

Copyright 2016 Gigigo.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
