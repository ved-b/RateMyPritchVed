# RateMyPritch Individual Project

## What are we about?
My fork focuses on adding two new features to the existing ratemypritch app. 

## List of Features worked on for this version:
In this fork, two new features have been added to the ratemypritch application. One is a CAPTCHA to verify users that are signing up. This is done using the Google ReCaptcha API using the Google Cloud Console. The second feature is an embedded map showing the location of pritchard, transofrming the application into a one stop shop for new users and students who aren't familiar with the campus layout. 
  
## How do I use this app?
At the end of 2022, the APIs will be terminated. To use it for yourself, follow these steps:
1. Download the files.
2. Open with android studio
3. Create an account with google cloud console. 
4. Search for the Google ReCaptcha API. 
5. Create the API (You may have to enter package name in google cloud to get the recaptcha API).
6. Copy the API key and paste it into the sitekey variable in captcha.java
7. Search for the Google Maps API.
8. Copy the API key.
9. Paste it into android::value in the android manifest file within the meta data tags.
10. You're good to go!
