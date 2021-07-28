package com.jpventura.domain


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
