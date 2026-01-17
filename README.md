# tiktokdownloader

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

A simple Android application to download TikTok videos (without watermark) for personal use and testing. This repository contains the Android app source, build instructions, usage notes, and a Demo section showing how to add and embed a demo video in the README.

> NOTE: Do not commit secrets or private API keys into this repository. See the Configuration section below for local setup.

Table of Contents
- Features
- Quick Start
- Build & Run (Windows PowerShell)
- Usage
- Demo (how to include a demo video in README)
  - Option A — YouTube (recommended)
  - Option B — GIF inside repo (small demos)
  - Option C — HTML5 video hosted (GitHub Pages or Release asset)
- Configuration
- Troubleshooting
- Contributing
- License
- Authors


Features
- Download TikTok videos using a URL input.
- Option to strip watermark (project-specific behavior; use responsibly).
- Simple UI for copying links and saving videos locally.


Quick Start

Requirements
- Android Studio Flamingo or later (recommended)
- JDK 11 or compatible
- Android SDK (match compileSdk and targetSdk in project)
- A connected Android device or emulator

Clone the repository (if you haven't already):

```powershell
git clone https://github.com/OWNER/REPO.git
cd REPO
```

(Replace the above URL with your repository remote.)

Build the debug APK with Gradle wrapper (Windows PowerShell):

```powershell
.\gradlew assembleDebug
```

Install the debug APK on a connected device/emulator:

```powershell
.\gradlew installDebug
# or install the built APK manually with adb:
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

Run the app from Android Studio or launch the installed app on the device.


Build & Run (additional commands)
- Clean build: `.\gradlew clean`
- Run unit tests: `.\gradlew test`
- Run instrumentation tests: `.\gradlew connectedAndroidTest`


Usage
- Open the app on your device.
- Paste a TikTok video URL into the input field.
- Tap Download. The app will fetch the video and save it to local storage (or to the configured download folder).
- Use the device's file manager or the app's built-in viewer to watch downloaded videos.


Demo — how to include a demo video in this README

Below are three safe and common ways to show a demo video in a GitHub README. Pick one (or more) depending on your needs. Important notes:
- GitHub strips some HTML tags from README.md; the safest, most compatible options are a YouTube thumbnail that links to the video, or an animated GIF kept under a few MB.
- Avoid committing large video files to the repository. Use GitHub Releases or GitHub Pages for hosting large assets.

Option A — YouTube thumbnail (recommended)
- Upload your demo video to YouTube (unlisted if you prefer private access).
- Use the video ID in the snippet below. This displays a thumbnail and links to the YouTube player.

Markdown snippet (replace VIDEO_ID):

```markdown
[![Watch the demo](https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg)](https://youtu.be/VIDEO_ID)
```

If `maxresdefault.jpg` isn't available for your video, try `hqdefault.jpg` or `sddefault.jpg`:
- https://img.youtube.com/vi/VIDEO_ID/hqdefault.jpg

Pros: Lightweight, reliable, and plays in YouTube. Cons: requires YouTube hosting.


Option B — Animated GIF stored in the repository
- Create a short, optimized GIF (5–10s, low resolution/frame rate) and place it in `docs/demo.gif`.
- GIFs show inline in the README, but they increase repo size. Keep GIF ≤ 3–5 MB.

Place demo GIF at: `docs/demo.gif`

Markdown snippet:

```markdown
![Demo](docs/demo.gif)
```

How to make a GIF from an MP4 using ffmpeg (example):

```powershell
# Resize, reduce frame rate, and convert
ffmpeg -i demo.mp4 -vf "scale=iw*0.6:-1" -r 12 -f gif demo.gif
# Optional: optimize with gifsicle (install gifsicle separately)
gifsicle -O3 --colors 256 demo.gif -o docs/demo.gif
```

Pros: Instant inline playback. Cons: Larger repo size and limited quality.


Option C — HTML5 video hosted via GitHub Pages or Release asset (best for larger videos)
- Recommended: Host the demo video on GitHub Pages or an external CDN and embed a preview or link in the README.
- Embedding raw <video> tags in README.md may be sanitized by GitHub; linking to a GitHub Pages page that contains the <video> tag is the most reliable approach.

Method 1 — Link to a GitHub Pages demo page (recommended for high-quality video):
1. Create a `gh-pages` branch or enable GitHub Pages from `docs/` or the `gh-pages` branch.
2. Put `demo.mp4` at `https://USERNAME.github.io/REPO/demo.mp4` or create a small `index.html` that embeds the video.
3. In README, link or show a clickable screenshot that opens the GitHub Pages page.

Markdown example (screenshot linking to demo page):

```markdown
[![Open demo page](docs/demo-thumbnail.png)](https://USERNAME.github.io/REPO/)
```

Method 2 — Use a Release asset and link to it (safer than committing large files to main branch):
- Create a GitHub Release and upload `demo.mp4` as an asset.
- Link to the release asset or show a thumbnail.

Markdown example (linking to a release asset):

```markdown
[Download demo video (release asset)](https://github.com/OWNER/REPO/releases/download/v1.0.0/demo.mp4)
```

Create a release from the command line with the GitHub CLI (`gh`) (PowerShell):

```powershell
# create a release and upload demo.mp4
gh release create v1.0.0 "path\to\demo.mp4" --title "Demo v1.0.0" --notes "Demo video"
```

Pros: Can host high-quality video. Cons: Slightly more setup; README may not autoplay/embed video directly.


Where to add demo files in this project
- Small GIF preview: add `docs/demo.gif` and commit (for a small preview only).
- Thumbnails/screenshots: add `docs/demo-thumbnail.png`.
- Large MP4: do not commit to main branch; upload as a Release asset or host on GitHub Pages / external CDN.


Configuration
- If the project requires API keys or special gradle properties, put them in `local.properties` or `gradle.properties` on your machine and do not commit them.
- Example (in `local.properties` — NOT in VCS):

```
# local.properties
sdk.dir = C:\Users\<username>\AppData\Local\Android\sdk
MY_PRIVATE_KEY = your_key_here
```

Add `local.properties` to `.gitignore` (Android projects typically already do this).


Troubleshooting
- Gradle cache issues: `.\gradlew clean` then rebuild.
- If the device doesn't install the APK: ensure USB debugging is enabled and `adb devices` shows the device.
- Common Java/Kotlin issues: check project SDK and Gradle plugin versions in `build.gradle.kts`.


Contributing
- Open issues for bugs or feature requests.
- Fork the repo, create a branch, implement changes, and submit a PR.
- Format code consistently and add brief tests where applicable.


License
This project uses the MIT License — see `LICENSE` for details.


Authors
- Maintainer: Your Name (replace with actual maintainer info)


Requirements coverage
- Create fully detailed README: Done
- Add place where a demo video can be added: Done (docs/ directory and Release/GitHub Pages guidance)
- Tell how to add video in README: Done (YouTube/GIF/Pages/Release instructions + code snippets)


Next steps (optional)
- Tell me which demo option you prefer and I will add a sample thumbnail or a small GIF under `docs/` for you.
- I can also add a short `CONTRIBUTING.md` or a `docs/` index page for GitHub Pages hosting if you want to host the demo there.
