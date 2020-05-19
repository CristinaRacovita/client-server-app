package prelucrare;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;

public class StatisticaPrajituri {
    private List<model.Prajitura> listaPrajituri = new ArrayList<model.Prajitura>();

    public void AdaugarePrajitura(model.Prajitura prajitura){
        listaPrajituri.add(prajitura);
    }

    public DefaultCategoryDataset generareDateBarChart(){
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(model.Prajitura p: listaPrajituri) {
            final String denumire = p.getNumePrajitura();
            dataset.addValue(p.getPret(),"Pret",denumire);
            dataset.addValue(Double.parseDouble(p.getValabilitate().substring(0,1)),"Valabilitate",denumire);
            Double disp = (p.isDisponibilitateProdus())?1.0:0.0;
            dataset.addValue(disp,"Disponibilitate",denumire);
        }
        return dataset;
    }
    public DefaultCategoryDataset generareDateBarChartTot(){
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(model.Prajitura p: listaPrajituri) {
            final String denumire = p.getNumeCofetarie();
            dataset.addValue(p.getPret(),"Pret",denumire);
            dataset.addValue(Double.parseDouble(p.getValabilitate().substring(0,1)),"Valabilitate",denumire);
            Double disp = (p.isDisponibilitateProdus())?1.0:0.0;
            dataset.addValue(disp,"Disponibilitate",denumire);
        }
        return dataset;
    }
    public void generareBarChart(String chartTitle,boolean tot) throws IOException {
        DefaultCategoryDataset dataset;
        if(!tot) {
            dataset = generareDateBarChart();
        }
        else{
            dataset = generareDateBarChartTot();
        }
        JFreeChart barChart = ChartFactory.createBarChart(chartTitle,"Produse","Evolutie",dataset, PlotOrientation.VERTICAL,true,true,false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560,370));
        File barChartFile = new File("BarChart.jpeg");
        ChartUtilities.saveChartAsJPEG(barChartFile, barChart,720,560);

    }

    public DefaultPieDataset generareDatePieChart(){
        final DefaultPieDataset dataset = new DefaultPieDataset();
        for(model.Prajitura p: listaPrajituri) {
            final String denumire = p.getNumePrajitura();
            dataset.setValue(denumire,p.getPret());
        }
        return dataset;
    }
    public DefaultPieDataset generareDatePieChartTot(){
        final DefaultPieDataset dataset = new DefaultPieDataset();
        for(model.Prajitura p: listaPrajituri) {
            final String denumire = p.getNumeCofetarie();
            dataset.setValue(denumire,p.getPret());
        }
        return dataset;
    }
    public void generarePieChart(String chartTitle,boolean tot) throws IOException {
        DefaultPieDataset dataset;
        if(!tot){
            dataset = generareDatePieChart();
        }
        else{
            dataset = generareDatePieChartTot();
        }
        JFreeChart chart = ChartFactory.createPieChart(chartTitle,dataset,true,true,false);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(560,367));
        File pieChartFile = new File("PieChart.jpeg");
        ChartUtilities.saveChartAsJPEG(pieChartFile, chart,720,560);
    }
    public void generareLineChart(String chartTitle,boolean tot) throws IOException {
        DefaultCategoryDataset dataset;
        if(!tot){
            dataset = generareDateBarChart();
        }
        else{
            dataset = generareDateBarChartTot();
        }
        JFreeChart lineChart = ChartFactory.createLineChart(chartTitle,"Produse","Evolutie",dataset,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel panel = new ChartPanel(lineChart);
        panel.setPreferredSize(new Dimension(560,367));
        File lineChartFile = new File("LineChart.jpeg");
        ChartUtilities.saveChartAsJPEG(lineChartFile, lineChart,720,560);
    }
}
