package prelucrare;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SalvareRapoarte {
    // ------------------- CSV -------------------

    public static void CSVFile(FileWriter writer,List<model.Prajitura> prajituraLista) throws IOException {
        writeLine(writer, Arrays.asList("Cofetarie","Denumire","Pret","Disponibilitate","Valabilitate"), ',', ' ');
        for(model.Prajitura p : prajituraLista) {
            String disp =(p.isDisponibilitateProdus())?"pe stoc":"stoc epuizat";
            String val = String.valueOf(p.getPret());
            writeLine(writer, Arrays.asList(p.getNumeCofetarie(),p.getNumePrajitura(),val,disp,p.getValabilitate()),',',' ');
        }
        writer.flush();
        writer.close();
    }
    private static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {
        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = ',';
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());

    }
    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }


    // ------------------- XML -------------------
    public static void XMLFile(String title,String fileXML,String[] coloane,List<model.Prajitura> prajituraLista) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder = icFactory.newDocumentBuilder();
        Document doc = icBuilder.newDocument();
        Element mainRootElement = doc.createElementNS("https://prajituri.com/"+title,"Prajituri");
        doc.appendChild(mainRootElement);
        for(model.Prajitura p : prajituraLista) {
            String disp =(p.isDisponibilitateProdus())?"pe stoc":"stoc epuizat";
            String val = String.valueOf(p.getPret());
            mainRootElement.appendChild(SalvareRapoarte.getCompany(doc,p.getNumeCofetarie(),p.getNumePrajitura(),val,disp,p.getValabilitate()));
        }
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(fileXML);
        transformer.transform(source, console);

    }

    private static Node getCompany(Document doc,String cofetarie, String name, String pret, String disp, String valab) {
        Element company = doc.createElement("Prajitura");
        company.setAttribute("Cofetarie",cofetarie);
        company.setAttribute("Denumire", name);
        company.appendChild(getCompanyElements(doc, "Pret", pret));
        company.appendChild(getCompanyElements(doc, "Disponibilitate", disp));
        company.appendChild(getCompanyElements(doc, "Valabilitate", valab));
        return company;
    }

    // utility method to create text node
    private static Node getCompanyElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    // ------------------- JSON -------------------

    public static void jsonFile(FileWriter file, String[] coloane, List<model.Prajitura> prajituraLista) throws IOException {
        JSONObject objPrajitura = new JSONObject();

        JSONArray objs = new JSONArray();
        int i = 0;
        for(model.Prajitura p: prajituraLista){
            objPrajitura.put(coloane[0],p.getNumeCofetarie());
            objPrajitura.put(coloane[1],p.getNumePrajitura());
            objPrajitura.put(coloane[2],String.valueOf(p.getPret()));
            if(p.isDisponibilitateProdus()) {
                objPrajitura.put(coloane[3],"pe stoc");
            }
            else {
                objPrajitura.put(coloane[3],"pe stoc");
            }
            objPrajitura.put(coloane[4],p.getValabilitate());
            objs.add(objPrajitura);
            objPrajitura = new JSONObject();
        }
        file.write(objs.toJSONString());
        file.flush();
    }

}
