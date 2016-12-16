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

