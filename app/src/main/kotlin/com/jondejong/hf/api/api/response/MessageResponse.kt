package com.jondejong.hf.api.api.response

class MessageResponse(val messsage: String) {
    companion object {
        fun success(): MessageResponse {
            return MessageResponse(
                messsage = "SUCCESS"
            )
        }
    }
}