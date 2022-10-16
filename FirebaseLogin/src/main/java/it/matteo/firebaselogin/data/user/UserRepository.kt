package it.matteo.firebaselogin.data.user

import it.matteo.firebaselogin.data.dtos.UserDto

interface UserRepository {
    fun createUser(userDto: UserDto)
}