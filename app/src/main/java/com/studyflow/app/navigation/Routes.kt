package com.studyflow.app.navigation

object Routes {

    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val DASHBOARD = "dashboard"
    const val MODULES = "modules"
    const val ADD_MODULE = "add_module"
    const val EDIT_MODULE = "edit_module"
    const val SETTINGS = "settings"

    // Builds the route used when editing a specific module.
    fun editModule(moduleId: Int): String {
        return "$EDIT_MODULE/$moduleId"
    }
}