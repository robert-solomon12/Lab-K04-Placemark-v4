package org.wit.placemark.console.controllers

import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkJSONStore
import org.wit.placemark.console.models.PlacemarkModel
import org.wit.placemark.console.views.PlacemarkView

class PlacemarkController {

    //val placemarks = PlacemarkMemStore()

    val placemarks = PlacemarkJSONStore()

    val placemarkView = PlacemarkView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 3.0")
    }


    fun menu() :Int { return placemarkView.menu() }

    fun add(){
        var aPlacemark = PlacemarkModel()

        if (placemarkView.addPlacemarkData(aPlacemark))
            placemarks.create(aPlacemark)
        else
            logger.info("Placemark Not Added")
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {

        placemarkView.listPlacemarks(placemarks)
        var searchId = placemarkView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            if(placemarkView.updatePlacemarkData(aPlacemark)) {
                placemarks.update(aPlacemark)
                placemarkView.showPlacemark(aPlacemark)
                logger.info("Placemark Updated : [ $aPlacemark ]")
            }
            else
                logger.info("Placemark Not Updated")
        }
        else
            println("Placemark Not Updated...")
    }

    fun delete() {
        placemarkView.listPlacemarks(placemarks)
        var searchId = placemarkView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            placemarks.delete(aPlacemark)
            println("Placemark Deleted...")
            placemarkView.listPlacemarks(placemarks)
        }
        else
            println("Placemark Not Deleted...")
    }

    fun search() {
        val aPlacemark = search(placemarkView.getId())!!
        placemarkView.showPlacemark(aPlacemark)
    }


    fun search(id: Long) : PlacemarkModel? {
        var foundPlacemark = placemarks.findOne(id)
        return foundPlacemark
    }

//    fun dummyData() {
//        placemarks.create(PlacemarkModel(title = "New York New York", description = "So Good They Named It Twice"))
//        placemarks.create(PlacemarkModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
//        placemarks.create(PlacemarkModel(title = "Waterford City", description = "You get great Blaas Here!!"))
//    }
}