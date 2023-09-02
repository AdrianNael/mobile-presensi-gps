
package com.example.mahapanca


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class Chart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        val barChart: BarChart = findViewById(R.id.chart)
        val pieChart: PieChart = findViewById(R.id.pieChart)

// Data untuk chart
        val numDays = 30

        val stackedValuesAdrian = floatArrayOf(0f, 5f, 25f) // Jumlah masuk, terlambat, absen Adrian
        val stackedValuesRiska = floatArrayOf(2f, 3f, 25f) // Jumlah masuk, terlambat, absen Riska
        val stackedValuesAyu = floatArrayOf(5f, 10f, 15f) // Jumlah masuk, terlambat, absen Ayu
        val stackedValuesIqbal = floatArrayOf(4f, 8f, 18f) // Jumlah masuk, terlambat, absen Iqbal
        val stackedValuesBeza = floatArrayOf(5f, 3f,22f) // Jumlah masuk, terlambat, absen Beza

        fun getStackedValuesByParticipant(index: Int): FloatArray {
            return when (index) {
                0 -> stackedValuesAdrian
                1 -> stackedValuesRiska
                2 -> stackedValuesAyu
                3 -> stackedValuesIqbal
                4 -> stackedValuesBeza
                else -> floatArrayOf()
            }
        }

        val stackLabels = arrayOf("Absen", "Terlambat", "Masuk")
        val participantNames = arrayOf("Adrian", "Riska", "Ayu", "Iqbal", "Beza")

        val entries: MutableList<BarEntry> = ArrayList()
        var stackIndex = 0f
        for (i in 0 until participantNames.size) {
            entries.add(BarEntry(stackIndex, getStackedValuesByParticipant(i), participantNames[i]))
            stackIndex += 1f
        }

        val barDataSet = BarDataSet(entries, "")
        barDataSet.colors = listOf(Color.parseColor("#FF3131"), Color.parseColor("#FFBD59"), Color.parseColor("#00BF63"))
        barDataSet.stackLabels = stackLabels

        val dataSets: MutableList<IBarDataSet> = ArrayList()
        dataSets.add(barDataSet)

        val barData = BarData(dataSets)
        barChart.data = barData

// Deskripsi chart
        val description = Description()
        description.text = ""
        barChart.description = description

// Mengatur label sumbu X
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(participantNames)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)
        xAxis.isGranularityEnabled = true

// Refresh chart
        barChart.invalidate()


        val totalHari = 30 // Total hari dalam periode yang diinginkan
        val masukHari = 25 // Jumlah hari masuk
        val terlambatHari = 3 // Jumlah hari terlambat
        val tidakMasukHari = 2 // Jumlah hari tidak masuk

        val pieEntries: MutableList<PieEntry> = ArrayList()
        pieEntries.add(PieEntry((masukHari.toFloat() / totalHari) * 100, "Masuk"))
        pieEntries.add(PieEntry((terlambatHari.toFloat() / totalHari) * 100, "Terlambat"))
        pieEntries.add(PieEntry((tidakMasukHari.toFloat() / totalHari) * 100, "Tidak Masuk"))
        val pieDataSet = PieDataSet(pieEntries, "")

        val valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()}%" // Menampilkan presentase
            }
        }
        pieDataSet.valueFormatter = valueFormatter



        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#00BF63")) // Hijau
        colors.add(Color.parseColor("#FFBD59")) // Kuning
        colors.add(Color.parseColor("#FF3131")) // Merah
        pieDataSet.colors = colors

        pieDataSet.setValueTextColor(Color.WHITE)
        pieDataSet.setValueTextSize(14f)
        val pieData = PieData(pieDataSet)
        pieChart.data = pieData

        pieChart.holeRadius = 0f // Mengatur radius lubang menjadi 0 (tidak ada lubang)
        pieChart.setDrawHoleEnabled(false) // Mematikan tampilan lubang
        pieChart.description.isEnabled = false


        pieChart.invalidate() // Melakukan refresh pada pie chart



    }
    fun backButton(view: View?) {
        val intent = Intent(this, Dashboard_Hr::class.java)
        startActivity(intent)
    }
}
