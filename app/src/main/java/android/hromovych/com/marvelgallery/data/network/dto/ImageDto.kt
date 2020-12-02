package android.hromovych.com.marvelgallery.data.network.dto

class ImageDto {
    lateinit var path: String
    lateinit var extension: String

    val completeImagePath: String
        get() = "$path.$extension"
}