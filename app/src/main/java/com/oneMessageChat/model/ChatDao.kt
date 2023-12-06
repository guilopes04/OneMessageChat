package com.oneMessageChat.model

interface ChatDao {
    fun createChat(chat: Chat): Int
    fun readChat(id: String): Chat?
    fun readAllChats(): MutableList<Chat>
    fun updateChat(chat: Chat): Int
}