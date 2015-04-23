package com.ubs.example.singleton

@Singleton(lazy = false, strict = true)
class GroovySingleton {

	void performOperation(String argument) {
		println argument
	}
}
