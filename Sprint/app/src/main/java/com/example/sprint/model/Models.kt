package com.example.sprint.model

data class Technologies(val technologies: List<Tech>)

data class Tech(val id: Int, val name: String, val description: String)

data class Structures(val structures: List<Structure>)

data class Structure(val id: Int, val name: String, val age: String)