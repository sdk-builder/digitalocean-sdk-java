/*
 * Copyright 2014 Stormpath, Inc.
 * Modifications Copyright 2018 DigitalOcean, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.digitalocean.sdk.impl.http.support;

import com.digitalocean.sdk.lang.Classes;
import com.digitalocean.sdk.lang.Strings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This class is in charge of constructing the <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.43">
 * User-Agent http header</a> string that will be sent to DigitalOcean in order to describe the current running environment of this Java SDK.
 * <p>
 * The form of this string is the concatenation of the following sub-items:
 * <ol>
 *     <li>The digital ocean integration and version separated by a '/'.  If there is no integration being used, this can be omitted
 *     <li>The digital ocean sdk and version separated by a '/'
 *     <li>The runtime information (runtime/version)
 *     <ol type="a">
 *          <li>Integration Runtime (if there is no integration being used, this can be omitted)
 *          <li>SDK Runtime
 *     </ol>
 *     <li>The OS common name and version separated by a '/'.
 *     <li>All other system information included in parentheses
 * </ol>
 * <p>
 * The User-Agent value is created when this class is loaded. The string can be obtained just by invoking
 * {@link UserAgent#getUserAgentString() UserAgent.getUserAgentString()}.
 * <p>
 *
 * @since 0.5.0
 */
public class UserAgent {
    
    //SDK
    private static final String DIGITAL_OCEAN_SDK_STRING = "digitalocean-sdk-java";
    
    //Runtime
    private static final String JAVA_SDK_RUNTIME_STRING = "java";

    ////Additional Information////
    
    //Web Servers
    private static final String WEB_SERVER_TOMCAT_ID = "tomcat";
    private static final String WEB_SERVER_TOMCAT_BOOTSTRAP_CLASS = "org.apache.catalina.startup.Bootstrap";
    private static final String WEB_SERVER_TOMCAT_EMBEDDED_CLASS = "org.apache.catalina.startup.Tomcat";
    private static final String WEB_SERVER_JETTY_ID = "jetty";
    private static final String WEB_SERVER_JETTY_CLASS = "org.eclipse.jetty.servlet.listener.ELContextCleaner";
    private static final String WEB_SERVER_JBOSS_ID = "jboss";
    private static final String WEB_SERVER_JBOSS_CLASS = "org.jboss.as.security.plugins.AuthenticationCacheEvictionListener";
    private static final String WEB_SERVER_WEBSPHERE_ID = "websphere";
    private static final String WEB_SERVER_WEBSPHERE_CLASS = "com.ibm.websphere.product.VersionInfo";
    private static final String WEB_SERVER_GLASSFISH_ID = "glassfish";
    private static final String WEB_SERVER_GLASSFISH_CLASS = "com.sun.enterprise.glassfish.bootstrap.GlassFishMain";
    private static final String WEB_SERVER_WEBLOGIC_ID = "weblogic";
    private static final String WEB_SERVER_WEBLOGIC_CLASS = "weblogic.version";
    private static final String WEB_SERVER_WILDFLY_ID = "wildfly";
    private static final String WEB_SERVER_WILDFLY_CLASS = "org.jboss.as.security.ModuleName";

    private static final String VERSION_SEPARATOR = "/";
    private static final String ENTRY_SEPARATOR = " ";

    //Placeholder for the actual User-Agent String
    private static final String USER_AGENT = createUserAgentString();

    private UserAgent() {
    }

    public static String getUserAgentString() {
        return USER_AGENT;
    }

    private static String createUserAgentString() {
        String userAgent =  getDigitalOceanSdkString() +
                getWebServerString() +                  // tomcat | jetty | jboss | websphere | glassfish | weblogic | wildfly
                getJavaSDKRuntimeString() +             // java
                getOSString();                          // Mac OS X
        return userAgent.trim();
    }

    private static String getDigitalOceanSdkString() {
        return DIGITAL_OCEAN_SDK_STRING + VERSION_SEPARATOR + Version.getClientVersion() + ENTRY_SEPARATOR;
    }

    private static String getJavaSDKRuntimeString() {
        return JAVA_SDK_RUNTIME_STRING + VERSION_SEPARATOR + System.getProperty("java.version") + ENTRY_SEPARATOR;
    }

    private static String getOSString() {
        return System.getProperty("os.name") + VERSION_SEPARATOR + System.getProperty("os.version") + ENTRY_SEPARATOR;
    }

    private static void append(StringBuilder sb, String value) {
        if (Strings.hasText(value)) {
            sb.append(value);
        }
    }

