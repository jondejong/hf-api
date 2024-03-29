package hf.api.database

import com.zaxxer.hikari.HikariConfig
import hf.api.properties.DatabaseProperties

class DatabaseConfig : HikariConfig() {
    companion object {
        fun build(databaseProperties: DatabaseProperties): DatabaseConfig {
            val config = DatabaseConfig()
            config.jdbcUrl = databaseProperties.url
            config.password = databaseProperties.password
            config.username = databaseProperties.username
            return config
        }
    }
}