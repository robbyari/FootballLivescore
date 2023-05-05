##---------------Begin: proguard configuration for SQLCipher  ----------
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}


