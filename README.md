# Soundboard base application
This application can serve as a basic framework for a soundboard. 

Copy your mp3 files into `app/src/main/res/raw` directory, 
ClipEntry will pick these up, replace underscores with spaces, and that filename will
be what is displayed in the application.

Alternatively, you can add a display name to `app/src/main/res/strings/xml` to change the display 
name to something different, below is an example:
* `<string name="filename_no_extension">Name on app screen</string>`