    private static String getWebServerString() {
        String webServerString;
        //Glassfish uses Tomcat internally, therefore the Glassfish check must be carried out before Tomcat's
        webServerString = getFullEntryStringUsingManifest(WEB_SERVER_GLASSFISH_CLASS, WEB_SERVER_GLASSFISH_ID);
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }
        webServerString = getFullEntryStringUsingManifest(WEB_SERVER_TOMCAT_BOOTSTRAP_CLASS, WEB_SERVER_TOMCAT_ID);
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }
        webServerString = getFullEntryStringUsingManifest(WEB_SERVER_TOMCAT_EMBEDDED_CLASS, WEB_SERVER_TOMCAT_ID);
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }
        webServerString = getFullEntryStringUsingManifest(WEB_SERVER_JETTY_CLASS, WEB_SERVER_JETTY_ID);
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }
        //WildFly must be before JBoss
        webServerString = getWildFlyEntryString();
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }
        webServerString = getFullEntryStringUsingManifest(WEB_SERVER_JBOSS_CLASS, WEB_SERVER_JBOSS_ID);
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }
        webServerString = getWebSphereEntryString();
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }
        webServerString = getWebLogicEntryString();
        if(Strings.hasText(webServerString)) {
            return webServerString;
        }

        return "";
    }

    private static String getFullEntryStringUsingPomProperties(String fqcn, String entryId) {
        if (Classes.isAvailable(fqcn)) {
            return entryId + VERSION_SEPARATOR + getVersionInfoFromPomProperties(fqcn) + ENTRY_SEPARATOR;
        }
        return null;
    }

    private static String getFullEntryStringUsingManifest(String fqcn, String entryId) {
        if (Classes.isAvailable(fqcn)) {
            return entryId + VERSION_SEPARATOR + getVersionInfoInManifest(fqcn) + ENTRY_SEPARATOR;
        }
        return null;
    }

    private static String getFullEntryStringUsingSDKVersion(String fqcn, String entryId) {
        if (Classes.isAvailable(fqcn)) {
            return entryId + VERSION_SEPARATOR + Version.getClientVersion() + ENTRY_SEPARATOR;
        }
        return null;
    }

    private static String getWebSphereEntryString() {
        if (Classes.isAvailable(WEB_SERVER_WEBSPHERE_CLASS)) {
            return WEB_SERVER_WEBSPHERE_ID + VERSION_SEPARATOR + getWebSphereVersion() + ENTRY_SEPARATOR;
        }
        return null;
    }

    private static String getWebLogicEntryString() {
        if (Classes.isAvailable(WEB_SERVER_WEBLOGIC_CLASS)) {
            return WEB_SERVER_WEBLOGIC_ID + VERSION_SEPARATOR + getWebLogicVersion() + ENTRY_SEPARATOR;
        }
        return null;
    }

    private static String getWildFlyEntryString() {
        try {
            if (Classes.isAvailable(WEB_SERVER_WILDFLY_CLASS)) {
                Package wildFlyPkg = Classes.forName(WEB_SERVER_WILDFLY_CLASS).getPackage();
                if (wildFlyPkg != null
                    && Strings.hasText(wildFlyPkg.getImplementationTitle()) && wildFlyPkg.getImplementationTitle().contains("WildFly")) {
                        return WEB_SERVER_WILDFLY_ID + VERSION_SEPARATOR + wildFlyPkg.getImplementationVersion() + ENTRY_SEPARATOR;
                }

            }

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e){ //NOPMD
            //there was a problem obtaining the WildFly version
        }
        return null;
    }

    /**
     * WARNING: This method must never be invoked unless we already know that the class identified by the parameter {@code fqcn}
     * really exists in the classpath. For example, we first need to assure that {@code Classes.isAvailable(fqcn))} is <code>TRUE</code>
     */
    private static String getVersionInfoFromPomProperties(String fqcn) {
        String version = "unknown";
        try{
            Class clazz = Classes.forName(fqcn);
            String className = clazz.getSimpleName() + ".class";
            String classPath = clazz.getResource(className).toString();

            String jarPath = null;
            if (classPath.startsWith("jar:file:")) {
                //Let's remove "jar:file:" from the beginning and also the className
                jarPath = classPath.subSequence(9, classPath.lastIndexOf("!")).toString();
            } else if (classPath.startsWith("vfs:")) {
                //Let's remove "vfs:" from the beginning and also the className
                jarPath = classPath.subSequence(4, classPath.lastIndexOf(".jar") + 4).toString();
            }

            if (jarPath == null) {
                //we were not able to obtain the jar path. Let's abort
                return version;
            }

            Enumeration<JarEntry> enumeration;
            String pomPropertiesPath;
            try (JarFile jarFile = new JarFile(jarPath)) {
                enumeration = jarFile.entries();
            }
            pomPropertiesPath = null;
            while (enumeration.hasMoreElements()) {
                JarEntry entry = enumeration.nextElement();
                if (entry.getName().endsWith("pom.properties")) {
                    pomPropertiesPath = entry.getName();
                    break;
                }
            }
            if (pomPropertiesPath != null) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream("/" + pomPropertiesPath), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("version=")) {
                            version = line.split("=")[1];
                            break;
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) { //NOPMD
            //Either the jar file or the internal "pom.properties" file could not be read, there is nothing we can do...
        }
        return version;
    }

    /**
     * WARNING: This method must never be invoked unless we already know that the class identified by the parameter {@code fqcn}
     * really exists in the classpath. For example, we first need to assure that {@code Classes.isAvailable(fqcn))} is <code>TRUE</code>
     */
    private static String getVersionInfoInManifest(String fqcn){
        //get class package
        Package thePackage = Classes.forName(fqcn).getPackage();
        //examine the package object
        String version = thePackage.getSpecificationVersion();
        if (!Strings.hasText(version)) {
            version = thePackage.getImplementationVersion();
        }
        if(!Strings.hasText(version)) {
            version = "null";
        }
        return version;
    }

    /**
     * This method should only be invoked after already knowing that the class identified by {@code WEB_SERVER_WEBSPHERE_CLASS}
     * really exists in the classpath. For example, it can be checked that {@code Classes.isAvailable(WEB_SERVER_WEBSPHERE_CLASS))}
     * is {@code TRUE}
     */
    private static String getWebSphereVersion() {
        try {
            Class<?> versionClass = Class.forName(WEB_SERVER_WEBSPHERE_CLASS);
            Object versionInfo = versionClass.newInstance();
            Method method = versionClass.getDeclaredMethod("runReport", String.class, PrintWriter.class);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            method.invoke(versionInfo, "", printWriter);
            String version = stringWriter.toString();
            // version looks like this, so we need to "substring" it:
            //
            //
            //IBM WebSphere Product Installation Status Report
            //--------------------------------------------------------------------------------
            //
            //Report at date and time August 13, 2014 1:12:06 PM ART
            //
            //Installation
            //--------------------------------------------------------------------------------
            //Product Directory        C:\Program Files\IBM\WebSphere\AppServer
            //Version Directory        C:\Program Files\IBM\WebSphere\AppServer\properties\version
            //DTD Directory            C:\Program Files\IBM\WebSphere\AppServer\properties\version\dtd
            //Log Directory            C:\Documents and Settings\All Users\Application Data\IBM\Installation Manager\logs
            //
            //Product List
            //--------------------------------------------------------------------------------
            //BASETRIAL                installed
            //
            //Installed Product
            //--------------------------------------------------------------------------------
            //Name                  IBM WebSphere Application Server
            //Version               8.5.5.2
            //ID                    BASETRIAL
            //Build Level           cf021414.01
            //Build Date            4/8/14
            //Package               com.ibm.websphere.BASETRIAL.v85_8.5.5002.20140408_1947
            //Architecture          x86 (32 bit)
            //Installed Features    IBM 32-bit WebSphere SDK for Java
            //WebSphere Application Server Full Profile

            version = version.substring(version.indexOf("Installed Product"));
            version = version.substring(version.indexOf("Version"));
            version = version.substring(version.indexOf(" "), version.indexOf("\n")).trim();
            return version;

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) { //NOPMD
            //there was a problem obtaining the WebSphere version
        }
        //returning null so we can identify in the User-Agent String that we are not properly handling some WebSphere version
        return "null";
    }

    /**
     * This method should only be invoked after already knowing that the class identified by {@code WEB_SERVER_WEBLOGIC_CLASS}
     * really exists in the classpath. For example, it can be checked that {@code Classes.isAvailable(WEB_SERVER_WEBLOGIC_CLASS))}
     * is {@code TRUE}
     */
    private static String getWebLogicVersion() {
        try {
            Class<?> versionClass = Class.forName(WEB_SERVER_WEBLOGIC_CLASS);
            Object version = versionClass.newInstance();
            Method method = versionClass.getDeclaredMethod("getReleaseBuildVersion");
            return (String) method.invoke(version);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) { //NOPMD
            //there was a problem obtaining the WebLogic version
        }
        //returning null so we can identify in the User-Agent String that we are not properly handling some WebLogic version
        return "null";
    }
}
