package it.matteo.firebaselogin.presentation.auth

enum class AuthScreenName {
    Login,
    SignIn {
        override fun toString(): String {
            return "Sign-in"
        }
           },
    ForgotPassword {
        override fun toString(): String {
            return "Forgot Password"
        }
    }
}