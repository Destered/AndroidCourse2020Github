package com.dester.androidcourse2020github

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.alert_dialog.*

class AddDialogFragment:DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Create")
       val v = inflater.inflate(R.layout.alert_dialog,null)
        val btn_ok = v.findViewById<Button>(R.id.btn_ok)
        val btn_cancle = v.findViewById<Button>(R.id.btn_cancle)
        btn_ok.setOnClickListener {
            val title = et_title.text.toString()
            val about = et_about.text.toString()
            val author = et_author.text.toString()
            val id = Integer.parseInt(et_id.text.toString())
            val position = Integer.parseInt(et_position.text.toString())
            val intent = Intent()
            intent.putExtra("title",title)
            intent.putExtra("about",about)
            intent.putExtra("author",author)
            intent.putExtra("id",id)
            intent.putExtra("position",position)
            /*parentFragment?.onActivityResult(9,RESULT_OK,activity?.intent)*/
            targetFragment?.onActivityResult(42,RESULT_OK,intent)
            dismiss()
        }
        btn_cancle.setOnClickListener {
            /*parentFragment?.onActivityResult(9, RESULT_CANCELED,activity?.intent)*/
            targetFragment?.onActivityResult(42, RESULT_CANCELED,Intent())
            dismiss()
        }
        return v
    }



}