package layout;

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
            tvNome = view.findViewById(R.id.tvNomeCarrinhoAdapter);
            tvQuantidade = view.findViewById(R.id.tvQuantidadeDeProdutoCarrinhoAdapater);
            tvValor = view.findViewById(R.id.tvTotalItemCarrinhoAdapter);
            imvFoto = view.findViewById(R.id.imvFotoProdutoCarrinhoAdapter);
            pbFotoDoProduto = view.findViewById(R.id.pb_foto_carrinho);
        }
    }