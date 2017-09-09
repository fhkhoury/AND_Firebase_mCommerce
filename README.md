# AND_Firebase_mCommerce

A sample m-commerce app to implement the Firebase Stack

## 0 - Project History

- September 9th, 8:30am - Fill product list from the database
     - "List" screen is now more generic 
     - Remove statical data from the app
     - "Promo" class does not exist anymore

- August 31st, 10:30pm - Anonymous auth linking
     - When anonymous user signes up with his email and password, his account and profile are linked with the new on (account and database)

- August 31st, 11:30am - Project structure
     - Create a models package with all data model classes

- August 29th, 5:30pm | Customize the behavior of 'connect' button regarding to the user status

- August 29th, 4:30pm | Create anonymous user freshly autenticated in the database
     - Create a "User.java" class which allow to create a user and write it to the Firebase database
     - Create a "Utils.java" class to init and use the Firebase database
     - Update the "splashscreen" screen to write the new user in the database after a n Anonymous authentication.

- August 29th, 12:15pm | Invert Database & 1st launch check
     - Invert get database and 1st launch check 
     - Add toast to display network, auth and database status on splashscreen

- August 29th, 11:30am | Add connectivity check at start
     - If connection is available, check if it's the first run to launch Anonymous auth. 
     - If not, display HP directly

- August 29th, 1:00am | Change remote database rules & Update AnonymousAuth
     - Change database rules to get categories and products if user is authenticated or not
     - Change databese rules to get users nodes only readable and writeable by authenticated users themselves only
     - Update AnonymousAuth to set it on the first launch only

- August 28th, 4:30pm | Test database
     - Database test is ok. Get the mail of user 'f.khouryat@gmail.com

- August 25th - 5:30pm | Add anomyous auth during the splashscreen to get default database content 

- August 24th, 2017 - 1:00 pm | Crash reporting (merge the 'crashreport' branch to 'master')
     - Setup the Firebase Crash Report SDK
     - Set a fatal error crash behind te "crash" button in the "Informations" class
     - Test and good tracking on the project crash dashboard

- August 23rd, 2017 - 6:00pm | Design database
     - Add the database model scheme on Github repo

- August 10th, 2017 - 4:00pm | Design database
     - Design the database
     - Transcript the database as "Firebase_mCommerce_originDatabase.json" file 
     - Fill the database
     - Import the json file to Firebase Real Time Database console
     
- August 10th, 2017 - 12:00pm | Update architectures scheme
     - Update scheme with "login", "signup" and "resetpassword" screens

- August 10th, 2017 - 11:30am | Implement authentication system
     - Add "firebase-auth" and "fuitebase-ui-auth" dependencies to the app gradle
     - Create "login", "signup" and "resetpassword" screens

- August 7th, 2017 - 3:00pm | Install Firebase SDK
     - Import "google-services.json" configuration file in the project 	
     - Add google play services dependencies to root gradle 	
     - Add Firebase core dependencies to the app gradle

- August 7th, 2017 -  10:45am | Firebase project creation
     - Create the "55 Firebase m-Commerce" project on Firebase console (project id: 
project-5176900217331074858)
     - Declare the "55 Firebase m-Commerce" android app in the project (app id: 1:596167132252:android:45a45979843f1dbf
and package name: fiftyfive.and_firebase_mcommerce)

- August 6th, 2017 - 8:15am | Add the "Cart" screen 
     - Create and design  the "Cart" screen with its listview adapter and menu
     - Update "Informations" screen to set the menu
     - Add an icon for the app

- August 4th, 2017 - 3:30pm | Improve intent between liste/promo and detail screen to pass products parameters 

- August 3rd, 2017 - 8:15pm | Add promotional banner in the home page of the app and create the promo screen with jewelery products

- August 3rd, 2017 - 6:45pm | Add the png file to illustrate the target architecture of the app

- August 3rd, 2017 - 3:00pm | Add the "Legal" screen which embeds a webview

- August 3rd, 2017 - 12:00am | Add the menu bar to detail screen

- July 28th, 2017 - 4:00am | Add a menu bar to homepage, list and informations screen

- July 28th, 2017 - 2:00am | Add a list view in the list screen

- July 27th, 2017 - 5:15pm | Duplicate the AND_SampleAppVierge repository in AND_Firebase_mCommerce and rename the project in Android Studio
------

## 1 - Application target architecture

![App target architecture](https://github.com/fhkhoury/AND_Firebase_mCommerce/blob/master/Firebase%20R%26D%C2%A0-%20mCommerce%20app%20Architecture%20cible.png "App target architecture")
------

## 2 - Application database model

![App database model](https://github.com/fhkhoury/AND_Firebase_mCommerce/blob/master/55%20Firebase%20m-Commerce%20%7C%C2%A0Database%20Model.png "App database model")
