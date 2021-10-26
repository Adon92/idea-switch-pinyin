package com.github.adon92.ideaswitchpinyin.services

import com.intellij.openapi.project.Project
import com.github.adon92.ideaswitchpinyin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
