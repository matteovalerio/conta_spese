package it.matteo.data.user

import com.google.firebase.firestore.FirebaseFirestore
import it.matteo.data.dtos.UserDto
import it.matteo.data.exceptions.FirestoreException

class UserRepositoryImpl (val firestore: FirebaseFirestore): UserRepository {
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