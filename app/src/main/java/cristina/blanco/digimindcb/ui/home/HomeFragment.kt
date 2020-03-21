package cristina.blanco.digimindcb.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cristina.blanco.digimindcb.R
import cristina.blanco.digimindcb.Task
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.task_view.view.*

class HomeFragment : Fragment() {

    private  var adaptador: AdaptadorTasks?=null


companion object{
     var tasks = ArrayList<Task>()
    var first=true
}
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
if(first){
    cargarTasks()
    first=false
}
     adaptador=AdaptadorTasks(root.context,tasks)

       root.tasksGridView.adapter =adaptador

        return root
    }

    fun cargarTasks(){
        tasks.add(Task("Practice", arrayListOf("Everyday"), "17:00"))
        tasks.add(Task("Practice", arrayListOf("Monday","Friday"), "17:00"))
        tasks.add(Task("Practice", arrayListOf("Sunday"), "17:00"))

    }
}


private class AdaptadorTasks: BaseAdapter {
    var tasks = ArrayList<Task>()
    var contexto: Context? = null

    constructor(contexto: Context, tasks: ArrayList<Task>){
        this.contexto = contexto
        this.tasks = tasks
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var task = tasks[position]
        var inflador = LayoutInflater.from(contexto)
        var vista = inflador.inflate(R.layout.task_view, null)
        vista.taskName.setText(task.nombre)
        vista.taskWhen.setText(task.dias.toString())
        vista.taskTime.setText(task.hora)
        return vista
    }

    override fun getItem(position: Int): Any {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return tasks.size
    }

}



