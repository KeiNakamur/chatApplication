package com.example.chatapplicationusingkotlin

class Message {
    //messageを一時的に保存するためのクラス
    var message: String? = null
    var sendId: String? = null

    constructor(){}

    constructor(message: String?, sendId: String?){
        this.message = message
        this.sendId = sendId
    }
}