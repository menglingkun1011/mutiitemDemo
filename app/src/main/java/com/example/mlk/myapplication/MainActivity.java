package com.example.mlk.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mlk.myapplication.bean.ProductBean;
import com.example.mlk.myapplication.view.MultiItemDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int ITEM_TEXT_TYPE = 0;
    public static final int ITEM_PIC_TYPE = 1;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter adapter;
    private Random random;
    private int[] businessAar = {300500,300800};
    private List<ProductBean> srcData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        random = new Random();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        //添加自定义分割线
        MultiItemDivider divider = new MultiItemDivider(this,DividerItemDecoration.VERTICAL,ContextCompat.getDrawable(this,R.drawable.custom_divider));
        recyclerView.addItemDecoration(divider);

        srcData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            srcData.add(new ProductBean("产品-------------------------》"+i,random.nextInt(100)+"元",300500));
        }
        adapter = new MyAdapter(srcData,this);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (view.getId()){
                    case R.id.ll_pic_layout:
                        Toast.makeText(MainActivity.this,"图文",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ll_text_layout:
                        Toast.makeText(MainActivity.this,"文本",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.iv:
                        Toast.makeText(MainActivity.this,"iv",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv:
                        if(srcData.get(position).getBusinessCode() == 300500){
                            Toast.makeText(MainActivity.this,"tv-->"+srcData.get(position).getBusinessCode(),Toast.LENGTH_SHORT).show();
                        }else  if(srcData.get(position).getBusinessCode() == 300800){
                            Toast.makeText(MainActivity.this,"tv-->"+srcData.get(position).getBusinessCode(),Toast.LENGTH_SHORT).show();
                        }

                        break;
                }
            }
        });
    }

    private static class MyAdapter extends RecyclerView.Adapter{

        private List<ProductBean> data;
        private Context context;

        public MyAdapter(List<ProductBean> data, Context context) {
            this.data = data;
            this.context = context;
        }

        private OnItemClickListener clickListener;

        public void setClickListener(OnItemClickListener clickListener) {
            this.clickListener = clickListener;
        }

        public interface OnItemClickListener {
            void onClick(View view, int position);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            View view = null;
            switch (viewType){
                case ITEM_PIC_TYPE:
                    view = LayoutInflater.from(context).inflate(R.layout.rv_item_pic,parent,false);
                    viewHolder = new PicViewHolder(view);
                    break;
                case ITEM_TEXT_TYPE:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_text,parent,false);
                    viewHolder = new TextViewHolder(view);
                    break;
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ProductBean productBean = data.get(position);

            if(holder instanceof TextViewHolder){
                productBean.setBusinessCode(300500);
                ((TextViewHolder)holder).fillData(productBean);
            }else if(holder instanceof PicViewHolder){
                productBean.setBusinessCode(300800);
                ((PicViewHolder)holder).fillData(productBean);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if(data.get(position).getName().contains("2")){
                return ITEM_PIC_TYPE;
            }
            return ITEM_TEXT_TYPE;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView tv;

            public TextViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                itemView.setOnClickListener(this);
                tv.setOnClickListener(this);
            }

            public void fillData(ProductBean productBean){
                tv.setText(productBean.getName());
            }

            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onClick(v,getAdapterPosition());
                }
            }
        }


        class PicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView tv;
            public ImageView iv;

            public PicViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                iv = itemView.findViewById(R.id.iv);
                itemView.setOnClickListener(this);
                iv.setOnClickListener(this);
                tv.setOnClickListener(this);
            }

            public void fillData(ProductBean productBean){
                tv.setText(productBean.getName());
            }

            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onClick(v,getAdapterPosition());
                }
            }
        }
    }
}
