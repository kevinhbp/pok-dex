package id.kputro.pkdex.core.entities.region

import id.kputro.pkdex.core.entities.base.BaseResponse
import java.io.Serializable

data class GetRegionResponse(val data: List<Region>?): BaseResponse(), Serializable