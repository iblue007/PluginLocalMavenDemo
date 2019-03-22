package com.buildsrc.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin implements  Plugin<Project>{

    @Override
    void apply(Project project) {
        System.out.print("hello world")
        project.extensions.create('apkname',ApkNameExtension)

        project.android.applicationVariants.all {
            variant ->
                variant.outputs.all {
                    output ->
                        //找到工程的 Android
                        def androidExtensions = project.extensions.findByName("android")
                        //得到versionName
                        def versionName = androidExtensions.defaultConfig.versionName

                        def outputFile = output.outputFile
                        def fileName
                        def apkName = project.extensions.apkname.apkName == "" ? project.name : project.extensions.apkname.apkName
                        if (outputFile != null && outputFile.name.endsWith('.apk')) {
                            if (variant.buildType.name.equals('release')) {
                                fileName = "${apkName}${versionName}.apk"
                            } else if (variant.buildType.name.equals('debug')) {
                                fileName = "${apkName}_debug.apk"
                            }
                            outputFileName = fileName
                        }
                }
        }
    }
}