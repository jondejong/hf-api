package hf.api.app

import hf.api.exception.IllegalDataStateException
import hf.api.exception.ItemNotFoundException

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