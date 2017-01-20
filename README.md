# imageLoader

#Library wrapper to load images using Glide and Picasso libraries

You can using this library like an Api Fluent. The syntax is equal to Glide library.

###Example of use

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
            .error(R.drawable.ic_loading)
            .into(imageView)
            .loaderCallback(new ImageLoaderCallback() {
              @Override public void onFinish(boolean isSuccess) {

                if (isSuccess) {
                  Snackbar.make(buttonGlide, "image Glide loaded!", Snackbar.LENGTH_SHORT).show();
                } else {
                  Snackbar.make(buttonGlide, "image Glide error :(", Snackbar.LENGTH_SHORT).show();
                }
              }
            })
            .build();
```


You can use this library importing the glide or picasso dependency in your build.gradle file

```gradle
compile('com.github.Gigigo-Android-Devs.gigigo-imageLoader-library-android:imageloader-glide:1.2.2')
compile('com.github.Gigigo-Android-Devs.gigigo-imageLoader-library-android:imageloader-picasso:1.2.2')
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
