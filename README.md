Network-Benchmark
=================

Measure network performance for mobile phone


##Installation
Specific settings for Google Map Android API should be done before run the project. Otherwise, the map function would not be running correctly.

You may need to install the **Google APIs** and **Google Play Service** before run the project. Once you download the two libraries, you need to import the **Google Play Service libs**. Be caution with the import.

##Configure the Google map API Key
You could use the *debug.keystore* which could be located at the project repo.
You could also create your own API key.

###Google map android API V2 doesn't support emulator
If you deploy the code in a android phone, you can ignore this part. Otherwise you will find a solution.

###Our code runs the map on the emulator
If you follow the instruction provided by Google to set the API key, you probably won't get the map show up on the emulator. Other solution you may found on the webistes is just a joke. We have found that you need to create both google map android v1 API key and v2 API key and put the two keys in the **right** places in order to get the emulator works.

