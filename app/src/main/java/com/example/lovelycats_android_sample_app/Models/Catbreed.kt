import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.annotations.SerializedName


data class BreedModel (
    val weight: Weight? = null,
    val id: String? = null,
    val name: String? = null,
    val cfaURL: String? = null,
    val vetstreetURL: String? = null,
    val vcahospitalsURL: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val countryCodes: String? = null,
    val countryCode: String? = null,
    val description: String? = null,
    val lifeSpan: String? = null,
    val indoor: Long? = null,
    val lap: Long? = null,
    val altNames: String? = null,
    val adaptability: Long? = null,
    val affectionLevel: Long? = null,
    val childFriendly: Long? = null,
    val dogFriendly: Long? = null,
    val energyLevel: Long? = null,
    val grooming: Long? = null,
    val healthIssues: Long? = null,
    val intelligence: Long? = null,
    val sheddingLevel: Long? = null,
    val socialNeeds: Long? = null,
    val strangerFriendly: Long? = null,
    val vocalisation: Long? = null,
    val experimental: Long? = null,
    val hairless: Long? = null,
    val natural: Long? = null,
    val rare: Long? = null,
    val rex: Long? = null,
    val suppressedTail: Long? = null,
    val shortLegs: Long? = null,
    val wikipediaURL: String? = null,
    val hypoallergenic: Long? = null,
    val referenceImageID: String? = null,
    val catFriendly: Long? = null,
    val bidability: Long? = null,
    var isFav: Boolean = false
)


data class Weight (
    val imperial: String,
    val metric: String
)
