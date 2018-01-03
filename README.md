# ShopifyMobile2018Challenge
##By Noah Steinberg
This respository is my submission for the Shopify 2018 Mobile Intership Application
This challenge was coded in Android Studio 3.0.1 for Android SDK 14 onwards.
It was tested using a Pixel 2 XL Virtual Device with Android 8.1.0 and a physical Samsung A5 7.0
The videos for testing included in my application were performed on the physical Samsung A5 device.
Videos/Screenshots included in the same directory as this readme

**Features:**
  1. Retrieves data from given endpoint 
    (https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6)
  2. Retrieves images from given data (from previous endpoint)
  3. All network communication is done asynchronusly and off the main thread (as is proper in Android)
  4. Individual product data can be seen by touching a product in list view
    a. In the product view, items can be added to a cart by pressing the add to cart and entering a variant number
  5. The list view can filter products by giving a search query, then the app searches the product details for matches
  6. Your cart total can be viewed by clicking on the shopping cart on the list view.
  

