package com.cabify.commons.network

import java.io.IOException

class NoConnectivityException: IOException() {
    override val message: String = "No internet conection"
}
