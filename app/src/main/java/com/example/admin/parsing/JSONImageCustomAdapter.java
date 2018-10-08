package com.example.admin.parsing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JSONImageCustomAdapter extends BaseAdapter {
    Context context;
    List<DataShowWithURL> dataShowWithURLS;
    ImageView imageView;
    TextView textView,population;
    public JSONImageCustomAdapter(Context context, List<DataShowWithURL> arrayList) {
        this.context = context;
        this.dataShowWithURLS = arrayList;
    }

    @Override
    public int getCount() {
        return dataShowWithURLS.size();
    }

    @Override
    public Object getItem(int i) {
        return dataShowWithURLS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 ;
        view = LayoutInflater.from(context).inflate(R.layout.show_data_with_image,viewGroup,false);
        view1 = view;
        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.name);
        population = view.findViewById(R.id.popu);
        textView.setText(dataShowWithURLS.get(i).getRank());
        population.setText(dataShowWithURLS.get(i).getPopulation());
        Picasso.get().load(dataShowWithURLS.get(i).getFlag()).into(imageView);
       /* String url = dataShowWithURLS.get(i).getFlag();
        new DownloadPage(imageView).execute(url);*/
        return view1;
    }
    /*private class DownloadPage extends AsyncTask<String,Void,Bitmap>
    {
        ImageView imageView;
        public DownloadPage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setTitle("Download Image Know");
//            progressDialog.setMessage("Loading...");
//            progressDialog.setIndeterminate(false);
//            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageURL = strings[0];

            Bitmap bitmap = null;
            try {
                //   Download Image from URL

                InputStream input = new URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
                input.close();
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
//            progressDialog.dismiss();
        }
    }*/
}

