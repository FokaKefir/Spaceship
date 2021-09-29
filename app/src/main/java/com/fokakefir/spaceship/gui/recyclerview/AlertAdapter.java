package com.fokakefir.spaceship.gui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.model.Alert;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    // region 1. Decl and Init

    private List<Alert> alerts;

    // endregion

    // region 2. Constructor

    public AlertAdapter(List<Alert> alerts) {
        this.alerts = alerts;
    }

    // endregion

    // region 3. Adapter

    @NonNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_alert, parent, false);
        AlertViewHolder viewHolder = new AlertViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
        Alert currentAlert = this.alerts.get(position);

        switch (currentAlert.getRoomId()) {
            case Alert.ROOM_COMMANDER_ID:
                holder.imgRoomIcon.setImageResource(R.drawable.ic_commander);
                break;
            case Alert.ROOM_MEDICAL_ID:
                holder.imgRoomIcon.setImageResource(R.drawable.ic_medical);
                break;
            case Alert.ROOM_LAB_ID:
                holder.imgRoomIcon.setImageResource(R.drawable.ic_lab);
                break;
            case Alert.ROOM_TECH_ID:
                holder.imgRoomIcon.setImageResource(R.drawable.ic_technology);
                break;
            case Alert.ROOM_AIRLOCK:
                holder.imgRoomIcon.setImageResource(R.drawable.ic_door);
                break;
        }

        holder.txtCode.setText(currentAlert.getCode());
        holder.txtDate.setText(currentAlert.getDate());
        holder.txtType.setText(currentAlert.getType());
        holder.txtProblem.setText(currentAlert.getProblem());
    }

    @Override
    public int getItemCount() {
        return this.alerts.size();
    }

    // endregion

    // region 4. Holder class

    public class AlertViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgRoomIcon;

        public TextView txtCode;
        public TextView txtDate;
        public TextView txtType;
        public TextView txtProblem;

        public AlertViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imgRoomIcon = itemView.findViewById(R.id.img_alert_room_icon);
            this.txtCode = itemView.findViewById(R.id.txt_alert_code);
            this.txtDate = itemView.findViewById(R.id.txt_alert_date);
            this.txtType = itemView.findViewById(R.id.txt_alert_type);
            this.txtProblem = itemView.findViewById(R.id.txt_alert_problem);
        }
    }

    // endregion
}
