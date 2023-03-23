package id.kputro.pkdex.core.entities.login

import id.kputro.pkdex.core.entities.base.BaseResponse
import java.io.Serializable

data class DoLoginResponse(val data: LoginResponse?) : Serializable, BaseResponse()