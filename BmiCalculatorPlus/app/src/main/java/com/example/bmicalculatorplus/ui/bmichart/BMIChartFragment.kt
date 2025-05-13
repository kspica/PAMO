package com.example.bmicalculatorplus.ui.bmichart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.bmicalculatorplus.R

/**
 * Fragment wyświetlający wykres zmian BMI w czasie.
 * Wykres jest renderowany w komponencie WebView z wykorzystaniem biblioteki Google Charts.
 */
class BMIChartFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_bmi_chart, container, false)
        webView = view.findViewById(R.id.bmi_webview)
        webView.settings.javaScriptEnabled = true
        webView.loadDataWithBaseURL(null, getHtml(), "text/html", "UTF-8", null)
        return view
    }

    private fun getHtml(): String {
        return """
            <html>
            <head>
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
                    google.charts.load('current', {'packages':['corechart']});
                    google.charts.setOnLoadCallback(drawChart);
                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ['Dzień', 'BMI'],
                            ['1.01', 24.5],
                            ['2.01', 24.3],
                            ['3.01', 24.0],
                            ['4.01', 23.8],
                            ['5.01', 23.6],
                            ['6.01', 23.3]
                        ]);
                        var options = {
                            title: 'Zmiany BMI w czasie',
                            curveType: 'function',
                            legend: { position: 'bottom' }
                        };
                        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
                        chart.draw(data, options);
                    }
                </script>
            </head>
            <body>
                <div id="curve_chart" style="width:100%; height:100%;"></div>
            </body>
            </html>
        """.trimIndent()
    }
}
