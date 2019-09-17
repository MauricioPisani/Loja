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

import java.util.List;

import br.edu.ifsul.loja.R;
import br.edu.ifsul.loja.model.Cliente;

public class ClienteAdapter extends ArrayAdapter<Cliente> {
    private final Context context;

    {

}

    public ClienteAdapter(@NonNull Context context, @NonNull List<Cliente> clientes) {
        super(context, 0, clientes);
        this.context = context;
}
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_produto_adapter, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Cliente cliente = getItem(position);
        holder.tv_nome_cliente.setText(cliente.getNome());
        holder.tv_codigo_cliente.setText(cliente.getCodigoDeBarras().toString());
        holder.tv_cpf_cliente.setText(cliente.getCpf());

        return convertView;
    }

    private class ViewHolder{
        final TextView tv_nome_cliente;
        final TextView tv_codigo_cliente;
        final TextView tv_cpf_cliente;
        final ImageView iv_cliente;
        //final ProgressBar pbFotoDoCliente;

        public ViewHolder(View view){
            tv_nome_cliente = view.findViewById(R.id.tv_nome_cliente);
            tv_codigo_cliente= view.findViewById(R.id.tv_codigo_cliente);
            tv_cpf_cliente= view.findViewById(R.id.tv_cpf_cliente);
            iv_cliente= view.findViewById(R.id.iv_cliente);
            //pbFotoDoProduto = view.findViewById(R.id.pb_foto_produtos_adapter);
        }
    }
}
