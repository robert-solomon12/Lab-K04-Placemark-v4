package org.wit.placemark.console

import mu.KotlinLogging
import org.wit.placemark.console.controllers.PlacemarkController
import org.wit.placemark.console.models.PlacemarkModel
import org.wit.placemark.console.views.PlacemarkView

private val logger = KotlinLogging.logger {}

//val placemarks = PlacemarkMemStore()
val placemarkView = PlacemarkView()
val controllers = PlacemarkController()


fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = placemarkView.menu()
        when(input) {
            1 -> controllers.add()
            2 -> controllers.update()
            3 -> controllers.list()
            4 -> controllers.search()
            5 -> controllers.delete()
           // 6 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}