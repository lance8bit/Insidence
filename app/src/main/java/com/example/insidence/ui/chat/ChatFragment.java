package com.example.insidence.ui.chat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.insidence.ChatModel;
import com.example.insidence.R;
import com.example.insidence.messageModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChatFragment extends Fragment {

    private FirebaseDatabase mDatabase;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerViewChat;
    private DatabaseReference refMessages;

    public ChatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewChat.setLayoutManager(linearLayoutManager);
        recyclerViewChat.setHasFixedSize(true);

        Bundle b = this.getArguments();
        if (b != null) {
            String idUser = b.getString("idUser");
            String idTech = b.getString("idTech");
            String idInc = b.getString("idInc");

            mDatabase = FirebaseDatabase.getInstance();
            refMessages = mDatabase.getReference("messages");

            Query query = refMessages.child("idIncidencia").orderByChild("timestamp").equalTo(idInc);
            FirebaseRecyclerOptions<ChatModel> options =
                    new FirebaseRecyclerOptions.Builder<ChatModel>()
                        .setQuery(query, ChatModel.class)
                        .build();

            FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<ChatModel, ChatHolder>(options) {

                @NonNull
                @Override
                public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.single_message_recycler, parent, false);
                    return new ChatHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull ChatHolder holder, int position, @NonNull ChatModel model) {

                }
            };

        }

        /*

        FirebaseRecyclerOptions<ChatModel> options=
                new FirebaseRecyclerOptions.Builder<ChatModel>()
                .setQuery(refMessages, ChatModel.class)
                .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<ChatModel, MessageViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull ChatModel chatMessage) {
                holder.bindMessage(chatMessage);
            }

            @NonNull
            @Override
            public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MessageViewHolder(inflater.inflate(R.layout.single_message_recycler, parent, false));
            }
        };

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setStackFromEnd(true);

        */

        return view;
    }

    public class ChatHolder extends RecyclerView.ViewHolder{

        public LinearLayout messageContainer;
        public TextView messageTxV;
        public TextView userTxV;

        public ChatHolder( View itemView) {
            super(itemView);
            messageContainer = itemView.findViewById(R.id.entryMessageContainer);
            messageTxV = itemView.findViewById(R.id.messageTextView);
            userTxV = itemView.findViewById(R.id.messengerTextView);
        }

        public void setTextMessage (String string){
            messageTxV.setText(string);
        }

        public void setTextUser (String string){
            userTxV.setText(string);
        }

    }

}