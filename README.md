# Crystal Launcher — Android

Native Android port of **Crystal Launcher v2.3.4** (CurseForge Proxy / Cloudflare Worker version).

## Goal
Exact visual + feature match of the desktop Electron launcher on Android:

- Dark theme with violet accent (`#9a8cff`)
- Instance management
- Modrinth search + install
- CurseForge via your Cloudflare Worker proxy
- Modpacks, mods, shaders, resource packs, datapacks
- Settings (RAM, loader, username, etc.)
- Same branding & layout language

## Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose + Material 3
- **Networking**: OkHttp + Kotlinx Serialization
- **Architecture**: single-activity, MVVM + Repository
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 35

## Cloudflare CurseForge Proxy
Same endpoint used by the desktop app:

```
https://crystal-cf-proxy.vansh123456789-minecraft.workers.dev
```

- CDN downloads are rewritten: `forgecdn.net/...` → `CF_PROXY_BASE/cdn/...`
- The Worker injects the private CurseForge API key.

## Project structure
```
app/src/main/java/ai/crystal/launcher/
├── ui/
│   ├── theme/          # Colors matching desktop CSS variables
│   ├── screens/        # Home, Instances, Content, Settings…
│   └── components/     # Shared Compose components
├── data/
│   ├── api/            # Modrinth + CF Proxy + Mojang clients
│   ├── model/          # Data classes
│   └── repository/     # Business logic
└── util/
```

## Current status
- [x] Project skeleton
- [x] Exact desktop color theme
- [x] API constants + Cloudflare proxy client
- [x] Basic data models
- [ ] Compose UI screens (mirroring desktop)
- [ ] Instance storage
- [ ] Download / install pipeline
- [ ] Game launch hand-off (Pojav / future)

## How to open
1. Open the `CrystalLauncher-Android` folder in Android Studio (Giraffe or newer).
2. Let Gradle sync.
3. Run on an emulator or device (API 26+).

## Notes for full “exact” parity
Launching Minecraft *Java Edition* on Android is a separate large subsystem (Java runtime + rendering).  
This port focuses first on making the **launcher UI + mod management** identical.  
Game execution can later integrate with PojavLauncher-style runtimes or a custom solution.
