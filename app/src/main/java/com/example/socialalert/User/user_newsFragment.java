package com.example.socialalert.User;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.socialalert.News.Articles;
import com.example.socialalert.News.NewsModel;
import com.example.socialalert.News.RetrofitAPI;
import com.example.socialalert.News.categoryAdapter;
import com.example.socialalert.News.categoryModel;
import com.example.socialalert.R;
import com.example.socialalert.News.Retrofitinstance;
import com.example.socialalert.News.newsAdapter;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_newsFragment extends Fragment implements categoryAdapter.Catagory_click_interface {
    private RecyclerView catagories;
    private RecyclerView news;
    private ProgressBar pb;
    private ArrayList<Articles> news_article;
    private ArrayList<categoryModel> news_catagory;
    private categoryAdapter cd;
    private newsAdapter nd;

    public user_newsFragment() {
        // Required empty public constructor
    }

    public static user_newsFragment newInstance() {
        return new user_newsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_news, container, false);

        catagories = v.findViewById(R.id.category);
        news = v.findViewById(R.id.allnews);
        pb = v.findViewById(R.id.progressbar);

        news_catagory = new ArrayList<>();
        news_article = new ArrayList<>();

        cd = new categoryAdapter(news_catagory, requireContext(), this::catagory_click);
        nd = new newsAdapter(news_article, requireContext());

        news.setLayoutManager(new LinearLayoutManager(requireContext()));
        news.setAdapter(nd);
        catagories.setAdapter(cd);

        get_catagory();
        get_news("All");
        nd.notifyDataSetChanged();

        return v;
    }

    @Override
    public void catagory_click(int position) {
        get_news(news_catagory.get(position).getC_name());
    }

    private void get_catagory() {
        news_catagory.add(new categoryModel("All"));
        news_catagory.add(new categoryModel("Science"));
        news_catagory.add(new categoryModel("Business"));
        news_catagory.add(new categoryModel("Entertainment"));
        news_catagory.add(new categoryModel("General"));
        news_catagory.add(new categoryModel("Health"));
        news_catagory.add(new categoryModel("Sports"));
        news_catagory.add(new categoryModel("Technology"));

        cd.notifyDataSetChanged();
    }

    private void get_news(String cat) {
        pb.setVisibility(View.VISIBLE);
        news_article.clear();

        String url = "https://newsapi.org/v2/top-headlines?country=in&category=" + cat + "&apiKey=99701a83819b4675af200302f724fc20";
        String allnews = "https://newsapi.org/v2/top-headlines?country=in&apiKey=99701a83819b4675af200302f724fc20";

        RetrofitAPI retro = Retrofitinstance.getRetrofit().create(RetrofitAPI.class);

        Call<NewsModel> call;
        call = cat.equals("All") ? retro.getallnews(allnews) : retro.getallnews(url);

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                Toast.makeText(requireContext(), cat, Toast.LENGTH_SHORT).show();

                NewsModel newsModel = response.body();
                pb.setVisibility(View.GONE);

                ArrayList<Articles> art = newsModel.getarticles();
                System.out.println(art);

                if (art == null)
                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < art.size(); i++) {
                    news_article.add(new Articles(
                            art.get(i).getTitle(),
                            art.get(i).getDescription(),
                            art.get(i).getUrlToImage(),
                            art.get(i).getUrl(),
                            art.get(i).getContent()
                    ));
                }
                nd.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
