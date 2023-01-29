package com.jondejong.hf.api.app

import com.jondejong.hf.api.exception.IllegalDataStateException
import com.jondejong.hf.api.exception.ItemNotFoundException

class ServiceUtils<T> {

    fun validateUniqueInstance(objects: Collection<T>): T {
        if (objects.size > 1) {
            throw IllegalDataStateException()
        }
        if (objects.isEmpty()) {
            throw ItemNotFoundException()
        }
        return objects.first()
    }

}