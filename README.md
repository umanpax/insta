# insta
MINI APP PRESENTANT UNE INTERFACE HOME INSTAGRAM, AVEC UN PETIT MOTEUR DE RECHERCHE
DESIGN PATTERN : MVVM


POUR ROOM : 

mettre dans proguard-rules.pro : 
-keep class  app.mypackage.model.** { *; }

---------------------------

Dans build app :
def room_version = "2.4.2"

     - - Room
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    testImplementation "androidx.room:room-testing:$room_version"
