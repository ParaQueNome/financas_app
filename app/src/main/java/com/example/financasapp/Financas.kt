package com.example.financasapp

import ExpenseAdapter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class Financas : AppCompatActivity() {
    private lateinit var salaryAmount: TextView
    private lateinit var expenseAmount: TextView
    private lateinit var expenseNameInput: TextInputEditText
    private lateinit var expenseInput: TextInputEditText
    private lateinit var addButton: MaterialButton
    private lateinit var expenseRecyclerView: RecyclerView
    private lateinit var expenseAdapter: ExpenseAdapter

    private var salary: Double = 5000.00
    private var totalExpenses: Double = 0.00
    private val expensesList: MutableList<Expense> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_financas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializando as views
        salaryAmount = findViewById(R.id.salaryAmount)
        expenseAmount = findViewById(R.id.expenseAmount)
        expenseNameInput = findViewById(R.id.expenseNameInput)
        expenseInput = findViewById(R.id.expenseInput)
        addButton = findViewById(R.id.addButton)
        expenseRecyclerView = findViewById(R.id.expenseRecyclerView)

        // Configurando o RecyclerView
        expenseRecyclerView.layoutManager = LinearLayoutManager(this)
        expenseAdapter = ExpenseAdapter(expensesList)
        expenseRecyclerView.adapter = expenseAdapter

        // Configurando o clique do botão de adicionar despesa
        addButton.setOnClickListener {
            val expenseNameText = expenseNameInput.text.toString()
            val expenseValueText = expenseInput.text.toString()

            if (expenseNameText.isNotEmpty() && expenseValueText.isNotEmpty()) {
                try {
                    val expenseValue = expenseValueText.toDouble()
                    val expense = Expense(expenseNameText, expenseValue)
                    addExpense(expense)
                    expenseNameInput.setText("")
                    expenseInput.setText("")
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Por favor, insira um valor válido.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha o nome e o valor da despesa.", Toast.LENGTH_SHORT).show()
            }
        }

        // Atualiza as visualizações iniciais
        updateViews()
    }

    private fun addExpense(expense: Expense) {
        expensesList.add(expense)
        totalExpenses += expense.value
        expenseAdapter.notifyDataSetChanged()
        updateViews()
    }

    private fun updateViews() {
        Log.d("Financas", "Atualizando views - Salário: $salary, Despesas totais: $totalExpenses")
        salaryAmount.text = getString(R.string.currency_format, salary)
        expenseAmount.text = getString(R.string.currency_format, totalExpenses)
    }
}
