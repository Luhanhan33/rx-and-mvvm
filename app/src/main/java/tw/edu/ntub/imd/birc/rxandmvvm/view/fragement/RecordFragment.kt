package tw.edu.ntub.imd.birc.rxandmvvm.view.fragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import tw.edu.ntub.imd.birc.rxandmvvm.R
import tw.edu.ntub.imd.birc.rxandmvvm.extension.attachToRecyclerView
import tw.edu.ntub.imd.birc.rxandmvvm.extension.mapSourceState
import tw.edu.ntub.imd.birc.rxandmvvm.view.adapter.ObservableAdapter
import tw.edu.ntub.imd.birc.rxandmvvm.view.adapter.item.DietRecordItem
import tw.edu.ntub.imd.birc.rxandmvvm.viewmodel.DietRecordViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RecordFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel: DietRecordViewModel by viewModel()

    private lateinit var dateEdit: EditText
    //private lateinit var adapter : Adapter
    private lateinit var spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //val adapter = ArrayAdapter.createFromResource(this, R.array.lunch, android.R.layout.simple_spinner_dropdown_item)
        //spinner.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_record, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.record_tabLayout_date)

/////////////////////////////////以下某段程式碼會導致導覽列跳轉頁面時app閃退，不能確定是哪部份
//        dateEdit = root.findViewById(R.id.date_edit)
//        dateEdit.setOnClickListener(dlistener)
//
//
//        spinner = root.findViewById(R.id.spinner)
//        ArrayAdapter.createFromResource(
//            this.requireContext(),
//            R.array.lunch, android.R.layout.simple_spinner_dropdown_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//        }
//
//        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
//                //return Toast.makeText(this, "你選的是" + lunch[pos], Toast.LENGTH_SHORT).show()
//            }
//            override fun onNothingSelected(parent: AdapterView<*>) {}
//        }

        for (i in 1..12) {
            tabLayout.addTab(tabLayout.newTab().setText("$i 月"))
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_food_record)
        val adapter = ObservableAdapter(
            viewModel.searchAll()
                .mapSourceState { it.data.map { dietRecord -> DietRecordItem(dietRecord) } }
        )
        adapter.attachToRecyclerView(recyclerView)


        return view
    }

//////////////////////////以下某段程式碼會導致導覽列跳轉頁面時app閃退，不能確定是哪部份
//    private val calender: Calendar = Calendar.getInstance()
//    private val dlistener = View.OnClickListener {
//
//        when (it) {
//
//            dateEdit -> datePicker()
//
//        }
//    }
//
//    private fun datePicker() {
//        DatePickerDialog(
//            this.requireContext(),
//            dateListener,
//            calender.get(Calendar.YEAR),
//            calender.get(Calendar.MONTH),
//            calender.get(Calendar.DAY_OF_MONTH)).show()
//    }
//
//    private val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
//        calender.set(year, month, day)
//        format("yyyy / MM / dd", dateEdit)
//    }
//
//    private fun format(format: String, view: View) {
//        val time = SimpleDateFormat(format, Locale.TAIWAN)
//        (view as EditText).setText(time.format(calender.time))
//    }

}