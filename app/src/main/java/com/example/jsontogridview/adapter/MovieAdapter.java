package com.example.jsontogridview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsontogridview.R;
import com.example.jsontogridview.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private Context context;
    private int idLayout;
    private ArrayList<Movie> list;
    private ViewHolder holder;

    public MovieAdapter(Context context, int idLayout, ArrayList<Movie> list) {
        super(context, idLayout, list);
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(idLayout, parent, false);
            holder = new ViewHolder();

            holder.tvTieuDe = convertView.findViewById(R.id.tvTieuDe);
            holder.tvNam = convertView.findViewById(R.id.tvNam);
            holder.ivPoster = convertView.findViewById(R.id.ivPoster);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Movie movie = list.get(position);
        holder.tvTieuDe.setText(movie.getTitle());
        holder.tvNam.setText(movie.getYear());
        // Sử dụng Picasso hoặc Glide để hiển thị ảnh từ URL
        Picasso.get().load(movie.getImage()).into(holder.ivPoster);

        return convertView;
    }

    private class ViewHolder {
        TextView tvTieuDe;
        TextView tvNam;
        ImageView ivPoster;
    }
}
