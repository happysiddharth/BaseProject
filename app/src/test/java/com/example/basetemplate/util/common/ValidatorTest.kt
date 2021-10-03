package com.example.basetemplate.util.common

import com.example.basetemplate.R
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.Assert.assertThat
import org.junit.Test

class ValidatorTest {
    @Test
    fun givenValidEmailAndValidPwd_whenValidate_shouldReturnSuccess(){
        val email = "test@gmail.com"
        val password = "123456"
        val validators = Validator.validateLoginFields(email,password)
        assertThat(validators,hasSize(2))
        assertThat(
            validators,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenInvalidEmailAndValidPwd_whenValidate_shouldReturnEmailError(){
        val email = "test"
        val password = "123456"
        val validators = Validator.validateLoginFields(email,password)
        assertThat(validators,hasSize(2))
        assertThat(
            validators,
            contains(
                Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_invalid)),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenValidEmailAndInvalidPwd_whenValidate_shouldReturnPwdError(){
        val email = "test@gmail.com"
        val password = "123"
        val validators = Validator.validateLoginFields(email,password)
        assertThat(validators, hasSize(2))
        assertThat(
            validators,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_small_length))
            )
        )
    }


}