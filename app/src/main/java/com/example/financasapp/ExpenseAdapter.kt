import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.Expense
import com.example.financasapp.R

class ExpenseAdapter(private val expensesList: List<Expense>) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentItem = expensesList[position]
        holder.expenseName.text = currentItem.name
        holder.expenseValue.text = holder.itemView.context.getString(R.string.currency_format, currentItem.value)
    }

    override fun getItemCount(): Int {
        return expensesList.size
    }

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expenseName: TextView = itemView.findViewById(R.id.expenseName)
        val expenseValue: TextView = itemView.findViewById(R.id.expenseValue)
    }
}