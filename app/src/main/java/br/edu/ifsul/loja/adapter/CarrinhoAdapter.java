package br.edu.ifsul.loja.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import br.edu.ifsul.loja.R;
import br.edu.ifsul.loja.model.ItemPedido;

public class CarrinhoAdapter extends ArrayAdapter<ItemPedido> {
    private final Context context;

    public CarrinhoAdapter(@NonNull Context context, @NonNull List<ItemPedido> carrinho) {
        super(context, 0,carrinho);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_carrinho_adapter, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        ItemPedido itemPedido = getItem(position);
        holder.tvNome.setText(itemPedido.getProduto().getNome());
        holder.tvQuantidade.setText(itemPedido.getQuantidade().toString());
        holder.tvValor.setText(NumberFormat.getCurrencyInstance().format(itemPedido.getProduto().getValor()*itemPedido.getQuantidade()));
        if(itemPedido.getProduto().getUrl_foto().equals("")){
            holder.pbFotoDoProduto.setVisibility(View.INVISIBLE);
        }else{
            //faz o downlod da foto aqui, usando API do serviço Storage
        }

        return convertView;
    }



    private class ViewHolder{
        final TextView tvNome;
        final TextView tvQuantidade;
        final TextView tvValor;
        final ImageView imvFoto;
        final ProgressBar pbFotoDoProduto;

        public ViewHolder(View view){
            tvNome = view.findViewById(R.id.tvNomeProdutoCarrinhoAdapter);
            tvQuantidade = view.findViewById(R.id.tvQuantidadeDeProdutoCarrinhoAdapater);
            tvValor = view.findViewById(R.id.tvTotalItemCarrinhoAdapter);
            imvFoto = view.findViewById(R.id.imvFotoProdutoCarrinhoAdapter);
            pbFotoDoProduto = view.findViewById(R.id.pb_foto_carrinho);
        }
    }
}
