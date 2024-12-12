package com.example.codeclicker.load

import android.app.Activity
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.GetPublicKeyCredentialOption
import com.example.codeclicker.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID

data class Registered(
    val userId: String,
    val created: Boolean
)

class LogInManager(private val activity: Activity) {

    val credentialManager = CredentialManager.create(activity)
    val auth = Firebase.auth
    val userId = auth.currentUser?.uid

    fun getCredentialOptions(requestJson: String) {
        val getPasswordOption = GetPasswordOption()

        val getPublicKeyCredentialOption = GetPublicKeyCredentialOption(
            requestJson = requestJson
        )
    }

    suspend fun signInGoogle(): Registered? {
        try {
            val ranNonce: String = UUID.randomUUID().toString()
            val bytes: ByteArray = ranNonce.toByteArray()
            val md: MessageDigest = MessageDigest.getInstance("SHA-256")
            val digest: ByteArray = md.digest(bytes)
            val hashedNonce: String = digest.fold("") { str, it -> str + "%02x".format(it) }

            // Set up Google ID option
            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(activity.getString(R.string.WebClient)) // Si no va probar AndroidClient
                .setNonce(hashedNonce)
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(activity, request)
            val credential = result.credential
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential =
                    GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential =
                    GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

                val a = auth.signInWithCredential(authCredential).await()
                println("SignInWithCredential:success")

                if (a.additionalUserInfo?.isNewUser == true) {
                    println("FALSO")
                    return userId?.let { Registered(userId = it, created = false) }
                } else if (a.additionalUserInfo?.isNewUser == false) {
                    println("VERDADERO")
                    return userId?.let { Registered(userId = it, created = true) }
                }

            } else {
                println("Error")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        println("ADIÓS")
        return null
    }
}
