package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageToCacheSaverImpl @Inject constructor(
    private val gitHubUsersRepository: GitHubUsersRepository,
    private val context: Context
) : ImageToCacheSaver {
    override fun save(userId: Long, bitmap: Bitmap): Single<String> {
        val outputFileName = "${context.externalCacheDir.toString()}/${userId}.jpg"
        return Completable.fromAction {
            val outputFile = File(outputFileName).apply {
                createNewFile()
            }
            val uri: Uri = Uri.fromFile(outputFile)
            context.contentResolver.openFileDescriptor(uri, "w")?.use {
                FileOutputStream(it.fileDescriptor).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }
            }
        }.andThen(gitHubUsersRepository.saveAvatarCachedPath(userId, outputFileName))
    }
}
