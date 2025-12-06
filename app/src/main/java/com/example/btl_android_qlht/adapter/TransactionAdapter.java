package com.example.btl_android_qlht.adapter; // Đổi package cho phù hợp

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.btl_android_qlht.R;
import com.example.btl_android_qlht.db.entity.Transaction;
import java.text.DecimalFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> list;

    public TransactionAdapter(List<Transaction> list) {
        this.list = list;
    }

    public void setData(List<Transaction> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sử dụng lại layout item_transaction.xml mà mình đã hướng dẫn ở các bài trước
        // Hoặc tạo layout đơn giản chứa các TextView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction item = list.get(position);
        if (item == null) return;
        if (item.getSubjectId() == null) {
            holder.tvTitle.setText("Nạp tiền vào tài khoản");
        } else {
            holder.tvTitle.setText("Thu học phí môn: " + item.getSubjectId());
        }
        // -------------------------------------

        holder.tvTime.setText(item.getTime());

        DecimalFormat formatter = new DecimalFormat("#,### đ");
        // Xử lý màu sắc tiền
        if (item.getAmount() >= 0) {
            holder.tvAmount.setTextColor(Color.parseColor("#4CAF50")); // Xanh
            holder.tvAmount.setText("+ " + formatter.format(item.getAmount()));
        } else {
            holder.tvAmount.setTextColor(Color.parseColor("#D32F2F")); // Đỏ
            holder.tvAmount.setText(formatter.format(item.getAmount()));
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvTime, tvAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_trans_name);
            tvTime = itemView.findViewById(R.id.tv_trans_time);
            tvAmount = itemView.findViewById(R.id.tv_trans_amount);
        }
    }
}