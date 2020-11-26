package com.dester.androidcourse2020github.notification

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.provider.SyncStateContract
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompatExtras
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
        lateinit var notification: NotificationCompat.Builder

        fun createNotification(context: Context, song: Song,icon:Boolean) {
            val notificationManagerCompat: NotificationManagerCompat =
                NotificationManagerCompat.from(context)
            val iconSet:Int = when (icon) {
                true -> {
                    R.drawable.ic_stop
                }
                false -> {
                    R.drawable.ic_play
                }
            }

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
                Intent(context, NotificationActionService::class.java).setAction(ACTION_PLAY)
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
            val notificationView:RemoteViews = RemoteViews(
                context.packageName,
                R.layout.notification_player
            )
            notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(song.title)
                .setContentText(song.author)
                .setContent(notificationView)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntentOpen)
                .setSmallIcon(R.drawable.note)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            notificationView.setImageViewResource(R.id.status_bar_play,iconSet)
            notificationView.setOnClickPendingIntent(R.id.status_bar_play, pendingIntentPlay)
            notificationView.setOnClickPendingIntent(R.id.status_bar_next, pendingIntentNext)
            notificationView.setOnClickPendingIntent(R.id.status_bar_prev, pendingIntentPrevious)
            notificationView.setTextViewText(R.id.status_bar_track_name, song.title)
            notificationView.setTextViewText(R.id.status_bar_artist_name, song.author)
            notificationView.setImageViewBitmap(R.id.status_bar_album_art,
                BitmapFactory.decodeResource(context.resources,song.poster))

            notificationManagerCompat.notify(1, this.notification.build())
        }
    }

}