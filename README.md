# ShadowAPI
ShadowAPI is a library for Minecraft Forge that provides utilites commonly used throughout my mods.

**WARNING: 3.0.0 IS INCREDIBLY WIP AND IN FLUX, API WILL CHANGE. A LOT.**

## Usage
1. To the `repositories` block of your `build.gradle` add this:

```
maven {
	name "shadowfacts"
	url "http://mvn.rx14.co.uk/shadowfacts/"
}
```

2. And add this to the `dependencies` block:

```
compile group: 'net.shadowfacts', name: 'ShadowMC', version: '1.8.9-3.0.0-SNAPSHOT'
```

3. Profit.