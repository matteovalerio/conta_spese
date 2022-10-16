package it.matteo.data.user

import it.matteo.data.dtos.UserDto

interface UserRepository {
    fun createUser(userDto: UserDto)
}