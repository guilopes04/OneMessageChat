package com.oneMessageChat.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oneMessageChat.databinding.ChatActivityBinding
import com.oneMessageChat.model.Chat
import com.oneMessageChat.model.Constants.EXTRA_CHAT
import com.oneMessageChat.model.Constants.VIEW_CHAT

class ChatActivity : AppCompatActivity() {
    private val cab: ChatActivityBinding by lazy {
        ChatActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(cab.root)

        setSupportActionBar(cab.toolbarIn.toolbar)
        supportActionBar?.subtitle = "Chat details"

        val receivedChat = intent.getParcelableExtra<Chat>(EXTRA_CHAT)
        receivedChat?.let {_receivedChat ->
            val viewChat: Boolean = intent.getBooleanExtra(VIEW_CHAT, false)
            with(cab) {
                if (viewChat) {
                    idEt.isEnabled = false
                    messageEt.isEnabled = false
                    saveBt.visibility = View.GONE
                }
                idEt.isEnabled = false
                idEt.setText(_receivedChat.id)
                messageEt.setText(_receivedChat.message)

            }


        }

        with(cab) {
            saveBt.setOnClickListener {
                if (idEt.text.isEmpty()) {
                    Toast.makeText(
                        this@ChatActivity,
                        "O ID não pode ser vazio.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (messageEt.text.isEmpty()) {
                    Toast.makeText(
                        this@ChatActivity,
                        "A mensagem não pode ser vazia.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                else {
                    val chat = Chat(
                        id = idEt.text.toString(),
                        message = messageEt.text.toString()
                    )

                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_CHAT, chat)
                    setResult(RESULT_OK, resultIntent)

                    finish()
                }

            }
        }
    }

}