package it.matteo.firebaselogin.data.user

import com.google.firebase.firestore.FirebaseFirestore
import it.matteo.firebaselogin.data.dtos.UserDto
import it.matteo.firebaselogin.data.exceptions.FirestoreException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val firestore: FirebaseFirestore): UserRepository {
    private val _documentName = "user"

    override fun createUser(userDto: UserDto) {
        firestore
            .collection(_documentName)
            .document(userDto.email)
            .set(userDto.toMap())
            .addOnFailureListener {
                throw FirestoreException(it.message.toString())
            }
    }
}