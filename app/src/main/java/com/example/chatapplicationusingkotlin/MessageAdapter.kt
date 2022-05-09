package com.example.chatapplicationusingkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


//recyclerViewとsendViewHolder、messageReceiveViewHolderを作成

//ArrayList<Message>はMessage.ktを参照
class MessageAdapter(val context: Context, val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_SENT = 2
    val ITEM_RECEIVED = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 1){
            //inflate received
            val view: View = LayoutInflater.from(context).inflate(R.layout.message_recieve, parent, false)
            return ReceiveViewHolder(view)
        }else{
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SendViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        //if holderがsendViewHolderの時にメッセージを送る処理
        if(holder.javaClass == SendViewHolder::class.java){
            //sendViewHolderの処理
            val viewHolder = holder as SendViewHolder
            //.messageはMessage.ktで宣言したmessage
            holder.messageSent.text = currentMessage.message

        }else{
            //receiveの処理
            val viewHolder = holder as ReceiveViewHolder
            holder.messageReceived.text = currentMessage.message
        }
    }

    //getItemViewTypeではリスト項目の場所(インデックス)をうけとり、それに応じたViewTypeの識別子を返す
    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]
        //相手、自分を識別するためにMessage.ktで宣言してるsenderIdとFirebaseAuthで登録しているcurrentUserと同じ場合と異なる場合で処理するため
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sendId)){
            //sendIdは送信した人なのでこの場合 ITEM_SENTを返す
            return ITEM_SENT
        }else{
            return ITEM_RECEIVED
        }

        //return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //layoutのsent.xmlを参照
        val messageSent = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    class ReceiveViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        //layoutのmessage_receiveを参照
        val messageReceived = itemView.findViewById<TextView>(R.id.txt_received_message)

    }
}