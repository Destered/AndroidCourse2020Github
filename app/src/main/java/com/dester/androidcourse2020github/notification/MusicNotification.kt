package com.dester.androidcourse2020github.notification

import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.activity.MusicDetailed
import com.dester.androidcourse2020github.model.Song
import com.dester.androidcourse2020github.services.NotificationActionService


final class MusicNotification {
    companion object {
        val CHANNEL_ID: String = "chanel1"
        val ACTION_PREVIOUS: String = "previousaction"
        val ACTION_PLAY: String = "playaction"
        val ACTION_NEXT: String = "nextaction"
        lateinit var notification: Notification

        fun createNotification(context: Context, song: Song) {
            val notificationManagerCompat: NotificationManagerCompat =
                NotificationManagerCompat.from(context)
            val mediaSessionCompat: MediaSessionCompat = MediaSessionCompat(context, "musicTag")
            val icon = BitmapFactory.decodeResource(context.resources, song.poster)

            val pendingIntentPrevious: PendingIntent?
            val intentPrevious =
                Intent(context, NotificationActionService::class.java).setAction(ACTION_PREVIOUS)
            pendingIntentPrevious = PendingIntent.getBroadcast(
                context,
                0,
                intentPrevious,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val pendingIntentPlay: PendingIntent?
            val intentPlay =
                Intent(context, NotificationActionService::class.java).setAction(ACTION_PREVIOUS)
            pendingIntentPlay = PendingIntent.getBroadcast(
                context,
                0,
                intentPlay,
                PendingIntent.FLAG_UPDATE_CURRENT
            )


            val pendingIntentNext: PendingIntent?
            val intentNext =
                Intent(context, NotificationActionService::class.java).setAction(ACTION_NEXT)
            pendingIntentNext = PendingIntent.getBroadcast(
                context,
                0,
                intentNext,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val pendingIntentOpen: PendingIntent?
            val intentOpen = Intent(context, MusicDetailed::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
            }
            intentOpen.putExtra("source", "notification")
            intentOpen.putExtra("song", song)
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addNextIntent(intentOpen)
            Log.d("Dest/Intent/MusicNotification", "intentOpenSong:$song")
            pendingIntentOpen = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

            notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(song.title)
                .setContentText(song.author)
                .setOnlyAlertOnce(true)
                .setLargeIcon(icon)
                .setContentIntent(pendingIntentOpen)
                .addAction(R.drawable.previous, "Previous", pendingIntentPrevious)
                .addAction(R.drawable.play, "Play", pendingIntentPlay)
                .addAction(R.drawable.nerv, "Next", pendingIntentNext)
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                        .setMediaSession(mediaSessionCompat.sessionToken)
                )
                .setSmallIcon(R.drawable.note)
                .setShowWhen(false)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()


            notificationManagerCompat.notify(1, notification)
        }
    }
}