package hf.api.app

import java.util.*

abstract class HFService<T> : HFConfigurable {

    fun validateUniqueInstance(objects: Collection<T>): T {
        return ServiceUtils<T>().validateUniqueInstance(objects)
    }

    fun validateUniqueInstance(objects: Collection<Int>): Int {
        return ServiceUtils<Int>().validateUniqueInstance(objects)
    }

    fun validateUniqueInstance(objects: Collection<UUID>): UUID {
        return ServiceUtils<UUID>().validateUniqueInstance(objects)
    }

    fun validateUniqueInstance(objects: Collection<String>): String {
        return ServiceUtils<String>().validateUniqueInstance(objects)
    }
}
