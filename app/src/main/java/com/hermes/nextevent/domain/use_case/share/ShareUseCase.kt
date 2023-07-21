package com.hermes.nextevent.domain.use_case.share

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent

class ShareUseCase {
    operator fun invoke(text: String, context: Context): Boolean {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, "Compartilhar via")
        return try {
            context.startActivity(shareIntent)
            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }

}