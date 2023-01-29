package com.jondejong.hf.api.app

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL

open class HFRepository : HFConfigurable {
    lateinit var context: DSLContext

    override fun setUp(factory: AppFactory) {
        context = DSL.using(factory.dataSource, SQLDialect.POSTGRES)
    }
}