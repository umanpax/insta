package com.insta.utils

import com.insta.model.Photo
import com.insta.model.User
import com.insta.utils.Workflow.Singleton.INSTANCE
import java.io.Serializable

class Workflow : Serializable {

    var user: User = User()
    var listPhotosByUserName: ArrayList<Photo> = ArrayList()

    constructor()

    constructor(user: User, listPhotosByUserName: ArrayList<Photo>) {
        this.user = user
        this.listPhotosByUserName = listPhotosByUserName
    }

    /** Instance unique non préinitialisée  */
    object Singleton {
        var INSTANCE: Workflow? = null
    }


    /** Point d'accès pour l'instance unique du singleton  */
    fun getInstance(): Workflow {
        if (INSTANCE == null) {
            INSTANCE = Workflow()
        }
        return INSTANCE as Workflow
    }

    fun updateInstance(workflow: Workflow) {
        INSTANCE = workflow
    }

}