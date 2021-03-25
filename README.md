# TickSpeed
A bukkit plugin that allows admins to change the server tickrate.

## Commands:
  - /tickspeed [realtime | \<tps\>]
    - Run without arguments displays target tps
    - Run with an integer as first argument sets target tps
    - Run with "realtime" as first argument makes the server run without sleeping
## Permissions:
 - tickspeed.view - View current target tps
 - tickspeed.change - Change target tps
 - tickspeed.notify - Notify when target tps is changed

##Contributing:
Make a pull request! All ideas welcome.

To build the plugin:

```
./gradlew clean build jar
```

Output is in `build/libs`