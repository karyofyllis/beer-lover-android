package com.gkarbeerlover.giorgoskaryofyllis.beerlover.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gkarbeerlover.giorgoskaryofyllis.beerlover.R;
import com.gkarbeerlover.giorgoskaryofyllis.beerlover.models.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {


    private List<Ingredient> mIngredients;

    public IngredientAdapter (List<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_ingredient, viewGroup, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder ingredientViewHolder, int i) {
        ingredientViewHolder.bindIngredient(mIngredients.get(i));
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvUnit;
        private TextView tvValue;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_ing_name);
            tvUnit = itemView.findViewById(R.id.tv_ing_unit);
            tvValue = itemView.findViewById(R.id.tv_ing_value);
        }


        public void bindIngredient(Ingredient ingredient) {
            tvName.setText(ingredient.getName());
            tvUnit.setText(ingredient.getUnit());
            tvValue.setText(ingredient.getValue());
        }
    }

}
