package com.example.bmicalculatorplus.ui.bmichart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bmicalculatorplus.R;

/**
 * Fragment wyświetlający wykres zmian BMI w czasie.
 * Wykres jest renderowany w komponencie {@link WebView} z wykorzystaniem biblioteki Google Charts.
 */
public class BMIChartFragment extends Fragment {

    private WebView webView;

    /**
     * Tworzy i zwraca widok hierarchii UI dla tego fragmentu.
     * Inicjalizuje {@link WebView} i ładuje dane HTML z wykresem.
     *
     * @param inflater           obiekt służący do „nadmuchania” layoutu XML
     * @param container          kontener, w którym zostanie umieszczony widok
     * @param savedInstanceState zapisany stan
     * @return główny widok fragmentu
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi_chart, container, false);
        webView = view.findViewById(R.id.bmi_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(null, getHtml(), "text/html", "UTF-8", null);
        return view;
    }

    /**
     * Generuje kod HTML zawierający skrypt JavaScript do wyświetlenia wykresu linii z wartościami BMI.
     *
     * @return kod HTML jako ciąg znaków
     */
    private String getHtml() {
        return "<html>" +
                "<head>" +
                "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>" +
                "<script type=\"text/javascript\">" +
                "google.charts.load('current', {'packages':['corechart']});" +
                "google.charts.setOnLoadCallback(drawChart);" +
                "function drawChart() {" +
                "var data = google.visualization.arrayToDataTable([" +
                "['Dzień', 'BMI']," +
                "['1.01', 24.5]," +
                "['2.01', 24.3]," +
                "['3.01', 24.0]," +
                "['4.01', 23.8]," +
                "['5.01', 23.6]," +
                "['6.01', 23.3]" +
                "]);" +
                "var options = {" +
                "title: 'Zmiany BMI w czasie'," +
                "curveType: 'function'," +
                "legend: { position: 'bottom' }" +
                "};" +
                "var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));" +
                "chart.draw(data, options);" +
                "}" +
                "</script>" +
                "</head>" +
                "<body>" +
                "<div id=\"curve_chart\" style=\"width:100%; height:100%;\"></div>" +
                "</body>" +
                "</html>";
    }
}

