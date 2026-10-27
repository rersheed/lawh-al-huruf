# لوح الحروف — Android app

The game runs fully offline: the page, the 1,800-word dictionary and the Arabic fonts are inside the app.

## Option A — Android Studio (about 2 minutes)
1. Install Android Studio (free) from developer.android.com/studio.
2. File → Open → choose this `LawhAlHuruf` folder. Wait for "Gradle sync" to finish.
3. Build → Build App Bundle(s) / APK(s) → Build APK(s).
4. Click "locate". The file is `app/build/outputs/apk/debug/app-debug.apk`.
5. Send that file to your phone (WhatsApp, Telegram, USB, Google Drive) and tap it to install.
   Allow "Install unknown apps" for that app when the phone asks.

## Option B — GitHub builds it for you (no computer setup)
1. Create a new repository on github.com and upload everything in this folder
   (keep the hidden `.github` folder — it holds the build instructions).
2. Open the repository's **Actions** tab. The "Build APK" job runs on its own (about 4 minutes).
3. Open the finished run and download **lawh-al-huruf-apk** under "Artifacts".
4. Unzip it on your phone and tap the `.apk` to install.

## Updating the game
Replace `app/src/main/assets/index.html` with a new version and build again.
Raise `versionCode` in `app/build.gradle` (1 → 2) so phones accept the update.

## Before publishing on Google Play
The release build is signed with a debug key so it installs directly. For Google Play,
create your own keystore (Build → Generate Signed Bundle / APK) and keep it safe.
