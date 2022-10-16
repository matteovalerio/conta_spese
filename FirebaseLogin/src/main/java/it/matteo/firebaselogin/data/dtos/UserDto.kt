package it.matteo.firebaselogin.data.dtos

data class UserDto(val name: String, val email: String) {
    companion object {
        fun fromMap(map: Map<String, Any>): UserDto {
            return UserDto(
                name = if (map["name"] != null) map["name"] as String else "",
                email = if (map["email"] != null) map["email"] as String else ""
            )
        }
    }

    fun toMap(): Map<String, Any> {
        return hashMapOf(
            "name" to name,
            "email" to email
        )
    }
}