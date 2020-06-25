package com.example.exercicio1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

  class Pessoa(var nome: String)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Lista de componentes
    val lista = findViewById<RecyclerView>(R.id.lista_de_pessoas)

    // Lista de dados
    val pessoas = mutableListOf<Pessoa>()
    for (i in 1..100) {
      pessoas.add(Pessoa("Pessoa $i"))
    }

    val linearLayoutManager = LinearLayoutManager(this)

    lista.apply {
      setHasFixedSize(true)
      adapter = AdaptadorListaPessoas(pessoas)
      layoutManager = linearLayoutManager
    }
  }

  class AdaptadorListaPessoas(private val pessoas: List<Pessoa>) :
    RecyclerView.Adapter<AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
      Log.i("ADAPTER", "onCreateViewHolder")
      return AdapterViewHolder(
        LayoutInflater.from(parent.context)
          .inflate(
            R.layout.item_pessoa,
            parent,
            false
          )
      )
    }

    override fun getItemCount(): Int {
      return pessoas.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
      Log.i("POSIÇÃO", "$position")
      val pessoa = pessoas[position]
      holder.insereDadosDePessoaNoItemDaLista(pessoa)
    }

  }

  class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun insereDadosDePessoaNoItemDaLista(pessoa: Pessoa) {
      val componenteDeTextoDeNome = itemView.findViewById<TextView>(R.id.text_nome_pessoa)
      componenteDeTextoDeNome.text = pessoa.nome
    }
  }
}
