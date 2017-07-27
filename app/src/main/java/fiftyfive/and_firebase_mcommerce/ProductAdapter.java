package fiftyfive.and_firebase_mcommerce;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Francois on 28/07/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    //tweets est la liste des models à afficher
    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_product,parent, false);
        }

        ProductViewHolder viewHolder = (ProductViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ProductViewHolder();
            viewHolder.productName = (TextView) convertView.findViewById(R.id.productName);
            viewHolder.productDescription = (TextView) convertView.findViewById(R.id.productDescription);
            viewHolder.productImage = (ImageView) convertView.findViewById(R.id.productImage);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Product product = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.productName.setText(product.getName());
        viewHolder.productDescription.setText(product.getDesc());
        viewHolder.productImage.setImageDrawable(new ColorDrawable(product.getColor()));

        return convertView;
    }

    private class ProductViewHolder{
        public TextView productName;
        public TextView productDescription;
        public ImageView productImage;
    }
}