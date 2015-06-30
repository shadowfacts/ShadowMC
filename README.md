# ShadowAPI
ShadowAPI is a library for Minecraft Forge that provides utilites commonly used throughout my mods.

## Usage
To the `repositories` block of your `build.gradle` add this:

```
maven {
	name "shadowfacts"
	url "http://maven.shadowfacts.net"
}
```

And to the `dependencies` block:

```
compile group: 'net.shadowfacts', name: 'ShadowMC', version: '2.3.0'
```

### Shading
**It is completely optional to do this.**

Shading is the process of including the source code of ShadowMC in *your* JAR file so your users don't have to download it separately. To accomplish this you must add several things to your `build.gradle`:

```
configurations {
	shade
	compile.extendsFrom shade
}
```

**Note:** The `configurations` block must be above the `dependencies` block.

Then, in your `dependencies` block on the line where it specifies to compile ShadowMC change it to this:

```
shade group: 'net.shadowfacts', name: 'ShadowMC', version: '2.3.0'
```

Lastly, add this somewhere else in your build.gradle:

```
jar {
	configurations.shade.each { dep ->
		from (project.zipTree(dep)) {
			exclude 'META-INF', 'META-INF/**'
		}
	}
}
```

**Note:** If you have any other tasks which produces JAR files, you must also add the `configurations.shade.each {}` block to them as well:

```
task deobfJar(type: Jar) {
	from sourceSets.main.output
	configurations.shade.each { dep ->
		from (project.zipTree(dep)) {
			exclude 'META-INF', 'META-INF/**'
		}
	}
	classifier = 'deobf'
}

task sourcesJar(type: Jar) {
	from sourceSets.main.allSource
	configurations.shade.each { dep ->
		from (project.zipTree(dep)) {
			exclude 'META-INF', 'META-INF/**'
		}
	}
	classifier = 'sources'
}
```