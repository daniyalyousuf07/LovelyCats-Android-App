import com.google.gson.annotations.SerializedName

data class BreedModel (
    val weight: Weight,
    val id: String,
    val name: String,
    @SerializedName("url") val cfaURL: String?,
    val vetstreetURL: String? = null,
    val vcahospitalsURL: String? = null,
    val temperament: String,
    val origin: String,
    val countryCodes: String,
    val countryCode: String,
    val description: String,
    val lifeSpan: String,
    val indoor: Long,
    val lap: Long? = null,
    val altNames: String? = null,
    val adaptability: Long,
    val affectionLevel: Long,
    val childFriendly: Long,
    val dogFriendly: Long,
    val energyLevel: Long,
    val grooming: Long,
    val healthIssues: Long,
    val intelligence: Long,
    val sheddingLevel: Long,
    val socialNeeds: Long,
    val strangerFriendly: Long,
    val vocalisation: Long,
    val experimental: Long,
    val hairless: Long,
    val natural: Long,
    val rare: Long,
    val rex: Long,
    val suppressedTail: Long,
    val shortLegs: Long,
    val wikipediaURL: String? = null,
    val hypoallergenic: Long,
    val referenceImageID: String? = null,
    val catFriendly: Long? = null,
    val bidability: Long? = null
)

data class Weight (
    val imperial: String,
    val metric: String
)
