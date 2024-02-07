package fr.hwrzn.naturecollection

class PlantModel(
    val id: String = "plant0",
    val name: String = "Plante",
    val description: String = "Petite description",
    val imageUrl: String = "https://images.unsplash.com/photo-1520412099551-62b6bafeb5bb?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    var liked: Boolean = false
)