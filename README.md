# maps-integration
It is an Android application that shows how the integration with the Google Maps service works.

#Setup
 - You must generate a key to integrate with Google Maps API. 
 - You must replace the value of your key in  android:value property placed on AndroidManifest.xml

        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/google_maps_key" />

 - For more information you can check out this link: https://developers.google.com/maps/documentation/android-api/start
