package com.jpventura.data


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
