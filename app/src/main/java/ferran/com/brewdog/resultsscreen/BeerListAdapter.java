package ferran.com.brewdog.resultsscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ferran.com.brewdog.R;
import ferran.com.brewdog.model.Beer;
import ferran.com.brewdog.util.PicassoHandler;

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Beer> beers;
    private ResultsScreenContract.Presenter presenter;

    BeerListAdapter(Context context, ArrayList<Beer> beers, ResultsScreenContract.Presenter presenter) {
        this.context = context;
        this.beers = beers;
        this.presenter = presenter;
    }

    void replaceData(ArrayList<Beer> beers) {
        setList(beers);
        notifyDataSetChanged();
    }

    private void setList(ArrayList<Beer> beers) {
        if (beers!=null)
            this.beers = beers;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.beer_image)
        ImageView beerImage;
        @BindView(R.id.beer_name)
        TextView beerName;
        @BindView(R.id.beer_tagline)
        TextView beerTagline;
        @BindView(R.id.beer_description)
        TextView beerDescription;
        @BindView(R.id.beer_abv)
        TextView beerABV;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_beers, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Beer beer = beers.get(position);
        PicassoHandler.getInstance(context).getPicasso()
                .load(beer.getImage_url())
                .placeholder(R.drawable.brewdog)
                .error(R.drawable.brewdog)
                .into(holder.beerImage);
        holder.beerName.setText(beer.getName());
        holder.beerTagline.setText(beer.getTagline());
        holder.beerDescription.setText(beer.getDescription());
        holder.beerABV.setText(String.valueOf(beer.getAbv()));
    }

    @Override
    public int getItemCount() {
        return beers!=null?beers.size():0;
    }
}