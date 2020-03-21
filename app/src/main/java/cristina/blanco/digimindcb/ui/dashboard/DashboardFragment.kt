package cristina.blanco.digimindcb.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cristina.blanco.digimindcb.R
import cristina.blanco.digimindcb.ui.home.HomeFragment
import cristina.blanco.digimindcb.Task
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        
        root.btn_time.setOnClickListener{
            val cal= Calendar.getInstance()
            val timeSetListener= TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
                cal.set(Calendar.MINUTE,minute)

                btn_time.text=SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(root.context,timeSetListener, cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }

        root.btn_save.setOnClickListener{
            var title= et_task.text.toString()
            var time= btn_time.text.toString()

            var days= ArrayList<String>()

            if(check_monday.isChecked)
                days.add("Monday")
            if (check_tuesday.isChecked)
                days.add("Tuesday")
            if(check_wednesday.isChecked)
                days.add("Wednesday")
            if(check_thurday.isChecked)
                days.add("Thursday")
            if(check_friday.isChecked)
                days.add("Friday")
            if(check_saturday.isChecked)
                days.add("Saturday")
            if(check_sunday.isChecked)
                days.add("Sunday")

            var task= Task(title, days, time)


            HomeFragment.tasks.add(task)


            Toast.makeText(root.context,"New task added",Toast.LENGTH_SHORT).show()

        }

        return root
    }
}