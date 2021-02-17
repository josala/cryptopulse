package com.josala.domain.service

import com.josala.service.BaseService

abstract class BaseCoinmarketService : BaseService() {

    //For review convenience, api key hardcoded here instead of using gradle.properties (Global)
    protected val apiKey = "8f18164a-1980-4a77-a8d1-7873f1f97c01"
}